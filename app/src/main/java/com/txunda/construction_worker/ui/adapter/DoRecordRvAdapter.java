package com.txunda.construction_worker.ui.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.DoRecordBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DoRecordRvAdapter extends BaseQuickAdapter<DoRecordBean.DataBean.RecordBean,BaseViewHolder>{

    public DoRecordRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoRecordBean.DataBean.RecordBean item) {
        TextView tv_type = helper.itemView.findViewById(R.id.item_do_record_tv_sum);
        if (item.getDone().equals("1")){
            tv_type.setText("未完成");
            tv_type.setTextColor(Color.parseColor("#FFDA0F"));
        }else {
            tv_type.setText("共"+item.getCount()+"道,做对"+item.getRight()+"道");
        }
        helper.setText(R.id.item_do_record_title,item.getTy_name())
                .setText(R.id.item_do_record_tv_time,item.getCreate_time());
    }
}