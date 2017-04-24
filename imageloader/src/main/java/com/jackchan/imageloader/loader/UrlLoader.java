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

package com.jackchan.imageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jackchan.imageloader.disklrucache.IOUtil;
import com.jackchan.imageloader.request.BitmapRequest;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
 * des ：${TODO}
 * gitVersion：2.12.0.windows.1
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 */

public class UrlLoader extends AbsLoader {

    @Override
    public Bitmap onLoadImage(BitmapRequest request) {
        final String imageUrl = request.imageUri;
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null ;
         HttpURLConnection conn = null ;
        try {
            URL url = new URL(imageUrl);
            conn = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            bitmap =  BitmapFactory.decodeStream(is, null, null);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(fos);
            if ( conn != null ) {
                // 关闭流
                conn.disconnect();
			}
        }
        return bitmap;
    }
}
