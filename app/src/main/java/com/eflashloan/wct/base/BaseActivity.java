package com.eflashloan.wct.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:09
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.BaseView {
    private volatile int loadDialogCount = 0;
    private Dialog loadDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoadDialog() {
        if (loadDialog == null) {

        }

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
        if (loadDialogCount <= 0) {
            loadDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        cancelLoadDialog(true);
        super.onDestroy();
    }
}
