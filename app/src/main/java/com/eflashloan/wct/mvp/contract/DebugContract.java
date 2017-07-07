package com.eflashloan.wct.mvp.contract;

import com.eflashloan.wct.base.BaseContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 9:28
 */
public interface DebugContract {
    interface View extends BaseContract.View {
        String getInputUrl();

        void showLocalSaveUrl();

        boolean httpChecked();

        void showSaveSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void handleSaveButtonClickEvent();
    }

    interface Model extends BaseContract.Model {
        void saveDebugUrl(String key, String value);

        void clearDebugUrl(String key);

        String readLocalSavedDebugUrl(String key);
    }
}
