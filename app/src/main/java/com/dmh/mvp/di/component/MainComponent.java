package com.dmh.mvp.di.component;

import com.dmh.mvp.di.module.ModelModule;
import com.dmh.mvp.di.module.PresenterModule;
import com.dmh.mvp.di.scope.PerActivity;
import com.dmh.mvp.di.scope.PerFragment;
import com.dmh.mvp.di.scope.PerPresenter;
import com.dmh.mvp.module.view.debug.DebugActivity;
import com.dmh.mvp.module.view.home.HomeActivity;
import com.dmh.mvp.module.view.home.SplashActivity;
import com.dmh.mvp.module.view.home.fragment.BuyCarFragment;
import com.dmh.mvp.module.view.home.fragment.CommunityFragment;
import com.dmh.mvp.module.view.home.fragment.FinanceFragment;
import com.dmh.mvp.module.view.home.fragment.PlayCarFragment;
import com.dmh.mvp.module.view.home.fragment.ShoppingGuideFragment;

import dagger.Component;

/**
 * Created by qiugang on 2017/8/5.
 */
@PerActivity
@PerFragment
@PerPresenter
@Component(modules = {PresenterModule.class, ModelModule.class})
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(DebugActivity activity);

    void inject(BuyCarFragment fragment);

    void inject(CommunityFragment fragment);

    void inject(FinanceFragment fragment);

    void inject(PlayCarFragment fragment);

    void inject(ShoppingGuideFragment fragment);
}
