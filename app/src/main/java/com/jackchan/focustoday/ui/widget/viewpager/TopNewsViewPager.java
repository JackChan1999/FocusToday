package com.jackchan.focustoday.ui.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * 博客：     http://blog.csdn.net/axi295309066
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
public class TopNewsViewPager extends ViewPager {

	private int mTouchSlop;
	int startX;
	int startY;

	public TopNewsViewPager(Context context) {
		this(context, null);
	}

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	/**
	 * 事件分发，请求父控件以及祖宗控件是否拦截事件
	 * 1、右划，且是第一个页面，需要父控件拦截
	 * 2、左划，且是最后一个页面，需要父控件拦截
	 * 3、上下滑动，需要父控件拦截
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 不要拦截，保证ACTION_MOVE调用
			getParent().requestDisallowInterceptTouchEvent(true);
			startX = (int) ev.getRawX();
			startY = (int) ev.getRawY();

			break;
		case MotionEvent.ACTION_MOVE:

			int endX = (int) ev.getRawX();
			int endY = (int) ev.getRawY();
			if (Math.abs(endX - startX) > (Math.abs(endY - startY))) {// 左右滑动
				if (endX > startX) {// 右划
					if (getCurrentItem() == 0) {
						// 第一个页面，需要拦截
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				} else {// 左划
					if (getCurrentItem() == getAdapter().getCount() - 1) {
						// 最后一个页面需要拦截
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}
			} else {
				// 上下滑动
				getParent().requestDisallowInterceptTouchEvent(false);
			}

			break;
		case MotionEvent.ACTION_UP:

			break;

		}
		return super.dispatchTouchEvent(ev);
	}

}
