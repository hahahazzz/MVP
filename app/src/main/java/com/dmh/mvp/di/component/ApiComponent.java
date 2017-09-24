package com.dmh.mvp.di.component;

import com.dmh.mvp.di.module.ApiMappingModule;
import com.dmh.mvp.http.Api;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by qiugang on 2017/8/6 10:42
 * Email : 1607868475@qq.com
 */
@Singleton
@Component(modules = {ApiMappingModule.class})
public interface ApiComponent {
    Api getApi();
}
