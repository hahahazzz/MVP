package com.eflashloan.wct.module.home.fragment;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.mvp.contract.PlayCarContract;
import com.eflashloan.wct.mvp.presenter.PlayCarPresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:26
 */
public class PlayCarFragment extends BaseFragment<PlayCarContract.Presenter> implements PlayCarContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_play_car;
    }

    @NonNull
    @Override
    protected PlayCarContract.Presenter createPresenter() {
        return new PlayCarPresenter(this);
    }
}
