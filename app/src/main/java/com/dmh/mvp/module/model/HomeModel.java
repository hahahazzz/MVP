package com.dmh.mvp.module.model;

import com.dmh.mvp.base.BaseModel;
import com.dmh.mvp.module.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by QiuGang on 2017/8/6 11:40
 * Email : 1607868475@qq.com
 */
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Inject
    public HomeModel() {
    }
}
