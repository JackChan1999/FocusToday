package com.jackchen.focustoday.ui.widget.slidingmenu;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
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
 * Package_Name：com.jackchen.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public interface MenuInterface {

	public abstract void scrollBehindTo(int x, int y,
										CustomViewBehind cvb, float scrollScale);
	
	public abstract int getMenuLeft(CustomViewBehind cvb, View content);
	
	public abstract int getAbsLeftBound(CustomViewBehind cvb, View content);

	public abstract int getAbsRightBound(CustomViewBehind cvb, View content);

	public abstract boolean marginTouchAllowed(View content, int x, int threshold);
	
	public abstract boolean menuOpenTouchAllowed(View content, int currPage, int x);
	
	public abstract boolean menuTouchInQuickReturn(View content, int currPage, int x);
	
	public abstract boolean menuClosedSlideAllowed(int x);
	
	public abstract boolean menuOpenSlideAllowed(int x);
	
	public abstract void drawShadow(Canvas canvas, Drawable shadow, int width);
	
	public abstract void drawFade(Canvas canvas, int alpha,
								  CustomViewBehind cvb, View content);
	
	public abstract void drawSelector(View content, Canvas canvas, float percentOpen);
	
}
