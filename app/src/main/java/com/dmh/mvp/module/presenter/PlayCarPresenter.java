package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.PlayCarContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:24
 */
public class PlayCarPresenter extends BasePresenter<PlayCarContract.View> implements PlayCarContract.Presenter {
    @Inject
    public PlayCarPresenter() {}
}
