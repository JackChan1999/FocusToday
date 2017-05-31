package com.jackchan.compiler.model;

import com.jackchan.annotation.OnClick;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

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
 * des ：方法信息的封装
 * gitVersion：2.12.0.windows.1
 * updateAuthor：AllenIverson
 * updateDate：2017/5/31 22:55
 * updateDes：${TODO}
 * ============================================================
 */

public class MethodViewBinding {

    private ExecutableElement mElement;
    private String mMethodName;
    private int[] mIds;
    private boolean mParameterEixt;
    private String mParameterName;

    public MethodViewBinding(Element element) throws IllegalArgumentException {
        mElement = (ExecutableElement) element;

        OnClick click = element.getAnnotation(OnClick.class);
        mIds = click.value();

        //方法名
        mMethodName = element.getSimpleName().toString();

        List<? extends VariableElement> parameters = mElement.getParameters();
        if (parameters.size() > 1) {  //参数不能超过1个
            throw new IllegalArgumentException(
                    String.format("The method annotated with @%s must less two parameters", OnClick.class.getSimpleName()));
        }

        if (parameters.size() == 1) { //如果有参数必须是View类型
            VariableElement variableElement = parameters.get(0);
            if (!variableElement.asType().toString().equals(ProxyClass.VIEW.toString())) {
                throw new IllegalArgumentException(
                        String.format("The method parameter must be %s type", ProxyClass.VIEW.toString()));
            }
            mParameterEixt = true;
            mParameterName=variableElement.getSimpleName().toString();
        }

    }


    public int[] getIds() {
        return mIds;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public boolean isParameterEixt() {
        return mParameterEixt;
    }

    public String getParameterName() {
        return mParameterName;
    }
}
