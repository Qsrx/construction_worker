package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.txunda.construction_worker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 14:20:19.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class LtAdapter extends BaseAdapter{
    private List<String> list;
    private Context context;

    public LtAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.home_lt_layout,null);
            vh = new ViewHolder();
            vh.ll_imgs = view.findViewById(R.id.ll_img);
            vh.recyclerView = view.findViewById(R.id.rv_img);
            vh.tv_talk = view.findViewById(R.id.home_it_talk);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        if (list.get(i).equals("2")||list.get(i).equals("5")){
            vh.ll_imgs.setVisibility(View.GONE);
        }else {
            vh.ll_imgs.setVisibility(View.VISIBLE);
            vh.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            List<String> path_list = new ArrayList<>();
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
            path_list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
            ImageAdapter imageAdapter = new ImageAdapter(context, path_list);
            vh.recyclerView.setAdapter(imageAdapter);
        }
        vh.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    class ViewHolder{
        RecyclerView recyclerView;
        LinearLayout ll_imgs;
        TextView tv_talk;
    }
}