package com.txunda.construction_worker.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.MyQuizBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：我的提问适配器
 * 联系方式： win_hzy@163.com
 */
public class MyQuizRvAdapter extends BaseQuickAdapter<MyQuizBean.DataBean,BaseViewHolder>{

    public MyQuizRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyQuizBean.DataBean item) {
        CircleImageView pic = helper.itemView.findViewById(R.id.iv_order_head);
        RadioButton radioButton = helper.itemView.findViewById(R.id.home_it_zan);
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.rv_img);
        LinearLayout linearLayout = helper.itemView.findViewById(R.id.ll_img);
        Glide.with(mContext).load(item.getHead_pic()).into(pic);
        if (item.getIs_like() == 1){
            radioButton.setChecked(true);
            radioButton.setTextColor(Color.parseColor("#FF7800"));
        }else if (item.getIs_like() == 2){
            radioButton.setChecked(false);
            radioButton.setTextColor(Color.parseColor("#666666"));
        }
        helper.setText(R.id.tv_name,item.getNickname())
                .setText(R.id.tv_time,item.getCreate_time())
                .setText(R.id.home_it_zan,item.getSupport())
                .setText(R.id.tv_content,item.getContent())
                .setText(R.id.home_it_talk,item.getCount())
                .addOnClickListener(R.id.home_it_zan);
//                .addOnClickListener(R.id.home_lt_rl_talk);
        if (item.getContent_pic().size()>0){
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            List<String> img_list = new ArrayList<>();
            for (int i = 0; i < item.getContent_pic().size(); i++) {
                img_list.add(item.getContent_pic().get(i).getPath());
            }
            ImageAdapter imageAdapter = new ImageAdapter(mContext, img_list);
            recyclerView.setAdapter(imageAdapter);
        }else {
            linearLayout.setVisibility(View.GONE);
        }
    }
}