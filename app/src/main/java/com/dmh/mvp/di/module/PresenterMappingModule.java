package com.dmh.mvp.di.module;

import com.dmh.mvp.di.scope.PerActivity;
import com.dmh.mvp.di.scope.PerFragment;
import com.dmh.mvp.module.contract.DebugContract;
import com.dmh.mvp.module.contract.HomeContract;
import com.dmh.mvp.module.contract.ShoppingGuideContract;
import com.dmh.mvp.module.contract.SplashContract;
import com.dmh.mvp.module.presenter.DebugPresenter;
import com.dmh.mvp.module.presenter.HomePresenter;
import com.dmh.mvp.module.presenter.ShoppingPresenter;
import com.dmh.mvp.module.presenter.SplashPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * Created by qiugang on 2017/8/5 16:17
 * Email : 1607868475@qq.com
 */
@Module
public abstract class PresenterMappingModule {
    @PerActivity
    @Binds
    public abstract SplashContract.Presenter provideSplashPresenter(SplashPresenter presenter);

    @PerActivity
    @Binds
    public abstract HomeContract.Presenter providehomePresenter(HomePresenter presenter);

    @PerActivity
    @Binds
    public abstract DebugContract.Presenter provideDebugPresenter(DebugPresenter presenter);

    @PerFragment
    @Binds
    public abstract ShoppingGuideContract.Presenter provideShoppingPresenter(ShoppingPresenter presenter);
}
