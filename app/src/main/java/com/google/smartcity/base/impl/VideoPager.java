package com.google.smartcity.base.impl;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.google.smartcity.R;
import com.google.smartcity.base.BasePager;
import com.google.smartcity.factory.VideoFragmentFactory;
import com.google.smartcity.ui.activity.MainActivity;
import com.google.smartcity.ui.fragment.ContentFragment;
import com.google.smartcity.utils.CommonUtil;

public class VideoPager extends BasePager implements ViewPager.OnPageChangeListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private String[] mTabText;

    public VideoPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        //super.initViews();
        //mActivity.getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
        mRootView = LayoutInflater.from(mActivity).inflate(R.layout.layout_video, null, false);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tab_video);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.vp_video);
        mViewPager.addOnPageChangeListener(this);
        MainActivity mainActivity = (MainActivity) mActivity;
        ContentFragment contentFragment = mainActivity.getContentFragment();
        mAdapter = new FragmentAdapter(contentFragment.getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(4);

        mTabText = CommonUtil.getStringArr(R.array.video);
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
            return VideoFragmentFactory.getFragment(position);
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
