package com.example.lijiapeng20190102_zhoukao1.presenter;

import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopYiangBean;
import com.example.lijiapeng20190102_zhoukao1.contract.NetWorkContract;
import com.example.lijiapeng20190102_zhoukao1.model.ShopModel;

import java.util.HashMap;

public class ShopPresenter {

    private NetWorkContract.requstCallback requstCallback;
    private ShopModel shopModel;

    public ShopPresenter(NetWorkContract.requstCallback requstCallback) {
        shopModel = new ShopModel();
        this.requstCallback = requstCallback;
    }


    public void shopJian() {
        shopModel.shopJian(new NetWorkContract.PresenterCallback() {
            @Override
            public void success(ShopBean shopBean) {
                if (requstCallback!=null){
                    requstCallback.success(shopBean);
                }
            }

            @Override
            public void successYiang(ShopYiangBean shopYiangBean) {

            }

            @Override
            public void onFailure(String s) {
                if (requstCallback!=null){
                    requstCallback.onFailure(s);
                }
            }
        });
    }

    public void shopYiang(HashMap<String,String> params) {

        shopModel.shopYiang(params, new NetWorkContract.PresenterCallback() {
            @Override
            public void success(ShopBean shopBean) {

            }

            @Override
            public void successYiang(ShopYiangBean shopYiangBean) {
                if (requstCallback!=null){
                    requstCallback.successYiang(shopYiangBean);
                }
            }

            @Override
            public void onFailure(String s) {
                if (requstCallback!=null){
                    requstCallback.onFailure(s);
                }
            }
        });


    }
}
