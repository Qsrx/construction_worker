package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.SelectionSubjectBean;

import java.util.List;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/26 026 14:46:35.
 * 功能描述：选择科目适配器
 * 联系方式： win_hzy@163.com
 */
public class SelectionSubjectsAdapter extends BaseAdapter{
    private List<SelectionSubjectBean.DataBean.ListBean> list;
    private Context context;

    public SelectionSubjectsAdapter(List<SelectionSubjectBean.DataBean.ListBean> list, Context context) {
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
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.slection_subjects_content_layout,null);
            vh.siv = view.findViewById(R.id.selection_subjects_content_pic);
            vh.tv_title = view.findViewById(R.id.selection_subjects_content_title);
            vh.tv_price = view.findViewById(R.id.selection_subjects_content_price);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getZu_pic()).into(vh.siv);
        vh.tv_title.setText(list.get(i).getTitle());
        vh.tv_price.setText("¥ "+list.get(i).getPrice());
        return view;
    }
    class ViewHolder{
        ShapedImageView siv;
        TextView tv_title;
        TextView tv_price;
    }
}