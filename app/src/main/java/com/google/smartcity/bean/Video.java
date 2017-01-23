package com.google.smartcity.bean;

import java.util.List;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：QuickNews
 * Package_Name：com.google.topnews
 * Version：1.0
 * time：2016/11/26 10:09
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class Video {
    
    public List<VideoData> V9LG4B3A0;

    public class VideoData {
        
        public VideoTopic videoTopic;
        public String     topicImg;
        public int        length;
        public String     mp4Hd_url;
        public String     description;
        public String     videosource;
        public String     title;
        public String     m3u8Hd_url;
        public String     mp4_url;
        public String     topicSid;
        public String     cover;
        public String     vid;
        public int        playCount;
        public String     replyBoard;
        public int        playersize;
        public String     replyid;
        public String     topicName;
        public String     sectiontitle;
        public String     ptime;
        public String     m3u8_url;
        public String     topicDesc;
        

        public class VideoTopic {
            
            public String ename;
            public String alias;
            public String tname;
            public String tid;
        }
    }
}
