package com.jackchan.focustoday.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * 博客：     http://blog.csdn.net/axi295309066
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
public class GsonRequest<T> extends Request<T> {
  
    private final Response.Listener<T> mListener;
  
    private Gson mGson;
  
    private Class<T> mClass;  
  
    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);  
        mGson = new Gson();  
        mClass = clazz;  
        mListener = listener;  
    }  
  
    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);  
    }  
  
    @Override  
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {  
            String jsonString = new String(response.data,  
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass),  
                    HttpHeaderParser.parseCacheHeaders(response));  
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }  
    }  
  
    @Override  
    protected void deliverResponse(T response) {  
        mListener.onResponse(response);  
    }  
  
} 