// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SplashActivity$$ViewBinder<T extends com.google.smartcity.ui.activity.SplashActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558471, "field 'rl_root'");
    target.rl_root = finder.castView(view, 2131558471, "field 'rl_root'");
  }

  @Override public void unbind(T target) {
    target.rl_root = null;
  }
}
