package com.example.lijiapeng20190102_zhoukao1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lijiapeng20190102_zhoukao1.R;
import com.example.lijiapeng20190102_zhoukao1.adapter.MyAdapter;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190102_zhoukao1.contract.NetWorkContract;
import com.example.lijiapeng20190102_zhoukao1.presenter.ShopPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements NetWorkContract.requstCallback {

    private MyAdapter myAdapter;
    private String pid;
    private ShopPresenter shopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        XRecyclerView lv = findViewById(R.id.lv);
        myAdapter = new MyAdapter(this);
        lv.setAdapter(myAdapter);
        lv.setLayoutManager(new GridLayoutManager(this,3));
        myAdapter.onClick(new MyAdapter.onItemClick() {
            @Override
            public void onItemViewClick(View v) {
            }

            @Override
            public void onLongItemViewClick(View v) {
            }
        });

    }

    private void initData() {
        shopPresenter = new ShopPresenter(this);
        shopJian();
    }

    private void shopJian(){
        shopPresenter.shopJian();
    }


    @Override
    public void success(ShopBean shopBean) {
        myAdapter.setList(shopBean.data.tuijian.list);
    }

    @Override
    public void onFailure(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }



}