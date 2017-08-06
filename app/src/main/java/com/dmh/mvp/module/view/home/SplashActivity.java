package com.dmh.mvp.module.view.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.dmh.mvp.R;
import com.dmh.mvp.base.BaseActivity;
import com.dmh.mvp.base.BaseContract;
import com.dmh.mvp.di.component.MainComponent;
import com.dmh.mvp.module.contract.SplashContract;

import javax.inject.Inject;

import butterknife.OnClick;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    @Inject
    SplashContract.Presenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @NonNull
    @Override
    protected BaseContract.Presenter injectPresenter(MainComponent component) {
        component.inject(this);
        return presenter;
    }

    @Override
    protected void start() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick({R.id.text_splash_hello})
    void click(View v) {
        startActivity(new Intent(activity, HomeActivity.class));
    }

    @Override
    public void openDebugPageAndCloseSelf() {
        startActivity(new Intent(activity, HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
