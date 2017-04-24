package com.jackchan.focustoday.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
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
public class BitmapCache implements ImageLoader.ImageCache {
    LruCache<String, Bitmap> mLruCache;
    // 存储结构/容器/集合缓存的最大值
    // 1.告知缓存的具体大小
    private int maxSize = 5 * 1024 * 1024;    // 5242880 byte
    // private int maxSize = 5; // 5m

    public BitmapCache() {
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            // 2.覆写sizeOf方法
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 返回每一个entry对应的大小
                // 具体大小需要和我们定义的maxsize单位统一
                // return value.getByteCount();
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {// 取图片
        return mLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {// 存图片
        mLruCache.put(url, bitmap);
    }

}