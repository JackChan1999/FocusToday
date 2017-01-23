// Generated code from Butter Knife. Do not modify!
package com.google.smartcity.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LeftMenuFragment$$ViewBinder<T extends com.google.smartcity.ui.fragment.LeftMenuFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558441, "field 'mListView'");
    target.mListView = finder.castView(view, 2131558441, "field 'mListView'");
  }

  @Override public void unbind(T target) {
    target.mListView = null;
  }
}
