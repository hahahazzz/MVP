package com.eflashloan.wct.http;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 15:00
 */
public final class ApiFactory {
    public static Api getDefaultApi() {
        return RetrofitApi.getApi();
    }
}
