package com.google.smartcity.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.smartcity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-8 下午7:31:18
 * 
 * 描 述 ： 新闻详情页
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class NewsDetailActivity extends Activity implements OnClickListener {
	@Bind(R.id.wv_web)
	WebView mWebView;

	@Bind(R.id.btn_back)
	ImageButton btn_back;

	@Bind(R.id.btn_share)
	ImageButton btn_share;

	@Bind(R.id.btn_size)
	ImageButton btn_size;

	@Bind(R.id.pb_progress)
	ProgressBar pbProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);
		ButterKnife.bind(this);

		btn_back.setOnClickListener(this);
		btn_share.setOnClickListener(this);
		btn_size.setOnClickListener(this);

		String url = getIntent().getStringExtra("url");

		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);// 支持js
		settings.setBuiltInZoomControls(true);// 显示缩小放大按钮
		settings.setUseWideViewPort(true);// 支持双击缩放

		mWebView.setWebViewClient(new WebViewClient() {
			/**
			 * 网页开始加载
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				pbProgress.setVisibility(View.VISIBLE);
			}

			/**
			 * 网页加载结束
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pbProgress.setVisibility(View.GONE);
			}

			/**
			 * 所有的跳转链接都会在此方法中回调
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("跳转url:" + url);
				view.loadUrl(url);
				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});

		// mWebView.goBack();

		mWebView.setWebChromeClient(new WebChromeClient() {
			/**
			 * 进度发生变化
			 */
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				// setTitle("Loading...");
				// setProgress(newProgress * 100);
			}

			/**
			 * 获取网页标题
			 */
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
			}

		});
		mWebView.loadUrl(url);// 加载网页
		//mWebView.loadDataWithBaseURL("file:///sdcard/zhbj/1007", "128.html", "text/html", "utf-8", null);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_share:

			break;
		case R.id.btn_size:
			showChooseDialog();
			break;

		}
	}

	private int mCurrentChooseItem;// 记录当前选中的item，点击确定前
	private int mCurrentItem = 2;// 记录当前选中的item，点击确定后

	/**
	 * 显示字体大小对话框
	 */
	private void showChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String[] items = new String[] { "超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体" };
		builder.setTitle("字体设置").setSingleChoiceItems(items, mCurrentItem, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				mCurrentChooseItem = which;
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				WebSettings settings = mWebView.getSettings();
				switch (mCurrentChooseItem) {
				case 0:
					// settings.setTextZoom(textZoom)
					settings.setTextSize(TextSize.LARGEST);
					break;
				case 1:
					settings.setTextSize(TextSize.LARGER);
					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					settings.setTextSize(TextSize.SMALLEST);
					break;

				}
				mCurrentItem = mCurrentChooseItem;
			}
		}).setNegativeButton("取消", null).show();
	}

}
