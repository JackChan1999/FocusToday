package com.jackchen.focustoday.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.jackchen.focustoday.R;
import com.jackchen.focustoday.bean.VideoSelection;
import com.jackchen.focustoday.http.RequestUrl;
import com.jackchen.focustoday.http.VolleyUtil;
import com.jackchen.focustoday.ui.activity.VideoPlayActivity;
import com.jackchen.focustoday.ui.adapter.recyclerview.CommonAdapter;
import com.jackchen.focustoday.ui.adapter.recyclerview.MultiItemTypeAdapter;
import com.jackchen.focustoday.ui.adapter.recyclerview.base.ViewHolder;
import com.jackchen.focustoday.ui.adapter.recyclerview.wrapper.LoadmoreWrapper;

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

public class VideoSelectionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MultiItemTypeAdapter.OnItemClickListener, LoadmoreWrapper.OnLoadMoreListener {

    private RecyclerView       mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LoadmoreWrapper    mLoadmoreWrapper;

    private VideoAdapter                   mAdapter;
    private List<VideoSelection.VideoData> mData;
    private int     page      = 1;
    private boolean isRefresh = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);

        mSwipeRefreshLayout = new SwipeRefreshLayout(getActivity());
        mSwipeRefreshLayout.addView(mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mSwipeRefreshLayout;

        /*View rootView = inflater.inflate(R.layout.layout_video_hot, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_video);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;*/
    }

    private void initData() {

        /*StringRequest request = new StringRequest(RequestUrl.getVideoSelectionUrl(page), new
        Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                parseData(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        VolleyUtil.getInstance(getActivity()).getQueue().add(request);*/

        requestData(RequestUrl.getVideoSelectionUrl(page), false);
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

    /*private void parseData(String result, boolean isLoadMore) {
        final Gson gson = new Gson();
        List<VideoSelection> data = gson.fromJson(result, new TypeToken<List<VideoSelection>>(){}
        .getType());
        mData = data.get(0).item;
        mAdapter = new VideoAdapter(getActivity(), R.layout.list_item_video_selection, mData);
        initListener();

        final LoadmoreWrapper loadmoreWrapper = new LoadmoreWrapper(mAdapter);
        loadmoreWrapper.setLoadMoreView(R.layout.list_footer_loading);
        loadmoreWrapper.setOnLoadMoreListener(new LoadmoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                StringRequest request = new StringRequest(RequestUrl.getVideoSelectionUrl(page),
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        System.out.println(result);
                        List<VideoSelection> data = new Gson().fromJson(result.trim(), new
                                TypeToken<List<VideoSelection>>() {
                        }.getType());
                        mData.addAll(data.get(0).item);
                        loadmoreWrapper.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

                VolleyUtil.getInstance(getActivity()).getQueue().add(request);
            }
        });

        mRecyclerView.setAdapter(loadmoreWrapper);
    }*/

    private void parseData(String result, boolean isLoadMore) {
        //GsonUtil.changeGsonToList(result,PhotoBean.class);
        List<VideoSelection> data = new Gson().fromJson(result, new
                TypeToken<List<VideoSelection>>() {}.getType());
        if (!isLoadMore) {
            if (isRefresh) {
                isRefresh = false;
                mSwipeRefreshLayout.setRefreshing(false);
                mData.clear();
                mData.addAll(data.get(0).item);
                mLoadmoreWrapper.notifyDataSetChanged();
            } else {
                mData = data.get(0).item;
                mAdapter = new VideoAdapter(getActivity(), R.layout.list_item_video_selection,
                        mData);
                mAdapter.setOnItemClickListener(this);
                mLoadmoreWrapper = new LoadmoreWrapper(mAdapter);
                mLoadmoreWrapper.setOnLoadMoreListener(this);
                mLoadmoreWrapper.setLoadMoreView(R.layout.list_footer_loading);
                mRecyclerView.setAdapter(mLoadmoreWrapper);
            }
        } else {
            int lastIndex = mData.size();
            if (mData.addAll(data.get(0).item)){
                System.out.println("notify: " + lastIndex + " " + data.get(0).item.size());
                mLoadmoreWrapper.notifyItemRangeInserted(lastIndex,data.get(0).item.size());
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


    public void enterVideoActivity(VideoSelection.VideoData data) {
        Bundle bundle = new Bundle();
        bundle.putString("videourl", data.video_url.replace("https","http"));
        bundle.putString("title", data.title);
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        requestData(RequestUrl.getVideoSelectionUrl(page), false);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        enterVideoActivity(mData.get(position));
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        requestData(RequestUrl.getVideoSelectionUrl(page), true);
    }

    private class VideoAdapter extends CommonAdapter<VideoSelection.VideoData> {

        public VideoAdapter(Context context, int layoutId, List<VideoSelection.VideoData> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, VideoSelection.VideoData videoData, int position) {
            ImageView iv = holder.getView(R.id.iv_video);
            Glide.with(mContext).load(videoData.image).into(iv);
            holder.setText(R.id.tv_title, videoData.title);
        }
    }

}
