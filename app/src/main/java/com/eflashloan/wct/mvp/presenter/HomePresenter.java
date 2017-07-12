package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.HomeContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/10 13:08
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    public HomePresenter(HomeContract.View view) {
        super(view);
    }
}
