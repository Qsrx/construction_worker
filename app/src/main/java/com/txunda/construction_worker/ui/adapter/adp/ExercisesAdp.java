package com.txunda.construction_worker.ui.adapter.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ExercisesBean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/9 009 16:14:10.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ExercisesAdp extends BaseAdapter{
    List<ExercisesBean.DataBean> list;
    Context context;

    public ExercisesAdp(List<ExercisesBean.DataBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_exercises_layout,null);
            vh = new ViewHolder();
            vh.tv_title = view.findViewById(R.id.item_exercise_tv_title);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tv_title.setText(list.get(i).getTitle());
        return view;
    }
    private class ViewHolder{
        TextView tv_title;
    }
}
