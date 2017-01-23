// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.base;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BasePager$$ViewBinder<T extends com.google.smartcity.base.BasePager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558490, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131558490, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131558428, "field 'flContent'");
    target.flContent = finder.castView(view, 2131558428, "field 'flContent'");
    view = finder.findRequiredView(source, 2131558412, "field 'btnMenu'");
    target.btnMenu = finder.castView(view, 2131558412, "field 'btnMenu'");
    view = finder.findRequiredView(source, 2131558415, "field 'btnSearch'");
    target.btnSearch = finder.castView(view, 2131558415, "field 'btnSearch'");
    view = finder.findRequiredView(source, 2131558414, "field 'btnPhoto'");
    target.btnPhoto = finder.castView(view, 2131558414, "field 'btnPhoto'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.flContent = null;
    target.btnMenu = null;
    target.btnSearch = null;
    target.btnPhoto = null;
  }
}
