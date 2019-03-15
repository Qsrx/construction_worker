package com.txunda.construction_worker.ui.fgt;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.txunda.construction_worker.ui.adapter.XzRvAdapter;
import com.txunda.construction_worker.ui.aty.HomeDetailsAty;
import com.txunda.construction_worker.ui.view.NoScrollListview;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
@Layout(R.layout.fgt_xz)
public class XzFgt extends BaseFgt {
    NoScrollListview lv;
    private List<String> lists;
    private String type;
    private XzRvAdapter adapter;
    RecyclerView recyclerView;
    private HomeBean homeBean;
    private SmartRefreshLayout smartRefreshLayout;
    public XzFgt(String type) {
        this.type = type;
    }
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        adapter = new XzRvAdapter(R.layout.home_xz_layout);
        recyclerView = findViewById(R.id.fgt_xz_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        recyclerView.setAdapter(adapter);
//        EventBus.getDefault().register(this);
        smartRefreshLayout = me.findViewById(R.id.fgt_home_smartrefresh);

    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<HomeBean.DataBean.ArticleBean> data = adapter.getData();
                Intent intent = new Intent(me, HomeDetailsAty.class);
//                intent.putExtra("article_id",homeBean.getData().getArticle().get(position).getId());
                intent.putExtra("article_id",data.get(position).getId());
                intent.putExtra("type",type);
                startActivity(intent);
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
    public void initDatas() {
        super.initDatas();
        //TODO:接口调用
        httpData();
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/index", new Parameter()
                        .add("token", MainAty.token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("type", type)
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
//                            EventBus.getDefault().postSticky(true);
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                homeBean = GsonUtil.GsonToBean(response, HomeBean.class);
//                                adapter.setNewData(homeBean.getData().getArticle());
//                                adapter.notifyDataSetChanged();
                                if (page == 1) {
                                    if (homeBean.getData().getArticle() == null || homeBean.getData().getArticle().size() == 0) {
                                        showTips("暂无数据");
//                                        return;
                                    } else {
                                        adapter.setNewData(homeBean.getData().getArticle());
                                    }
                                } else {
                                    if (homeBean.getData().getArticle() == null || homeBean.getData().getArticle().size() == 0) {
                                        toast("没有更多数据了");
                                        page = 1;
                                        return;
                                    }
                                    adapter.addData(homeBean.getData().getArticle());
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                toast(objectMap.get("message"));
                            }
                        }
                    }
                });
    }
//    //定义处理接收的方法
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void userEventBus(String str){
//        page = str;
//        httpData();
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //注销注册
//        EventBus.getDefault().unregister(this);
//    }
}