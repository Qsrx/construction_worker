package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.CourseSelectionBean;
import com.txunda.construction_worker.ui.fgt.CourseSelectionFgt;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class CoureseRvAdapter extends RecyclerView.Adapter<CoureseRvAdapter.ViewHolder>{
    private List<CourseSelectionBean.DataBean.CourseListBean> list;
    private Context context;

    public CoureseRvAdapter(List<CourseSelectionBean.DataBean.CourseListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course_top_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (list.get(position).getType().equals("2")){
            holder.imageView.setVisibility(View.VISIBLE);
            holder.tv_title.setVisibility(View.GONE);
        }else {
            holder.tv_title.setVisibility(View.VISIBLE);
            holder.tv_title.setText(list.get(position).getCourse());
            holder.imageView.setVisibility(View.GONE);
        }
        if (position == CourseSelectionFgt.index){
            holder.tv_title.setTextColor(Color.parseColor("#FFDA0F"));
        }else {
            holder.tv_title.setTextColor(Color.parseColor("#333333"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myItemClick.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_course_top_tv);
            imageView = itemView.findViewById(R.id.item_course_top_lock);
        }
    }
private MyItemClick myItemClick;

    public interface MyItemClick{
        void onItemClick(View view, int postion);
    }
    public void setOnItemClick(MyItemClick myItemClick){
        this.myItemClick = myItemClick;
    }
}