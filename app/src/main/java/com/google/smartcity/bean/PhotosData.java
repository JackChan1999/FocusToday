package com.google.smartcity.bean;

import java.util.ArrayList;
/**
 * ============================================================
 * 
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 
 * 作 者 : 陈冠杰
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2016-3-31 下午8:01:44
 * 
 * 描 述 ：组图数据
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class PhotosData {

	public int retcode;
	public PhotosInfo data;

	public class PhotosInfo {
		public String title;
		public ArrayList<PhotoInfo> news;
	}

	public class PhotoInfo {
		public String id;
		public String listimage;
		public String pubdate;
		public String title;
		public String type;
		public String url;
	}
}
