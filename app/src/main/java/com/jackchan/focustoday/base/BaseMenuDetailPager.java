package com.jackchan.focustoday.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class BaseMenuDetailPager {

	public Activity mActivity;
	public View mRootView;// 根布局对象

	public BaseMenuDetailPager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @return
	 */
	protected abstract View initView();

	/**
	 * 初始化数据
	 */
	public void initData() {
	}

	public View inflate(int resource, ViewGroup rootView, boolean attachToRoot){
		return LayoutInflater.from(mActivity).inflate(resource,rootView,attachToRoot);
	}

}
