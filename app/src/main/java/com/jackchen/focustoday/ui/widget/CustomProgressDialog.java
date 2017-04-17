package com.jackchen.focustoday.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.jackchen.focustoday.R;
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
