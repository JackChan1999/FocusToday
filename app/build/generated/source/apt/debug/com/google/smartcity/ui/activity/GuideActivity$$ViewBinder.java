// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GuideActivity$$ViewBinder<T extends com.google.smartcity.ui.activity.GuideActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558444, "field 'llPointGroup'");
    target.llPointGroup = finder.castView(view, 2131558444, "field 'llPointGroup'");
    view = finder.findRequiredView(source, 2131558496, "field 'view_red_point'");
    target.view_red_point = view;
    view = finder.findRequiredView(source, 2131558498, "field 'vpGuide'");
    target.vpGuide = finder.castView(view, 2131558498, "field 'vpGuide'");
    view = finder.findRequiredView(source, 2131558418, "field 'btn_start'");
    target.btn_start = finder.castView(view, 2131558418, "field 'btn_start'");
  }

  @Override public void unbind(T target) {
    target.llPointGroup = null;
    target.view_red_point = null;
    target.vpGuide = null;
    target.btn_start = null;
  }
}
