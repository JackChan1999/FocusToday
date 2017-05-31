package com.jackchan.focustoday.ui.tabnews;

import android.app.Activity;

import com.jackchan.focustoday.base.BaseTabDetailPager;
import com.jackchan.focustoday.http.RequestUrl;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * CSDN博客： http://blog.csdn.net/axi295309066
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
public class TabEmotionNews extends BaseTabDetailPager {

    public TabEmotionNews(Activity activity) {
        super(activity);
    }

    @Override
    public String getNewsUrl(int index) {
        return RequestUrl.getEmotionNewsUrl(index);
    }

    @Override
    public String getNewsId() {
        return RequestUrl.EmotionId;
    }

}
