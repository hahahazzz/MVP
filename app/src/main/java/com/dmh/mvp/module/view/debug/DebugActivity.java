package com.dmh.mvp.module.view.debug;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import com.dmh.mvp.R;
import com.dmh.mvp.base.BaseActivity;
import com.dmh.mvp.base.BaseContract;
import com.dmh.mvp.di.component.MainComponent;
import com.dmh.mvp.module.contract.DebugContract;
import com.dmh.mvp.util.ResourcesUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DebugActivity extends BaseActivity implements DebugContract.View {
    @BindView(R.id.toolbar_debug)
    Toolbar toolbarDebug;
    @BindView(R.id.til_layout)
    TextInputLayout tilLayout;
    @BindView(R.id.radio_item_http)
    RadioButton radioItemHttp;
    @BindView(R.id.edit_url)
    TextInputEditText editUrl;
    @Inject
    DebugContract.Presenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_debug;
    }

    @NonNull
    @Override
    protected BaseContract.Presenter injectPresenter(MainComponent component) {
        component.inject(this);
        return presenter;
    }

    @Override
    protected void start() {
        setSupportToolbar(toolbarDebug);
        tilLayout.setHintEnabled(true);
        tilLayout.setHint(ResourcesUtils.getString(this, R.string.text_hint_target_url));
    }

    @Override
    public String getInputUrl() {
        return editUrl.getText().toString().trim();
    }

    @Override
    public void showLocalSaveUrl(String localSavedUrl) {
        if (!TextUtils.isEmpty(localSavedUrl)) {
            editUrl.setText(localSavedUrl);
            editUrl.setSelection(localSavedUrl.length());
        }
    }

    @Override
    public boolean httpChecked() {
        return radioItemHttp.isChecked();
    }

    @Override
    public void showSaveSuccess() {
        showToast(R.string.toast_debug_url_save_success);
    }

    @OnClick(R.id.btn_save_url)
    public void onViewClicked(View v) {
        presenter.handleSaveButtonClickEvent();
    }
}
