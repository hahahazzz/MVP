package com.eflashloan.wct.base;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:11
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
