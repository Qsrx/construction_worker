package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ExercisesBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：课程列表适配器
 * 联系方式： win_hzy@163.com
 */
public class ExercisesRvAdapter extends BaseQuickAdapter<ExercisesBean.DataBean,BaseViewHolder>{

    public ExercisesRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExercisesBean.DataBean item) {
        helper.setText(R.id.item_exercise_tv_title,item.getTitle());
    }
}