package com.jackchan.compiler.model;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

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
 * des ：代理类
 * gitVersion：2.12.0.windows.1
 * updateAuthor：AllenIverson
 * updateDate：2017/5/31 22:55
 * updateDes：${TODO}
 * ============================================================
 */

public class ProxyClass {

    /**
     * 类元素
     */
    public TypeElement mTypeElement;
    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;
    /**
     * FieldViewBinding类型的集合
     */
    private Set<FieldViewBinding>  bindViews = new HashSet<>();
    private Set<MethodViewBinding> mMethods  = new HashSet<>();

    public ProxyClass(TypeElement mTypeElement, Elements mElementUtils) {
        this.mTypeElement = mTypeElement;
        this.mElementUtils = mElementUtils;
    }

    public void add(FieldViewBinding bindView) {
        bindViews.add(bindView);
    }

    public void add(MethodViewBinding methodViewBinding) {
        mMethods.add(methodViewBinding);
    }

    //proxytool.IProxy
    public static final ClassName IPROXY = ClassName.get("proxytool.api", "ViewInjector");
    //android.view.View
    public static final ClassName VIEW = ClassName.get("android.view", "View");
    //android.view.View.OnClickListener
    public static final ClassName VIEW_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    //生成代理类的后缀名
    public static final String SUFFIX = "$$Proxy";

    /**
     * 用于生成代理类
     */
    public JavaFile generateProxy() {

        //生成public void inject(final T target, View root)方法
        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mTypeElement.asType()), "target", Modifier.FINAL)
                .addParameter(VIEW, "root");

        //在inject方法中，添加我们的findViewById逻辑
        for (FieldViewBinding model : bindViews) {
            // find views
            injectMethodBuilder.addStatement("target.$N = ($T)(root.findViewById($L))", model.getVariableName(),
                    ClassName.get(model.getTypeMirror()), model.getResId());
        }

        if (mMethods.size() > 0) {
            injectMethodBuilder.addStatement("$T listener", VIEW_ON_CLICK_LISTENER);
        }


        for (MethodViewBinding method : mMethods) {
            // declare OnClickListener anonymous class
            TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(VIEW_ON_CLICK_LISTENER)
                    .addMethod(MethodSpec.methodBuilder("onClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.VOID)
                            .addParameter(VIEW, "view")
                            .addStatement("target.$N($L)", method.getMethodName(), method.isParameterEixt() ? method.getParameterName() : "")
                            .build())
                    .build();
            injectMethodBuilder.addStatement("listener = $L ", listener);
            for (int id : method.getIds()) {
                // set listeners
                injectMethodBuilder.addStatement("(root.findViewById($L)).setOnClickListener(listener)", id);
            }
        }


        // 添加以$$Proxy为后缀的类
        TypeSpec finderClass = TypeSpec.classBuilder(mTypeElement.getSimpleName() + SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                //添加父接口
                .addSuperinterface(ParameterizedTypeName.get(IPROXY, TypeName.get(mTypeElement.asType())))
                .addMethod(injectMethodBuilder.build())
                .build();

        //添加包名
        String packageName = mElementUtils.getPackageOf(mTypeElement).getQualifiedName().toString();

        //生成file文件
        return JavaFile.builder(packageName, finderClass).build();
    }
}
