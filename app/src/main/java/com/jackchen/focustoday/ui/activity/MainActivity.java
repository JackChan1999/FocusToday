package com.jackchen.focustoday.ui.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jackchen.focustoday.R;
import com.jackchen.focustoday.base.impl.NewsCenterPager;
import com.jackchen.focustoday.base.menudetail.NewsMenuDetailPager;
import com.jackchen.focustoday.ui.fragment.ContentFragment;
import com.jackchen.focustoday.ui.fragment.LeftMenuFragment;
import com.jackchen.focustoday.ui.widget.slidingmenu.SlidingMenu;
import com.jackchen.focustoday.ui.widget.slidingmenu.app.SlidingFragmentActivity;
import com.thinkland.yellowpages.activity.ExpressActivity;
import com.thinkland.yellowpages.activity.HospitalActivity;
import com.thinkland.yellowpages.activity.IllegalActivity;
import com.thinkland.yellowpages.activity.PhoneListActivity;
import com.thinkland.yellowpages.activity.RechargeActivity;
import com.thinkland.yellowpages.activity.TrainActivity;
import com.thinkland.yellowpages.activity.YellowPageActivity;
import com.thinkland.yellowpages.util.MResource;

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
public class MainActivity extends SlidingFragmentActivity implements OnClickListener {

	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT = "fragment_content";

	private Dialog checkBalanceDialog;

	private Button bt_cancel;
	private Button bt_enter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_home);
		setBehindContentView(R.layout.left_menu);// 设置侧边栏布局
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
		int width = getWindowManager().getDefaultDisplay().getWidth();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		slidingMenu.setBehindOffset(width/2);// 设置屏幕预留的宽度
		initFagment();

		if (Build.VERSION.SDK_INT > 21){
			getWindow().setStatusBarColor(Color.parseColor("#b0120a"));
		}

		/*setContentView(R.layout.layout_content);
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingmenu);
		mSlidingMenu.setMenu(R.layout.left_menu);
		int width = getWindowManager().getDefaultDisplay().getWidth();
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		mSlidingMenu.setBehindOffset(width/2);// 设置屏幕预留的宽度
		initFagment();*/

		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			//getWindow().setStatusBarColor(Color.BLUE);
		}*/

		/*final int statusBarHeight = UIUtils.getStatusBarHeight(this);
		System.out.println("statusBarHeight--"+statusBarHeight);
		mSlidingMenu.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				RelativeLayout rl = (RelativeLayout) mSlidingMenu.getContent().findViewById(R.id.header);
				rl.setBackgroundColor(Color.BLUE);
				rl.measure(0,0);

				*//**//*TextView tv = (TextView) mSlidingMenu.findViewById(R.id.tv_title);
				ImageView menu = (ImageView) mSlidingMenu.findViewById(R.id.btn_menu);
				ImageView search = (ImageView) mSlidingMenu.findViewById(R.id.btn_search);
				menu.measure(0,0);
				search.measure(0,0);*//**//*

				int measureHeight = rl.getMeasuredHeight();
				System.out.println("measureHeight--"+measureHeight);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,statusBarHeight + measureHeight);
				rl.setLayoutParams(params);


				*//**//*//**//*//*+measureHeight/2-45
				int paddingTop = statusBarHeight;
				rl.setPadding(0,paddingTop,0,0);
				rl.requestLayout();*//**//*

				mSlidingMenu.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});*/
	}

	/*public SlidingMenu getSlidingMenu(){
		return mSlidingMenu;
	}*/

	/**
	 * 初始化fragment，将fragment数据填充给布局文件
	 */
	private void initFagment() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		transaction.add(R.id.fl_left_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);
		transaction.add(R.id.fl_content, new ContentFragment(), FRAGMENT_CONTENT);

		//transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);
		//transaction.replace(R.id.fl_content, new ContentFragment(), FRAGMENT_CONTENT);
		transaction.commit();
	}

	// 获取侧边栏fragment
	public LeftMenuFragment getLeftMenuFragment() {
		LeftMenuFragment fragment = (LeftMenuFragment) getSupportFragmentManager()
				.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;
	}

	// 获取主页fragment
	public ContentFragment getContentFragment() {
		ContentFragment fragment = (ContentFragment) getSupportFragmentManager()
				.findFragmentByTag(FRAGMENT_CONTENT);
		return fragment;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0){
			if (data.getBooleanExtra("isDataChange",false)){
				ContentFragment contentFragment = (ContentFragment) getSupportFragmentManager()
						.findFragmentByTag(FRAGMENT_CONTENT);
				NewsCenterPager newsCenterPager = (NewsCenterPager) contentFragment.getPageList().get(1);
				NewsMenuDetailPager newsMenuDetailPager = (NewsMenuDetailPager) newsCenterPager.getMenuDetailPage().get(0);
				newsMenuDetailPager.initData();
			}
		}
	}

	public void onFunctionButtonClick(View v) {
		switch (v.getId()) {
		case R.id.ll_recharge_1:
			startActivity(new Intent(this, RechargeActivity.class));
			break;
		case R.id.ll_checkbalance_1:
			showDialog();
			break;
		case R.id.ll_express_1:
			startActivity(new Intent(this, ExpressActivity.class));
			break;
		case R.id.ll_illegal_1:
			startActivity(new Intent(this, IllegalActivity.class));
			break;
		case R.id.ll_train_1:
			startActivity(new Intent(this, TrainActivity.class));
			break;
		case R.id.ll_hospital_1:
			startActivity(new Intent(this, HospitalActivity.class));
			break;
		}
	}
	
	
	public void onUsefulClick(View view)
	  {
	    Intent intent = new Intent(this, PhoneListActivity.class);
	    if (view.getId() == R.id.ll_express)
	      intent.putExtra("phoneId", 122);
	    else if (view.getId() == R.id.ll_delicacies)
	      intent.putExtra("phoneId", 119);
	    else if (view.getId() == R.id.ll_aviation)
	      intent.putExtra("phoneId", 136);
	    else if (view.getId() == R.id.ll_hotel)
	      intent.putExtra("phoneId", 124);
	    else if (view.getId() == R.id.ll_bank)
	      intent.putExtra("phoneId", 123);
	    else if (view.getId() == R.id.ll_drivers)
	      intent.putExtra("phoneId", 126);
	    else if (view.getId() == R.id.ll_finance)
	      intent.putExtra("phoneId", 128);
	    else if (view.getId() == R.id.ll_game)
	      intent.putExtra("phoneId", 17);
	    else if (view.getId() == R.id.ll_aftersales)
	      intent.putExtra("phoneId", 592);
	    else if (view.getId() == R.id.ll_ecommerce)
	      intent.putExtra("phoneId", 132);
	    else if (view.getId() == R.id.ll_car)
	      intent.putExtra("phoneId", 108);
	    else if (view.getId() == R.id.ll_hotline) {
	      intent.putExtra("phoneId", 129);
	    }
	    startActivity(intent);
	  }
	

	public void showDialog() {
		if (checkBalanceDialog != null) {
			checkBalanceDialog.show();
			return;
		}
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout dialogView = (LinearLayout) inflater.inflate(
				MResource.getIdByName("layout", "dialog_checkbalance"), null);
		checkBalanceDialog = new Dialog(this, MResource.getIdByName("style",
				"custom_dialog"));
		WindowManager.LayoutParams localLayoutParams = this.checkBalanceDialog
				.getWindow().getAttributes();
		localLayoutParams.gravity = 80;
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		dialogView.setMinimumWidth(screenWidth);
		checkBalanceDialog.getWindow().setAttributes(localLayoutParams);
		checkBalanceDialog.onWindowAttributesChanged(localLayoutParams);
		checkBalanceDialog.setCancelable(true);
		checkBalanceDialog.setCanceledOnTouchOutside(true);
		checkBalanceDialog.setContentView(dialogView);
		bt_cancel = (Button) dialogView.findViewById(R.id.bt_cancel);
		bt_cancel.setOnClickListener(this);
		bt_enter = (Button) dialogView.findViewById(R.id.bt_enter);
		bt_enter.setOnClickListener(this);

		if (!isFinishing())
			this.checkBalanceDialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_cancel:
			checkBalanceDialog.dismiss();
			break;
		case R.id.bt_enter:
			getSimOperatorName();
			checkBalanceDialog.dismiss();
			break;
		}
	}

	protected String getSimOperatorName() {
		String snm = null;

		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		String OPERATOR = tm.getNetworkOperator();
		if ((OPERATOR.startsWith("46000")) || (OPERATOR.startsWith("46002"))) {
			sendMsg("10086", "XCYE");
			snm = "中国移动";
		} else if (OPERATOR.startsWith("46001")) {
			sendMsg("10010", "YE");
			snm = "中国联通";
		} else if (OPERATOR.startsWith("46003")) {
			sendMsg("10001", "102");
			snm = "中国电信";
		}

		return snm;
	}

	private void sendMsg(String phone, String message) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
				YellowPageActivity.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phone, null, message, pi, null);
	}

}
