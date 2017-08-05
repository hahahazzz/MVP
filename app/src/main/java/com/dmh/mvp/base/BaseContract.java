package com.dmh.mvp.base;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:11
 */
public interface BaseContract {
    interface View {
        void showLoadDialog();

        void cancelLoadDialog();

        void showToast(int msgId);

        void showToast(String msg);

        void showSnack(String msg, int actionResId, Runnable action);

        void close();
    }

    interface Presenter<V extends BaseContract.View> {
        void attachView(V view);

        void start();

        void resume();

        void pause();

        void destroy();
    }

    interface Model {

    }
}
