package com.google.smartcity.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	protected boolean isInit = false;
	protected boolean isLoad = false;
	
	//public Activity mActivity ;
	//protected LayoutInflater mInflater;
	private View rootView;

	/**
	 * fragment创建
	 */
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isInit = true;
		//mActivity = getActivity();
		//mInflater = LayoutInflater.from(mActivity);
	}

	/**
	 * 处理fragment的布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = initView(inflater, container);
		return rootView;
	}

	/**
	 * 依附的activity创建完成
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
		//isCanLoadData();
	}

	/**
	 * 视图销毁的时候讲Fragment是否初始化的状态变为false
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		isInit = false;
		isLoad = false;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		isCanLoadData();
	}

	/**
	 * 子类必须实现初始化布局的方法
	 * 
	 * @return
	 */
	public abstract View initView(LayoutInflater inflater, ViewGroup container);

	public View getRootView(){
		return rootView;
	}

	// 初始化数据
	public void initData() {

	}

	/**
	 * 是否可以加载数据
	 * 可以加载数据的条件：
	 * 1.视图已经初始化
	 * 2.视图对用户可见
	 */
	private void isCanLoadData() {
		if (!isInit) {
			return;
		}

		if (getUserVisibleHint()) {
			//initData();
			isLoad = true;
		} else {
			if (isLoad) {
				stopLoad();
			}
		}
	}

	/**
	 * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据
	 */
	protected void stopLoad() {
	}

}
