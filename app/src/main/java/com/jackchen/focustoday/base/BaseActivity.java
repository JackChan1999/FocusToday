package com.jackchen.focustoday.base;

import android.content.Intent;
import android.os.Bundle;

import com.jackchen.focustoday.ui.widget.slidingmenu.app.SlidingActivity;
import com.jackchen.focustoday.utils.AppManager;
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
public class BaseActivity extends SlidingActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}

	protected String getStringExtra(String name) {
		return getIntent().getStringExtra(name);
	}

	protected int getIntExtra(String name) {
		return getIntent().getIntExtra(name, -1);
	}

	public void comeOnBaby(Class clazz) {
		startActivity(new Intent(this, clazz));
	}
}
