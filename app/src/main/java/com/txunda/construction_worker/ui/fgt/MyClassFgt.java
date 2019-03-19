package com.txunda.construction_worker.ui.fgt;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.MyClassFBean;
import com.txunda.construction_worker.ui.adapter.MyClassRvAdapter;
import com.txunda.construction_worker.ui.aty.WebDetailsAty;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.GlideImageLoader;
import com.txunda.construction_worker.utils.MessageHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Layout(R.layout.fgt_my_class)
public class MyClassFgt extends BaseFgt implements View.OnClickListener{
    SmartRefreshLayout smartRefreshLayout;
    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    ImageView iv_nobuy;
    ImageView iv_close;
    RelativeLayout rl_banner;
    Banner banner;
    List<List<String>> list = new ArrayList<>();
    private List<String> lists = new ArrayList<>();
    private List<String> path_list = new ArrayList<>();
    private MyClassRvAdapter adapter;
    LinearLayout ll_teacher,ll_call;
    private MyClassFBean classFBean;
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        banner = findViewById(R.id.fgt_my_class_banner);
        recyclerView = findViewById(R.id.fgt_my_class_recycler);
        smartRefreshLayout = findViewById(R.id.fgt_my_class_smartrefresh);
        nestedScrollView = findViewById(R.id.fgt_my_class_nsv);
        iv_nobuy = findViewById(R.id.fgt_my_class_not_buy);
        ll_teacher = findViewById(R.id.fgt_my_class_ll_teacher);
        ll_call = findViewById(R.id.fgt_my_class_ll_call);
        rl_banner = findViewById(R.id.fgt_my_class_rl_banner);
        iv_close = findViewById(R.id.fgt_my_class_iv_close);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(2000);
        for (int i = 0; i < 10; i++) {
            lists.add(i+"");
            list.add(lists);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new MyClassRvAdapter(R.layout.my_class_rv_layout);
        recyclerView.setAdapter(adapter);
        nestedScrollView.smoothScrollTo(0, 0);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        ll_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
                    // 进入主页面
                    Intent intent = new IntentBuilder(me)
                            .setVisitorInfo(MessageHelper.createVisitorInfo())
                            .setServiceIMNumber("kefuchannelimid_716578")
                            .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                            .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                            .setShowUserNick(true)
                            .setBundle(bundle)
                            .build();
                    startActivity(intent);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    showTips("暂未登陆");
                }
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(me, WebDetailsAty.class);
                intent.putExtra("url",classFBean.getData().getAdvert().get(position).getUrl());
                startActivity(intent);
            }
        });
        iv_close.setOnClickListener(this);
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
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                List<MyClassFBean.DataBean.MyCourseBean> data = adapter.getData();
//                Intent intent = new Intent(me, SelectionSubjectsAty.class);
//                intent.putExtra("taocan_id",data.get(position).getSubject_id());
//                startActivity(intent);
//            }
//        });
    }
    private void showDialog(){
        new AlertDialog(me).builder().setTitle("建工邦教育").setMsg("400 875 6958")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //拨打电话
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4008756958"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        }).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_my_class_iv_close:
                rl_banner.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){
            nestedScrollView.smoothScrollTo(0, 0);
        }
    }

    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Mycourse", new Parameter()
                        .add("token", token)
                        .add("page", String.valueOf(page)), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.d("myclassbean", "onResponse: ============"+response);
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                classFBean = GsonUtil.GsonToBean(response, MyClassFBean.class);
                                if (page == 1) {
                                    if (classFBean.getData().getMyCourse() == null || classFBean.getData().getMyCourse().size() == 0) {
//                                        showTips("暂无数据");
                                        iv_nobuy.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    } else {
                                        iv_nobuy.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        adapter.setNewData(classFBean.getData().getMyCourse());
                                    }
                                    path_list.clear();
                                    for (int i = 0; i < classFBean.getData().getAdvert().size(); i++) {
                                        path_list.add(classFBean.getData().getAdvert().get(i).getPath());
                                    }
                                    banner.setImages(path_list);
                                    banner.start();
                                } else {
                                    if (classFBean.getData().getMyCourse() == null || classFBean.getData().getMyCourse().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(classFBean.getData().getMyCourse());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );
    }
}