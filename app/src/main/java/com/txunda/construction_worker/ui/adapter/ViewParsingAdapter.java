package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txunda.construction_worker.R;

import java.util.List;

import static com.txunda.construction_worker.ui.aty.ViewParsingAty.STR;
import static com.txunda.construction_worker.ui.aty.ViewParsingAty.select_index;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ViewParsingAdapter extends RecyclerView.Adapter<ViewParsingAdapter.ViewHolder>{
    private List<String> list;
    private List<String> title;
    private Context context;

    public ViewParsingAdapter(List<String> list, List<String> title, Context context) {
        this.list = list;
        this.title = title;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_do_work_small_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_number.setText(list.get(position));
        holder.tv_title.setText(title.get(position));
        if (select_index!=-1){
            if (position == select_index){
                holder.tv_number.setBackgroundResource(R.drawable.shape_select);
                holder.tv_title.setTextColor(Color.parseColor("#333333"));
            }else {
                holder.tv_number.setBackgroundResource(R.drawable.shape_unselect);
                holder.tv_title.setTextColor(Color.parseColor("#666666"));
            }
        }else {
            if (STR.contains(list.get(position))){
                holder.tv_number.setBackgroundResource(R.drawable.shape_select);
                holder.tv_title.setTextColor(Color.parseColor("#333333"));
            }else {
                holder.tv_number.setBackgroundResource(R.drawable.shape_unselect);
                holder.tv_title.setTextColor(Color.parseColor("#666666"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_number;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_dowork_small_title);
            tv_number = itemView.findViewById(R.id.item_dowork_small_number);
        }
    }
}