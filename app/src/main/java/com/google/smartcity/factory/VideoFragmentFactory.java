package com.google.smartcity.factory;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.google.smartcity.ui.fragment.VideoAmusementFragment;
import com.google.smartcity.ui.fragment.VideoFunnyFragment;
import com.google.smartcity.ui.fragment.VideoHotFragmentTest;
import com.google.smartcity.ui.fragment.VideoSelectionFragment;

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
 * Package_Name：com.google.smartcity
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class VideoFragmentFactory {

    public static final int FRAGMENT_SELECTION = 0;
    public static final int FRAGMENT_HOT       = 1;
    public static final int FRAGMENT_AMUSEMENT = 2;
    public static final int FRAGMENT_FUNNY     = 3;

    private static SparseArray<Fragment> cachesFragment = new SparseArray<>();

    public static Fragment getFragment(int position) {

        // 如果缓存里面有对应的fragment,就直接取出返回
        Fragment fragment = cachesFragment.get(position);
        if (fragment != null) {
            return fragment;
        }

        switch (position) {

            case FRAGMENT_SELECTION:
                fragment = new VideoSelectionFragment();
                break;
            case FRAGMENT_HOT:
                fragment = new VideoHotFragmentTest();
                break;
            case FRAGMENT_AMUSEMENT:
                fragment = new VideoAmusementFragment();
                break;
            case FRAGMENT_FUNNY:
                fragment = new VideoFunnyFragment();
                break;
        }

        cachesFragment.put(position, fragment);
        return fragment;
    }

}
