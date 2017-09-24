package com.dmh.mvp.di.module;

import com.dmh.mvp.http.Api;
import com.dmh.mvp.http.OkHttpApi;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by QiuGang on 2017/8/6 12:40
 * Email : 1607868475@qq.com
 */
@Module
public abstract class ApiMappingModule {
    @Singleton
    @Binds
    public abstract Api provideApi(OkHttpApi okHttpApi);
}
