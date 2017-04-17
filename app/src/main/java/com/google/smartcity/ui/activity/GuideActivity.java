package com.google.smartcity.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.smartcity.R;
import com.google.smartcity.ui.adapter.BasePagerAdapter;
import com.google.smartcity.utils.DensityUtils;
import com.google.smartcity.utils.PackageUtils;
import com.google.smartcity.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class GuideActivity extends Activity {

	private static final int[] mImagesIds = new int[] { R.mipmap.guide_1,
			R.mipmap.guide_2, R.mipmap.guide_3, };
	private List<ImageView> mImageViewList;

	@BindView(R.id.ll_point_guide)
	 LinearLayout llPointGroup;

	@BindView(R.id.view_red_point)
	 View view_red_point;

	@BindView(R.id.vp_guide)
	 ViewPager vpGuide;

	@BindView(R.id.btn_start)
	 Button btn_start;

	private int mPointWith;// 圆点间距

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
		initView();
		vpGuide.setAdapter(new GuideAdapter(mImageViewList));
		vpGuide.setOnPageChangeListener(new GuidePagerListener());
	}

	private void initView() {
		setContentView(R.layout.activity_guide);
		ButterKnife.bind(this);
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 更新sp,表示已经展示引导页
				SPUtils.saveBoolean(GuideActivity.this,
						"is_userGuide_showed", true);
				PackageUtils.startActivity(GuideActivity.this,
						MainActivity.class);
				finish();
			}
		});
		// 初始化引导页的3个页面
		mImageViewList = new ArrayList<>();
		for (int i = 0; i < mImagesIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(mImagesIds[i]);
			mImageViewList.add(imageView);
		}

		// 初始化引导页的三个小圆点
		for (int i = 0; i < mImagesIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_darkgray);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));
			if (i > 0) {
				params.leftMargin = DensityUtils.dp2px(this, 10);
			}
			point.setLayoutParams(params);
			llPointGroup.addView(point);
		}
		/**
		 * 获取视图树，对layout事件结束进行监听
		 */
		llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					// 当布局完毕的时候执行此方法
					@Override
					public void onGlobalLayout() {
						llPointGroup.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWith = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
					}
				});
	}

	/**
	 * ViewPager数据适配器
	 * 
	 * @author AllenIverson
	 * 
	 */
	class GuideAdapter extends BasePagerAdapter<ImageView> {

		public GuideAdapter(List<ImageView> data) {
			super(data);
		}

		@Override
		public View getView(int position) {
			return mImageViewList.get(position);
		}
	}

	/**
	 * viewpager的滑动监听
	 * 
	 * @author AllenIverson
	 * 
	 */
	class GuidePagerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int p, float positionOffset,
				int positionOffsetPixels) {
			// positionOffset移动的百分比,positionOffsetPixels移动的像素
			// 圆点移动的距离
			int len = (int) (mPointWith * positionOffset + p * mPointWith);
			// 小红点的布局参数
			LayoutParams params = (LayoutParams) view_red_point
					.getLayoutParams();
			params.leftMargin = len;
			// 重新给小红点设置参数
			view_red_point.setLayoutParams(params);
		}

		@Override
		public void onPageSelected(int p) {
			// 最后一个页面显示开始体验按钮
			if (p == mImagesIds.length - 1) {
				btn_start.setVisibility(View.VISIBLE);
			} else {
				btn_start.setVisibility(View.INVISIBLE);
			}

		}

	}
}
