package com.eflashloan.wct.http;

import android.telecom.Call;

import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 15:29
 */
public interface ApiService {

    @GET
    Call<ResponseBody> getUserNameList();
}
