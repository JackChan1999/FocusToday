package com.jackchan.compiler;


import com.google.auto.service.AutoService;
import com.jackchan.annotation.BindView;
import com.jackchan.annotation.OnClick;
import com.jackchan.compiler.model.FieldViewBinding;
import com.jackchan.compiler.model.MethodViewBinding;
import com.jackchan.compiler.model.ProxyClass;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.STATIC;

/**
 * ============================================================
 * Copyright：JackChan和他的朋友们有限公司版权所有 (c) 2017
 * Author：   JackChan
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChan1999
 * GitBook：  https://www.gitbook.com/@alleniverson
 * CSDN博客： http://blog.csdn.net/axi295309066
 * 个人博客： https://jackchan1999.github.io/
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：FocusToday
 * Package_Name：com.jackchan.compiler
 * Version：1.0
 * time：2017/5/31 22:55
 * des ：注解处理器
 * gitVersion：2.12.0.windows.1
 * updateAuthor：AllenIverson
 * updateDate：2017/5/31 22:55
 * updateDes：${TODO}
 * ============================================================
 */
@AutoService(Processor.class)
public class AnnotationProcessTool extends AbstractProcessor {


    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类

    /**
     * 处理器的初始化方法，可以获取相关的工具类
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }

    private Map<String, ProxyClass> mProxyClassMap = new HashMap<>();

    /**
     * 处理器的主方法，用于扫描处理注解，生成java文件
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //处理被BindView注解的元素
        for (Element element : roundEnv.getElementsAnnotatedWith(BindView.class)) {
           if (!isValid(BindView.class, "fields", element)) {
                return true;
            }
            parseBindView(element);
        }

        //处理被OnClick注解的元素
        for (Element element : roundEnv.getElementsAnnotatedWith(OnClick.class)) {
            if (!isValid(OnClick.class, "methods", element)) {
                return true;
            }
            try {
                parseClick(element);
            } catch (IllegalArgumentException e) {
                error(element, e.getMessage());
                return true;
            }
        }

        //为每个宿主类生成所对应的代理类
        for (ProxyClass proxyClass: mProxyClassMap.values()) {
            try {
                proxyClass.generateProxy().writeTo(mFiler);
            } catch (IOException e) {
                error(null, e.getMessage());
            }
        }
        mProxyClassMap.clear();

        return true;
    }

    /**
     * 处理ViewById注解
     *
     * @param element
     */
    private void parseBindView(Element element) {
        ProxyClass proxyClass = getProxyClass(element);
        //把被注解的view对象封装成一个model，放入代理类的集合中
        FieldViewBinding bindView = new FieldViewBinding(element);
        proxyClass.add(bindView);
    }

    /**
     * 处理Click注解
     *
     * @param element
     */
    private void parseClick(Element element) throws IllegalArgumentException {
        ProxyClass proxyClass = getProxyClass(element);
        //把被注解的view对象封装成一个model，放入代理类的集合中
        MethodViewBinding bindView = new MethodViewBinding(element);
        proxyClass.add(bindView);
    }


    /**
     *  生成或获取注解元素所对应的ProxyClass类
     */
    private ProxyClass getProxyClass(Element element) {
        // 被注解的变量所在的类,MainActivity
        TypeElement classElement = (TypeElement) element.getEnclosingElement();
        // com.jackchan.ui.MainActivity
        String qualifiedName = classElement.getQualifiedName().toString();
        ProxyClass proxyClass = mProxyClassMap.get(qualifiedName);
        if (proxyClass == null) {
            //生成每个宿主类所对应的代理类，后面用于生产java文件
            proxyClass = new ProxyClass(classElement, mElementUtils);
            mProxyClassMap.put(qualifiedName, proxyClass);
        }
        return proxyClass;
    }


    private boolean isValid(Class<? extends Annotation> annotationClass, String targetThing, Element element) {
        boolean isVaild = true;

        //获取变量的所在的父元素，肯能是类、接口、枚举
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        //父元素的全限定名
        String qualifiedName = enclosingElement.getQualifiedName().toString();

        // 所在的类不能是private或static修饰
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(PRIVATE) || modifiers.contains(STATIC)) {
            error(element, "@%s %s must not be private or static. (%s.%s)",
                    annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(),
                    element.getSimpleName());
            isVaild = false;
        }

        // 父元素必须是类，而不能是接口或枚举
        if (enclosingElement.getKind() != ElementKind.CLASS) {
            error(enclosingElement, "@%s %s may only be contained in classes. (%s.%s)",
                    annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(),
                    element.getSimpleName());
            isVaild = false;
        }

        //不能在Android框架层注解
        if (qualifiedName.startsWith("android.")) {
            error(element, "@%s-annotated class incorrectly in Android framework package. (%s)",
                    annotationClass.getSimpleName(), qualifiedName);
            return false;
        }
        //不能在java框架层注解
        if (qualifiedName.startsWith("java.")) {
            error(element, "@%s-annotated class incorrectly in Java framework package. (%s)",
                    annotationClass.getSimpleName(), qualifiedName);
            return false;
        }

        return isVaild;
    }

    /**
     *  指定哪些注解应该被注解处理器注册
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        return types;
    }

    /**
     *  用来指定你使用的 java 版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }

}
