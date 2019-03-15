package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ClassBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SystemClassRvAdapter extends BaseQuickAdapter<ClassBean.DataBean.SystemBean,BaseViewHolder>{

    public SystemClassRvAdapter(int layoutResId) {
        super(R.layout.system_class_rv_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper,ClassBean.DataBean.SystemBean item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.system_class_rv_siv);
        Glide.with(mContext).load(item.getZu_pic()).into(siv);
        helper.setText(R.id.system_class_rv_title,item.getTitle())
        .setText(R.id.system_class_rv_price,"¥ "+item.getPrice())
        .setText(R.id.system_class_rv_pay,item.getNum());
    }

}