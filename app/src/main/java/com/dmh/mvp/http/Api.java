package com.dmh.mvp.http;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:57
 */
public interface Api {
    String API_HOST = "http://www.sanbiaoge.cn/";
    String KEY_TOKEN = "token";
    String TAG_CANCEL_ALL_REQUEST = "cancelAllRequest";

    void cancelRequest(Object tag);
}
