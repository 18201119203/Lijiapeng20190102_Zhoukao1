package com.example.lijiapeng20190102_zhoukao1.net;

import android.os.Handler;

import com.example.lijiapeng20190102_zhoukao1.contract.NetWorkContract;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetWorkUtils {
    /**
     * 私有属性
     */
    private static NetWorkUtils netWorkUtils;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    /**
     * 私有构造器
     */
    private NetWorkUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    /**
     * 单列
     */
    public static NetWorkUtils initNetWorkUtils(){
        if (netWorkUtils==null){
            synchronized (NetWorkUtils.class){
                if (netWorkUtils==null){
                    netWorkUtils=new NetWorkUtils();
                }
            }
        }
        return netWorkUtils;
    }

    public void doPost(String url, HashMap<String,String> params , final Class Clazz, final NetWorkContract.responseCallback responseCallback){

        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            formBody.add(p.getKey(),p.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseCallback.onFailure("当前网络有问题,请稍后重试");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                int code = response.code();
                String string = response.body().string();
                getData(string,Clazz,responseCallback);

            }
        });
    }

    public void doGet(String url, final Class Clazz, final NetWorkContract.responseCallback responseCallback){

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseCallback.onFailure("当前网络有问题,请稍后重试");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                int code = response.code();
                String string = response.body().string();
                getData(string,Clazz,responseCallback);

            }
        });
    }

    private <T>void getData(String string, Class<T> clazz, final NetWorkContract.responseCallback responseCallback) {

        final T t = new Gson().fromJson(string, clazz);
        handler.post(new Runnable() {
            @Override
            public void run() {
                responseCallback.success(t);
            }
        });
    }

    public void cancenl(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }



}
