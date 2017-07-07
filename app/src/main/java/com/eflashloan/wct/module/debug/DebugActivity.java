package com.eflashloan.wct.module.debug;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;
import com.eflashloan.wct.mvp.contract.DebugContract;
import com.eflashloan.wct.mvp.presenter.DebugPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class DebugActivity extends BaseActivity<DebugContract.Presenter> implements DebugContract.View {
    @BindView(R.id.toolbar_debug)
    Toolbar toolbarDebug;
    @BindView(R.id.til_layout)
    TextInputLayout tilLayout;
    @BindView(R.id.radio_item_http)
    RadioButton radioItemHttp;
    @BindView(R.id.edit_url)
    TextInputEditText editUrl;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_debug;
    }

    @NonNull
    @Override
    protected DebugContract.Presenter createPresenter() {
        return new DebugPresenter(this);
    }

    @Override
    public String getInputUrl() {
        return editUrl.getText().toString().trim();
    }

    @Override
    public void showLocalSaveUrl() {

    }

    @Override
    public boolean httpChecked() {
        return radioItemHttp.isChecked();
    }

    @Override
    public void showSaveSuccess() {
        Toast.makeText(act, R.string.toast_debug_url_save_success, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_save_url)
    public void onViewClicked(View v) {
        presenter.handleSaveButtonClickEvent();
    }
}
