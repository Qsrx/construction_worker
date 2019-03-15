package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.MyQuizBean;
import com.txunda.construction_worker.ui.adapter.MyQuizRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的提问
 */
@Layout(R.layout.aty_my_quiz)
public class MyQuizAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_my_quiz_rv)
    RecyclerView atyMyQuizRv;
    @BindView(R.id.aty_my_quiz_refreshLayout)
    SmartRefreshLayout atyMyQuizRefreshLayout;
    private MyQuizRvAdapter adapter;
    RadioButton radioButton;
    private int page = 1;
    private List<MyQuizBean.DataBean> data;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("我的提问");
        atyMyQuizRv.setLayoutManager(new LinearLayoutManager(me));
        adapter = new MyQuizRvAdapter(R.layout.item_quiz_layout);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i+"");
        }
        atyMyQuizRv.setAdapter(adapter);
//        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
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

    @Override
    public void setEvents() {
        super.setEvents();
        //刷新
        atyMyQuizRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        atyMyQuizRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (data == null){
                    data = adapter.getData();
                }
                radioButton = (RadioButton) adapter.getViewByPosition(atyMyQuizRv, position, R.id.home_it_zan);
                pointGreat(data.get(position).getId());
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (data == null){
                    data = adapter.getData();
                }
                Intent intent = new Intent(me, MainBodyAty.class);
                intent.putExtra("bbs_id",data.get(position).getId());
                startActivity(intent);
            }
        });

    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/myask", new Parameter()
                .add("token", token)
                .add("page", String.valueOf(page)), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    WaitDialog.dismiss();
                    atyMyQuizRefreshLayout.finishRefresh();
                    atyMyQuizRefreshLayout.finishLoadmore();
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        MyQuizBean mySelfBean = GsonUtil.GsonToBean(response, MyQuizBean.class);
                        if (page == 1) {
                            if (mySelfBean.getData() == null || mySelfBean.getData().size() == 0) {
                                showErrorTip("暂无数据");
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
        });
    }

    /**
     * 点赞和取消
     * @param id
     */
    private void pointGreat(String id){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/like", new Parameter()
                        .add("token", MainAty.token)
                        .add("bbs_id", id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                httpData();
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