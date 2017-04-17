// Generated code from Butter Knife. Do not modify!
package com.jackchen.focustoday.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jackchen.focustoday.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsDetailActivity_ViewBinding implements Unbinder {
  private NewsDetailActivity target;

  @UiThread
  public NewsDetailActivity_ViewBinding(NewsDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewsDetailActivity_ViewBinding(NewsDetailActivity target, View source) {
    this.target = target;

    target.mWebView = Utils.findRequiredViewAsType(source, R.id.wv_web, "field 'mWebView'", WebView.class);
    target.btn_back = Utils.findRequiredViewAsType(source, R.id.btn_back, "field 'btn_back'", ImageButton.class);
    target.btn_share = Utils.findRequiredViewAsType(source, R.id.btn_share, "field 'btn_share'", ImageButton.class);
    target.btn_size = Utils.findRequiredViewAsType(source, R.id.btn_size, "field 'btn_size'", ImageButton.class);
    target.pbProgress = Utils.findRequiredViewAsType(source, R.id.pb_progress, "field 'pbProgress'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewsDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;
    target.btn_back = null;
    target.btn_share = null;
    target.btn_size = null;
    target.pbProgress = null;
  }
}
