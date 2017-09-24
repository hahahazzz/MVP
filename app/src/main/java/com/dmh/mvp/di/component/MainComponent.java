package com.dmh.mvp.di.component;

import com.dmh.mvp.di.module.ModelMappingModule;
import com.dmh.mvp.di.module.PresenterMappingModule;
import com.dmh.mvp.di.scope.PerActivity;
import com.dmh.mvp.di.scope.PerFragment;
import com.dmh.mvp.di.scope.PerPresenter;
import com.dmh.mvp.module.view.debug.DebugActivity;
import com.dmh.mvp.module.view.home.HomeActivity;
import com.dmh.mvp.module.view.home.SplashActivity;
import com.dmh.mvp.module.view.home.fragment.ShoppingGuideFragment;

import dagger.Component;

/**
 * Created by qiugang on 2017/8/5.
 */
@PerActivity
@PerFragment
@PerPresenter
@Component(modules = {PresenterMappingModule.class, ModelMappingModule.class}, dependencies = {ApiComponent.class})
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(DebugActivity activity);

    void inject(ShoppingGuideFragment fragment);
}
