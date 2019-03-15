package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ChapterExercisesBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：章节练习题适配器
 * 联系方式： win_hzy@163.com
 */
public class ChapterExercisesRvAdapter extends BaseQuickAdapter<ChapterExercisesBean.DataBean,BaseViewHolder>{

    public ChapterExercisesRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChapterExercisesBean.DataBean item) {
        helper.setText(R.id.item_chapter_exercises_title,item.getTitle())
                .setText(R.id.item_chapter_exercises_sum,"共"+item.getCount()+"道题");
    }
}