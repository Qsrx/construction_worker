package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.CurriculumBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：课程列表适配器
 * 联系方式： win_hzy@163.com
 */
public class CurriculumRvAdapter extends BaseQuickAdapter<CurriculumBean.DataBean,BaseViewHolder>{

    public CurriculumRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CurriculumBean.DataBean item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.selection_subjects_content_pic);
        Glide.with(mContext).load(item.getPic()).into(siv);
        helper.setText(R.id.selection_subjects_content_title,item.getTitle())
                .setText(R.id.selection_subjects_content_price,"¥ "+item.getPrice());
    }
}