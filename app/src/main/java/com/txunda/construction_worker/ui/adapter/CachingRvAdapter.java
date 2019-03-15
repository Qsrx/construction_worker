package com.txunda.construction_worker.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：正在缓存适配器
 * 联系方式： win_hzy@163.com
 */
public class CachingRvAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public CachingRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}