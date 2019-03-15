package com.txunda.construction_worker.ui.fgt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.HomeBean;
import com.txunda.construction_worker.ui.adapter.LtRvAdapter;
import com.txunda.construction_worker.ui.aty.MainBodyAty;
import com.txunda.construction_worker.ui.view.NoScrollListview;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Layout(R.layout.fgt_lt)
public class LtFgt extends BaseFgt {
    RecyclerView recyclerView;
    List<String> list = new ArrayList<>();
    NoScrollListview lv;
    private LtRvAdapter adapter;
    RadioButton radioButton;
    private List<HomeBean.DataBean.BbsBean> data = null;
    private SmartRefreshLayout smartRefreshLayout;
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_lt_rv);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new LtRvAdapter(R.layout.home_lt_layout);
        recyclerView.setAdapter(adapter);
        smartRefreshLayout = me.findViewById(R.id.fgt_home_smartrefresh);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (data == null){
                    data = adapter.getData();
//                }
                Intent intent = new Intent(me, MainBodyAty.class);
                intent.putExtra("bbs_id",data.get(position).getId());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (data == null){
                    data = adapter.getData();
//                }
                radioButton = (RadioButton) adapter.getViewByPosition(recyclerView, position, R.id.home_it_zan);
                pointGreat(data.get(position).getId(),position);
            }
        });
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
            httpData();
        }
    }
    @Override
    public void initDatas() {
        super.initDatas();
        //TODO:接口调用
        httpData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lgfgtdata", "onPause: ===========");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lgfgtdata", "onCreate: ==============");
    }

    @Override
    public void onResume() {
        super.onResume();
        httpData();
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/index", new Parameter()
                        .add("token", MainAty.token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("type", "1")
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                HomeBean homeBean = GsonUtil.GsonToBean(response, HomeBean.class);
//                                adapter.setNewData(homeBean.getData().getBbs());
//                                adapter.notifyDataSetChanged();
                                if (page == 1) {
                                    if (homeBean.getData().getBbs() == null || homeBean.getData().getBbs().size() == 0) {
                                        showTips("暂无数据");
//                                        return;
                                    } else {
                                        adapter.setNewData(homeBean.getData().getBbs());
                                    }
                                } else {
                                    if (homeBean.getData().getBbs() == null || homeBean.getData().getBbs().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(homeBean.getData().getBbs());
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                toast(objectMap.get("message"));
                            }
                        }
                    }
                });
    }
    private void pointGreat(String id, int postion){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/like", new Parameter()
                        .add("token", MainAty.token)
                        .add("bbs_id", id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
//                                if (data.get(postion).getIs_like() == 1){
//                                    int i = Integer.valueOf(radioButton.getText().toString()) + 1;
//                                    radioButton.setText(String.valueOf(i));
//                                    radioButton.setChecked(true);
//                                    radioButton.setTextColor(Color.parseColor("#FF7800"));
//                                }else {
//                                    int i = Integer.valueOf(radioButton.getText().toString()) - 1;
//                                    radioButton.setText(String.valueOf(i));
//                                    radioButton.setTextColor(Color.parseColor("#666666"));
//                                    radioButton.setChecked(false);
//                                }
                                httpData();
//                            adapter.notifyDataSetChanged();
                            }else {
                                radioButton.setChecked(false);
                                toast(objectMap.get("message"));
                            }
                        }
                    }
                }
        );
    }
}