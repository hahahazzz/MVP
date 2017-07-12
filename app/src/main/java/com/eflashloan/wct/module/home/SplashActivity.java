package com.eflashloan.wct.module.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;
import com.eflashloan.wct.mvp.contract.SplashContract;
import com.eflashloan.wct.mvp.presenter.SplashPresenter;

import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashContract.Presenter> implements SplashContract.View {
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick({R.id.text_splash_hello})
    void click(View v) {
        startActivity(new Intent(act, HomeActivity.class));
    }

    @Override
    public void openDebugPageAndCloseSelf() {
        startActivity(new Intent(act, HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
