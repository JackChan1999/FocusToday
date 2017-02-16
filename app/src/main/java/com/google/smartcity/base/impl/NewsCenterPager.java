package com.google.smartcity.base.impl;

import android.app.Activity;
import android.view.View;

import com.google.smartcity.R;
import com.google.smartcity.base.BaseMenuDetailPager;
import com.google.smartcity.base.BasePager;
import com.google.smartcity.base.menudetail.InteractMenuDetailPager;
import com.google.smartcity.base.menudetail.NewsMenuDetailPager;
import com.google.smartcity.base.menudetail.PhotoMenuDetailPager;
import com.google.smartcity.base.menudetail.TopicMenuDetailPager;
import com.google.smartcity.bean.NewsData;
import com.google.smartcity.ui.activity.MainActivity;
import com.google.smartcity.ui.fragment.LeftMenuFragment;
import com.google.smartcity.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class NewsCenterPager extends BasePager {

	private List<BaseMenuDetailPager> mPagers;// 四个菜单详情页的集合
	private NewsData                  mNewsData;
	private String[]                  mTitle;
	// private NewsBean newsBean ;

	public NewsCenterPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		setSlidingMenuEnable(true);
		mTitle = CommonUtil.getStringArr(R.array.leftmenu);
		/*String cache = CacheUtils.getCache(Constants.URLS.CATEGORIES_URL, mActivity);
		if (!TextUtils.isEmpty(cache)) {
			parseData(cache);
		}*/
		getDataFromServer();
	}

	/**
	 * 从服务器获取数据
	 */
	private void getDataFromServer() {
		/*OkHttpUtils.get().url(Constants.URLS.CATEGORIES_URL).build()
				.execute(new StringCallback() {

					@Override
					public void onResponse(String result) {
						parseData(result);
						// 设置缓存
						CacheUtils.setCache(Constants.URLS.CATEGORIES_URL, result, mActivity);
					}

					@Override
					public void onError(Call arg0, Exception error) {
						error.printStackTrace();
					}
				});*/
		parseData(null);
	}

	/**
	 * 解析网络数据
	 * @param result
	 */
	private void parseData(String result) {
		//mNewsData = GsonUtil.changeGsonToBean(result, NewsData.class);
		// newsBean = GsonTools.changeGsonToBean(result, NewsBean.class);
		// LogUtils.e(newsBean.toString());

		isLoading = true;

		// 刷新侧边栏的数据
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(Arrays.asList(mTitle));

		// 准备侧边栏对应的四个菜单详情页
		mPagers = new ArrayList<BaseMenuDetailPager>();
		mPagers.add(new NewsMenuDetailPager(mActivity));// 新闻
		mPagers.add(new TopicMenuDetailPager(mActivity));// 专题
		mPagers.add(new PhotoMenuDetailPager(mActivity, btnPhoto));// 组图
		mPagers.add(new InteractMenuDetailPager(mActivity));// 互动

		setCurrentMenuDetailPager(0);// 设置菜单详情页 - 新闻为默认当前页
	}

	public List<BaseMenuDetailPager> getMenuDetailPage(){
		return mPagers;
	}

	/**
	 * 设置当前菜单详情页
	 * 
	 * @param position
	 */
	public void setCurrentMenuDetailPager(int position) {
		BaseMenuDetailPager pager = mPagers.get(position);// 获取当前要显示的菜单详情页

		flContent.removeAllViews();// 清除之前的布局
		flContent.addView(pager.mRootView);// 将菜单详情页的布局设置给帧布局
		// 设置当前页的标题
		//NewsMenuData newsMenuData = mNewsData.data.get(position);
		tvTitle.setText(mTitle[position]);
		if (mTitle[position].equals("新闻")){
			tvTitle.setText("今日关注");
		}

		pager.initData();// 初始化当前页面的数据
		if (pager instanceof PhotoMenuDetailPager) {
			btnSearch.setVisibility(View.GONE);
			btnPhoto.setVisibility(View.VISIBLE);
		} else {
			btnSearch.setVisibility(View.VISIBLE);
			btnPhoto.setVisibility(View.GONE);
		}
	}

}
