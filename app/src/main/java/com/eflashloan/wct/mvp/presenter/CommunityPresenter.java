package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.CommunityContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:25
 */
public class CommunityPresenter extends BasePresenter<CommunityContract.View> implements CommunityContract.Presenter {
    public CommunityPresenter(CommunityContract.View view) {
        super(view);
    }
}
