package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.WatchingHistoryBean;

import java.util.List;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/7 007 20:24:50.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class WatchingHistoryLvAdp extends BaseAdapter{
    List<WatchingHistoryBean.DataBean> list;
    Context context;

    public WatchingHistoryLvAdp(List<WatchingHistoryBean.DataBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.watching_history_rv_layout,null);
            vh = new ViewHolder();
            vh.siv = view.findViewById(R.id.watching_history_siv_header);
            vh.tv_title = view.findViewById(R.id.watching_history_title);
            vh.tv_mule = view.findViewById(R.id.watching_history_tv_menu);
            vh.tv_time = view.findViewById(R.id.watching_history_tv_time);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getPic()).into(vh.siv);
        vh.tv_title.setText(list.get(i).getTitle());
        vh.tv_mule.setText(list.get(i).getMulu());
        vh.tv_time.setText(list.get(i).getTime());
        return view;
    }
    private class ViewHolder{
        ShapedImageView siv;
        TextView tv_title;
        TextView tv_mule;
        TextView tv_time;
    }
}
