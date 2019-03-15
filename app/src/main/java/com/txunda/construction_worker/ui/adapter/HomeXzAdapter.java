package com.txunda.construction_worker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.txunda.construction_worker.R;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 11:34:03.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class HomeXzAdapter extends BaseAdapter{
    private List<String> list;
    private Context context;

    public HomeXzAdapter(List<String> list, Context context) {
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
        view = LayoutInflater.from(context).inflate(R.layout.home_xz_layout,null);
        return view;
    }
}
