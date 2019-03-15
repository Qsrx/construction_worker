package com.txunda.construction_worker.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.OrderBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：我的订单适配器
 * 联系方式： win_hzy@163.com
 */
public class MyOrderRvAdapter extends BaseQuickAdapter<OrderBean.DataBean,BaseViewHolder>{

    public MyOrderRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.DataBean item) {
        TextView tv_state = helper.itemView.findViewById(R.id.item_order_state);
        TextView tv_right = helper.itemView.findViewById(R.id.item_order_right);
        TextView tv_left = helper.itemView.findViewById(R.id.item_order_left);
        TextView tv_success = helper.itemView.findViewById(R.id.item_order_success);

        ShapedImageView siv = helper.itemView.findViewById(R.id.item_order_pic);
        Glide.with(mContext).load(item.getPic()).into(siv);
        helper.setText(R.id.item_order_type,item.getTitle())
        .setText(R.id.item_order_name,item.getSub_title())
        .setText(R.id.item_order__price,"¥ "+item.getPrice())
        .setText(R.id.order_context_state_type,item.getSon_title())
        .setText(R.id.item_order_price_sum,"合计：¥"+item.getPrice())
        .addOnClickListener(R.id.item_order_right)
        .addOnClickListener(R.id.item_order_left);
//        .addOnClickListener(R.id.item_order_success);
        if (item.getPay_status().equals("1")){
            tv_state.setText("待支付");
        }else if (item.getPay_status().equals("2")){
            tv_state.setText("已购买");
            if (item.getEvaluate() == 1){
                tv_right.setVisibility(View.GONE);
                tv_left.setVisibility(View.GONE);
                tv_success.setVisibility(View.VISIBLE);
            }else {
                tv_right.setText("去评价");
                tv_left.setVisibility(View.GONE);
            }
        }else {
            tv_state.setText("已取消");
        }
    }
}