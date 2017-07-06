package com.eflashloan.wct.module;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;

public class SplashActivity extends BaseActivity<SplashContract.Presenter> implements SplashContract.View {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @NonNull
    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }
}
