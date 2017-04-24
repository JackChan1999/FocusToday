package com.jackchan.focustoday.ui.fragment;

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
import com.jackchan.focustoday.R;
import com.jackchan.focustoday.bean.Video;
import com.jackchan.focustoday.http.RequestUrl;
import com.jackchan.focustoday.http.VolleyUtil;
import com.jackchan.focustoday.ui.activity.VideoPlayActivity;
import com.jackchan.focustoday.ui.adapter.recyclerview.CommonAdapter;
import com.jackchan.focustoday.ui.adapter.recyclerview.MultiItemTypeAdapter;
import com.jackchan.focustoday.ui.adapter.recyclerview.base.ViewHolder;
import com.jackchan.focustoday.ui.adapter.recyclerview.wrapper.LoadmoreWrapper;
import com.jackchan.focustoday.utils.GsonUtil;

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

public class VideoHotFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView       mRecyclerView;
    private VideoAdapter       mAdapter;
    private Video              mData;
    private int index        = 0;
    private int currentPagte = 1;
    private LoadmoreWrapper mLoadmoreWrapper;

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
        View rootView = inflater.inflate(R.layout.layout_video_hot, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_video);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setItemAnimator(new RvItemAnimator());
        //((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        return rootView;
    }

    private void initData() {

        StringRequest request = new StringRequest(RequestUrl.getVideoHotUrl(1), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String result) {
                parseData(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        VolleyUtil.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }

    private void parseData(String result) {
        mData = GsonUtil.changeGsonToBean(result, Video.class);
        mAdapter = new VideoAdapter(getActivity(), R.layout.list_item_video, mData.V9LG4B3A0);
        initListener();
        loadMore();
        mRecyclerView.setAdapter(mLoadmoreWrapper);
    }

    public void loadMore() {
        mLoadmoreWrapper = new LoadmoreWrapper(mAdapter);
        mLoadmoreWrapper.setLoadMoreView(R.layout.list_footer_loading);
        mLoadmoreWrapper.setOnLoadMoreListener(new LoadmoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPagte++;
                index = index + 20;
                StringRequest request = new StringRequest(RequestUrl.getVideoHotUrl(index), new
                        Response.Listener<String>() {
                            @Override
                            public void onResponse(String result) {
                                mData.V9LG4B3A0.addAll(GsonUtil.changeGsonToBean(result, Video.class).V9LG4B3A0);
                                mLoadmoreWrapper.notifyDataSetChanged();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                VolleyUtil.getInstance(getActivity().getApplicationContext()).addToRequestQueue
                        (request);
            }
        });
    }

    public void initListener() {
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                enterVideoActivity(mData.V9LG4B3A0.get(position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int
                    position) {
                return false;
            }
        });
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
        StringRequest request = new StringRequest(RequestUrl.getVideoHotUrl(0), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String result) {
                mData.V9LG4B3A0.clear();
                mData.V9LG4B3A0.addAll(GsonUtil.changeGsonToBean(result, Video.class).V9LG4B3A0);
                mLoadmoreWrapper.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        VolleyUtil.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }

    private class VideoAdapter extends CommonAdapter<Video.VideoData> {

        public VideoAdapter(Context context, int layoutId, List<Video.VideoData> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Video.VideoData videoData, int position) {
            ImageView iv = holder.getView(R.id.iv_video);
            Glide.with(mContext).load(videoData.cover).into(iv);
            holder.setText(R.id.tv_title, videoData.title);
        }
    }

}
