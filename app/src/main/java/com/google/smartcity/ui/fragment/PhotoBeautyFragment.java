package com.google.smartcity.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.smartcity.R;
import com.google.smartcity.base.BaseApplication;
import com.google.smartcity.bean.PhotoBean;
import com.google.smartcity.bean.Video;
import com.google.smartcity.http.RequestUrl;
import com.google.smartcity.http.VolleyUtil;
import com.google.smartcity.ui.activity.VideoPlayActivity;
import com.google.smartcity.ui.adapter.recyclerview.CommonAdapter;
import com.google.smartcity.ui.adapter.recyclerview.MultiItemTypeAdapter;
import com.google.smartcity.ui.adapter.recyclerview.base.ViewHolder;
import com.google.smartcity.ui.adapter.recyclerview.wrapper.LoadmoreWrapper;

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

public class PhotoBeautyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MultiItemTypeAdapter.OnItemClickListener, LoadmoreWrapper.OnLoadMoreListener {

    private  RecyclerView       mRecyclerView;
    private LoadmoreWrapper    mLoadmoreWrapper;
    private  SwipeRefreshLayout mSwipeRefreshLayout;

    private PhotoAdapter mAdapter;
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

    public View initView(LayoutInflater inflater, ViewGroup container) {
        mRecyclerView = new RecyclerView(getActivity());
        //mRecyclerView.setBackgroundColor(Color.parseColor("#eaeaea"));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mSwipeRefreshLayout = new SwipeRefreshLayout(getActivity());
        mSwipeRefreshLayout.addView(mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mSwipeRefreshLayout;
    }

    private void initData() {
        int count = BaseApplication.getSpUtils().getInt("index_beauty");
        lastupdatetime = BaseApplication.getSpUtils().getLong("lastupdatetime_beauty");
        if (count != -1) {
            if ((lastupdatetime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {
                lastupdatetime = System.currentTimeMillis();
                BaseApplication.getSpUtils().putLong("lastupdatetime_beauty", lastupdatetime);
                int n = (int) (System.currentTimeMillis() / (lastupdatetime + (24 * 60 * 60 * 1000)));
                count = (count + 20) * n;
            } else {
                indexId = count;
            }
        } else {
            indexId = 90000;
            BaseApplication.getSpUtils().putLong("lastupdatetime_beauty", System.currentTimeMillis());
            BaseApplication.getSpUtils().putInt("index_beauty", indexId);
        }

        index = indexId;
        requestData(RequestUrl.getPicBeautyUrl(indexId), false);
    }

    public void requestData(String url, final boolean isLoadMore) {
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

    private void parseData(String result, boolean isLoadMore) {
        /*//mdata = GsonUtil.changeGsonToList(result,PhotoBean.class);
        mdata = new Gson().fromJson(result,new TypeToken<List<PhotoBean>>(){}.getType());
        mAdapter = new PhotoAdapter(getActivity(), R.layout.list_item_photo, mdata);
        mRecyclerView.setAdapter(mAdapter);*/

        List<PhotoBean> data = new Gson().fromJson(result, new TypeToken<List<PhotoBean>>(){}.getType());
        if (!isLoadMore) {
            if (isRefresh){
                isRefresh = false;
                mdata.clear();
                mSwipeRefreshLayout.setRefreshing(false);
                mdata.addAll(data);
                mLoadmoreWrapper.notifyDataSetChanged();
            }else {
                mdata = data;
                mAdapter = new PhotoAdapter(getActivity(), R.layout.list_item_photo_beauty, mdata);
                mAdapter.setOnItemClickListener(this);
                mLoadmoreWrapper = new LoadmoreWrapper(mAdapter);
                mLoadmoreWrapper.setOnLoadMoreListener(this);
                mLoadmoreWrapper.setLoadMoreView(R.layout.list_footer_loading);
                mRecyclerView.setAdapter(mLoadmoreWrapper);
            }
        } else {
            int lastIndex = mdata.size();
            if (mdata.addAll(data)){
                mLoadmoreWrapper.notifyItemRangeInserted(lastIndex,data.size());
            }
        }
    }

    /*private class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

        private Context               mContext;
        private List<Video.VideoData> mListData;

        public VideoAdapter(Context context, List<Video.VideoData> listdata) {
            mContext = context;
            mListData = listdata;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_video, parent,
                    false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();

                }
            });
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.setData(mListData.get(position));
        }

        @Override
        public int getItemCount() {
            return mListData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv;
            private TextView  title;

            public ViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.iv_video);
                title = (TextView) itemView.findViewById(R.id.tv_title);
            }

            private void setData(Video.VideoData data) {
                Glide.with(mContext).load(data.cover).into(iv);
                title.setText(data.title);
            }
        }
    }*/


    public void enterVideoActivity(Video.VideoData data) {
        Bundle bundle = new Bundle();
        bundle.putString("videourl",data.mp4_url);
        bundle.putString("title",data.title);
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onRefresh() {
        currentPagte = 1;
        isRefresh = true;
        index = index + 5;
        requestData(RequestUrl.getPicBeautyUrl(index),false);
        BaseApplication.getSpUtils().putInt("index_beauty",index);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    public void onLoadMoreRequested() {
        currentPagte++;
        indexId = indexId - 10;
        requestData(RequestUrl.getPicBeautyUrl(indexId), true);
    }

    private class PhotoAdapter extends CommonAdapter<PhotoBean> {

        public PhotoAdapter(Context context, int layoutId, List<PhotoBean> data) {
            super(context, layoutId, data);
        }

        @Override
        protected void convert(ViewHolder holder, PhotoBean data, int position) {
            ImageView iv = holder.getView(R.id.iv_photo);
            holder.setText(R.id.tv_title,data.setname);
            Glide.with(getActivity()).load(data.cover).into(iv);
        }
    }

}
