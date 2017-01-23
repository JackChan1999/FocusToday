// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ContentFragment$$ViewBinder<T extends com.google.smartcity.ui.fragment.ContentFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558497, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131558497, "field 'mViewPager'");
    view = finder.findRequiredView(source, 2131558769, "field 'mBottomNavigation'");
    target.mBottomNavigation = finder.castView(view, 2131558769, "field 'mBottomNavigation'");
  }

  @Override public void unbind(T target) {
    target.mViewPager = null;
    target.mBottomNavigation = null;
  }
}
