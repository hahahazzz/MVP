package com.dmh.mvp.http;

import android.support.v4.util.ArrayMap;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:57
 */
public interface Api {
    String API_HOST = "http://www.sanbiaoge.cn/";
    String KEY_TOKEN = "token";
    String TAG_CANCEL_ALL_REQUEST = "cancelAllRequest";

    <T> void post(String url, Object tag, ArrayMap<String, String> params, ResponseHandler<T> handler);

    <T> void get(String url, Object tag, ResponseHandler<T> handler);

    <T> void get(String url, Object tag, ArrayMap<String, String> params, ResponseHandler<T> handler);

    void cancelRequest(Object tag);
}
