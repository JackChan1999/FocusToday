package com.jackchan.focustoday.ui.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jackchan.focustoday.R;
import com.jackchan.focustoday.base.BasePager;
import com.jackchan.focustoday.base.impl.HomePager;
import com.jackchan.focustoday.base.impl.NewsCenterPager;
import com.jackchan.focustoday.base.impl.PhotoPager;
import com.jackchan.focustoday.base.impl.SettingPager;
import com.jackchan.focustoday.base.impl.VideoPager;
import com.jackchan.commons.adapter.BasePagerAdapter;
import com.jackchan.focustoday.ui.widget.viewpager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * 博客：     http://blog.csdn.net/axi295309066
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
public class ContentFragment extends BaseFragment {

    @BindView(R.id.vp_content)
    NoScrollViewPager mViewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    MenuItem prevMenuItem;

    private List<BasePager> mPagerList;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        initListener();
        return view;
    }

    @Override
    public void initData() {
        //rg_group.check(R.id.rb_home);// 设置默认勾选新闻中心页面
        // 初始化5个子界面
        mPagerList = new ArrayList<>();
        mPagerList.add(new HomePager(getActivity()));
        mPagerList.add(new NewsCenterPager(getActivity()));
        mPagerList.add(new VideoPager(getActivity()));
        mPagerList.add(new PhotoPager(getActivity()));
        mPagerList.add(new SettingPager(getActivity()));

        //mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new ContentAdapter(mPagerList));
        //mViewPager.setPageTransformer(true, new DepthPageTransformer());

        //mPagerList.get(0).initData();// 初始化首页数据
    }

    public void initListener() {
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.item_newscenter:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.item_video:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.item_gov:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.item_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                }
                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new NoScrollViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                /*if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mBottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                mBottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mBottomNavigation.getMenu().getItem(position);*/
                BasePager basePager = mPagerList.get(position);
                if (!basePager.isLoading) {
                    basePager.initData();// 获取当前被选中的页面, 初始化该页面数据
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*public void initBottomNavigationView(){
        int[] image = new int[]{R.mipmap.home, R.mipmap.newscenter, R.mipmap.smartservice, R
                .mipmap.govaffairs, R.mipmap.setting};

        int[] color = {ContextCompat.getColor(getActivity(), R.color.firstColor), ContextCompat.getColor(getActivity(), R.color.secondColor),
                ContextCompat.getColor(getActivity(), R.color.thirdColor), ContextCompat.getColor(getActivity(), R.color.fourthColor)};

        mBottomNavigation.isWithText(true);
        mBottomNavigation.isColoredBackground(false);
        mBottomNavigation.disableShadow();
        mBottomNavigation.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(getActivity(), R.color.tab_checked));
        mBottomNavigation.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
        mBottomNavigation.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
        //mBottomNavigation.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(getActivity(), R.color.firstColor));

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("主页", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("新闻", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("视频", color[2], image[2]);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("图片", color[3], image[3]);
        BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
                ("设置", color[3], image[4]);


        mBottomNavigation.addTab(bottomNavigationItem);
        mBottomNavigation.addTab(bottomNavigationItem1);
        mBottomNavigation.addTab(bottomNavigationItem2);
        mBottomNavigation.addTab(bottomNavigationItem3);
        mBottomNavigation.addTab(bottomNavigationItem4);

        mBottomNavigation.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                mViewPager.setCurrentItem(index, false);
            }
        });
    }*/

    /**
     * 获取新闻中心页面
     */
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) mPagerList.get(1);
    }

    public List<BasePager> getPageList() {
        return mPagerList;
    }

    class ContentAdapter extends BasePagerAdapter<BasePager> {

        public ContentAdapter(List<BasePager> data) {
            super(data);
        }

        @Override
        public View getView(int position) {
            return mData.get(position).mRootView;
        }
    }

    class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}