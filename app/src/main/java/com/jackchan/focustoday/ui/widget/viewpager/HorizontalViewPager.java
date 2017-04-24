package com.jackchan.focustoday.ui.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
 * des ：11个子页签水平滑动的Viewpager
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public class HorizontalViewPager extends ViewPager {

	public HorizontalViewPager(Context context) {
		super(context);
	}

	public HorizontalViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * 事件分发，请求父控件以及祖宗是否拦截事件
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		if (getCurrentItem() != 0) {
			getParent().requestDisallowInterceptTouchEvent(true);//第一个页面不拦截
		}else {
			getParent().requestDisallowInterceptTouchEvent(false);
		}
		return super.dispatchTouchEvent(ev);
	}
	
}
