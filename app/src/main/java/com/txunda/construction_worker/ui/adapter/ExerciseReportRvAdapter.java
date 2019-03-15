package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ExerciseReportBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：练习报告适配器
 * 联系方式： win_hzy@163.com
 */
public class ExerciseReportRvAdapter extends BaseQuickAdapter<ExerciseReportBean.DataBean.ListBean,BaseViewHolder>{

    public ExerciseReportRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExerciseReportBean.DataBean.ListBean item) {
        helper.setText(R.id.item_dtk_tv,item.getSort());
        if (item.getIs_right().equals("1")){
            helper.setBackgroundRes(R.id.item_dtk_tv,R.drawable.shape_ching);
        }else if (item.getIs_right().equals("2")){
            helper.setBackgroundRes(R.id.item_dtk_tv,R.drawable.shape_orange);
        }else {
            helper.setBackgroundRes(R.id.item_dtk_tv,R.drawable.shape_unselect);
        }
    }
}