// Generated code from Butter Knife. Do not modify!
package com.jackchen.focustoday.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jackchen.focustoday.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GuideActivity_ViewBinding implements Unbinder {
  private GuideActivity target;

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target, View source) {
    this.target = target;

    target.llPointGroup = Utils.findRequiredViewAsType(source, R.id.ll_point_guide, "field 'llPointGroup'", LinearLayout.class);
    target.view_red_point = Utils.findRequiredView(source, R.id.view_red_point, "field 'view_red_point'");
    target.vpGuide = Utils.findRequiredViewAsType(source, R.id.vp_guide, "field 'vpGuide'", ViewPager.class);
    target.btn_start = Utils.findRequiredViewAsType(source, R.id.btn_start, "field 'btn_start'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GuideActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llPointGroup = null;
    target.view_red_point = null;
    target.vpGuide = null;
    target.btn_start = null;
  }
}
