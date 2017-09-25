package com.dmh.mvp.http;

import com.google.gson.JsonElement;

/**
 * Created by QiuGang on 2017/9/24 22:23
 * Email : 1607868475@qq.com
 */
public class ApiResponse {
    private int code;
    private JsonElement data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
