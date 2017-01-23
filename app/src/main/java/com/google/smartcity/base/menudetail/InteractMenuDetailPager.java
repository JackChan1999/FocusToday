package com.google.smartcity.base.menudetail;

import com.google.smartcity.base.BaseMenuDetailPager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-5 上午10:30:38
 * 
 * 描 述 ：
 * 		菜单详情页-互动
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class InteractMenuDetailPager extends BaseMenuDetailPager {

	public InteractMenuDetailPager(Activity mActivity) {
		super(mActivity);
	}

	@Override
	protected View initView() {
		TextView text = new TextView(mActivity);
		text.setText("菜单详情页-互动");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);

		return text;
	}

}
