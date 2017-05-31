package com.jackchan.focustoday.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.jackchan.focustoday.http.HttpFactory;
import com.jackchan.focustoday.ui.widget.CustomWebView;
import com.jackchan.focustoday.utils.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * CSDN博客： http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchan.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class DetailWebViewActivity extends Activity {

	private Context mContext;

	private String who;// 新闻详情页是哪一种类型
	private String nid;
	private String address ;
	private String urls ;//当type=4的时候，webview直接加载url即可
	
	private CustomWebView webView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;
	}

	/**
	 * 将返回的json字符串以nid为文件名保存到sd卡
	 * 
	 * @param filename
	 *            nid.txt--文件名
	 * @param json
	 *            返回的json字符串
	 */
	public void saveToSDCard(String filename, String json) {
		File file;
		// 从sp中取值record
		if (TextUtils.isEmpty(SPUtils.getString(mContext,
				"record", null))) {

			SPUtils.saveString(mContext, "record", filename);
		} else {
			// 将文件名保存到sp（追加）
			SPUtils.saveString(
					mContext,
					"record",
					(new StringBuilder(String.valueOf(SPUtils
							.getString(mContext, "record", null))).append(",")
							.append(filename).toString()));
		}
		// MyApplication.getInstance().saveValue("record", (new
		// StringBuilder(String.valueOf(MyApplication.getInstance().getStrValue("record")))).append(",").append(filename).toString());
		// /data/data/files
		file = new File(mContext.getFilesDir(), filename);
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			fileoutputstream.write(json.getBytes());
			fileoutputstream.close();
			return;
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	public void load() {
		String urlWeb = "";
		String urlContent = "";
		if (who.equals("1")) {
			urlWeb = HttpFactory.LOAD_WEB;
			urlContent = HttpFactory.CONTENT;
		}
		if (who.equals("2")) {
			urlWeb = HttpFactory.LOAD_WEB2;
			urlContent = HttpFactory.CONTENT2;
		}
		if (who.equals("3")) {
			urlWeb = HttpFactory.LOAD_WEB3;
			urlContent = HttpFactory.CONTENT3;
		}

		// url+nid
		/*HttpUtils.loadSearchNewsData(HttpMethod.GET, urlWeb + nid, null,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}
				});*/
	}
	
	/**
	 * 根据type去加载网页
	 * @param webview
	 * @param type
	 */
	 private void init(CustomWebView webview, String type)
	    {
	       // loading.setVisibility(8);
	        boolean flag = type.equals("3");
	        String url = null;
	        if(flag)
	        	url = "file:///android_asset/zhuanti.html";
	        if(type.equals("0"))
	        	url = "file:///android_asset/tuwen.html";
	        if(type.equals("2"))
	        	url = "file:///android_asset/tuwen.html";
	        if(type.equals("1"))
	        	url = "file:///android_asset/groupimg.html";
	        if(type.equals("web"))
	        	url = "file:///android_asset/policy.html";
	        if(type.equals("immigration"))
	        	url = "file:///android_asset/immigration.html";
	        if(type.equals("idcard"))
	        	url = "file:///android_asset/idcard.html";
	        if(type.equals("marriage"))
	        	url = "file:///android_asset/marriage.html";
	        if(type.equals("transport"))
	        	url = "file:///android_asset/transport.html";
	        if(type.equals("out"))
	        	url = address;
	        if(type.equals("4"))
	        	url = urls;
	        webview.loadUrl(url);//加载本地网页
	    }

}
