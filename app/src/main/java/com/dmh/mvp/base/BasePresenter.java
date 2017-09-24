package com.dmh.mvp.base;

import com.dmh.mvp.util.DebugUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:11
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
    protected V view;

    @Override
    public void attachView(BaseContract.View view) {
        this.view = (V) view;
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
    }
}
