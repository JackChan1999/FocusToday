package com.jackchan.focustoday.bean;

import java.util.List;

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
public class NewsData {
    public String         template;
    public List<Imgextra> imgextra;
    public String         skipID;
    public String         lmodify;
    public String         postid;
    public String         source;
    public String         title;
    public int            hasImg;
    public String         digest;
    public String         boardid;
    public String         photosetID;
    public String         alias;
    public int            hasAD;
    public String         imgsrc;
    public String         ptime;
    public int            hasHead;
    public int            order;
    public int            votecount;
    public boolean        hasCover;
    public String         docid;
    public String         tname;
    public int            priority;
    public List<Ads>      ads;
    public int            replyCount;
    public String         ename;
    public int            imgsum;
    public boolean        hasIcon;
    public String         skipType;
    public String         cid;
    public String         url;
    public String         url_3w;
    public String         TAG;
    public String         TAGS;

    public class Imgextra {
        public String imgsrc;
    }

    public static class Ads {
        public String subtitle;
        public String tag;
        public String title;
        public String imgsrc;
        public String url;
    }
}
