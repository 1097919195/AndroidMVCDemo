package com.xjp.androidmvcdemo.MVP.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public abstract class BaseMVPActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View{

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();//MVP模式的时候需要在这个抽象实现里获取view -> presenter.attachView(this);

    protected abstract void initView();

    @Override
    public void showLoading(String title) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showError(String message) {
        Log.e("BaseMVPActivity----", message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            Log.e("BaseMVPActivity----", "presenter解除关联View");
            presenter.detachView();
        }
    }
}
