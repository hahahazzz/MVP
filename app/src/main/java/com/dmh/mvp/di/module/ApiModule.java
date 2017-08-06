package com.dmh.mvp.di.module;

import com.dmh.mvp.BuildConfig;
import com.dmh.mvp.Constants;
import com.dmh.mvp.http.ApiService;
import com.dmh.mvp.http.RetrofitApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
            builder.addInterceptor(RetrofitApi.getInterceptor());
        }
        return builder;
    }

    @Singleton
    @Provides
    public OkHttpClient provideClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client, CallAdapter.Factory callAdapter, Converter.Factory
            factory) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.API_HOST)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(factory)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public CallAdapter.Factory provideCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }
}
