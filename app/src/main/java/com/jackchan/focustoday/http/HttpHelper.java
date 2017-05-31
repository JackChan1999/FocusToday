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
public class HttpHelper {
    /**头条*/
    public static String getNewsUrl(String index){
        return Constants.NEWS.TopUrl + Constants.NEWS.TopId + "/" + index + Constants.URLS.endUrl;
    }
    /**评论*/
    public static String getCommonUrl(String index, String itemId) {
        return Constants.NEWS.CommonUrl + itemId + "/" + index + Constants.URLS.endUrl;
    }

    public static String getLocalUrl(String index, String itemId) {
        return Constants.NEWS.Local + itemId + "/" + index + Constants.URLS.endUrl;
    }
    /**房产*/
    public static String getFangUrl(String index, String itemId) {
        return Constants.NEWS.FangChan + itemId + "/" + index + Constants.URLS.endUrl;
    }
    /**图集*/
    public static String getPhotosUrl(String index) {
        return Constants.NEWS.TuJi + index + Constants.NEWS.TuJiEnd;
    }
    /**图集--精选*/
    public static String getReDianPicsUrl(String index) {
        return Constants.NEWS.TuPianReDian + index + Constants.NEWS.TuJiEnd;
    }
    /**独家*/
    public static String getDuJiaPicsUrl(String index) {
        return Constants.NEWS.TuPianDuJia + index + Constants.NEWS.TuJiEnd;
    }
    /**明星*/
    public static String getMingXingPicsUrl(String index) {
        return Constants.NEWS.TuPianMingXing + index + Constants.NEWS.TuJiEnd;
    }
    /**体坛*/
    public static String getTiTanPicsUrl(String index) {
        return Constants.NEWS.TuPianTiTan + index + Constants.NEWS.TuJiEnd;
    }
    /**美图*/
    public static String getMeiTuPicsUrl(String index) {
        return Constants.NEWS.TuPianMeiTu + index + Constants.NEWS.TuJiEnd;
    }

    /**新浪图片新闻--精选*/
    public static String getSinaJingXuan(String index) {
        return Constants.PICTURE.JINGXUAN_ID + index;
    }
    /**新浪图片新闻--趣图*/
    public static String getSinaQuTu(String index) {
        return Constants.PICTURE.QUTU_ID + index;
    }
    /**新浪图片新闻--美图*/
    public static String getSinaMeiTu(String index) {
        return Constants.PICTURE.MEITU_ID + index;
    }
    /**新浪图片新闻--故事*/
    public static String getSinaGuShi(String index) {
        return Constants.PICTURE.GUSHI_ID + index;
    }
    /**新浪图片新闻--视频*/
    public static String getVideoUrl(String index, String videoId) {
        return Constants.VIDEO.Video + videoId + Constants.VIDEO.VideoCenter +
                index + Constants.VIDEO.videoEndUrl;
    }
}
