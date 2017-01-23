package com.google.smartcity.ui.adapter.abslistview.base;

import com.google.smartcity.ui.adapter.abslistview.ViewHolder;

public interface ItemViewDelegate<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder holder, T t, int position);



}
