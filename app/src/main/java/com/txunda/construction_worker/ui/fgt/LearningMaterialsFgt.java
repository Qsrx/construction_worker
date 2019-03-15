package com.txunda.construction_worker.ui.fgt;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.CourseSelectionBean;
import com.txunda.construction_worker.ui.adapter.LearningRvAdapter;
import com.txunda.construction_worker.ui.aty.AllTalkAty;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 学习资料fragment
 */
@Layout(R.layout.fgt_learning_materials)
public class LearningMaterialsFgt extends BaseFgt {
    LinearLayout ll_see_all,ll_talk;
    CircleImageView civ;
    TextView tv_name,tv_time,tv_content,tv_noppt;
    RatingBar ratingBar;
    RecyclerView rv;
    LearningRvAdapter adapter;
    @Override
    public void initViews() {
        super.initViews();
        ll_see_all = findViewById(R.id.fgt_learning_materials_see_all);
        civ = findViewById(R.id.iv_order_head);
        tv_name = findViewById(R.id.tv_name);
        tv_time = findViewById(R.id.tv_time);
        tv_content = findViewById(R.id.tv_content);
        ll_talk = findViewById(R.id.fgt_learning_materials_ll_talk);
        tv_noppt = findViewById(R.id.fgt_learning_materials_tv_noppt);
        ratingBar = findViewById(R.id.item_talk_star);
        rv = findViewById(R.id.fgt_learning_materials_rl);
        rv.setLayoutManager(new LinearLayoutManager(me));
        ll_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump(AllTalkAty.class);
            }
        });
        adapter = new LearningRvAdapter(R.layout.item_learning_img_layout);
    }
    @Override
    public void initDatas() {
        super.initDatas();
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Course_selection", new Parameter()
                        .add("token", token)
                        .add("subject_id", CourseSelectionAty.subject_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    CourseSelectionBean selectionBean = GsonUtil.GsonToBean(response, CourseSelectionBean.class);
                                    adapter.setNewData(selectionBean.getData().getPpt());
                                    rv.setAdapter(adapter);
                                    List<CourseSelectionBean.DataBean.PptBean> data = adapter.getData();
                                    for (int i = 0; i < data.size(); i++) {
                                        Log.d("learningdata", "onResponse: ==========="+data.get(i).getPath());
                                    }
                                    if (isNull(selectionBean.getData().getComment().get(0).getContent())||selectionBean.getData().getComment().get(0).getContent().equals(null)){
                                        ll_talk.setVisibility(View.GONE);
                                    }else {
                                        try {
                                            Glide.with(me).load(selectionBean.getData().getComment().get(0).getHead_pic()).into(civ);
                                            tv_name.setText(selectionBean.getData().getComment().get(0).getNickname());
                                            tv_time.setText(selectionBean.getData().getComment().get(0).getCreate_time());
                                            tv_content.setText(selectionBean.getData().getComment().get(0).getContent());
                                            ratingBar.setRating(Float.valueOf(selectionBean.getData().getComment().get(0).getStar()));
                                        }catch (NullPointerException e){
                                            ll_talk.setVisibility(View.GONE);
                                    }
                                    }


                                }
                            } catch (NullPointerException e) {
                                ll_talk.setVisibility(View.GONE);
                            }

                        }
                    }
                }
        );
    }
}