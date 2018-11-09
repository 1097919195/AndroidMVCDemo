package com.xjp.androidmvcdemo.MVP.test;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public class MVPPresenter2 extends MVPContract.basePresenter {

    private CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public void getData() {
        DisposableObserver<Long> disposableObserver = new DisposableObserver<Long>() {
            @Override
            public void onNext(Long l) {
                getView().returnData("简单的MVP与Rxjava回调成功了！");
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        disposable.add(disposableObserver);
    }
}
