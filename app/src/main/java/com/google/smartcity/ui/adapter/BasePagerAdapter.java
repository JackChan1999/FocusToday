package com.google.smartcity.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-5 上午9:35:24
 * 
 * 描 述 ： 抽象的PagerAdapter实现类,封装了内容为View的公共操作
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public abstract class BasePagerAdapter<T> extends PagerAdapter {

	protected List<T> mData;
	private SparseArray<View> mViews;

	public BasePagerAdapter(List<T> data) {
		super();
		mData = data;
		mViews = new SparseArray<View>(data.size());
	}

	@Override
	public int getCount() {
		if (mData == null) return 0;
		return mData.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mViews.get(position);
		if (view == null) {
			view = getView(position);
			mViews.put(position, view);
		}
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViews.get(position));
	}

	public abstract View getView(int position);

	public T getItem(int position) {
		return mData.get(position);
	}

}
