package com.xjp.androidmvcdemo.MVP.base;

public interface BaseContract {
    interface View {
        void showLoading(String title);
        boolean isNetworkConnected();
        void showError(String message);
    }

    interface Presenter<V extends BaseContract.View> {
        void attachView(V view);
        void detachView();
    }
}
