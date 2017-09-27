package com.dmh.mvp.base;

import com.dmh.mvp.util.DebugUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:11
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
    protected V view;
    private BaseContract.Model baseModel;

    @Override
    public void attachViewAndLinkModel(BaseContract.View view) {
        this.view = (V) view;
        baseModel = linkModel();
    }

    protected BaseContract.Model linkModel() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {
        if (DebugUtils.debugConnection()) {
            view.close();
        }
    }

    @Override
    public void pause() {
        if (DebugUtils.debugConnection()) {
            view.close();
        }
    }

    @Override
    public void destroy() {
        if (baseModel != null) {
            baseModel.destroy();
        }
    }
}
