package com.google.smartcity.base;

import android.content.Intent;
import android.os.Bundle;

import com.google.smartcity.ui.widget.slidingmenu.app.SlidingActivity;
import com.google.smartcity.utils.AppManager;

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
