package com.jackchen.focustoday.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.jackchen.focustoday.R;
import com.jackchen.focustoday.bean.NewsData;
import com.jackchen.focustoday.bean.TopNews;
import com.jackchen.focustoday.http.RequestUrl;
import com.jackchen.focustoday.http.VolleyUtil;
import com.jackchen.focustoday.ui.activity.NewsDetailActivity;
import com.jackchen.focustoday.ui.adapter.BasePagerAdapter;
import com.jackchen.focustoday.ui.widget.pageindicator.CirclePageIndicator;
import com.jackchen.focustoday.ui.adapter.recyclerview.MultiItemTypeAdapter;
import com.jackchen.focustoday.ui.adapter.recyclerview.base.ItemViewDelegate;
import com.jackchen.focustoday.ui.adapter.recyclerview.base.ViewHolder;
import com.jackchen.focustoday.ui.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.jackchen.focustoday.ui.adapter.recyclerview.wrapper.LoadmoreWrapper;
import com.jackchen.focustoday.utils.GlideUtils;
import com.jackchen.focustoday.utils.GsonUtil;

import java.util.ArrayList;
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
public class TabDetailPager extends BaseMenuDetailPager implements LoadmoreWrapper
        .OnLoadMoreListener, ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView        mRecyclerView;
    private SwipeRefreshLayout  mRefreshLayout;
    private TextView            tvTitle;
    private CirclePageIndicator mIndicator;
    private ViewPager           mViewPager;
    private View                mHeaderView;
    private View    mLoadMoreView;

    private LoadmoreWrapper mLoadmoreWrapper;
    private TopNewsAdapter mTopNewsAdapter;

    private List<NewsData> mNewsData = new ArrayList<>();

    private boolean isRefresh;
    private int index = 0;

    private Handler mHandler;
    private MultiItemTypeAdapter<NewsData> mMultiItemTypeAdapter;

    public TabDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View initView() {
        View rootView = inflate(R.layout.layout_news, null, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.pull_to_refresh);
        mRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(manager);
        initHeaderView();
        return rootView;
    }

    public void initHeaderView(){
        mHeaderView = inflate(R.layout.list_header_topnews, mRecyclerView, false);
        tvTitle = (TextView) mHeaderView.findViewById(R.id.tv_title);
        mIndicator = (CirclePageIndicator) mHeaderView.findViewById(R.id.indicator);
        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.vp_news);
    }

    @Override
    public void initData() {
        requestData(RequestUrl.getTopNewsUrl(index), false);
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

        VolleyUtil.getInstance(mActivity.getApplicationContext()).addToRequestQueue(request);
    }

    private void parseData(String result, boolean isLoadMore) {
        List<NewsData> data = GsonUtil.changeGsonToBean(result, TopNews.class).T1348647909107;
        if (!isLoadMore) {
            if (isRefresh) {
                isRefresh = false;
                mRefreshLayout.setRefreshing(false);
                mNewsData.clear();
                mNewsData.addAll(data);
                mLoadmoreWrapper.notifyDataSetChanged();
            } else {
                mNewsData = data;
                setRecyclerViewAdapter();
                setViewPagerAdapter();
            }
        } else {
            int lastIndex = mNewsData.size();
            ArrayList<NewsData> oldTemp = new ArrayList<>(mNewsData);
            if (mNewsData.addAll(data)) {
                /*for (TopNews.NewsData news: mNewsData) {
                    System.out.println("docid  " + news.docid + "  title  " + news.title);
                }*/
                //mLoadmoreWrapper.notifyItemRangeInserted(lastIndex + 1,data.size());
                //mLoadmoreWrapper.notifyDataSetChanged();
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(oldTemp, mNewsData),true);
                diffResult.dispatchUpdatesTo(mLoadmoreWrapper);
            } else {
                ProgressBar loading_progress = (ProgressBar) mLoadMoreView.findViewById(R.id.loading_progress);
                TextView loading_text = (TextView) mLoadMoreView.findViewById(R.id.loading_text);
                loading_progress.setVisibility(View.GONE);
                loading_text.setText("没有过多数据了");
            }
        }

        /*MultiItemTypeAdapter<TabAmusementNews.NewsData> multiItemTypeAdapter =
                new MultiItemTypeAdapter<TabAmusementNews.NewsData>(mActivity, mNewsDatas);

        multiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<TabAmusementNews.NewsData>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_image;
            }

            @Override
            public boolean isForViewType(TabAmusementNews.NewsData item, int position) {
                return item.imgextra != null;
            }

            @Override
            public void convert(ViewHolder holder, TabAmusementNews.NewsData newsData, int position) {

                ImageView iv1 = holder.getView(R.id.imageView1);
                ImageView iv2 = holder.getView(R.id.imageView2);
                ImageView iv3 = holder.getView(R.id.imageView3);

                Glide.with(mActivity).load(newsData.imgsrc).into(iv1);
                Glide.with(mActivity).load(newsData.imgextra.get(0).imgsrc).into(iv2);
                Glide.with(mActivity).load(newsData.imgextra.get(1).imgsrc).into(iv3);

                holder.setText(R.id.title, newsData.title);
            }
        });*/



        /*multiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<TabAmusementNews.NewsData>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_newslist;
            }

            @Override
            public boolean isForViewType(TabAmusementNews.NewsData item, int position) {
                return item.imgextra == null;
            }

            @Override
            public void convert(ViewHolder holder, TabAmusementNews.NewsData newsData, int position) {
                ImageView iv = holder.getView(R.id.iv_pic);

                Glide.with(mActivity).load(newsData.imgsrc).into(iv);
                holder.setText(R.id.title, newsData.title);
                holder.setText(R.id.digest, newsData.digest);
            }
        });*/

    }

    private void setViewPagerAdapter() {
        mTopNewsAdapter = new TopNewsAdapter(mNewsData.get(0).ads);
        mViewPager.setAdapter(mTopNewsAdapter);
        //mViewPager.setPageTransformer(true, new StackTransformer());
        //setScrollDuration();
        mIndicator.setViewPager(mViewPager);
        mIndicator.setSnap(true);
        mIndicator.setOnPageChangeListener(this);
        mIndicator.onPageSelected(0);
        tvTitle.setText(mNewsData.get(0).ads.get(0).title);

        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    int currentItem = mViewPager.getCurrentItem();
                    if (currentItem < mNewsData.get(0).ads.size() - 1) {
                        currentItem++;
                    } else {
                        currentItem = 0;
                    }

                    mViewPager.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            };
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    }

    public void setRecyclerViewAdapter() {
        mMultiItemTypeAdapter = new
                MultiItemTypeAdapter<NewsData>(mActivity, mNewsData) {
                   /* @Override
                    protected boolean areItemsTheSame(TopNews.NewsData oldItem, TopNews.NewsData newItem) {
                        return oldItem.;
                    }

                    @Override
                    protected boolean areContentsTheSame(TopNews.NewsData oldItem, TopNews.NewsData newItem) {
                        return false;
                    }*/
                };

        mMultiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<NewsData>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_image;
            }

            @Override
            public boolean isForViewType(NewsData item, int position) {
                return item.imgextra != null;
            }

            @Override
            public void convert(ViewHolder holder, NewsData newsData, int position) {

                ImageView iv1 = holder.getView(R.id.imageView1);
                ImageView iv2 = holder.getView(R.id.imageView2);
                ImageView iv3 = holder.getView(R.id.imageView3);
                GlideUtils.display(mActivity, newsData.imgsrc, iv1);
                GlideUtils.display(mActivity, newsData.imgextra.get(0).imgsrc, iv2);
                GlideUtils.display(mActivity, newsData.imgextra.get(1).imgsrc, iv3);

                holder.setText(R.id.title, newsData.title);
            }
        });

        mMultiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<NewsData>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_newslist;
            }

            @Override
            public boolean isForViewType(NewsData item, int position) {
                return item.imgextra == null;
            }

            @Override
            public void convert(ViewHolder holder, NewsData newsData, int position) {
                ImageView iv = holder.getView(R.id.iv_pic);
                GlideUtils.display(mActivity, newsData.imgsrc, iv);

                holder.setText(R.id.title, newsData.title);
                holder.setText(R.id.tv_source, newsData.source);
                holder.setText(R.id.tv_replyCount, newsData.replyCount + "跟帖");
            }
        });

        mMultiItemTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                enterNewsDetailActivity(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int
                    position) {
                return false;
            }
        });

        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper
                (mMultiItemTypeAdapter);
        headerAndFooterWrapper.addHeaderView(mHeaderView);

        mLoadmoreWrapper = new LoadmoreWrapper(headerAndFooterWrapper);
        mLoadMoreView = inflate(R.layout.list_footer_loading, mRecyclerView, false);
        mLoadmoreWrapper.setLoadMoreView(mLoadMoreView);

        mLoadmoreWrapper.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mLoadmoreWrapper);
    }

    public void enterNewsDetailActivity(int position) {
        Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();

        String ids = BaseApplication.getSpUtils().getString("read_ids", "");

        // 跳转新闻详情页
        Intent intent = new Intent();
        intent.setClass(mActivity, NewsDetailActivity.class);
        intent.putExtra("url", mNewsData.get(position).url_3w);
        mActivity.startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        index += 20;
        requestData(RequestUrl.getTopNewsUrl(index), true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        NewsData.Ads topNewsData = mNewsData.get(0).ads.get(position);
        tvTitle.setText(topNewsData.title);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        index = 0;
        requestData(RequestUrl.getTopNewsUrl(index), false);
    }

    /**
     * 头条新闻适配器
     */

    class TopNewsAdapter extends BasePagerAdapter<NewsData.Ads> {

        public TopNewsAdapter(List<NewsData.Ads> data) {
            super(data);
        }

        @Override
        public View getView(final int position) {
            ImageView image = new ImageView(mActivity);
            image.setScaleType(ImageView.ScaleType.FIT_XY);

            // 设置点击监听
            image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 跳转新闻详情页
                    /*Intent intent = new Intent();
                    intent.setClass(mActivity, NewsDetailActivity.class);
                    intent.putExtra("url", mTopNewsList.get(position).url);
                    mActivity.startActivity(intent);*/
                }
            });
            // 设置触摸监听
            image.setOnTouchListener(new TopNewsTouchListener());
            NewsData.Ads topNewsData = mData.get(position);
            Glide.with(mActivity).load(topNewsData.imgsrc).into(image);
            return image;
        }
    }

    private class TopNewsTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mHandler.removeCallbacksAndMessages(null);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                    break;
                case MotionEvent.ACTION_UP:
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                    break;
            }
            return true;
        }
    }

    public class DiffCallBack extends DiffUtil.Callback {
        private List<NewsData> mOldDatas, mNewDatas;

        public DiffCallBack(List<NewsData> mOldDatas, List<NewsData> mNewDatas) {
            this.mOldDatas = mOldDatas;
            this.mNewDatas = mNewDatas;
        }

        @Override
        public int getOldListSize() {
            return mOldDatas != null ? mOldDatas.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewDatas != null ? mNewDatas.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldDatas.get(oldItemPosition).docid.equals(mNewDatas.get(newItemPosition).docid);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            NewsData beanOld = mOldDatas.get(oldItemPosition);
            NewsData beanNew = mNewDatas.get(newItemPosition);
            if (!beanOld.title.equals(beanNew.title)) {
                return false;
            }
            if (!beanOld.imgsrc.equals(beanNew.imgsrc)) {
                return false;
            }
            return true;
        }
    }
}
