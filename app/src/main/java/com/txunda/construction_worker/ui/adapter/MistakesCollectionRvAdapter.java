package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.MistakesCollectionBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MistakesCollectionRvAdapter extends BaseQuickAdapter<MistakesCollectionBean.DataBean.ListBean,BaseViewHolder>{

    public MistakesCollectionRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MistakesCollectionBean.DataBean.ListBean item) {
        helper.setText(R.id.item_miss_tv_title,item.getTitle())
                .setText(R.id.item_miss_tv_s_title,item.getSubtitle());
    }
}