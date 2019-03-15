package com.txunda.construction_worker.ui.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.RedPackageBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class RedPackageRvAdapter extends BaseQuickAdapter<RedPackageBean.DataBean.ContentBean,BaseViewHolder>{

    public RedPackageRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RedPackageBean.DataBean.ContentBean item) {
        TextView tv_num = helper.itemView.findViewById(R.id.item_red_package_num);
        helper.setText(R.id.item_red_package_num,item.getMoney())
        .setText(R.id.item_red_package_time,item.getCreate_time())
        .setText(R.id.item_red_package_title,item.getTitle());
        if (item.getSymbol().equals("+")){
            tv_num.setTextColor(Color.parseColor("#FF7800"));
        }else {
            tv_num.setTextColor(Color.parseColor("#333333"));
        }
    }
}