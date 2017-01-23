package com.google.smartcity.ui.widget;

import com.google.smartcity.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class CustomProgressDialog extends ProgressDialog {
	private String content;
	private TextView progress_dialog_content;

	public CustomProgressDialog(Context paramContext, String paramString) {
		super(paramContext);
		this.content = paramString;
		setCanceledOnTouchOutside(false);
	}

	private void initData() {
		this.progress_dialog_content.setText(this.content);
	}

	private void initView() {
		setContentView(R.layout.custom_progress_dialog);
		this.progress_dialog_content = ((TextView) findViewById(R.id.progress_dialog_content));
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		initView();
		initData();
	}

	public void setContent(String paramString) {
		this.progress_dialog_content.setText(paramString);
	}
}
