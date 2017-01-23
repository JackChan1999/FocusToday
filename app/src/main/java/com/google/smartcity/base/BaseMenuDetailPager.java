package com.google.smartcity.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-2 下午1:00:01
 * 
 * 描 述 ： 菜单详情页基类
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public abstract class BaseMenuDetailPager {

	public Activity mActivity;
	public View mRootView;// 根布局对象

	public BaseMenuDetailPager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
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
