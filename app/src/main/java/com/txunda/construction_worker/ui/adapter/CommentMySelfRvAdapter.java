package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.CommentMySelfBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：评论我的
 * 联系方式： win_hzy@163.com
 */
public class CommentMySelfRvAdapter extends BaseQuickAdapter<CommentMySelfBean.DataBean,BaseViewHolder>{

    public CommentMySelfRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentMySelfBean.DataBean item) {
        CircleImageView civ = helper.itemView.findViewById(R.id.iv_order_head);
        Glide.with(mContext).load(item.getHead_pic()).into(civ);
        helper.setText(R.id.tv_name,item.getNickname())
                .setText(R.id.tv_time,item.getCreate_time())
                .setText(R.id.tv_content,item.getReplay_content());
    }
}