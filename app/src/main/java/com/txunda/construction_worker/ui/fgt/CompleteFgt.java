package com.txunda.construction_worker.ui.fgt;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.CompleteBean;
import com.txunda.construction_worker.ui.adapter.CompleteRvAdapter;
import com.txunda.construction_worker.ui.aty.CachingAty;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
@Layout(R.layout.fgt_complete)
public class CompleteFgt extends BaseFgt {
    private String type;

    @SuppressLint("ValidFragment")
    public CompleteFgt(String type) {
        this.type = type;
    }

    RecyclerView recyclerView;
    private CompleteRvAdapter adapter;
    private RelativeLayout relativeLayout;
    private SmartRefreshLayout refreshLayout;
    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_curriculum_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new CompleteRvAdapter(R.layout.item_offline_cache_layout);
        relativeLayout = findViewById(R.id.fgt_complte_no_data);
        refreshLayout = findViewById(R.id.fgt_curriculum_refreshLayout);
        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i+"");
//        }
        recyclerView.setAdapter(adapter);
//        adapter.setNewData(list);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CompleteBean.DataBean> data = adapter.getData();
                if (type.equals("1")){
//                    jump(CourseCacheDetailsAty.class);
                    Intent intent = new Intent(me, CourseSelectionAty.class);
                    intent.putExtra("subject_id",data.get(position).getSubject_id());
                    startActivity(intent);
                }else {
//                    jump(CachingAty.class);
                    Intent intent = new Intent(me, CachingAty.class);
                    intent.putExtra("id",data.get(position).getSubject_id());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    private void httpData(){
        Log.d("compltedata", "onResponse: =========="+token);
        Log.d("type", "onResponse: =========="+type);
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/cache_completed", new Parameter()
                        .add("token", token)
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("compltedata", "onResponse: =========="+response);
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            CompleteBean completeBean = GsonUtil.GsonToBean(response,CompleteBean.class);
                            if (completeBean.getData().size() == 0|| completeBean.getData() == null){
                                relativeLayout.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
                                return;
                            }
                            adapter.setNewData(completeBean.getData());
                            return;
                        }
                        relativeLayout.setVisibility(View.VISIBLE);
                        refreshLayout.setVisibility(View.GONE);
                        toast(objectMap.get("message"));
                    }
                });
    }
}