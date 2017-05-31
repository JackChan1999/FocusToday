package com.jackchan.focustoday.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jackchan.focustoday.R;
import com.jackchan.focustoday.ui.activity.MainActivity;
import com.jackchan.focustoday.ui.widget.slidingmenu.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * CSDN博客： http://blog.csdn.net/axi295309066
 * 个人博客： https://jackchan1999.github.io/
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchan.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class BasePager {

    public Activity mActivity;
    public View     mRootView;// 布局对象

    @BindView(R.id.tv_title)
    public TextView tvTitle;// 标题对象

    @BindView(R.id.fl_content)
    public FrameLayout flContent;// 内容

    @BindView(R.id.btn_menu)
    public ImageButton btnMenu;// 菜单按钮

    @BindView(R.id.btn_search)
    public ImageButton btnSearch;// 搜索按钮

    @BindView(R.id.btn_photo)
    public ImageButton btnPhoto;

    public boolean isLoading = false;

    public BasePager(Activity activity) {
        mActivity = activity;
        initViews();
        initListener();
    }

    /**
     * 初始化布局
     */
    public void initViews() {
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);
        ButterKnife.bind(this, mRootView);
    /*	flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
        btnMenu = (ImageButton) mRootView.findViewById(R.id.btn_menu);
		btnSearch = (ImageButton) mRootView.findViewById(R.id.btn_search);
		btnPhoto = (ImageButton) mRootView.findViewById(R.id.btn_photo);
		tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);*/

       /* RelativeLayout rl = (RelativeLayout) mRootView.findViewById(R.id.header);
        rl.measure(0, 0);
        int measureHeight = rl.getMeasuredHeight();
        final int statusBarHeight = UIUtils.getStatusBarHeight(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight + measureHeight);
        rl.setLayoutParams(params);
        int paddingTop = statusBarHeight + measureHeight/2 - 30;
        //rl.setPadding(0, paddingTop, 0, 0);

        btnMenu.measure(0,0);
        int menuheight = btnMenu.getMeasuredHeight();
        btnSearch.measure(0,0);
        int searchheight = btnSearch.getMeasuredHeight();
        tvTitle.measure(0,0);
        int tvTitleheight = tvTitle.getMeasuredHeight();

        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) btnMenu
        .getLayoutParams();
        params1.topMargin = statusBarHeight + measureHeight/2 - menuheight/2;
        btnMenu.setLayoutParams(params1);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) btnSearch
        .getLayoutParams();
        params2.topMargin = statusBarHeight + measureHeight/2 - searchheight/2;
        btnSearch.setLayoutParams(params2);

        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) tvTitle
        .getLayoutParams();
        params3.topMargin = statusBarHeight + measureHeight/2 - tvTitleheight/2;
        tvTitle.setLayoutParams(params3);*/

    }

    public View getRootView(){
        return mRootView;
    }

    public void initListener() {
        btnMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }

    /**
     * 切换slidingmenu状态
     */
    protected void toggleSlidingMenu() {
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();// 切换slidingmenu状态，显示时隐藏，隐藏时显示
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置侧边栏开启或关闭
     */
    protected void setSlidingMenuEnable(boolean enable) {
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
