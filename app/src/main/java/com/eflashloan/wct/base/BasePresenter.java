package com.eflashloan.wct.base;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:11
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
    protected V view;
    private CompositeDisposable disposableContainer = new CompositeDisposable();

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
        disposableContainer.clear();
    }

    protected <T> void addDisposable(Observable<T> observable, Observer<T> observer, boolean autoSchedule) {
        if (autoSchedule) {
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        observable
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        disposableContainer.add(disposable);
                    }
                })
                .subscribe(observer);
    }

    protected <T> void addDisposable(Observable<T> observable, Observer<T> observer) {
        addDisposable(observable, observer, true);
    }
}
