package com.example.lijiapeng20190102_zhoukao1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lijiapeng20190102_zhoukao1.R;
import com.example.lijiapeng20190102_zhoukao1.bean.ShopBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private onItemClick onitemClick;
    private List<ShopBean.GetShop.GetData.GetInfo> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ShopBean.GetShop.GetData.GetInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.price.setText(list.get(position).price);
        holder.title.setText(list.get(position).title);
        String[] split = list.get(position).images.split("!");
        Glide.with(context).load(split[0]).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClick.onItemViewClick(v);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onitemClick.onLongItemViewClick(v);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,price;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
        }
    }

    public void onClick(onItemClick onitemClick){
        this.onitemClick = onitemClick;
    }

    public interface onItemClick{
        void onItemViewClick(View v);
        void onLongItemViewClick(View v);
    }


}
