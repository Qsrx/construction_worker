package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.MyClassFBean;
import com.txunda.construction_worker.ui.adapter.MyClassRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_my_class)
public class MyClassAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_my_class_rv)
    RecyclerView atyMyClassRv;
    @BindView(R.id.fgt_my_class_smartrefresh)
    SmartRefreshLayout fgtMyClassSmartrefresh;
    private MyClassRvAdapter adapter;
    private int page = 1;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("我的课程");
        atyMyClassRv.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new MyClassRvAdapter(R.layout.my_class_rv_layout);
        atyMyClassRv.setAdapter(adapter);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<MyClassFBean.DataBean.MyCourseBean> data = adapter.getData();
//                jump(SelectionSubjectsAty.class);
                Intent intent = new Intent(me, SelectionSubjectsAty.class);
                try {
                    intent.putExtra("taocan_id",data.get(position).getSubject_id().toString());
                    startActivity(intent);
                }catch (NullPointerException e){
                    showErrorTip("该科目暂无法查看");
                }

            }
        });
        //刷新
        fgtMyClassSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        fgtMyClassSmartrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }
    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Mycourse", new Parameter()
                        .add("token", token)
                        .add("page", String.valueOf(page)), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            fgtMyClassSmartrefresh.finishRefresh();
                            fgtMyClassSmartrefresh.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                MyClassFBean classFBean = GsonUtil.GsonToBean(response, MyClassFBean.class);
                                if (page == 1) {
                                    if (classFBean.getData().getMyCourse() == null || classFBean.getData().getMyCourse().size() == 0) {
                                        showErrorTip("暂无数据");
                                        return;
                                    }
                                    adapter.setNewData(classFBean.getData().getMyCourse());
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