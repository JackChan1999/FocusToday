// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.base.menudetail;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsMenuDetailPager$$ViewBinder<T extends com.google.smartcity.base.menudetail.NewsMenuDetailPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558499, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131558499, "field 'mViewPager'");
    view = finder.findRequiredView(source, 2131558477, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131558477, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131558413, "method 'nextPage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.nextPage(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mViewPager = null;
    target.mTabLayout = null;
  }
}
