package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.EverydayPunchTestBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：每日打卡适配器
 * 联系方式： win_hzy@163.com
 */
public class DkRvAdapter extends BaseQuickAdapter<EverydayPunchTestBean.DataBean.ListBean,BaseViewHolder>{

    public DkRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EverydayPunchTestBean.DataBean.ListBean item) {
        helper.setText(R.id.item_every_dk_time,item.getTitle());
    }
}