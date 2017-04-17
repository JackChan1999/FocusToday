package com.jackchen.focustoday.base.impl;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.jackchen.focustoday.R;
import com.jackchen.focustoday.base.BasePager;
import com.jackchen.focustoday.factory.PhotoFragmentFactory;
import com.jackchen.focustoday.ui.activity.MainActivity;
import com.jackchen.focustoday.ui.fragment.ContentFragment;
import com.jackchen.focustoday.utils.CommonUtil;
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
public class PhotoPager extends BasePager implements ViewPager.OnPageChangeListener{

    private TabLayout                  mTabLayout;
    private ViewPager                  mViewPager;
    private PhotoPager.FragmentAdapter mAdapter;
    private String[] mTabText;

    public PhotoPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        //super.initViews();
        mRootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_photo, null, false);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tab_photo);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.vp_photo);
        mViewPager.addOnPageChangeListener(this);

        MainActivity mainActivity = (MainActivity) mActivity;
        ContentFragment contentFragment = mainActivity.getContentFragment();

        mAdapter = new PhotoPager.FragmentAdapter(contentFragment.getChildFragmentManager());
        //mViewPager.setOffscreenPageLimit(4);
        mTabText = CommonUtil.getStringArr(R.array.photo);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //flContent.addView(videoView);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoFragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return mTabText.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabText[position];
        }
    }
}
