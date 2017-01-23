package com.google.smartcity.ui.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-6 下午10:39:30
 * 
 * 描 述 ：
 * 		11个子页签水平滑动的Viewpager
 * 
 * 修订历史 ：
 * 
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
