package com.eflashloan.wct.mvp.model;

import com.eflashloan.wct.mvp.contract.DebugContract;
import com.eflashloan.wct.util.SharedPrefUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 10:16
 */
public class DebuModel implements DebugContract.Model {
    @Override
    public void saveDebugUrl(String key, String value) {
        SharedPrefUtils.save(key, value, true);
    }

    @Override
    public void clearDebugUrl(String key) {
        SharedPrefUtils.remove(key);
    }

    @Override
    public String readLocalSavedDebugUrl(String key) {
        return SharedPrefUtils.get(key, "");
    }
}
