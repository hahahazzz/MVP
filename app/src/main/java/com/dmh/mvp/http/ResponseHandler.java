package com.dmh.mvp.http;

/**
 * Created by QiuGang on 2017/9/24 20:20
 * Email : 1607868475@qq.com
 */
public interface ResponseHandler<T> {
    Class<T> getDataClass();

    boolean isOk();

    void setOk(boolean ok);

    void setTag(String key, Object tag);

    Object getTag(String key);

    void onStart();

    void onSuccess(int responseCode, ApiResponse apiResponse, T data);

    void onFailed(String reqUrl, int errCode, String errMsg);

    void onComplete();
}
