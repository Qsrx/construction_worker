package com.txunda.construction_worker.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.SpecialExercisesBean;
import com.txunda.construction_worker.ui.aty.SpecialExercisesAty;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SpecialExercisesRvAdapter extends BaseQuickAdapter<SpecialExercisesBean.DataBean.ContentBean,BaseViewHolder>{

    public SpecialExercisesRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialExercisesBean.DataBean.ContentBean item) {
        TextView tv_title = helper.itemView.findViewById(R.id.item_special_exercises_title);
        TextView tv_sum = helper.itemView.findViewById(R.id.item_special_exercises_sum);
        TextView tv_test = helper.itemView.findViewById(R.id.item_special_exercises_test);
        tv_sum.setText("共"+item.getCount()+"题");
        if (item.getTitle().equals("1")) {
            tv_title.setText("单选题");
        }else if (item.getTitle().equals("2")){
            tv_title.setText("多选题");
        }else if (item.getTitle().equals("3")){
            tv_title.setText("判断题");
        }else if (item.getTitle().equals("4")){
            tv_title.setText("案例分析题");
        }
        if (SpecialExercisesAty.is_pay == 1){
//            tv_test.setVisibility(View.GONE);
            tv_test.setText("开始答题");
        }else {
            tv_test.setVisibility(View.VISIBLE);
        }
    }
}