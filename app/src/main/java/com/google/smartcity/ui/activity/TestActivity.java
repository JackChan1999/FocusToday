package com.google.smartcity.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.smartcity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.google.smartcity.ui.activity
 * Version：1.0
 * time：2016/12/1 20:50
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class TestActivity extends FragmentActivity {

    @Bind(R.id.vp_content)
    ViewPager mViewPager;

    @Bind(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_content);
        ButterKnife.bind(this);

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_newscenter:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_video:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.item_gov:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.item_setting:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mBottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                mBottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mBottomNavigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(new ContentAdapter());
    }

    class ContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(TestActivity.this);
            tv.setTextColor(Color.RED);
            tv.setText(position+"");
            container.addView(tv);
            return tv;
        }
    }
}
