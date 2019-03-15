package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txunda.construction_worker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/14 014 11:31:33.
 * 功能描述：大列表 练习报告
 * 联系方式： win_hzy@163.com
 */
public class LxBgAdapter extends RecyclerView.Adapter<LxBgAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public LxBgAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_do_work_layout,parent,false);
        return new LxBgAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getHead_pic()).into(holder.iv_header);
//        holder.tv_name.setText(list.get(position).getNickname());
//        holder.ratingBar.setRating(new Float(list.get(position).getStar()));
//        holder.tv_content.setText(list.get(position).getContent());
//        holder.tv_time.setText(list.get(position).getCreate_time());
//        if (list.get(position).getAppraise_pic().get(0).getPath().length()>1){
//            holder.ll_img.setVisibility(View.VISIBLE);
            holder.rv_imgs.setLayoutManager(new GridLayoutManager(context, 6, LinearLayoutManager.VERTICAL, false));

            ExerciseReportRvAdapter imageAdapter = new ExerciseReportRvAdapter(R.layout.item_dtk_layout);
            List<String> list = new ArrayList<>();
            for (int i = 1; i < 21; i++) {
                list.add(String.valueOf(i));
            }
//            imageAdapter.setNewData(list);
            holder.rv_imgs.setAdapter(imageAdapter);
//        }else {
//            holder.ll_img.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        RecyclerView rv_imgs;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_do_work_title);
            rv_imgs = itemView.findViewById(R.id.item_do_work_rv);
        }
    }
}
