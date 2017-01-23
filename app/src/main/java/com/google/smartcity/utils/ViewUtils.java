package com.google.smartcity.utils;

/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2016
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.google.smartcity.utils
 * Version：1.0
 * time：2016/12/22 18:59
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class ViewUtils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(500);
    }

    public static boolean isFastDoubleClick(long timeMillis) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < timeMillis) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
