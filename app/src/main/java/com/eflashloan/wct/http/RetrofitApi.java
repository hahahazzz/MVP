package com.eflashloan.wct.http;

import android.icu.text.Replaceable;
import android.icu.text.UFormat;

import com.eflashloan.wct.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:57
 */
public class RetrofitApi implements Api {
    private static Api api;
    private final ApiService apiService;
    private OkHttpClient requestClient;

    static {
    }

    private RetrofitApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.readTimeout(30L, TimeUnit.SECONDS);
        builder.writeTimeout(30L, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LogInterceptor());
        }
        requestClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(requestClient)
                .baseUrl("https://")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Api getApi() {
        if (api == null) {
            synchronized (RetrofitApi.class) {
                api = new RetrofitApi();
            }
        }
        return api;
    }

    static class LogInterceptor implements Interceptor {
        private final static String LINE_SEPARATOR = System.lineSeparator();
        private final static String TAB = "\t";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String requestName = Request.class.getSimpleName();
            String requestUrl = request.url().toString();
            String requestBody = request.body().toString();

            String responseName = Response.class.getSimpleName();
            int responseCode = response.code();
            String responseMessage = response.message();
            String responseBody = response.body().string();

            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append(requestName);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append(requestUrl);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append(requestBody);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(responseName);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append("code=").append(responseCode);
            logBuilder.append(TAB);
            logBuilder.append("msg=").append(responseMessage);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append("body=").append(responseBody);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), requestBody)).build();
        }
    }
}
