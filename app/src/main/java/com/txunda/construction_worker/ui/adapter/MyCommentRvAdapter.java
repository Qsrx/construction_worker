package com.txunda.construction_worker.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.MyCommentBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 18:13:20.
 * 功能描述：我的评论
 * 联系方式： win_hzy@163.com
 */
public class MyCommentRvAdapter extends BaseQuickAdapter<MyCommentBean.DataBean,BaseViewHolder>{

    public MyCommentRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCommentBean.DataBean item) {
        CircleImageView pic = helper.itemView.findViewById(R.id.iv_order_head);
        CircleImageView user_pic = helper.itemView.findViewById(R.id.iv_order_head_user);
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.rv_img);
        LinearLayout linearLayout = helper.itemView.findViewById(R.id.ll_img);
        Glide.with(mContext).load(item.getHead_pic()).into(pic);
        Glide.with(mContext).load(item.getOther_pic()).into(user_pic);
        helper.setText(R.id.tv_name,item.getNickname())
        .setText(R.id.tv_time,item.getCreate_time())
        .setText(R.id.tv_content,item.getReplay_content())
        .setText(R.id.tv_name_user,item.getOther_nickname())
        .setText(R.id.tv_content_user,item.getOther_content());
//        if (item.getContent_pic().size()>0){
//            linearLayout.setVisibility(View.VISIBLE);
//            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//            List<String> img_list = new ArrayList<>();
//            for (int i = 0; i < item.getContent_pic().size(); i++) {
//                img_list.add(item.getContent_pic().get(i).getPath());
//            }
//            ImageAdapter imageAdapter = new ImageAdapter(mContext, img_list);
//            recyclerView.setAdapter(imageAdapter);
//        }else {
            linearLayout.setVisibility(View.GONE);
//        }
    }
}