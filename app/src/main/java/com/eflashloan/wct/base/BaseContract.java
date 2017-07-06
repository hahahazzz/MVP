package com.eflashloan.wct.base;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:11
 */
public interface BaseContract {
    interface View<P extends Presenter> {
        void showLoadDialog();

        void cancelLoadDialog();
    }

    interface Presenter<V extends View> {
        void start();

        void resume();

        void pause();

        void destroy();
    }

    interface Model {

    }
}
