package com.txunda.construction_worker.ui.fgt;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.txunda.construction_worker.bean.OrderBean;
import com.txunda.construction_worker.ui.adapter.MyOrderRvAdapter;
import com.txunda.construction_worker.ui.aty.OrderDetailsAty;
import com.txunda.construction_worker.ui.aty.PublicationEvaluationAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.wxapi.CallBack;
import com.txunda.construction_worker.wxapi.GetPrepayIdTask;
import com.txunda.construction_worker.wxapi.WXPayEntryActivity;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
@Layout(R.layout.fgt_my_order)
public class MyOrderFgt extends BaseFgt {
    RecyclerView recyclerView;
    private MyOrderRvAdapter adapter;
    private String type;
    SmartRefreshLayout refreshLayout;
    public MyOrderFgt(String type) {
        this.type = type;
    }
    private int page = 1;
    private OrderBean classFBean;
    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_my_order_recycler);
        refreshLayout = findViewById(R.id.fgt_my_order_smartrefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new MyOrderRvAdapter(R.layout.item_order_layout);
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i+"");
//        }
        recyclerView.setAdapter(adapter);
//        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<OrderBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, OrderDetailsAty.class);
                intent.putExtra("order_id",data.get(position).getOrder_id());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<OrderBean.DataBean> data = adapter.getData();
                TextView order_context_best_right = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.item_order_right);
                TextView order_context_best_left = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.item_order_left);
                if (view.getId() == R.id.item_order_right) {
                    if (order_context_best_right.getText().toString().equals("去评价")){
                        Intent intent = new Intent(me, PublicationEvaluationAty.class);
                        intent.putExtra("order_id",data.get(position).getOrder_id());
                        startActivity(intent);
                    }else if (order_context_best_right.getText().toString().equals("立即支付")){
                        httpWXPay(data.get(position).getOrder_id(),data.get(position).getPrice());
                    }
                }else if (view.getId() == R.id.item_order_left){
                    if (order_context_best_left.getText().toString().equals("取消订单")){
                        Log.d("orderfgtid", "onItemChildClick: =============="+data.get(position).getOrder_id());
                        cancelOrder(data.get(position).getOrder_id());
                    }
                }
            }
        });
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        WXPayEntryActivity.setCallBack(new CallBack() {
            @Override
            public void CallBack() {
                httpData();
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/myorder", new Parameter()
                .add("token", token)
                .add("page", String.valueOf(page))
                .add("type",type), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                classFBean = GsonUtil.GsonToBean(response, OrderBean.class);
                                if (page == 1) {
                                    if (classFBean.getData() == null || classFBean.getData().size() == 0) {
                                        showTips("暂无数据");
                                        return;
                                    }
                                    adapter.setNewData(classFBean.getData());
                                } else {
                                    if (classFBean.getData() == null || classFBean.getData().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(classFBean.getData());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );
    }

    /**
     * 取消订单请求
     * @param order_id
     */
    private void cancelOrder(String order_id){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/cancleorder", new Parameter()
                .add("token", token)
                .add("order_id", order_id), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        toast(objectMap.get("message"));
                        httpData();
                        return;
                    }
                    showTips(objectMap.get("message").toString());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        httpData();
    }

    /**
     * 微信支付
     *
     * @param id
     * @param price
     */
    private void httpWXPay(String id, String price) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Pay/WechatPay", new Parameter()
                        .add("token", token)
                        .add("order_id", id)
                        .add("pay_price", price), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(response);
                            if (stringMap.get("code").equals("1")) {
                                Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));
                                GetPrepayIdTask wxPay = new GetPrepayIdTask(me, data.get("sign"), data.get("appid"), data.get("noncestr"), data.get("package"), data.get("timestamp"),
                                        data.get("prepayid"), data.get("partnerid"), "");
                                wxPay.execute();
                            } else {
                                showTips(stringMap.get("message"));
                            }
                        }
                    }
                }
        );
    }
}