package com.google.smartcity.factory;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.google.smartcity.ui.fragment.PhotoBeautyFragment;
import com.google.smartcity.ui.fragment.PhotoExclusiveFragment;
import com.google.smartcity.ui.fragment.PhotoSelectionFragment;
import com.google.smartcity.ui.fragment.PhotoSportFragment;
import com.google.smartcity.ui.fragment.PhotoStartFragment;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.google.smartcity.factory
 * Version：1.0
 * time：2016/12/2 15:16
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class PhotoFragmentFactory {

    public static final int FRAGMENT_SELECTION  = 0;
    public static final int FRAGMENT_PRETTY_PICTURE = 1;
    public static final int FRAGMENT_FUNNY_PICTURE = 2;
    public static final int FRAGMENT_STORY = 3;
    public static final int FRAGMENT_START = 4;

    private static SparseArray<Fragment> cachesFragment = new SparseArray<>();

    public static Fragment getFragment(int position) {

        // 如果缓存里面有对应的fragment,就直接取出返回
        Fragment fragment = cachesFragment.get(position);
        if (fragment != null) {
            return fragment;
        }

        switch (position) {

            case FRAGMENT_SELECTION://精选
                fragment = new PhotoSelectionFragment();
                break;
            case FRAGMENT_PRETTY_PICTURE://美图
                fragment = new PhotoBeautyFragment();
                break;
            case FRAGMENT_FUNNY_PICTURE://独家
                fragment = new PhotoExclusiveFragment();
                break;
            case FRAGMENT_STORY://体坛
                fragment = new PhotoSportFragment();
                break;
            case FRAGMENT_START://明星
                fragment = new PhotoStartFragment();
                break;
        }

        cachesFragment.put(position, fragment);
        return fragment;
    }

}
