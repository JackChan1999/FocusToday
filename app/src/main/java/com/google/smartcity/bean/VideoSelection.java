package com.google.smartcity.bean;

import java.util.List;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.google.smartcity.bean
 * Version：1.0
 * time：2016/12/2 17:45
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class VideoSelection {

    public List<VideoData> item;
    public int totalPage;

    public class VideoData {
        public String title;
        public String typename;
        public String typeid;
        public String video_url;
        public String shareUrl;
        public String thumbnail;
        public String video_size;
        public String image;
        public String commentsUrl;
        public String commentsall;
        public String documentId;
        public String duration;
    }
}
