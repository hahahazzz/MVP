package com.dmh.mvp.di.module;

import com.dmh.mvp.BuildConfig;
import com.dmh.mvp.http.OkHttpApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by qiugang on 2017/8/6 10:42
 * Email : 1607868475@qq.com
 */
@Module
public class ApiModule {
    @Singleton
    @Provides
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.readTimeout(30L, TimeUnit.SECONDS);
        builder.writeTimeout(30L, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpApi.getInterceptor());
        }
        return builder;
    }

    @Singleton
    @Provides
    public OkHttpClient provideClient(OkHttpClient.Builder builder) {
        return builder.build();
    }
}
