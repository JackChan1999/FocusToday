package com.google.smartcity.ui.activity;

import com.google.smartcity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class FeedbackActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_feedback);
		
	}
}
