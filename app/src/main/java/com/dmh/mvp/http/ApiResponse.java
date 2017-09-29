package com.dmh.mvp.http;

/**
 * Created by QiuGang on 2017/9/24 22:23
 * Email : 1607868475@qq.com
 */
public class ApiResponse {
    private int code;
    private String result;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
