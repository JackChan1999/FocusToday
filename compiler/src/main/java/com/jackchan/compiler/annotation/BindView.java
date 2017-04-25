package com.jackchan.compiler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * ============================================================
 * Copyright：JackChan和他的朋友们有限公司版权所有 (c) 2017
 * Author：   JackChan
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChan1999
 * GitBook：  https://www.gitbook.com/@alleniverson
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：FocusToday
 * Package_Name：com.jackchan.compiler
 * Version：1.0
 * time：2016/4/25 19:15
 * des ：注入控件
 * gitVersion：2.12.0.windows.1
 * updateAuthor：JackChan
 * updateDate：2016/4/25 19:15
 * updateDes：${TODO}
 * ============================================================
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindView
{
    int value();
}