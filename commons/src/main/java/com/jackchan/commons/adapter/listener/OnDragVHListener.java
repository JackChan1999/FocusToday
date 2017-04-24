package com.jackchan.commons.adapter.listener;

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
 * des ：ViewHolder 被选中 以及 拖拽释放 触发监听器
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/

public interface OnDragVHListener {
    /**
     * Item被选中时触发
     */
    void onItemSelected();


    /**
     * Item在拖拽结束/滑动结束后触发
     */
    void onItemFinish();
}
