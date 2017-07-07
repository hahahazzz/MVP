package com.eflashloan.wct.module;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;
import com.eflashloan.wct.module.debug.DebugActivity;
import com.eflashloan.wct.mvp.contract.SplashContract;
import com.eflashloan.wct.mvp.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashContract.Presenter> implements SplashContract.View {
    @BindView(R.id.toolbar_splash)
    Toolbar toolbarSplash;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @NonNull
    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected void start() {
        setSupportToolbar(toolbarSplash);
    }

    @OnClick({R.id.text_splash_hello})
    void click(View v) {
        startActivity(new Intent(act, DebugActivity.class));
    }

    @Override
    public void openDebugPage() {
        startActivity(new Intent(act, DebugActivity.class));
    }
}
