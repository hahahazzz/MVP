package com.eflashloan.wct.module.debug;

import com.eflashloan.wct.base.BasePresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 9:32
 */
public class DebugPresenter extends BasePresenter<DebugContract.View> implements DebugContract.Presenter {
    public DebugPresenter(DebugContract.View view) {
        super(view);
    }

    @Override
    public void saveDebugUrl() {

    }

    @Override
    public void readLocalSavedDebugUrl() {

    }
}
