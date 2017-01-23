package com.google.smartcity.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.smartcity.R;
import com.google.smartcity.ui.activity.MainActivity;
import com.google.smartcity.ui.widget.slidingmenu.SlidingMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * <p/>
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * <p/>
 * 作 者 : 陈冠杰
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 * 创建日期 ： 2016-2-29 下午10:33:49
 * <p/>
 * 描 述 ： 主页下5个子页面的基类
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class BasePager {

    public Activity mActivity;
    public View     mRootView;// 布局对象

    @Bind(R.id.tv_title)
    public TextView tvTitle;// 标题对象

    @Bind(R.id.fl_content)
    public FrameLayout flContent;// 内容

    @Bind(R.id.btn_menu)
    public ImageButton btnMenu;// 菜单按钮

    @Bind(R.id.btn_search)
    public ImageButton btnSearch;// 搜索按钮

    @Bind(R.id.btn_photo)
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
