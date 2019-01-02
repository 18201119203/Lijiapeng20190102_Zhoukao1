package com.example.lijiapeng20190102_zhoukao1.bean;

import java.util.List;

public class ShopBean {

    public String code;
    public String msg;
    public GetShop data;

    public class GetShop{

        public GetData tuijian;

        public class GetData{
            public String name;
            public List<GetInfo> list;

            public class GetInfo{
                public String images;
                public String price;
                public String title;
                public int pid;
            }

        }

    }

}
