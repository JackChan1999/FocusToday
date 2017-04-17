package com.jackchen.focustoday.ui.tabnews;

import android.app.Activity;

import com.jackchen.focustoday.base.BaseTabDetailPager;
import com.jackchen.focustoday.http.RequestUrl;

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
public class TabGameNews extends BaseTabDetailPager {

    public TabGameNews(Activity activity) {
        super(activity);
    }

    @Override
    public String getNewsUrl(int index) {
        return RequestUrl.getGameNewsUrl(index);
    }

    @Override
    public String getNewsId() {
        return RequestUrl.GameId;
    }

}
