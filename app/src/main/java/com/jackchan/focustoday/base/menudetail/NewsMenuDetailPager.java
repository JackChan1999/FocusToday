package com.jackchan.focustoday.base.menudetail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackchan.focustoday.R;
import com.jackchan.focustoday.base.BaseMenuDetailPager;
import com.jackchan.focustoday.base.BaseTabDetailPager;
import com.jackchan.focustoday.factory.TabNewsFactory;
import com.jackchan.focustoday.ui.activity.ChannelActivity;
import com.jackchan.focustoday.ui.activity.MainActivity;
import com.jackchan.commons.adapter.BasePagerAdapter;
import com.jackchan.focustoday.ui.widget.slidingmenu.SlidingMenu;
import com.jackchan.focustoday.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * CSDN博客： http://blog.csdn.net/axi295309066
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
public class NewsMenuDetailPager extends BaseMenuDetailPager implements OnPageChangeListener {

	@BindView(R.id.vp_menu_detail)
	ViewPager mViewPager;

	@BindView(R.id.tab_layout)
	TabLayout mTabLayout;

	private ArrayList<BaseTabDetailPager> mPagerList;
	private List<String> mTabData;

	//private ArrayList<NewsTabData> mTabDatas;// 页签网络数据

	public NewsMenuDetailPager(Activity activity) {
		super(activity);
		//mTabDatas = children;
	}

	@Override
	protected View initView() {
		View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
		ButterKnife.bind(this,view);
		mViewPager.addOnPageChangeListener(this);
		// 注意:当viewpager和Indicator绑定时,滑动监听需要设置给Indicator而不是viewpager
		// mIndicator.setOnPageChangeListener(this);
		return view;
	}

	// 跳转至下一个页面
	@OnClick(R.id.btn_next)
	public void nextPage(View view) {
		/*int currentItem = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem(++currentItem);*/
		Intent intent = new Intent(mActivity, ChannelActivity.class);
		mActivity.startActivityForResult(intent,0);
	}

	@Override
	public void initData() {
		mPagerList = new ArrayList<>();

		SharedPreferences sp = mActivity.getSharedPreferences("channel", Activity.MODE_PRIVATE);
		String mychannel = sp.getString("mychannel","");
		if (!mychannel.equals("")){
			mTabData = new Gson().fromJson(mychannel,new TypeToken<List<String>>(){}.getType());
		}else {
			mTabData = Arrays.asList(CommonUtil.getStringArr(R.array.mychannel));
		}

		for (int i = 0; i < mTabData.size(); i++) {
			BaseTabDetailPager pager = TabNewsFactory.getTabNewsPage(mTabData.get(i),mActivity);
			mPagerList.add(pager);
		}
		//mViewPager.setOffscreenPageLimit(4);
		mViewPager.setAdapter(new MenuDetailAdapter(mPagerList));
		//mViewPager.setPageTransformer(true, new DepthPageTransformer());
		// 将viewpager和indicator关联起来，必须在viewpager设置完adapter后才能调用
		// mIndicator.setViewPager(mViewPager);
		mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		mTabLayout.setupWithViewPager(mViewPager);
		//UIUtils.setIndicator(mActivity,mTabLayout,15,15);
	}

	class MenuDetailAdapter extends BasePagerAdapter<BaseTabDetailPager> {

		public MenuDetailAdapter(List<BaseTabDetailPager> data) {
			super(data);
		}

		@Override
		public View getView(int position) {
			BaseTabDetailPager pager = mPagerList.get(position);
			pager.initData();
			return pager.mRootView;
		}

		/**
		 * 重写次方法，返回页面标题，用于viewpagerIndicator的页签显示
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabData.get(position);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();

		if (position == 0) {
			// 只有在第一个页面, 侧边栏才允许出来
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

}
