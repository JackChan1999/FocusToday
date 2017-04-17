package com.jackchen.focustoday.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.jackchen.focustoday.utils.typebuilder.TypeBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartCity
 * Package_Name：com.jackchen.focustoday
 * Version：1.0
 * time：2016/2/16 10:06
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class GsonUtil {
    public GsonUtil() {
    }

    public static String createGsonString(Object object) {
        Gson gson = new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }

    public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        T t = gson.fromJson(gsonString, cls);
        return t;
    }

    public static <T> T changeGsonToBean(String gsonString, Type type) {
        Gson gson = new Gson();
        T t = gson.fromJson(gsonString, type);
        return t;
    }

    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        Type type = TypeBuilder.newInstance(List.class).addTypeParam(cls).build();
        Gson gson = new Gson();
        List<T> list = gson.fromJson(gsonString, type);
        return list;
    }

    public static <T> List<T> changeGsonToList(JsonElement jsonElement, Class<T> cls) {
        Type type = TypeBuilder.newInstance(List.class).addTypeParam(cls).build();
        Gson gson = new Gson();
        List<T> list = gson.fromJson(jsonElement, type);
        return list;
    }

    public static <T> List<Map<String, T>> changeGsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
        return map;
    }

    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}