package com.google.smartcity.http;

import com.google.smartcity.utils.LogUtils;

/**
 * ============================================================
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 作 者 : 陈冠杰
 * 版 本 ： 1.0
 * 创建日期 ：2016/3/20 17:01
 * 描 述 ：URL
 * 修订历史 ：
 * ============================================================
 **/
public class Constants {

	public static final int	DEBUGLEVEL		= LogUtils.LEVEL_ALL;
	public static final int	PAGESIZE		= 20;
	public static final int	PROTOCOLTIMEOUT	= 30*60*1000;

	public static final class URLS {
		public static final String SERVER_URL = "http://127.0.0.1:8090/zhbj";
		// 获取分类信息的接口
		public static final String CATEGORIES_URL = SERVER_URL + "?name=category";
		public static final String NEWS_URL = SERVER_URL + "?name=";
		public static final String IMAGEBASEURL = SERVER_URL + "?name=";
		// 获取组图信息的接口
		public static final String PHOTOS_URL = SERVER_URL + "/photos/photos_1.json";

		public static final String host = "http://c.m.163.com/";
		public static final String endUrl = "-20.html";
		public static final String endDetailUrl = "/full.html";
	}

	/**新闻*/
	public static final class NEWS {
		// 头条Url.NewsUrl + RequestUrl.TopId + "/" + index + RequestUrl.endUrl;
		public static final String TopUrl = URLS.host + "nc/article/headline/";
		public static final String TopId = "T1348647909107";
		// 新闻详情
		public static final String NewDetail = URLS.host + "nc/article/";
		//评论
		public static final String CommonUrl = URLS.host + "nc/article/list/";
		// 足球
		public static final String FootId = "T1399700447917";
		// 娱乐
		public static final String YuLeId = "T1348648517839";
		// 体育
		public static final String TiYuId = "T1348649079062";
		// 财经
		public static final String CaiJingId = "T1348648756099";
		// 科技
		public static final String KeJiId = "T1348649580692";
		// 电影
		public static final String DianYingId = "T1348648650048";
		// 汽车
		public static final String QiChiId = "T1348654060988";
		// 笑话
		public static final String XiaoHuaId = "T1350383429665";
		// 笑话
		public static final String YouXiId = "T1348654151579";
		// 时尚
		public static final String ShiShangId = "T1348650593803";
		// 情感
		public static final String QingGanId = "T1348650839000";
		// 精选
		public static final String JingXuanId = "T1370583240249";
		// 电台
		public static final String DianTaiId = "T1379038288239";
		// nba
		public static final String NBAId = "T1348649145984";
		// 数码
		public static final String ShuMaId = "T1348649776727";
		// 数码
		public static final String YiDongId = "T1351233117091";
		// 彩票
		public static final String CaiPiaoId = "T1356600029035";
		// 教育
		public static final String JiaoYuId = "T1348654225495";
		// 论坛
		public static final String LunTanId = "T1349837670307";
		// 旅游
		public static final String LvYouId = "T1348654204705";
		// 手机
		public static final String ShouJiId = "T1348649654285";
		// 博客
		public static final String BoKeId = "T1349837698345";
		// 社会
		public static final String SheHuiId = "T1348648037603";
		// 家居
		public static final String JiaJuId = "T1348654105308";
		// 暴雪游戏
		public static final String BaoXueId = "T1397016069906";
		// 亲子
		public static final String QinZiId = "T1397116135282";
		// CBA
		public static final String CBAId = "T1348649475931";
		// 消息
		public static final String MsgId = "T1371543208049";

		// 北京
		public static final String Local = URLS.host + "nc/article/local/";

		public static final String BeiJingId = "5YyX5Lqs";
		// 军事
		public static final String JunShiId = "T1348648141035";
		// 房产
		public static final String FangChan = URLS.host + "nc/article/house/";
		// 房产id
		public static final String FangChanId = "5YyX5Lqs";
		// 图集
		public static final String TuJi = URLS.host + "photo/api/morelist/0096/4GJ60096/";// 42358.json
		// 图集end
		public static final String TuJiEnd = ".json";
		// 热点42577
		public static final String TuPianReDian = URLS.host + "photo/api/morelist/0096/54GI0096/";
		// 独家42010
		public static final String TuPianDuJia = URLS.host + "photo/api/morelist/0096/54GJ0096/";
		// 明星 42599.json
		public static final String TuPianMingXing = URLS.host + "photo/api/morelist/0096/54GK0096/";
		// 明星 42262.json
		public static final String TuPianTiTan = URLS.host + "photo/api/morelist/0096/54GM0096/";
		// 美图 39683.json    RequestUrl.TuPianMeiTu + index + RequestUrl.TuJiEnd;
		public static final String TuPianMeiTu = URLS.host + "photo/api/morelist/0096/54GN0096/";
	}

	/**视频*/
	public static final class VIDEO {
		// 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
		// RequestUrl.Video + videoId + RequestUrl.VideoCenter + index + RequestUrl.videoEndUrl
		public static final String Video = URLS.host + "nc/video/list/";
		public static final String VideoCenter = "/n/";
		public static final String videoEndUrl = "-10.html";
		// 热点视频
		public static final String VideoReDianId = "V9LG4B3A0";
		// 娱乐视频
		public static final String VideoYuLeId = "V9LG4CHOR";
		// 搞笑视频
		public static final String VideoGaoXiaoId = "V9LG4E6VR";
		// 精品视频
		public static final String VideoJingPinId = "00850FRB";
	}

	/**天气预报*/
	public static final class WEATHER {
		public static final String WeatherHost = "http://wthrcdn.etouch.cn/weather_mini?city=";
		// http://v.juhe.cn/weather/index?cityname=
		public static final String WeatherKey = "&key=1734f933d24634331a24aaadc1cb088f";
	}

	/**图片新闻*/
	public static final class PICTURE{
		// 精选列表Url.JINGXUAN_ID + index
		public static final String JINGXUAN_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";
		// 图片详情
		public static final String JINGXUANDETAIL_ID = "http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";
		// 趣图列表Url.QUTU_ID + index
		public static final String QUTU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
		// 美图列表Url.MEITU_ID + index
		public static final String MEITU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
		// 故事列表Url.GUSHI_ID + index
		public static final String GUSHI_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
	}

}
