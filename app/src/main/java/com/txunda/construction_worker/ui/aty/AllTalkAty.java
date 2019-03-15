package com.txunda.construction_worker.ui.aty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
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
import com.txunda.construction_worker.bean.AllTalkBean;
import com.txunda.construction_worker.ui.adapter.DtkAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.aty.CourseSelectionAty.subject_id;

@Layout(R.layout.aty_all_talk)
public class AllTalkAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_all_talk_rv)
    RecyclerView atyAllTalkRv;
    @BindView(R.id.fgt_my_order_smartrefresh)
    SmartRefreshLayout fgtMyOrderSmartrefresh;
    private DtkAdapter adapter;
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("课程评价");
        atyAllTalkRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DtkAdapter(R.layout.item_forum_fabu);
        atyAllTalkRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        //刷新
        fgtMyOrderSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        fgtMyOrderSmartrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    private void httpData() {
        WaitDialog.show(me, "数据加载中…");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/comment_all", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            fgtMyOrderSmartrefresh.finishRefresh();
                            fgtMyOrderSmartrefresh.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                AllTalkBean bean = GsonUtil.GsonToBean(response, AllTalkBean.class);
                                if (page == 1) {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        showErrorTip("暂无数据");
                                        return;
                                    }
                                    adapter.setNewData(bean.getData());
                                } else {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(bean.getData());
                                }
                                adapter.notifyDataSetChanged();
                                return;
                            }
                            showErrorTip(objectMap.get("message").toString());
                        }
                    }
                });
    }
}