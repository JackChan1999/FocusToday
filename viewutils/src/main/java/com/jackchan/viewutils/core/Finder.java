package com.jackchan.viewutils.core;

import android.app.Activity;
import android.view.View;
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
 * Package_Name：com.jackchan.viewutils
 * Version：1.0
 * time：2016/4/25 17:34
 * des ：
 * gitVersion：2.12.0.windows.1
 * updateAuthor：JackChan
 * updateDate：2016/4/25 17:34
 * updateDes：${TODO}
 * ============================================================
 */
public enum Finder
{
    VIEW
            {
                @Override
                public View findView(Object source, int id)
                {
                    return ((View) source).findViewById(id);
                }
            },
    ACTIVITY
            {
                @Override
                public View findView(Object source, int id)
                {
                    return ((Activity) source).findViewById(id);
                }
            };

    public abstract View findView(Object source, int id);
}