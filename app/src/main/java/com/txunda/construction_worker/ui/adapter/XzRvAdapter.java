package com.txunda.construction_worker.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.HomeBean;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：须知/动态/考点 适配器
 * 联系方式： win_hzy@163.com
 */
public class XzRvAdapter extends BaseQuickAdapter<HomeBean.DataBean.ArticleBean,BaseViewHolder>{

    public XzRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ArticleBean item) {
        ImageView iv_pic = helper.itemView.findViewById(R.id.home_xz_pic);
        Glide.with(mContext).load(item.getCover()).into(iv_pic);
        helper.setText(R.id.home_xz_tv_title,item.getTitle())
                .setText(R.id.home_xz_create_time,item.getCreate_time());
    }
}