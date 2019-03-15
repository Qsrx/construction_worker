package com.txunda.construction_worker.ui.adapter;

import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.AllTalkBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/14 014 11:31:33.
 * 功能描述：课程评价适配器
 * 联系方式： win_hzy@163.com
 */
public class DtkAdapter extends BaseQuickAdapter<AllTalkBean.DataBean,BaseViewHolder> {

    public DtkAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllTalkBean.DataBean item) {
        CircleImageView civ = helper.itemView.findViewById(R.id.iv_order_head);
        RatingBar ratingBar = helper.itemView.findViewById(R.id.item_talk_star);
        ratingBar.setRating(new Float(new Integer(item.getStar())/2));
        Glide.with(mContext).load(item.getPic()).into(civ);
        helper.setText(R.id.tv_name,item.getNickname())
                .setText(R.id.tv_time,item.getCreate_time())
                .setText(R.id.tv_content,item.getContent());
    }
}