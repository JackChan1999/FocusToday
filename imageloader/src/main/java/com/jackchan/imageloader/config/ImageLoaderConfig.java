/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 jackychan2040@gmail.com (JackChan)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jackchan.imageloader.config;


import com.jackchan.imageloader.cache.BitmapCache;
import com.jackchan.imageloader.cache.MemoryCache;
import com.jackchan.imageloader.policy.LoadPolicy;
import com.jackchan.imageloader.policy.SerialPolicy;
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
 * Project_Name：ImageLoader
 * Package_Name：com.jackchan.imageloader
 * Version：1.0
 * time：2016/4/24 10:50
 * des ：ImageLoader配置类
 * gitVersion：2.12.0.windows.1
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 */

public class ImageLoaderConfig {

    /**
     * 图片缓存配置对象
     */
    public BitmapCache bitmapCache = new MemoryCache();

    /**
     * 加载图片时的loading和加载失败的图片配置对象
     */
    public DisplayConfig displayConfig = new DisplayConfig();
    /**
     * 加载策略
     */
    public LoadPolicy    loadPolicy    = new SerialPolicy();

    /**
     * CPU核心数
     */
    public int threadCount = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * @param count
     * @return
     */
    public ImageLoaderConfig setThreadCount(int count) {
        threadCount = Math.max(1, count);
        return this;
    }

    public ImageLoaderConfig setCache(BitmapCache cache) {
        bitmapCache = cache;
        return this;
    }

    public ImageLoaderConfig setLoadingPlaceholder(int resId) {
        displayConfig.loadingResId = resId;
        return this;
    }

    public ImageLoaderConfig setNotFoundPlaceholder(int resId) {
        displayConfig.failedResId = resId;
        return this;
    }

    public ImageLoaderConfig setLoadPolicy(LoadPolicy policy) {
        if (policy != null) {
            loadPolicy = policy;
        }
        return this;
    }
}
