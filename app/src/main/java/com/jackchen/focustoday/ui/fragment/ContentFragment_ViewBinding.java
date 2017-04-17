// Generated code from Butter Knife. Do not modify!
package com.jackchen.focustoday.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jackchen.focustoday.R;
import com.jackchen.focustoday.ui.widget.viewpager.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContentFragment_ViewBinding implements Unbinder {
  private ContentFragment target;

  @UiThread
  public ContentFragment_ViewBinding(ContentFragment target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.vp_content, "field 'mViewPager'", NoScrollViewPager.class);
    target.mBottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottom_navigation, "field 'mBottomNavigation'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.mBottomNavigation = null;
  }
}
