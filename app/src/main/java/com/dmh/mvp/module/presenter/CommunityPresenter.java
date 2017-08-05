package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.CommunityContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:25
 */
public class CommunityPresenter extends BasePresenter<CommunityContract.View> implements CommunityContract.Presenter {
    @Inject
    public CommunityPresenter() {

    }
}
