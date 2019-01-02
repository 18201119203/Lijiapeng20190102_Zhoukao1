package com.example.lijiapeng20190102_zhoukao1.model;

import com.example.lijiapeng20190102_zhoukao1.aip.UrlAip;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopYiangBean;
import com.example.lijiapeng20190102_zhoukao1.contract.NetWorkContract;
import com.example.lijiapeng20190102_zhoukao1.net.NetWorkUtils;

import java.util.HashMap;

public class ShopModel{

    public void shopJian(final NetWorkContract.PresenterCallback presenterCallback) {

        NetWorkUtils.initNetWorkUtils().doGet(UrlAip.SHOP_JIAN, ShopBean.class, new NetWorkContract.responseCallback() {
            @Override
            public void success(Object o) {
                if (presenterCallback!=null){
                    presenterCallback.success((ShopBean) o);
                }
            }

            @Override
            public void successYiang(Object o) {

            }

            @Override
            public void onFailure(String s) {
                if (presenterCallback!=null){
                    presenterCallback.onFailure(s);
                }
            }
        });

    }

    public void shopYiang(HashMap<String,String> params, final NetWorkContract.PresenterCallback presenterCallback) {

        NetWorkUtils.initNetWorkUtils().doPost(UrlAip.SHOP_YANG, params, ShopYiangBean.class, new NetWorkContract.responseCallback() {
            @Override
            public void success(Object o) {

            }

            @Override
            public void successYiang(Object o) {
                if (presenterCallback!=null){
                    presenterCallback.successYiang((ShopYiangBean) o);
                }
            }

            @Override
            public void onFailure(String s) {
                if (presenterCallback!=null){
                    presenterCallback.onFailure(s);
                }
            }
        });

    }
}
