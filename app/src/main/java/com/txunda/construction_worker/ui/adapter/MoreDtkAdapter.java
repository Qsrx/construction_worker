package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.txunda.construction_worker.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/14 014 11:31:33.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MoreDtkAdapter extends RecyclerView.Adapter<MoreDtkAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public MoreDtkAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_do_work_layout,parent,false);
        return new MoreDtkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getHead_pic()).into(holder.iv_header);
//        holder.tv_name.setText(list.get(position).getNickname());
//        holder.ratingBar.setRating(new Float(list.get(position).getStar()));
//        holder.tv_content.setText(list.get(position).getContent());
//        holder.tv_time.setText(list.get(position).getCreate_time());
//        if (list.get(position).getAppraise_pic().get(0).getPath().length()>1){
//          holder.ll_img.setVisibility(View.VISIBLE);
            holder.rv_imgs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            List<String> path_list = new ArrayList<>();
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
            ImageAdapter imageAdapter = new ImageAdapter(context, path_list);
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
        CircleImageView iv_header;
        TextView tv_name;
        TextView tv_time;
        TextView tv_content;
        RecyclerView rv_imgs;
        LinearLayout ll_img;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_order_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            rv_imgs = itemView.findViewById(R.id.rv_img);
            ll_img = itemView.findViewById(R.id.ll_img);
        }
    }
}
