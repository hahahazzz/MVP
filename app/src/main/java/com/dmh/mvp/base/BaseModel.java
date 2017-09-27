package com.dmh.mvp.base;

import com.dmh.mvp.http.Api;
import com.dmh.mvp.http.OkHttpApi;

/**
 * Created by QiuGang on 2017/9/27 11:26
 * Email : 1607868475@qq.com
 */
public class BaseModel implements BaseContract.Model {
    protected Api api;

    public BaseModel() {
        api = OkHttpApi.getApi();
    }

    @Override
    public void destroy() {
        api.cancelRequest(this);
    }
}
