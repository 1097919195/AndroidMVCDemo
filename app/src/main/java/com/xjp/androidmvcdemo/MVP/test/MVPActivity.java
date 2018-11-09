package com.xjp.androidmvcdemo.MVP.test;

import android.view.View;
import android.widget.Toast;

import com.xjp.androidmvcdemo.MVP.base.BaseMVPActivity;
import com.xjp.androidmvcdemo.R;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public class MVPActivity extends BaseMVPActivity<MVPPresenter> implements MVPContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    //因为在BaseMVPActivity中没有实现presenter类实例化所以需要new，可以模仿新闻APP的实现    （测试MVPPresenter2直接把本类中的MVPPresenter全部替换了就行）
    @Override
    protected void initPresenter() {
        // presenter 关联 view
        if (presenter == null) {
            presenter = new MVPPresenter();
        }
        presenter.attachView(this);
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getData();
            }
        });
    }

    @Override
    public void returnData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
