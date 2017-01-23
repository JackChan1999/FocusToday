package com.google.smartcity.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.google.smartcity.R;
import com.google.smartcity.ui.widget.StatusBarCompat;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		StatusBarCompat.compat(this);
		setContentView(R.layout.login);
	}

}
