package com.example.lijiapeng20190102_zhoukao1.contract;

import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;

public interface NetWorkContract {

    /**
     * p 层
     */
    interface PresenterCallback{
        void success(ShopBean shopBean);
        void onFailure(String s);
    }

    /**
     * view 层接口
     */
    interface requstCallback{
        void success(ShopBean shopBean);
        void onFailure(String s);
    }
    /**
     * model 层接口
     */
    interface responseCallback{
        void success(Object o);
        void onFailure(String s);
    }


}
