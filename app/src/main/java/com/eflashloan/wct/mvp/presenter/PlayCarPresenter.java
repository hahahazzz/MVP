package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.PlayCarContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:24
 */
public class PlayCarPresenter extends BasePresenter<PlayCarContract.View> implements PlayCarContract.Presenter {
    public PlayCarPresenter(PlayCarContract.View view) {
        super(view);
    }
}
