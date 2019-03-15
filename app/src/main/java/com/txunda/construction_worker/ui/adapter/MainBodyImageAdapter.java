package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/28 028 11:04:09.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MainBodyImageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MainBodyImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.iv_pic);
        Glide.with(siv.getContext())
                .load(item)
                .into(siv);
    }
}