package com.txunda.construction_worker.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.SearchHotBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：正在缓存适配器
 * 联系方式： win_hzy@163.com
 */
public class SearchRvAdapter extends BaseQuickAdapter<SearchHotBean.DataBean,BaseViewHolder>{

    public SearchRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHotBean.DataBean item) {
        TextView tv = helper.itemView.findViewById(R.id.aty_search_tv_class);
        ImageView imageView = helper.itemView.findViewById(R.id.aty_search_iv_class);
        tv.setText(item.getName());
        if (item.getSort().equals("1")){
            tv.setBackgroundResource(R.drawable.shape_search_hot_bg);
            imageView.setVisibility(View.VISIBLE);
        }else {
            tv.setBackgroundResource(R.drawable.shape_search_bg);
            imageView.setVisibility(View.INVISIBLE);
        }

    }
}