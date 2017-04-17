package com.jackchen.focustoday.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
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
public class VolleyUtil {

    private RequestQueue           mQueue;
    private ImageLoader            mImageLoader;
    private ImageLoader.ImageCache mImageCache;
    private Context mContext;

    private static VolleyUtil instance;

    private VolleyUtil(Context context) {
        mContext = context;
        mQueue = getQueue();
        mImageCache = new BitmapCache();
        mImageLoader = new ImageLoader(mQueue, mImageCache);

        /*mImageLoader = new ImageLoader(mQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });*/

    }

    public static VolleyUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (VolleyUtil.class) {
                if (instance == null) {
                    instance = new VolleyUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 请求队列
     */
    public RequestQueue getQueue() {
        if (mQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getQueue().add(req);
    }

    /**
     * 图片加载器
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 自定义内存缓存
     */
    public ImageLoader.ImageCache getImageCache() {
        return mImageCache;
    }
}