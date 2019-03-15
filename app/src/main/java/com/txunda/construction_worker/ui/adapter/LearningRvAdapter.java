package com.txunda.construction_worker.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.CourseSelectionBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：学习资料适配器
 * 联系方式： win_hzy@163.com
 */
public class LearningRvAdapter extends BaseQuickAdapter<CourseSelectionBean.DataBean.PptBean,BaseViewHolder>{

    public LearningRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseSelectionBean.DataBean.PptBean item) {
        ImageView iv = helper.itemView.findViewById(R.id.item_learning_img_iv);
        Glide.with(mContext).load(item.getPath()).into(iv);
    }
}