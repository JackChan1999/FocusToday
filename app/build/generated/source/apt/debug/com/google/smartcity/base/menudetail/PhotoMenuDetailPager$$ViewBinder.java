// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.base.menudetail;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PhotoMenuDetailPager$$ViewBinder<T extends com.google.smartcity.base.menudetail.PhotoMenuDetailPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558467, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131558467, "field 'mRecyclerView'");
  }

  @Override public void unbind(T target) {
    target.mRecyclerView = null;
  }
}
