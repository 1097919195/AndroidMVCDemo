package com.xjp.androidmvcdemo.MVP.test;

import com.xjp.androidmvcdemo.MVP.base.BaseContract;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public interface MVPContract {
    interface View extends BaseContract.View {
        void returnData(String data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getData();
    }

}
