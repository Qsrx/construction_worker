//package com.txunda.construction_worker.ui.aty;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.txunda.businesstour.R;
//import com.txunda.businesstour.bean.AllTalkBean;
//import com.txunda.construction_worker.ui.adapter.ImageAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
///**
// * 开发者： Hzy
// * 创建时间： 2018/12/14 014 11:31:33.
// * 功能描述：
// * 联系方式： win_hzy@163.com
// */
//public class AllTalkAdapter extends RecyclerView.Adapter<AllTalkAdapter.ViewHolder>{
//    private Context context;
//    private List<AllTalkBean.DataBean> list;
//
//    public AllTalkAdapter(Context context, List<AllTalkBean.DataBean> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_forum_fabu,parent,false);
//        return new AllTalkAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getHead_pic()).into(holder.iv_header);
//        holder.tv_name.setText(list.get(position).getNickname());
//        holder.ratingBar.setRating(new Float(list.get(position).getStar()));
//        holder.tv_content.setText(list.get(position).getContent());
//        holder.tv_time.setText(list.get(position).getCreate_time());
//        if (list.get(position).getAppraise_pic().get(0).getPath().length()>1){
//            holder.ll_img.setVisibility(View.VISIBLE);
//            holder.rv_imgs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//            List<String> img_list = new ArrayList<>();
//            for (int i = 0; i < list.get(position).getAppraise_pic().size(); i++) {
//                img_list.add(list.get(position).getAppraise_pic().get(i).getPath());
//            }
//            ImageAdapter imageAdapter = new ImageAdapter(context, img_list);
//            holder.rv_imgs.setAdapter(imageAdapter);
//        }else {
//            holder.ll_img.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        CircleImageView iv_header;
//        TextView tv_name;
//        TextView tv_time;
//        RatingBar ratingBar;
//        TextView tv_content;
//        RecyclerView rv_imgs;
//        LinearLayout ll_img;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            iv_header = itemView.findViewById(R.id.iv_order_head);
//            tv_name = itemView.findViewById(R.id.tv_name);
//            tv_time = itemView.findViewById(R.id.tv_time);
//            tv_content = itemView.findViewById(R.id.tv_content);
//            ratingBar = itemView.findViewById(R.id.item_talk_star);
//            rv_imgs = itemView.findViewById(R.id.rv_img);
//            ll_img = itemView.findViewById(R.id.ll_img);
//        }
//    }
//}
