package com.txunda.construction_worker.ui.adapter;

import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.CompleteBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：离线缓存适配器
 * 联系方式： win_hzy@163.com
 */
public class CompleteRvAdapter extends BaseQuickAdapter<CompleteBean.DataBean,BaseViewHolder>{

    public CompleteRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CompleteBean.DataBean item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.selection_subjects_content_pic);
        TextView tv_sub = helper.itemView.findViewById(R.id.selection_subjects_content_kd);
        Glide.with(mContext).load(item.getPic()).into(siv);
        if (item.getCount() == null){
            item.setCount("0");
        }
        if (item.getCache_count() == null){
            item.setCache_count("0");
        }
        Log.d("comwaewadadw", "convert: ============"+"共"+item.getCount()+"节,已缓存"+item.getCache_count()+"节");
        String s = "共" + item.getCount() + "节,已缓存" + item.getCache_count() + "节";
        helper.setText(R.id.selection_subjects_content_title,item.getTitle());
        tv_sub.setText(s);

    }
}