package com.eflashloan.wct.module.debug;

import com.eflashloan.wct.base.BaseContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 9:28
 */
public interface DebugContract {
    interface View extends BaseContract.View {
        String getInputUtl();

        void showLocalSaveUrl();
    }

    interface Presenter extends BaseContract.Presenter {
        void saveDebugUrl();

        void readLocalSavedDebugUrl();
    }
}
