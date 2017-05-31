package com.jackchan.focustoday.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jackchan.focustoday.R;

import java.text.SimpleDateFormat;
import java.util.Date;

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
 * des ：下拉刷新
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class RefreshListView extends ListView implements OnScrollListener,
		AdapterView.OnItemClickListener {

	private static final int STATE_PULL_REFRESH = 0;// 下拉刷新
	private static final int STATE_RELEASE_REFRESH = 1;// 松开刷新
	private static final int STATE_REFRESHING = 2;// 正在刷新
	private int mCurrrentState = STATE_PULL_REFRESH;// 当前状态

	private TextView tvTitle;
	private TextView tvTime;
	private ImageView ivArrow;
	private ProgressBar pbProgress;
	private RotateAnimation animUp;
	private RotateAnimation animDown;
	private View mFooterView;// 脚布局
	private View mHeadView;// 头布局
	private int startY = -1;// 滑动起点的Y坐标
	private int mHeadViewHeight;
	private int mFooterViewHeight;
	
	private LayoutInflater inflater ;

	public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initHeadView(context);
		initFooterView(context);
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RefreshListView(Context context) {
		this(context, null);
	}

	/**
	 * 初始化头布局
	 */
	private void initHeadView(Context context) {
		inflater = LayoutInflater.from(context);
		mHeadView = inflater.inflate(R.layout.refresh_header, null,false);
		this.addHeaderView(mHeadView);

		tvTitle = (TextView) mHeadView.findViewById(R.id.tv_title);
		tvTime = (TextView) mHeadView.findViewById(R.id.tv_time);
		ivArrow = (ImageView) mHeadView.findViewById(R.id.iv_arr);
		pbProgress = (ProgressBar) mHeadView.findViewById(R.id.pb_progress);

		mHeadView.measure(0, 0);
		mHeadViewHeight = mHeadView.getMeasuredHeight();
		mHeadView.setPadding(0, -mHeadViewHeight, 0, 0);// 隐藏头布局

		initArrowAnim();

		tvTime.setText("最后刷新时间:" + getCurrentTime());
	}

	private void initFooterView(Context context) {

		mFooterView = inflater.inflate(R.layout.refresh_listview_footer,null,false);
		this.addFooterView(mFooterView);

		mFooterView.measure(0, 0);
		mFooterViewHeight = mFooterView.getMeasuredHeight();
		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);// 隐藏

		this.setOnScrollListener(this);
	}

	/*@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (mCurrrentState == STATE_REFRESHING ) {
			return true ;
		}
		return false;
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();

			break;
		case MotionEvent.ACTION_MOVE:
			if (startY == -1) {
				startY = (int) ev.getRawY();
			}
			if (mCurrrentState == STATE_REFRESHING) {// 正在刷新时不做处理
				break;
			}
			int endY = (int) ev.getRawY();
			int dy = endY - startY;
			if (dy > 0 && getFirstVisiblePosition() == 0) {// 只有下拉并且当前是第一个item,才允许下拉
				int padding = dy - mHeadViewHeight;// 计算padding
				mHeadView.setPadding(0, padding, 0, 0);// 设置当前padding
				if (padding > 0 && mCurrrentState != STATE_RELEASE_REFRESH) {// padding>0时，状态改为松开刷新
					mCurrrentState = STATE_RELEASE_REFRESH;
					refreshState();
				} else if (padding < 0 && mCurrrentState != STATE_PULL_REFRESH) {// 改为下拉刷新
					mCurrrentState = STATE_PULL_REFRESH;
					refreshState();
				}
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			startY = -1;// 重置
			if (mCurrrentState == STATE_RELEASE_REFRESH) {
				mCurrrentState = STATE_REFRESHING;// 正在刷新
				mHeadView.setPadding(0, 0, 0, 0);// 显示
				refreshState();
			} else if (mCurrrentState == STATE_PULL_REFRESH) {
				mHeadView.setPadding(0, -mHeadViewHeight, 0, 0);// 隐藏
			}
			break;

		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 下拉刷新控件的布局
	 */
	private void refreshState() {
		switch (mCurrrentState) {
		case STATE_PULL_REFRESH:
			this.setEnabled(false);
			tvTitle.setText("下拉刷新");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(animDown);
			break;
		case STATE_REFRESHING:
			this.setEnabled(true);
			tvTitle.setText("正在刷新...");
			ivArrow.clearAnimation();// 必须先清除动画,才能隐藏
			ivArrow.setVisibility(View.INVISIBLE);
			pbProgress.setVisibility(View.VISIBLE);
			if (mListener != null) {
				mListener.OnRefresh();
			}
			break;
		case STATE_RELEASE_REFRESH:
			this.setEnabled(false);
			tvTitle.setText("松开刷新");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(animUp);
			break;

		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	private String getCurrentTime() {

		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 初始化箭头动画
	 */
	private void initArrowAnim() {
		animUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animUp.setDuration(200);
		animUp.setFillAfter(true);

		animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animDown.setDuration(200);
		animDown.setFillAfter(true);
	}

	/**
	 * 下拉刷新接口
	 * 
	 * @author AllenIverson
	 * 
	 */
	public interface OnRefreshListener {
		public void OnRefresh();

		public void OnLoadMore();// 加载下一页数据
	}

	public OnRefreshListener mListener;
	/**
	 * 设置下拉刷新监听
	 * @param listener
	 */
	public void setOnRefreshListener(OnRefreshListener listener) {
		mListener = listener;
	}
	

	/**
	 * 收起下拉刷新控件
	 */

	public void OnRefreshComplete(boolean success) { 
		if (isLoadingMore) {
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
			isLoadingMore = false;
		} else {
			mCurrrentState = STATE_PULL_REFRESH;
			tvTitle.setText("下拉刷新");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);

			mHeadView.setPadding(0, -mHeadViewHeight, 0, 0);// 隐藏

			if (success) {
				tvTitle.setText("最后刷新时间:" + getCurrentTime());
			}
		}
	}

	public OnItemClickListener mItemClickListener;

	@Override
	public void setOnItemClickListener(
			OnItemClickListener listener) {
		super.setOnItemClickListener(this);
		mItemClickListener = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (mItemClickListener != null) {
			mItemClickListener.onItemClick(parent, view, position
					- getHeaderViewsCount(), id);
		}
	}

	private boolean isLoadingMore;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE
				|| scrollState == SCROLL_STATE_FLING) {
			if (getLastVisiblePosition() == getCount() - 1 && !isLoadingMore) {// 滑动到最后
				mFooterView.setPadding(0, 0, 0, 0);// 显示
				setSelection(getCount() - 1);// 改变listview的位置
				isLoadingMore = true;
				if (mListener != null) {
					mListener.OnLoadMore();
				}
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

	}

}
