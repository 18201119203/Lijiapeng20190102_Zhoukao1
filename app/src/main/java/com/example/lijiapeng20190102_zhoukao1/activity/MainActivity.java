package com.example.lijiapeng20190102_zhoukao1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.example.lijiapeng20190102_zhoukao1.R;
import com.example.lijiapeng20190102_zhoukao1.adapter.MyAdapter;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopYiangBean;
import com.example.lijiapeng20190102_zhoukao1.contract.NetWorkContract;
import com.example.lijiapeng20190102_zhoukao1.net.NetWorkUtils;
import com.example.lijiapeng20190102_zhoukao1.presenter.ShopPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetWorkContract.requstCallback {

    private MyAdapter myAdapter;
    private int pid;
    private ShopPresenter shopPresenter;
    private List<ShopBean.GetShop.GetData.GetInfo> list;

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
            public void onItemViewClick(View v,int position) {
                pid = list.get(position).pid;
                HashMap<String,String> params = new HashMap<>();
                params.put("pid",pid+"");
                shopPresenter = new ShopPresenter(MainActivity.this);
                shopYiang(params);

            }

            @Override
            public void onLongItemViewClick(View v,int position) {
                list.remove(position);
                myAdapter.setList(list);
                Toast.makeText(MainActivity.this,"删除了条目",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void shopYiang(HashMap<String, String> params) {
        shopPresenter.shopYiang(params);

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
        list = shopBean.data.tuijian.list;
        myAdapter.setList(shopBean.data.tuijian.list);
    }

    @Override
    public void successYiang(ShopYiangBean shopYiangBean) {
        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        String icon = shopYiangBean.seller.icon;
        String name = shopYiangBean.seller.name;
        double productNums = shopYiangBean.seller.productNums;
        intent.putExtra("icon",icon);
        intent.putExtra("name",name);
        intent.putExtra("productNums",productNums+"");
        startActivity(intent);
        Toast.makeText(MainActivity.this,"页面详情",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onFailure(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetWorkUtils.initNetWorkUtils().cancenl();
    }
}
