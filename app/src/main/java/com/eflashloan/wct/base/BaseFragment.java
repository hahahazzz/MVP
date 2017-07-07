package com.eflashloan.wct.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Preconditions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:31
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    private View contentView = null;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, contentView);
            presenter = Preconditions.checkNotNull(createPresenter());
            presenter.start();
        }
        return contentView;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract P createPresenter();

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void cancelLoadDialog() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
