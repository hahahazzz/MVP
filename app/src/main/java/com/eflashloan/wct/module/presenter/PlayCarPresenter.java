package com.eflashloan.wct.module.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.module.contract.PlayCarContract;

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
