package com.google.smartcity.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.smartcity.R;

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
 * time：2016/12/11 11:01
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class GlideUtils {
    public static void display(Context context, String url, ImageView iv){
        Glide.with(context)
                .load(url)
                .placeholder(new ColorDrawable(context.getResources().getColor(R.color.placeholder)))
                .error(new ColorDrawable(context.getResources().getColor(R.color.error)))
                .into(iv);
    }
}
