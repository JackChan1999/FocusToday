/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 bboyfeiyu@gmail.com, Inc
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

package com.jackchan.imageloader.request;

import android.widget.ImageView;

import com.jackchan.imageloader.config.DisplayConfig;
import com.jackchan.imageloader.core.ImageLoader;
import com.jackchan.imageloader.policy.LoadPolicy;
import com.jackchan.imageloader.utils.ImageViewHelper;
import com.jackchan.imageloader.utils.Md5Helper;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
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
 * des ：网络请求类. 注意GET和DELETE不能传递参数,因为其请求的性质所致,用户可以将参数构建到url后传递进来到Request中.
 * gitVersion：2.12.0.windows.1
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 */

public class BitmapRequest implements Comparable<BitmapRequest> {

    Reference<ImageView> mImageViewRef;
    public DisplayConfig             displayConfig;
    public ImageLoader.ImageListener imageListener;
    public String imageUri = "";
    public String imageUriMd5 = "";
    /**
     * 请求序列号
     */
    public int serialNum = 0;
    /**
     * 是否取消该请求
     */
    public boolean isCancel = false;

    /**
     * 
     */
    public boolean justCacheInMem = false;

    /**
     * 加载策略
     */
    LoadPolicy mLoadPolicy = ImageLoader.getInstance().getConfig().loadPolicy;

    /**
     * @param imageView
     * @param uri
     * @param config
     * @param listener
     */
    public BitmapRequest(ImageView imageView, String uri, DisplayConfig config,
            ImageLoader.ImageListener listener) {
        mImageViewRef = new WeakReference<ImageView>(imageView);
        displayConfig = config;
        imageListener = listener;
        imageUri = uri;
        imageView.setTag(uri);
        imageUriMd5 = Md5Helper.toMD5(imageUri);
    }

    /**
     * @param policy
     */
    public void setLoadPolicy(LoadPolicy policy) {
        if (policy != null) {
            mLoadPolicy = policy;
        }
    }

    /**
     * 判断imageview的tag与uri是否相等
     * 
     * @return
     */
    public boolean isImageViewTagValid() {
        return mImageViewRef.get() != null ? mImageViewRef.get().getTag().equals(imageUri) : false;
    }

    public ImageView getImageView() {
        return mImageViewRef.get();
    }

    public int getImageViewWidth() {
        return ImageViewHelper.getImageViewWidth(mImageViewRef.get());
    }

    public int getImageViewHeight() {
        return ImageViewHelper.getImageViewHeight(mImageViewRef.get());
    }

    @Override
    public int compareTo(BitmapRequest another) {
        return mLoadPolicy.compare(this, another);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((imageUri == null) ? 0 : imageUri.hashCode());
        result = prime * result + ((mImageViewRef == null) ? 0 : mImageViewRef.get().hashCode());
        result = prime * result + serialNum;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitmapRequest other = (BitmapRequest) obj;
        if (imageUri == null) {
            if (other.imageUri != null)
                return false;
        } else if (!imageUri.equals(other.imageUri))
            return false;
        if (mImageViewRef == null) {
            if (other.mImageViewRef != null)
                return false;
        } else if (!mImageViewRef.get().equals(other.mImageViewRef.get()))
            return false;
        if (serialNum != other.serialNum)
            return false;
        return true;
    }

}
