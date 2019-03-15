package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.MyClassFBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MyClassRvAdapter extends BaseQuickAdapter<MyClassFBean.DataBean.MyCourseBean,BaseViewHolder>{

    public MyClassRvAdapter(int layoutResId) {
        super(R.layout.my_class_rv_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper,MyClassFBean.DataBean.MyCourseBean item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.my_class_rv_siv);
        Glide.with(mContext).load(item.getZu_pic()).into(siv);
        helper.setText(R.id.my_class_rv_tv_title,item.getTitle());
    }

}