package com.txunda.construction_worker.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

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
public class TextRvAdapter extends BaseQuickAdapter<CourseSelectionBean.DataBean.CourseListBean,BaseViewHolder>{

    public TextRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseSelectionBean.DataBean.CourseListBean item) {
        ImageView iv_lock = helper.itemView.findViewById(R.id.item_course_top_lock);
        TextView tv_title = helper.itemView.findViewById(R.id.item_course_top_tv);
        if (item.getType().equals("1")){

        }
    }
}