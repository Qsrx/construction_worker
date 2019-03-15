package com.txunda.construction_worker.ui.fgt;


import android.content.Intent;
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
import com.kongzue.dialog.v2.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.MyCommentBean;
import com.txunda.construction_worker.ui.adapter.MyCommentRvAdapter;
import com.txunda.construction_worker.ui.aty.MainBodyAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.List;
import java.util.Map;

/**
 * 我的评论
 */
@Layout(R.layout.fgt_my_comment)
public class MyCommentFgt extends BaseFgt {
    RecyclerView recyclerView;
    SmartRefreshLayout refreshLayout;
    private MyCommentRvAdapter adapter;
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_my_comment_rv);
        refreshLayout = findViewById(R.id.fgt_my_comment_refreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new MyCommentRvAdapter(R.layout.item_my_talk_layout);
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
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(me, CommentDetailsAty.class);
//                intent.putExtra("type","1");
//                startActivity(intent);
//            }
//        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<MyCommentBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, MainBodyAty.class);
                intent.putExtra("bbs_id",data.get(position).getB_id());
                startActivity(intent);
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
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    private void httpData(){
        WaitDialog.show(me,"加载数据中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/mycomment", new Parameter()
                        .add("token", token)
                        .add("page", String.valueOf(page)), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                MyCommentBean mySelfBean = GsonUtil.GsonToBean(response, MyCommentBean.class);
                                if (page == 1) {
                                    if (mySelfBean.getData() == null || mySelfBean.getData().size() == 0) {
                                        showTips("暂无数据");
                                        return;
                                    } else {
                                        adapter.setNewData(mySelfBean.getData());
                                    }
                                } else {
                                    if (mySelfBean.getData() == null || mySelfBean.getData().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(mySelfBean.getData());
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                toast(objectMap.get("message"));
                            }
                        }
                    }
                }
        );
    }
}