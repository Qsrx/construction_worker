package com.txunda.construction_worker.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.TrueQuestionsBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class TestQuestionsRvAdapter extends BaseQuickAdapter<TrueQuestionsBean.DataBean.ListBean,BaseViewHolder>{

    public TestQuestionsRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrueQuestionsBean.DataBean.ListBean item) {
        TextView tv_start = helper.itemView.findViewById(R.id.item_test_tv_start);
        ImageView iv_lock = helper.itemView.findViewById(R.id.item_test_iv_lock);
        helper.setText(R.id.item_test_tv_title,item.getTitle());
        if (item.getIs_fee() == 1){
            tv_start.setText("开始答题");
            iv_lock.setVisibility(View.GONE);
        }else {
            tv_start.setText("");
            iv_lock.setVisibility(View.VISIBLE);
        }
    }
}