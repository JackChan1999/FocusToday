// Generated code from Butter Knife. Do not modify!
package com.jackchen.focustoday.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jackchen.focustoday.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestActivity_ViewBinding implements Unbinder {
  private TestActivity target;

  @UiThread
  public TestActivity_ViewBinding(TestActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestActivity_ViewBinding(TestActivity target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.vp_content, "field 'mViewPager'", ViewPager.class);
    target.mBottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottom_navigation, "field 'mBottomNavigation'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.mBottomNavigation = null;
  }
}
