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
import com.google.smartcity.factory.PhotoFragmentFactory;
import com.google.smartcity.ui.activity.MainActivity;
import com.google.smartcity.ui.fragment.ContentFragment;
import com.google.smartcity.utils.CommonUtil;

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
