package com.dmh.mvp.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:56
 */
public final class JsonUtils {
    private static JsonUtils parser;
    private final Gson gson;

    private JsonUtils() {
        gson = new Gson();
    }

    public static JsonUtils getParser() {
        if (parser == null) {
            synchronized (JsonUtils.class) {
                parser = new JsonUtils();
            }
        }
        return parser;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    public <T> T fromJson(JsonElement element, Class<T> cls) {
        return gson.fromJson(element, cls);
    }

    public <T> ArrayList<T> jsonToArrayList(String json, Class<T[]> cls) {
        T[] tArray = gson.fromJson(json, cls);
        ArrayList<T> list = new ArrayList<>(tArray.length);
        for (T item : tArray) {
            list.add(item);
        }
        return list;
    }

    public <T> ArrayList<T> jsonToArrayList(JsonElement element, Class<T[]> cls) {
        T[] tArray = gson.fromJson(element, cls);
        ArrayList<T> list = new ArrayList<>(tArray.length);
        for (T item : tArray) {
            list.add(item);
        }
        return list;
    }
}
