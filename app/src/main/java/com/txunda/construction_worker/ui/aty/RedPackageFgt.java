package com.txunda.construction_worker.ui.aty;


import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.RedPackageBean;
import com.txunda.construction_worker.ui.adapter.RedPackageRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
@Layout(R.layout.fgt_red_package)
public class RedPackageFgt extends BaseFgt {
    private RedPackageRvAdapter adapter;
    private RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
    private String type;
    private int page = 1;

    public RedPackageFgt(String type) {
        this.type = type;
    }

    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_red_package_rv);
        smartRefreshLayout = findViewById(R.id.fgt_red_package_refreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i+"");
        }
        adapter = new RedPackageRvAdapter(R.layout.item_red_package_layout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initDatas() {
        super.initDatas();
//        TODO:接口调用
        httpData(page);
    }
    private void httpData(int pg){
        WaitDialog.show(me,"数据加载中...");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/moneyBag", new Parameter()
                        .add("token", token)
                        .add("type", type)
                        .add("page", String.valueOf(pg))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        Log.d("redpackbean", "onResponse: =============="+type+"\n"+response);
                        if (error == null){
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")){
                                    RedPackageBean packageBean = GsonUtil.GsonToBean(response, RedPackageBean.class);
                                    if (page == 1) {
                                        if (packageBean.getData().getContent() == null || packageBean.getData().getContent().size() == 0) {
                                            showTips("暂无数据");
                                            return;
                                        } else {
                                            adapter.setNewData(packageBean.getData().getContent());
                                        }
                                    } else {
                                        if (packageBean.getData() == null || packageBean.getData().getContent().size() == 0) {
                                            toast("没有更多数据了");
                                            return;
                                        }
                                        adapter.addData(packageBean.getData().getContent());
                                    }
                                    adapter.notifyDataSetChanged();
                                }else {
                                    showTips(objectMap.get("message").toString());
                                }
                            }catch (NullPointerException e){
                                showTips("数据解析错误");
                            }
                        }
                    }
                });
    }

    @Override
    public void setEvents() {
        super.setEvents();
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData(page);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData(page);
            }
        });
    }
}