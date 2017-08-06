package com.dmh.mvp.di.module;

import com.dmh.mvp.di.scope.PerPresenter;
import com.dmh.mvp.module.contract.DebugContract;
import com.dmh.mvp.module.contract.HomeContract;
import com.dmh.mvp.module.model.DebugModel;
import com.dmh.mvp.module.model.HomeModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by qiugang on 2017/8/5 16:41
 * Email : 1607868475@qq.com
 */
@Module
public abstract class ModelMappingModule {
    @PerPresenter
    @Binds
    public abstract DebugContract.Model provideDebugModel(DebugModel model);

    @PerPresenter
    @Binds
    public abstract HomeContract.Model provideHomeModel(HomeModel model);
}
