package com.google.smartcity.bean;

import com.google.smartcity.base.BaseBean;

import java.util.List;

/**
 * ============================================================
 * 版 权 ： Google互联网有限公司版权所有 (c) 2016
 * 作 者 : 陈冠杰
 * 版 本 ： 1.0
 * 创建日期 ：2016/6/6 15:07
 * 描 述 ：图片新闻数据
 * 修订历史 ：
 * ============================================================
 **/
public class PhotoBean extends BaseBean{

    public String cover;
    public String createdate;
    public String datetime;
    public String desc;
    public String scover;
    public String setid;
    public String setname;
    public String seturl;
    public String tcover;
    public int imgsum;
    public int replynum;

    public List<String> pics;
}
