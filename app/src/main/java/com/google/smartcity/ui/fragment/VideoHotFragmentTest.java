package com.google.smartcity.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.google.smartcity.R;
import com.google.smartcity.bean.Video;
import com.google.smartcity.http.RequestUrl;
import com.google.smartcity.http.VolleyUtil;
import com.google.smartcity.ui.activity.VideoPlayActivity;
import com.google.smartcity.ui.adapter.recyclerview.CommonAdapter;
import com.google.smartcity.ui.adapter.recyclerview.base.ViewHolder;
import com.google.smartcity.utils.GsonUtil;

import java.util.Collection;
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

public class VideoHotFragmentTest extends Fragment implements OnRefreshListener,
        OnLoadMoreListener,OnItemClickListener{

    private LRecyclerView         mRecyclerView;
    private VideoAdapter          mAdapter;
    private LRecyclerViewAdapter  mRecyclerViewAdapter;
    private List<Video.VideoData> mData;

    private int     index        = 0;
    private int     currentPagte = 1;
    private boolean isRefresh    = false;

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
        mRecyclerView = new LRecyclerView(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        //mRecyclerView.setHasFixedSize(true);
        //((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        //mRecyclerView.setItemAnimator(new RvItemAnimator());
        //mRecyclerView.setRefreshing(true);
        return mRecyclerView;
    }

    private void initData() {
        requestData(index, false);
    }

    private void requestData(int index, final boolean isLoadMore) {
        StringRequest request = new StringRequest(RequestUrl.getVideoHotUrl(index), new Response
                .Listener<String>() {
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
        List<Video.VideoData> data = GsonUtil.changeGsonToBean(result, Video.class).V9LG4B3A0;

        if (!isLoadMore) {
            if (isRefresh) {
                isRefresh = false;
                mRecyclerView.refreshComplete();
                mData.clear();
                mData.addAll(data);
                mAdapter.notifyDataSetChanged();
            } else {
                mData = data;
                mAdapter = new VideoAdapter(getActivity(), R.layout.list_item_video, mData);
                mRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
                mRecyclerViewAdapter.setOnItemClickListener(this);
                mRecyclerView.setOnRefreshListener(this);
                mRecyclerView.setOnLoadMoreListener(this);
            }
        } else {
            int lastIndex = mData.size();
            if (mData.addAll(data)) {
                mAdapter.notifyItemRangeInserted(lastIndex, data.size());
            }
        }
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(List<Video.VideoData> list) {
        mAdapter.addAll(list);
    }

    private void notifyDataSetChanged() {
        mRecyclerViewAdapter.notifyDataSetChanged();
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
    public void onLoadMore() {
        index = index + 20;
        currentPagte++;
        LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
        if (state == LoadingFooter.State.Loading) {
            return;
        }
        RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, 20, LoadingFooter.State.Loading, null);
        requestData(index, true);
    }

    @Override
    public void onRefresh() {
        index = 0;
        currentPagte = 1;
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
        //mAdapter.clear();
        //mRecyclerViewAdapter.notifyDataSetChanged();
        isRefresh = true;
        requestData(index, false);
    }

    @Override
    public void onItemClick(View view, int position) {
        enterVideoActivity(mAdapter.getDataList().get(position));
    }

   /* @Override
    public void onRefresh() {
        StringRequest request = new StringRequest(RequestUrl.getVideoHotUrl(0), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String result) {
                mData = GsonUtil.changeGsonToBean(result, Video.class);
                mLoadmoreWrapper.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        VolleyUtil.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
    }*/

    private class VideoAdapter extends CommonAdapter<Video.VideoData> {

        public VideoAdapter(Context context, int layoutId, List<Video.VideoData> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Video.VideoData videoData, int position) {
            ImageView iv = holder.getView(R.id.iv_video);
            Glide.with(mContext)
                    .load(videoData.cover)
                    .placeholder(new ColorDrawable(Color.parseColor("#eaeaea")))
                    .error(R.mipmap.pic_item_list_default)
                    .crossFade()
                    .into(iv);
            holder.setText(R.id.tv_title, videoData.title);
        }

        public List<Video.VideoData> getDataList() {
            return mDatas;
        }

        public void setDataList(Collection<Video.VideoData> list) {
            mDatas.clear();
            mDatas.addAll(list);
            notifyDataSetChanged();
        }

        public void addAll(Collection<Video.VideoData> list) {
            int lastIndex = mDatas.size();
            if (mDatas.addAll(list)) {
                notifyItemRangeInserted(lastIndex, list.size());
            }
        }

        public void clear() {
            mDatas.clear();
            notifyDataSetChanged();
        }

        public void remove(int position) {
            if (mDatas.size() > 0) {
                mDatas.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

}
