
package com.jackchan.focustoday.http;
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
public class RequestUrl {

    public static final String host         = "http://c.m.163.com/";
    public static final String NewsUrl = host + "nc/article/headline/";
    public static final String endUrl       = "-20.html";
    public static final String endDetailUrl = "/full.html";

    // 新闻详情
    public static final String NewDetail = host + "nc/article/";
    //评论
    public static final String CommonUrl = host + "nc/article/list/";

    //头条
    public static final String TopId        = "T1348647909107";
    // 足球
    public static final String FootballId   = "T1399700447917";
    // 娱乐
    public static final String AmusementId  = "T1348648517839";
    // 体育
    public static final String SportId      = "T1348649079062";
    // 财经
    public static final String FinanceId    = "T1348648756099";
    // 科技
    public static final String TechnologyId = "T1348649580692";
    // 电影
    public static final String MovieId      = "T1348648650048";
    // 汽车
    public static final String CarId        = "T1348654060988";
    // 笑话
    public static final String FunnyId      = "T1350383429665";
    // 游戏
    public static final String GameId       = "T1348654151579";
    // 时尚
    public static final String FashionId    = "T1348650593803";
    // 情感
    public static final String EmotionId    = "T1348650839000";
    // 精选
    public static final String SelectionId  = "T1370583240249";
    // 电台
    public static final String FMId         = "T1379038288239";
    // nba
    public static final String NBAId        = "T1348649145984";
    // 数码
    public static final String DigitalId    = "T1348649776727";
    // 移动
    public static final String MobileId     = "T1351233117091";
    // 彩票
    public static final String LotteryId    = "T1356600029035";
    // 教育
    public static final String EducationId  = "T1348654225495";
    // 论坛
    public static final String BBSId        = "T1349837670307";
    // 旅游
    public static final String JourneyId    = "T1348654204705";
    // 手机
    public static final String PhoneId      = "T1348649654285";
    // CSDN博客
    public static final String BlogId       = "T1349837698345";
    // 社会
    public static final String SocietyId    = "T1348648037603";
    // 家居
    public static final String HomeId       = "T1348654105308";
    // 暴雪游戏
    public static final String BlizzardId   = "T1397016069906";
    // 亲子
    public static final String FamilyId     = "T1397116135282";
    // CBA
    public static final String CBAId        = "T1348649475931";
    // 消息
    public static final String MsgId        = "T1371543208049";

    // 北京
    public static final String Local = host + "nc/article/local/";

    public static final String BeiJingId  = "5YyX5Lqs";
    // 军事
    public static final String MilitaryId = "T1348648141035";
    // 房产
    public static final String House      = host + "nc/article/house/";
    // 房产id
    public static final String HouseId    = "5YyX5Lqs";


    // 图集
    public static final String PhotoSet       = host + "photo/api/morelist/0096/4GJ60096/";
    // 图集end
    public static final String PhotoSetEnd        = ".json";
    // 热点
    public static final String TuPianReDian   = host + "photo/api/morelist/0096/54GI0096/";
    // 独家
    public static final String TuPianDuJia    = host + "photo/api/morelist/0096/54GJ0096/";
    // 明星
    public static final String TuPianMingXing = host + "photo/api/morelist/0096/54GK0096/";
    // 体坛
    public static final String TuPianTiTan    = host + "photo/api/morelist/0096/54GM0096/";
    // 美图
    public static final String TuPianMeiTu    = host + "photo/api/morelist/0096/54GN0096/";

    public static final String Video          = host + "nc/video/list/";
    public static final String VideoCenter    = "/n/";
    public static final String videoEndUrl    = "-10.html";
    // 热点视频
    public static final String VideoReDianId  = "V9LG4B3A0";
    // 娱乐视频
    public static final String VideoYuLeId    = "V9LG4CHOR";
    // 搞笑视频
    public static final String VideoGaoXiaoId = "V9LG4E6VR";
    // 精品视频
    public static final String VideoJingPinId = "00850FRB";

    // 天气预报url
    public static final String WeatherHost = "http://wthrcdn.etouch.cn/weather_mini?city=";
    // http://v.juhe.cn/weather/index?cityname=

    public static final String WeatherKey = "&key=1734f933d24634331a24aaadc1cb088f";

    /**
     * 新浪图片新闻
     */
    // 精选列表Url.JINGXUAN_ID + index
    public static final String JINGXUAN_ID = "http://api.sina.cn/sinago/list" +
            ".json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from" +
            "=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";

    // 图片详情
    public static final String JINGXUANDETAIL_ID = "http://api.sina.cn/sinago/article" +
            ".json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm" +
            "=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";

    // 趣图列表Url.QUTU_ID + index
    public static final String QUTU_ID = "http://api.sina.cn/sinago/list" +
            ".json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from" +
            "=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid" +
            "=802909da86d9f5fc&p=";

    // 美图列表Url.MEITU_ID + index
    public static final String MEITU_ID = "http://api.sina.cn/sinago/list" +
            ".json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from" +
            "=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid" +
            "=802909da86d9f5fc&p=";

    // 故事列表Url.GUSHI_ID + index
    public static final String GUSHI_ID = "http://api.sina.cn/sinago/list" +
            ".json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from" +
            "=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid" +
            "=802909da86d9f5fc&p=";

    /*新闻url*/
    public static String getTopNewsUrl(int index) {
        return NewsUrl + TopId + "/" + index + endUrl;
    }

    public static String getAmusementNewsUrl(int index) {
        return NewsUrl + AmusementId + "/" + index + endUrl;
    }

    public static String getFootballNewsUrl(int index) {
        return NewsUrl + FootballId + "/" + index + endUrl;
    }

    public static String getSportNewsUrl(int index) {
        return NewsUrl + SportId + "/" + index + endUrl;
    }

    public static String getFinanceNewsUrl(int index) {
        return NewsUrl + FinanceId + "/" + index + endUrl;
    }

    public static String getTechnologyNewsUrl(int index) {
        return NewsUrl + TechnologyId + "/" + index + endUrl;
    }

    public static String getMovieNewsUrl(int index) {
        return NewsUrl + MovieId + "/" + index + endUrl;
    }

    public static String getNBANewsUrl(int index) {
        return NewsUrl + NBAId + "/" + index + endUrl;
    }

    public static String getDigitalNewsUrl(int index) {
        return NewsUrl + DigitalId + "/" + index + endUrl;
    }

    public static String getLotteryNewsUrl(int index) {
        return NewsUrl + LotteryId + "/" + index + endUrl;
    }

    public static String getEducationNewsUrl(int index) {
        return NewsUrl + EducationId + "/" + index + endUrl;
    }

    public static String getBBSNewsUrl(int index) {
        return NewsUrl + BBSId + "/" + index + endUrl;
    }

    public static String getFamilyNewsUrl(int index) {
        return NewsUrl + FamilyId + "/" + index + endUrl;
    }

    public static String getMobileNewsUrl(int index) {
        return NewsUrl + MobileId + "/" + index + endUrl;
    }

    public static String getCBANewsUrl(int index) {
        return NewsUrl + CBAId + "/" + index + endUrl;
    }

    public static String getFunnyNewsUrl(int index) {
        return NewsUrl + FunnyId + "/" + index + endUrl;
    }

    public static String getCarNewsUrl(int index) {
        return NewsUrl + CarId + "/" + index + endUrl;
    }

    public static String getFashionNewsUrl(int index) {
        return NewsUrl + FashionId + "/" + index + endUrl;
    }

    public static String getMilitaryNewsUrl(int index) {
        return NewsUrl + MilitaryId + "/" + index + endUrl;
    }

    public static String getHouseNewsUrl(int index) {
        return House + HouseId + "/" + index + endUrl;
    }

    public static String getGameNewsUrl(int index) {
        return NewsUrl + GameId + "/" + index + endUrl;
    }

    public static String getSelectionNewsUrl(int index) {
        return NewsUrl + SelectionId + "/" + index + endUrl;
    }

    public static String getFMNewsUrl(int index) {
        return NewsUrl + FMId + "/" + index + endUrl;
    }

    public static String getEmotionNewsUrl(int index) {
        return NewsUrl + EmotionId + "/" + index + endUrl;
    }

    public static String getJourneyNewsUrl(int index) {
        return NewsUrl + JourneyId + "/" + index + endUrl;
    }

    public static String getPhoneNewsUrl(int index) {
        return NewsUrl + PhoneId + "/" + index + endUrl;
    }

    public static String getBlogNewsUrl(int index) {
        return NewsUrl + BlogId + "/" + index + endUrl;
    }

    public static String getSocietyNewsUrl(int index) {
        return NewsUrl + SocietyId + "/" + index + endUrl;
    }

    public static String getHomeNewsUrl(int index) {
        return NewsUrl + HomeId + "/" + index + endUrl;
    }

    public static String getBlizzardNewsUrl(int index) {
        return NewsUrl + BlizzardId + "/" + index + endUrl;
    }

    /*图片url*/

    public static String getPicHotUrl(int index) {
        return TuPianReDian + index + PhotoSetEnd;
    }

    public static String getPicExclusiveUrl(int index) {
        return TuPianDuJia + index + PhotoSetEnd;
    }

    public static String getPicStartUrl(int index) {
        return TuPianMingXing + index + PhotoSetEnd;
    }

    public static String getPicSportsUrl(int index) {
        return TuPianTiTan + index + PhotoSetEnd;
    }

    public static String getPicBeautyUrl(int index) {
        return TuPianMeiTu + index + PhotoSetEnd;
    }

    public static String getBeautyUrl() {
        return "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=美女&tag2=全部";
    }

    /*视频url*/

    public static String getVideoHotUrl(int index) {
        return Video + VideoReDianId + VideoCenter + index + videoEndUrl;
    }

    public static String getVideoAmusementUrl(int index) {
        return Video + VideoYuLeId + VideoCenter + index + videoEndUrl;
    }

    public static String getVideoFunnyUrl(int index) {
        return Video + VideoGaoXiaoId + VideoCenter + index + videoEndUrl;
    }

    public static String getJingPinVideoUrl(int index) {
        return Video + VideoJingPinId + VideoCenter + index + videoEndUrl;
    }

    public static String getVideoSelectionUrl(int index) {
        return "http://api.iclient.ifeng.com/ifengvideoList?page=" + index +
                "&gv=5.3.2" +
                "&av=5.3.2" +
                "&uid=866007023266156" +
                "&deviceid=866007023266156" +
                "&proid=ifengnews" +
                "&os=android_22" +
                "&df=androidphone" +
                "&vt=5" +
                "&screen=1152x1920" +
                "&publishid=6103" +
                "&nw=wifi";
    }
}
