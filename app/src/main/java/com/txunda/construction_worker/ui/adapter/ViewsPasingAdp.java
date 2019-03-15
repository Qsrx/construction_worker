package com.txunda.construction_worker.ui.adapter;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.PanDuanBean;
import com.txunda.construction_worker.utils.PanDuanUtils;

import java.util.Map;


/**
 * @author dell-pc
 * @date 2019/2/14
 */

public class ViewsPasingAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public ViewsPasingAdp(int item_do_work_small_layout) {
        super(item_do_work_small_layout);
    }
    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        TextView item_dowork_small_number = helper.itemView.findViewById(R.id.item_dowork_small_number);
        TextView item_dowork_small_title = helper.itemView.findViewById(R.id.item_dowork_small_title);
        Log.d("viewspasingada", "convert: =========="+item);
        helper.setText(R.id.item_dowork_small_number, item.get("answer"))
                .setText(R.id.item_dowork_small_title, item.get("question"));
        int xz = Integer.valueOf(item.get("xz"));
        PanDuanBean panDuanBean = PanDuanUtils.views_list.get(xz);
        if (panDuanBean.user.contains(item.get("answer"))){
            item_dowork_small_number.setBackgroundResource(R.drawable.shape_select);
            item_dowork_small_title.setTextColor(Color.parseColor("#333333"));
        }else {
            item_dowork_small_number.setBackgroundResource(R.drawable.shape_unselect);
            item_dowork_small_title.setTextColor(Color.parseColor("#666666"));
        }
    }
}
