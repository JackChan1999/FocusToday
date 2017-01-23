package com.google.smartcity.ui.tabnews;

import android.app.Activity;

import com.google.smartcity.base.BaseTabDetailPager;
import com.google.smartcity.http.RequestUrl;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.google.smartcity.ui.tabnews
 * Version：1.0
 * time：2016/12/15 9:22
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class TabHomeNews extends BaseTabDetailPager {

    public TabHomeNews(Activity activity) {
        super(activity);
    }

    @Override
    public String getNewsUrl(int index) {
        return RequestUrl.getHomeNewsUrl(index);
    }

    @Override
    public String getNewsId() {
        return RequestUrl.HomeId;
    }

}
