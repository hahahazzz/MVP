package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:10
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private final static long SPLASH_DURATION = 3000L;

    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        addDisposable(Observable.timer(SPLASH_DURATION, TimeUnit.MILLISECONDS), new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Long aLong) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.openDebugPage();
            }

            @Override
            public void onComplete() {
                view.openDebugPage();
            }
        });
    }
}
