package com.jackchan.focustoday.utils;

import java.util.Iterator;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * CSDN博客： http://blog.csdn.net/axi295309066
 * 个人博客： https://jackchan1999.github.io/
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
public class AppManager {
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	public static AppManager getAppManager() {
		if (instance == null)
			instance = new AppManager();
		return instance;
	}

	public void AppExit(Context paramContext) {
		try {
			finishAllActivity();
			((ActivityManager) paramContext.getSystemService("activity"))
					.restartPackage(paramContext.getPackageName());
			System.exit(0);
			android.os.Process.killProcess(android.os.Process.myPid());
			return;
		} catch (Exception localException) {
		}
	}

	public void addActivity(Activity paramActivity) {
		if (activityStack == null)
			activityStack = new Stack();
		activityStack.add(paramActivity);
	}

	public Activity currentActivity() {
		return (Activity) activityStack.lastElement();
	}

	public void finishActivity() {
		finishActivity((Activity) activityStack.lastElement());
	}

	public void finishActivity(Activity paramActivity) {
		if (paramActivity != null) {
			activityStack.remove(paramActivity);
			paramActivity.finish();
		}
	}

	public void finishActivity(Class<?> paramClass) {
		Iterator localIterator = activityStack.iterator();
		while (true) {
			if (!localIterator.hasNext())
				return;
			Activity localActivity = (Activity) localIterator.next();
			if (!localActivity.getClass().equals(paramClass))
				continue;
			finishActivity(localActivity);
		}
	}

	public void finishAllActivity() {
		int i = 0;
		int j = activityStack.size();
		while (true) {
			if (i >= j) {
				activityStack.clear();
				return;
			}
			if (activityStack.get(i) != null)
				((Activity) activityStack.get(i)).finish();
			i++;
		}
	}
}
