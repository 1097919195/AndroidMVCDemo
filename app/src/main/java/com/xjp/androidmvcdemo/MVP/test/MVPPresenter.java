package com.xjp.androidmvcdemo.MVP.test;

import android.util.Log;

import com.xjp.androidmvcdemo.MVP.base.BasePresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/11/9 0009.
 */
//此处如果继承了BasePresenter<MVPContract.View>的话就可以不一定实现attachView和detachView(需要用的话再重写就可以了),重写后再baseMVPActivity中会执行了连接和断开view的关联
public class MVPPresenter extends BasePresenter<MVPContract.View> implements MVPContract.Presenter {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void getData() {
        Log.e("TAG----", "P中的方法执行了");
        //一 简单的打印测试  https://blog.csdn.net/tyrantu1989/article/details/69062990
        Observable.timer(1000,TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("TAG----",aLong.toString()); // 0
                    }
                });
        //二 测试发送个错误
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onError(new RuntimeException("测试error"));
            }
        }).subscribe(new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {
                getView().showError(t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        //三 MVP成功的回调
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

    //取消订阅及断开了和view的关联操作
    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
