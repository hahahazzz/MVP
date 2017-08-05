package com.dmh.mvp.module.contract;

import com.dmh.mvp.base.BaseContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:06
 */
public interface SplashContract {
    interface View extends BaseContract.View {

        void openDebugPageAndCloseSelf();
    }

    interface Presenter extends BaseContract.Presenter {

    }
}
