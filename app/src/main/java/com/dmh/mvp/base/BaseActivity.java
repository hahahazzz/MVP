package com.dmh.mvp.base;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Preconditions;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dmh.mvp.BuildConfig;
import com.dmh.mvp.R;
import com.dmh.mvp.di.component.DaggerMainComponent;
import com.dmh.mvp.di.component.MainComponent;
import com.dmh.mvp.util.ActivityUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:09
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    /**
     * 加载数据的对话框显示次数的计数
     */
    private volatile int loadDialogCount = 0;
    /**
     * 加载数据显示的对话框
     */
    private Dialog loadDialog = null;

    private BaseContract.Presenter basePresenter;

    protected AppCompatActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        activity = this;
        ActivityUtils.get().add(this);
        basePresenter = Preconditions.checkNotNull(injectPresenter(DaggerMainComponent.builder().apiComponent
                (App.getApp().getApiComponent()).build()));
        basePresenter.attachView(this);
        start();
        basePresenter.start();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract BaseContract.Presenter injectPresenter(MainComponent component);

    protected void start() {}

    protected void setSupportToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showLoadDialog() {
        if (loadDialog == null) {
            loadDialog = new Dialog(this, R.style.LoadDialog);
            View loadDialogContentView = LayoutInflater.from(this).inflate(R.layout.layout_load_dialog, null);
            ProgressBar progressBar = (ProgressBar) loadDialogContentView.findViewById(R.id.progress_load_dialog);
            int progressColor = ContextCompat.getColor(this, R.color.colorPrimary);
            progressBar.getIndeterminateDrawable().setColorFilter(progressColor, PorterDuff.Mode.SRC_ATOP);
            loadDialog.setCanceledOnTouchOutside(BuildConfig.DEBUG);
            loadDialog.setCancelable(true);
            loadDialog.setContentView(loadDialogContentView);
            loadDialogCount = 0;
        }
        if (!loadDialog.isShowing()) {
            loadDialog.show();
        }
        loadDialogCount++;
    }

    @Override
    public void cancelLoadDialog() {
        cancelLoadDialog(false);
    }

    protected void cancelLoadDialog(boolean force) {
        if (force) {
            loadDialogCount = 0;
        }
        loadDialogCount--;
        if (loadDialogCount <= 0 && loadDialog != null) {
            loadDialog.cancel();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int msgId) {
        Toast.makeText(activity, msgId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnack(String msg, int actionResId, final Runnable action) {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction(actionResId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.run();
            }
        }).show();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        basePresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        basePresenter.pause();
    }

    @Override
    protected void onDestroy() {
        cancelLoadDialog(true);
        ActivityUtils.get().remove(this);
        super.onDestroy();
    }
}
