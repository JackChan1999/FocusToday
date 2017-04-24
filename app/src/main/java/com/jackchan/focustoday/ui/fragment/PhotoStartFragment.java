package com.jackchan.focustoday.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackchan.focustoday.R;
import com.jackchan.focustoday.base.BaseApplication;
import com.jackchan.focustoday.bean.PhotoBean;
import com.jackchan.focustoday.bean.Video;
import com.jackchan.focustoday.http.RequestUrl;
import com.jackchan.focustoday.http.VolleyUtil;
import com.jackchan.focustoday.ui.activity.VideoPlayActivity;
import com.jackchan.commons.adapter.recyclerview.CommonAdapter;
import com.jackchan.commons.adapter.recyclerview.MultiItemTypeAdapter;
import com.jackchan.commons.adapter.recyclerview.base.ViewHolder;
import com.jackchan.commons.adapter.recyclerview.wrapper.LoadmoreWrapper;

import java.util.List;

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

public class PhotoStartFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MultiItemTypeAdapter.OnItemClickListener, LoadmoreWrapper.OnLoadMoreListener {

    private RecyclerView       mRecyclerView;
    private LoadmoreWrapper    mLoadmoreWrapper;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PhotoAdapter mAdapter;
    String url = "http://c.m.163.com/photo/api/morelist/0096/54GK0096/90000.json";
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
        mRecyclerView.setBackgroundColor(Color.parseColor("#eaeaea"));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout = new SwipeRefreshLayout(getActivity());
        mSwipeRefreshLayout.addView(mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mSwipeRefreshLayout;
    }

    private void initData() {
        int count = BaseApplication.getSpUtils().getInt("index_star");
        lastupdatetime = BaseApplication.getSpUtils().getLong("lastupdatetime_star");
        if (count != -1) {
            if ((lastupdatetime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {
                lastupdatetime = System.currentTimeMillis();
                BaseApplication.getSpUtils().putLong("lastupdatetime_star", lastupdatetime);
                int n = (int) (System.currentTimeMillis() / (lastupdatetime + (24 * 60 * 60 *
                        1000)));
                count = (count + 20) * n;
            } else {
                indexId = count;
            }
        } else {
            indexId = 90000;
            BaseApplication.getSpUtils().putLong("lastupdatetime_star", System.currentTimeMillis());
            BaseApplication.getSpUtils().putInt("index_star", indexId);
        }

        index = indexId;
        requestData(RequestUrl.getPicStartUrl(indexId), false);
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
        //GsonUtil.changeGsonToList(result,PhotoBean.class);
        List<PhotoBean> data = new Gson().fromJson(result, new TypeToken<List<PhotoBean>>() {
        }.getType());
        if (!isLoadMore) {
            if (isRefresh) {
                isRefresh = false;
                mSwipeRefreshLayout.setRefreshing(false);
                mdata.clear();
                mdata.addAll(data);
                mLoadmoreWrapper.notifyDataSetChanged();
            } else {
                mdata = data;
                mAdapter = new PhotoAdapter(getActivity(), R.layout.list_item_photo_start, mdata);
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
        bundle.putString("videourl", data.mp4_url);
        bundle.putString("title", data.title);
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onRefresh() {
        currentPagte = 1;
        isRefresh = true;
        index = index + 5;
        requestData(RequestUrl.getPicStartUrl(index), false);
        BaseApplication.getSpUtils().putInt("index_star",index);
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
        requestData(RequestUrl.getPicStartUrl(indexId), true);
    }

    private class PhotoAdapter extends CommonAdapter<PhotoBean> {
        private int largeCardHeight, smallCardHeight;

        public PhotoAdapter(Context context, int layoutId, List<PhotoBean> data) {
            super(context, layoutId, data);
            largeCardHeight = (int) context.getResources().getDisplayMetrics().density * 300;
            smallCardHeight = (int) context.getResources().getDisplayMetrics().density * 200;
        }

        @Override
        protected void convert(ViewHolder holder, PhotoBean data, int position) {
            //修改高度，模拟交错效果
            /*CardView cardView = holder.getView(R.id.cardview);
            cardView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;*/

            ImageView iv = holder.getView(R.id.iv_start);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
            iv.setLayoutParams(params);

            Glide.with(getActivity()).load(data.cover).placeholder(new ColorDrawable(Color.parseColor("#eaeaea"))).into(iv);
            holder.setText(R.id.tv_title, data.setname);
        }
    }

}
