package com.txunda.construction_worker.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.WatchingHistoryBean;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class WatchingHistoryRvAdapter extends BaseQuickAdapter<WatchingHistoryBean.DataBean,BaseViewHolder>{

    public WatchingHistoryRvAdapter(int layoutResId) {
        super(R.layout.watching_history_rv_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper,WatchingHistoryBean.DataBean item) {
        ShapedImageView siv = helper.itemView.findViewById(R.id.watching_history_siv_header);
        Glide.with(mContext).load(item.getPic()).into(siv);
        helper.setText(R.id.watching_history_title,item.getTitle())
                .setText(R.id.watching_history_tv_menu,item.getMulu())
                .setText(R.id.watching_history_tv_time,item.getTime());

    }
}