package com.google.smartcity.base;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.smartcity.R;
import com.google.smartcity.common.utils.SPUtils;
import com.thinkland.yellowpages.activity.YellowPageApplication;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class BaseApplication extends YellowPageApplication {

	private static BaseApplication mApplication;
	private static Context mContext;
	private static Handler mHandler;
	private static long mMainTreadId;
	private static SPUtils mSpUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		mHandler = new Handler();
		mMainTreadId = Process.myTid();
		mContext = getApplicationContext();
		ToastMabager.builder.init(mContext);
		mSpUtils = new SPUtils(this,"smartCity");
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}

	public static Context getApplication() {
		return mApplication;
	}

	public static Context getContext() {
		return mContext;
	}

	public static Handler getHandler() {
		return mHandler;
	}

	public static long getMainTreadId() {
		return mMainTreadId;
	}

	public static SPUtils getSpUtils(){
		return mSpUtils;
	}

	private class ExceptionHandler implements UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			ex.printStackTrace();
			try {
				PrintWriter printWriter = new PrintWriter(Environment.getExternalStorageDirectory() + "/smartcity.log");
				ex.printStackTrace(printWriter);
				printWriter.close(); 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Process.killProcess(Process.myPid());
		}
		
	}

	public enum ToastMabager {
		builder;

		private View     view;
		private TextView tv;
		private Toast    toast;

		/**
		 * 初始化toast
		 */
		public void init(Context context) {
			view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
			tv = (TextView) view.findViewById(R.id.tv_toast);
			toast = new Toast(context);
			toast.setView(view);
		}

		/**
		 * 显示toast
		 * @param content  显示的内容
		 * @param duration 持续时间
		 */
		public void display(CharSequence content, int duration) {
			if (content.length() != 0) {
				tv.setText(content);
				toast.setDuration(duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}
	}
}
