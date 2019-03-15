package com.txunda.construction_worker.ui.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.utils.PanDuanUtils;

import java.util.Map;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：离线缓存适配器
 * 联系方式： win_hzy@163.com
 */
public class DtkRvAdapter extends BaseQuickAdapter<Map<String, String>,BaseViewHolder>{

    public DtkRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
//        helper.setText(R.id.item_dtk_tv,item.get("questions_id"));
        int xunze = Integer.valueOf(item.get("xunze"));
        Log.d("dtkrvdadta", "convert: ==========="+PanDuanUtils.list.get(xunze).user);
        helper.setText(R.id.item_dtk_tv,String.valueOf(xunze+1));
        if (PanDuanUtils.list.get(xunze).user .length()>0){
            helper.setBackgroundRes(R.id.item_dtk_tv,R.drawable.shape_select);
        }else {
            helper.setBackgroundRes(R.id.item_dtk_tv,R.drawable.shape_unselect);
        }
    }
}