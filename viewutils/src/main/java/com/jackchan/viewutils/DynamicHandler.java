package com.jackchan.viewutils;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
/**
 * ============================================================
 * Copyright：JackChan和他的朋友们有限公司版权所有 (c) 2017
 * Author：   JackChan
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChan1999
 * GitBook：  https://www.gitbook.com/@alleniverson
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：FocusToday
 * Package_Name：com.jackchan.viewutils
 * Version：1.0
 * time：2016/4/25 17:34
 * des ：DynamicHandler
 * gitVersion：2.12.0.windows.1
 * updateAuthor：JackChan
 * updateDate：2016/4/25 17:34
 * updateDes：${TODO}
 * ============================================================
 */

public class DynamicHandler implements InvocationHandler
{
	private WeakReference<Object> handlerRef;
	private final HashMap<String, Method> methodMap = new HashMap<String, Method>(1);

	public DynamicHandler(Object handler)
	{
		this.handlerRef = new WeakReference<Object>(handler);
	}

	public void addMethod(String name, Method method)
	{
		methodMap.put(name, method);
	}

	public Object getHandler()
	{
		return handlerRef.get();
	}

	public void setHandler(Object handler)
	{
		this.handlerRef = new WeakReference<Object>(handler);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		Object handler = handlerRef.get();
		if (handler != null)
		{
			String methodName = method.getName();
			method = methodMap.get(methodName);
			if (method != null)
			{
				return method.invoke(handler, args);
			}
		}
		return null;
	}
}
