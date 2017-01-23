package com.google.smartcity.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.google.smartcity.R;
import com.google.smartcity.utils.PackageUtils;
import com.google.smartcity.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-2-21 下午8:45:11
 * 
 * 描 述 ： 闪屏页
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class SplashActivity extends Activity {
	@Bind(R.id.rl_root)
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
