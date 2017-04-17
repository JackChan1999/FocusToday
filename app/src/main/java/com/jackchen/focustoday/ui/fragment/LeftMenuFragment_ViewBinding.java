// Generated code from Butter Knife. Do not modify!
package com.jackchen.focustoday.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jackchen.focustoday.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeftMenuFragment_ViewBinding implements Unbinder {
  private LeftMenuFragment target;

  @UiThread
  public LeftMenuFragment_ViewBinding(LeftMenuFragment target, View source) {
    this.target = target;

    target.mListView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'mListView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeftMenuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mListView = null;
  }
}
