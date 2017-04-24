package com.jackchan.focustoday.http;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/jackchan1999
 * 博客：     http://blog.csdn.net/axi295309066
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
public class HttpFactory
{
  public static String AD;
  public static String AMEND_PW;
  public static String CANCEL_BIND;
  public static String CANCEL_COLLECT;
  public static String CCOMMENT_LIST;
  public static String CHANGE_PHOTO;
  public static String CHANGE_USERNAME;
  public static String COLLECT;
  public static String COLLECT_LIST;
  public static String COLLECT_M;
  public static String COMFORT;
  public static String COMMENT_COMMENT;
  public static String CONFIDENTIAL;
  public static String CONTENT;
  public static String CONTENT2;
  public static String CONTENT3;
  public static String DEC_COMMENT;
  public static String DEMOCRACY_MORE;
  public static String FIND_PW;
  public static String FIRST;
  public static String GUIDE;
  public static String GUIDE_TIME;
  public static String IMG;
  public static String IMGIOS;
  public static String IMGURL;
  public static String LOAD_WEB;
  public static String LOAD_WEB2;
  public static String LOAD_WEB3;
  public static String LOGIN;
  public static String LOGON_CHECK;
  public static String MORE;
  public static String MORE_M;
  public static String MY_COMMENT;
  public static String MY_MSG;
  public static String MY_MSG_MIN;
  public static String MY_PRAISE;
  public static String NEWS_COMMENT;
  public static String NEWS_LIST;
  public static String NEWS_MORE;
  public static String OBTAIN_CITY_CODE;
  public static String OBTAIN_CITY_WEATHER;
  public static String OPINION_BACK;
  public static String POLICY_MORE;
  public static String PRAISE;
  public static String PROTOCOL;
  public static String QUESTION;
  public static String REGISTER;
  public static String REPORT;
  public static String SAVE_LIKE;
  public static String SEACH;
  public static String SEND_DEMOCRACY_COMMENT;
  public static String SEND_NEWS_COMMENT;
  public static String SEVEN;
  public static String SEVEN_M;
  public static String THIRDLOGIN;
  public static String TIME;
  public static String TIME_M;
  public static String TIME_P;
  public static String TODAY;
  public static String TODAY_M;
  public static String UPDATA;
  public static String UPLOAD;
  public static String URL = "http://mmsweb.qianlong.com/api/";
  public static String USER_BIND;

  static
  {
    UPLOAD = "http://mmscms.qianlong.com/SmartBeijingCMS/upload/news/image";
    IMGURL = "http://mmsweb.qianlong.com/api/";
    IMGIOS = "http://mmsimg.qianlong.com/";
    IMG = IMGURL + "images/showImage/";
    LOGIN = URL + "u/login";
    THIRDLOGIN = URL + "u/userBind";
    REGISTER = URL + "u/regist";
    FIND_PW = URL + "u/resetPwd";
    AMEND_PW = URL + "u/modifyPwd";
    FIRST = URL + "news/date/";
    SEVEN = URL + "news/sevenDatesList";
    SEVEN_M = URL + "democracy/sevenDates";
    TODAY = URL + "news/currentDate";
    TODAY_M = URL + "democracy/currentDate";
    MORE = URL + "news/sevenDates/";
    MORE_M = URL + "democracy/sevenDates/";
    OBTAIN_CITY_CODE = URL + "democracy/code/weather?likename=";
    OBTAIN_CITY_WEATHER = URL + "democracy/predict/weather/";
    GUIDE = URL + "policy/indexAll";
    TIME = URL + "news/isNew/";
    TIME_M = URL + "democracy/isNew/";
    TIME_P = URL + "policy/isNew/";
    GUIDE_TIME = URL + "policy/isIndexNew";
    LOAD_WEB = URL + "news/news/";
    LOAD_WEB2 = URL + "democracy/democ_mobile/";
    LOAD_WEB3 = URL + "policy/getDetailJson/";
    CONTENT = URL + "news/news/html/";
    CONTENT2 = URL + "democracy/news/html/";
    CONTENT3 = URL + "policy/getDetailContent/";
    SEACH = URL + "utils/search";
    NEWS_MORE = URL + "utils/search/moreNews";
    DEMOCRACY_MORE = URL + "utils/search/moreDemocracy";
    POLICY_MORE = URL + "utils/search/morePolicy";
    SEND_NEWS_COMMENT = URL + "news/saveComment";
    SEND_DEMOCRACY_COMMENT = URL + "democracy/saveComment";
    NEWS_LIST = URL + "news/news/comment/";
    NEWS_COMMENT = URL + "news/news/comment/";
    DEC_COMMENT = URL + "democracy/news/comment/";
    SAVE_LIKE = URL + "news/saveLikes/";
    COLLECT = URL + "news/saveCollect/";
    COLLECT_M = URL + "democracy/saveCollect/";
    LOGON_CHECK = URL + "u/logon_check";
    COLLECT_LIST = URL + "u/collects/";
    MY_COMMENT = URL + "u/comment/";
    MY_PRAISE = URL + "u/likes/";
    OPINION_BACK = URL + "news/saveFeedback";
    CANCEL_COLLECT = URL + "news/collect/delete/";
    MY_MSG = URL + "news/notice/comment/";
    MY_MSG_MIN = URL + "democracy/notice/comment/";
    PRAISE = URL + "news/notice/likes/";
    USER_BIND = URL + "u/userBind";
    CANCEL_BIND = URL + "u/userUnbind";
    COMMENT_COMMENT = URL + "news/saveComment";
    REPORT = URL + "news/saveWarning";
    CHANGE_USERNAME = URL + "u/modifyNickName";
    CHANGE_PHOTO = URL + "u/modifyAvatar";
    COMFORT = URL + "democracy/predict/weather_index/";
    CCOMMENT_LIST = URL + "news/ccommentList/";
    AD = URL + "news/adv/launch";
    UPDATA = "http://mmscms.qianlong.com/software/version.flag";
    CONFIDENTIAL = "http://mmscms.qianlong.com/declaim/confidential.html";
    PROTOCOL = "http://mmscms.qianlong.com/declaim/protocol.html";
    QUESTION = "http://mmscms.qianlong.com/declaim/q_and_a.html";
  }
}
