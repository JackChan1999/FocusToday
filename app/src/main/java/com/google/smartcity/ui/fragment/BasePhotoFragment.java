package com.google.smartcity.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.smartcity.base.BaseApplication;
import com.google.smartcity.bean.PhotoBean;
import com.google.smartcity.bean.Video;
import com.google.smartcity.http.VolleyUtil;
import com.google.smartcity.ui.activity.VideoPlayActivity;

import java.util.List;

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
 * Package_Name：com.google.smartcity
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public abstract class BasePhotoFragment extends Fragment {

    private List<PhotoBean> mdata;

    private long lastupdatetime;
    private int  indexId;
    private int  index;
    private int     currentPagte = 1;
    private boolean isRefresh    = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected void initData() {

    }

    protected void initPageIndex() {
        int count = BaseApplication.getSpUtils().getInt("indexId");
        lastupdatetime = BaseApplication.getSpUtils().getLong("lastupdatetime");
        if (count != -1) {
            if ((lastupdatetime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {
                lastupdatetime = System.currentTimeMillis();
                BaseApplication.getSpUtils().putLong("lastupdatetime", lastupdatetime);
                int n = (int) (System.currentTimeMillis() / (lastupdatetime + (24 * 60 * 60 *
                        1000)));
                count = (count + 20) * n;
            } else {
                indexId = count;
            }
        } else {
            indexId = 111100;
            BaseApplication.getSpUtils().putLong("lastupdatetime", System.currentTimeMillis());
            BaseApplication.getSpUtils().putInt("indexId", indexId);
        }

        index = indexId;
    }

    protected void requestData(String url, final boolean isLoadMore) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                parseData(result, isLoadMore);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        VolleyUtil.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }

    protected void parseData(String result, boolean isLoadMore) {

    }

    public void enterVideoActivity(Video.VideoData data) {
        Bundle bundle = new Bundle();
        bundle.putString("videourl", data.mp4_url);
        bundle.putString("title", data.title);
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

}
