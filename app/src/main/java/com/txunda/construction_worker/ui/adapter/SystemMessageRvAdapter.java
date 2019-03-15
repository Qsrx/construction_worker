package com.txunda.construction_worker.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.SystemMessageBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：系统消息列表适配器
 * 联系方式： win_hzy@163.com
 */
public class SystemMessageRvAdapter extends BaseQuickAdapter<SystemMessageBean.DataBean,BaseViewHolder>{

    public SystemMessageRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMessageBean.DataBean item) {
        CircleImageView civ = helper.itemView.findViewById(R.id.item_system_message_point);
        //is_read=1未读or已读
        if (item.getIs_read() == 1) {
            civ.setVisibility(View.VISIBLE);
        }else {
            //隐藏且占位
            civ.setVisibility(View.INVISIBLE);
        }
        helper.setText(R.id.item_system_message_content,item.getAct_msg_content())
                .setText(R.id.item_system_message_time,item.getCreate_time())
                .setText(R.id.item_system_message_title,item.getAct_msg_title());
    }
}