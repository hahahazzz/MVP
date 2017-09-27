package com.dmh.mvp.module.model;

import com.dmh.mvp.base.BaseModel;
import com.dmh.mvp.module.contract.DebugContract;
import com.dmh.mvp.util.SharedPrefUtils;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 10:16
 */
public class DebugModel extends BaseModel implements DebugContract.Model {

    @Inject
    public DebugModel() {
    }

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
