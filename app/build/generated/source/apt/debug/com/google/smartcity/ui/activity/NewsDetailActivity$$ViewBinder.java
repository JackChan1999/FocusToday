// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsDetailActivity$$ViewBinder<T extends com.google.smartcity.ui.activity.NewsDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558501, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131558501, "field 'mWebView'");
    view = finder.findRequiredView(source, 2131558410, "field 'btn_back'");
    target.btn_back = finder.castView(view, 2131558410, "field 'btn_back'");
    view = finder.findRequiredView(source, 2131558416, "field 'btn_share'");
    target.btn_share = finder.castView(view, 2131558416, "field 'btn_share'");
    view = finder.findRequiredView(source, 2131558417, "field 'btn_size'");
    target.btn_size = finder.castView(view, 2131558417, "field 'btn_size'");
    view = finder.findRequiredView(source, 2131558457, "field 'pbProgress'");
    target.pbProgress = finder.castView(view, 2131558457, "field 'pbProgress'");
  }

  @Override public void unbind(T target) {
    target.mWebView = null;
    target.btn_back = null;
    target.btn_share = null;
    target.btn_size = null;
    target.pbProgress = null;
  }
}
