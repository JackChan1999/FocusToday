package com.google.smartcity.base.impl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.google.smartcity.R;
import com.google.smartcity.base.BasePager;

/**
 * ============================================================
 * <p>
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * <p>
 * 作 者 : 陈冠杰
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2016-3-2 上午10:12:02
 * <p>
 * 描 述 ：
 * 首页实现
 * <p>
 * 修订历史 ：
 * <p>
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
