package com.jackchen.focustoday.base.impl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.jackchen.focustoday.R;
import com.jackchen.focustoday.base.BasePager;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchen.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        super.initViews();
        tvTitle.setText("聚合黄页");//修改标题
        btnMenu.setVisibility(View.GONE);//隐藏菜单按钮
        btnSearch.setVisibility(View.GONE);//隐藏搜索按钮
        setSlidingMenuEnable(false);//关闭侧边栏

        View homepager = LayoutInflater.from(mActivity).inflate(R.layout.activity_yellowpage, null, false);

        flContent.addView(homepager);
    }
}
