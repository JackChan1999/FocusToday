package com.jackchan.focustoday.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.jackchan.focustoday.R;
import com.jackchan.focustoday.utils.PackageUtils;
import com.jackchan.focustoday.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class SplashActivity extends Activity {
	@BindView(R.id.rl_root)
	RelativeLayout rl_root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);
		startAnimation();
	}

	/**
	 * 开启动画
	 */
	private void startAnimation() {
		// 动画集合
		AnimationSet set = new AnimationSet(false);
		// 旋转动画，旋转角度0-360，相对于自身重心旋转
		RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(1000);// 动画时长
		rotateAnim.setFillAfter(true);// 保持动画状态

		// 渐变动画
		AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
		alphaAnim.setDuration(2000);
		alphaAnim.setFillAfter(true);

		// 缩放动画
		ScaleAnimation scaleAnim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(1000);
		scaleAnim.setFillAfter(true);

		set.addAnimation(alphaAnim);
		set.addAnimation(rotateAnim);
		set.addAnimation(scaleAnim);

		// 动画监听
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 动画结束
				jumpNextPage();
			}

		});

		rl_root.startAnimation(set);

	}

	private void jumpNextPage() {
		// 判断有没有显示过新手引导页
		boolean userGuide = SPUtils.getBoolean(this, "is_userGuide_showed", false);
		if (!userGuide) {
			// 跳转至新手引导页
			PackageUtils.startActivity(this, GuideActivity.class);
		} else {
			PackageUtils.startActivity(this, MainActivity.class);
		}
		finish();
	}

}
