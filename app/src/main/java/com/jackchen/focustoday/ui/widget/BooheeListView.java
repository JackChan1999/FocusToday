package com.jackchen.focustoday.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
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
public class BooheeListView extends ListView implements AbsListView.OnScrollListener {
    private boolean isLastStatus;
    private OnLoadMoreListener mListener;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public BooheeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount != totalItemCount || totalItemCount <= 0) {
            this.isLastStatus = false;
            return;
        }
        loadMore();
        this.isLastStatus = true;
    }

    private void loadMore() {
        if (this.mListener != null && !this.isLastStatus) {
            this.mListener.onLoadMore();
        }
    }
}