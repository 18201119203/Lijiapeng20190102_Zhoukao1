package com.example.lijiapeng20190102_zhoukao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lijiapeng20190102_zhoukao1.R;

public class ShopActivity extends AppCompatActivity {

    private ImageView mImage;
    /**
     * 标题
     */
    private TextView mTitle;
    private TextView mProductNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();


    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        mTitle = (TextView) findViewById(R.id.title);
        mProductNums = (TextView) findViewById(R.id.productNums);
        Intent intent = getIntent();
        String icon = intent.getStringExtra("icon");
        String name = intent.getStringExtra("name");
        String productNums = intent.getStringExtra("productNums");
        mTitle.setText(name);
        mProductNums.setText(productNums);
        Glide.with(this).load(icon).into(mImage);

    }
}
