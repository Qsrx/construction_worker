package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.txunda.construction_worker.R;

import java.util.List;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/14 014 13:49:53.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private Context context;
    private List<String> img_list;

    public ImageAdapter(Context context, List<String> img_list) {
        this.context = context;
        this.img_list = img_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_pic,parent,false);
        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(img_list.get(position)).apply(RequestOptions.errorOf(R.mipmap.img_error)).into(holder.iv_pic);
    }

    @Override
    public int getItemCount() {
        return img_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShapedImageView iv_pic;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }
}