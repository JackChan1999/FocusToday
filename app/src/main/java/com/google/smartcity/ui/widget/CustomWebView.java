package com.google.smartcity.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

public class CustomWebView extends WebView
{
    public static interface ScrollInterface
    {

        public abstract void onSChanged(int i, int j, int k, int l);
    }


    public CustomWebView(Context context)
    {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CustomWebView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        Log.e("hhah", (new StringBuilder()).append(i).append(" ").append(j).append(" ").append(k).append(" ").append(l).toString());
        mt.onSChanged(i, j, k, l);
    }

    public void setOnCustomScroolChangeListener(ScrollInterface scrollinterface)
    {
        mt = scrollinterface;
    }

    ScrollInterface mt;
}
