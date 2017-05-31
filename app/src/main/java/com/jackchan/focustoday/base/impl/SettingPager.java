package com.jackchan.focustoday.base.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.jackchan.focustoday.R;
import com.jackchan.focustoday.base.BasePager;
import com.jackchan.focustoday.ui.activity.FeedbackActivity;
import com.jackchan.focustoday.ui.activity.LoginActivity;
import com.jackchan.focustoday.utils.SPUtils;
import com.jackchan.focustoday.utils.ToastUtils;
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
public class SettingPager extends BasePager implements OnClickListener {

	private Button bt_login;
	private Button bt_return;

	private TableRow more_page_row0;
	private TableRow more_page_row1;
	private TableRow more_page_row2;
	private TableRow more_page_row3;
	private TableRow more_page_row4;
	private TableRow more_page_row5;
	private TableRow more_page_row6;
	private TableRow more_page_row7;
	private TableRow more_page_row8;
	private TableRow more_page_row9;

	private TextView tv_cache;

	public SettingPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("设置");
		btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
		btnSearch.setVisibility(View.GONE);
		setSlidingMenuEnable(false);// 关闭侧边栏

		// TextView text = new TextView(mActivity);
		// text.setText("设置");
		// text.setTextColor(Color.RED);
		// text.setTextSize(25);
		// text.setGravity(Gravity.CENTER);

		View view = View.inflate(mActivity, R.layout.layout_setting, null);
		bt_login = (Button) view.findViewById(R.id.bt_login);
		bt_return = (Button) view.findViewById(R.id.bt_return);
		tv_cache = (TextView) view.findViewById(R.id.tv_cache);
		more_page_row5 = (TableRow) view.findViewById(R.id.more_page_row5);
		more_page_row6 = (TableRow) view.findViewById(R.id.more_page_row6);

		bt_login.setOnClickListener(this);
		bt_return.setOnClickListener(this);
		tv_cache.setOnClickListener(this);
		more_page_row5.setOnClickListener(this);
		more_page_row6.setOnClickListener(this);

		// 向FrameLayout中动态添加布局
		flContent.addView(view);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
			break;
		case R.id.more_page_row6:
			mActivity.startActivity(new Intent(mActivity, FeedbackActivity.class));
			break;
		case R.id.bt_return:
			exit();
			break;
		case R.id.more_page_row5:
			//new BitmapUtils(mActivity).clearDiskCache();
			ToastUtils.showSafeToast(mActivity, "成功清除所有缓存");
			break;
		}
	}

	private void exit() {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
		localBuilder.setTitle("提示");
		localBuilder.setMessage("确定退出登陆 吗？");
		localBuilder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface paramDialogInterface,
							int paramInt) {

						SPUtils.saveString(mActivity,
								"ql_token", "");
						mActivity.finish();
						// 杀死自己
						android.os.Process.killProcess(android.os.Process
								.myPid());
					}
				});
		localBuilder.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface paramDialogInterface,
							int paramInt) {
					}
				});
		localBuilder.create().show();
	}

}
