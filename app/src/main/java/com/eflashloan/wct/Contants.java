package com.eflashloan.wct;

import com.eflashloan.wct.util.SharedPrefUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:08
 */
public final class Contants {
    /**
     * 存储到本地的测试API的key
     */
    public static final String LOCAL_SAVED_API_HOST = "localSavedApiHost";

    /**
     * 测试的API的HOST,Debug模式下,如果本地存储了指定的HOST,则使用指定的HOST,否则使用Release的HOST.Release强制使用Release的HOST
     */
    public static final String API_HOST = BuildConfig.DEBUG ? SharedPrefUtils.get(LOCAL_SAVED_API_HOST, BuildConfig
            .API_HOST) : BuildConfig.API_HOST;
}
