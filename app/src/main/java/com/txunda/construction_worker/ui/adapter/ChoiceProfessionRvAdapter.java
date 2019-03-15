package com.txunda.construction_worker.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.ChoiceProfessionBean;
import com.txunda.construction_worker.ui.fgt.HomeFgt;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：选择专业适配器
 * 联系方式： win_hzy@163.com
 */
public class ChoiceProfessionRvAdapter extends BaseQuickAdapter<ChoiceProfessionBean.DataBean,BaseViewHolder>{

    public ChoiceProfessionRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChoiceProfessionBean.DataBean item) {
        ImageView pic = helper.itemView.findViewById(R.id.item_select_class_iv_pic);
        TextView tv_title = helper.itemView.findViewById(R.id.item_select_class_name);
        try {
            subject_id = null;
            if (HomeFgt.Industry_ID.equals(item.getId())){
                Glide.with(mContext).load(item.getIndustry_pic()).into(pic);
//                tv_title.setTextColor(Color.parseColor("#FFDA0F"));
            }else {
                Glide.with(mContext).load(item.getGeneral_pic()).into(pic);
//                tv_title.setTextColor(Color.parseColor("#333333"));
            }
        }catch (NullPointerException e){
            Glide.with(mContext).load(item.getGeneral_pic()).into(pic);
        }
//        helper.setText(R.id.item_select_class_name,item.getIname());
        tv_title.setText(item.getIname());
    }
}