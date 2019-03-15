package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.txunda.construction_worker.bean.EverydayPunchTestBean;
import com.txunda.construction_worker.ui.adapter.DkRvAdapter;
import com.txunda.construction_worker.ui.fgt.ItemBankFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 每日打卡练习
 */
@Layout(R.layout.aty_everyday_punch_clock_test)
public class EverydayPunchClockTestAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_everyday_punch_clock_test_title)
    TextView atyEverydayPunchClockTestTitle;
    @BindView(R.id.aty_everyday_punch_clock_test_time)
    TextView atyEverydayPunchClockTestTime;
    @BindView(R.id.aty_everyday_punch_clock_test_dk)
    TextView atyEverydayPunchClockTestDk;
    @BindView(R.id.aty_everyday_punch_clock_test_rv)
    RecyclerView atyEverydayPunchClockTestRv;
    @BindView(R.id.aty_do_record_refreshLayout)
    SmartRefreshLayout atyDoRecordRefreshLayout;
    @BindView(R.id.aty_everyday_punch_clock_test_name)
    TextView atyEverydayPunchClockTestName;
    private int page = 1;
    private DkRvAdapter adapter;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("每日打卡练习");
        atyEverydayPunchClockTestRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DkRvAdapter(R.layout.item_everyday_dk_layout);
        atyEverydayPunchClockTestRv.setAdapter(adapter);
    }

    @OnClick({R.id.header_iv_back, R.id.aty_everyday_punch_clock_test_dk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_everyday_punch_clock_test_dk:
                if (atyEverydayPunchClockTestDk.getText().toString().equals("立即分享")) {
                    jump(ShardAty.class);
                    return;
                }
                jump(DoWorkAty.class);
                break;
            default:
                break;
        }
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
        atyDoRecordRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        atyDoRecordRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<EverydayPunchTestBean.DataBean.ListBean> data = adapter.getData();
                Intent intent = new Intent(me, ViewParsingAty.class);
                intent.putExtra("questions_id",data.get(position).getQuestions_id());
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpData();
    }

    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/punch_page", new Parameter()
                .add("token", token)
                .add("subject_id", ItemBankFgt.subject_id)
                .add("page", String.valueOf(page)), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Log.d("evevryiwqiedata", "onResponse: ============"+response);
                    WaitDialog.dismiss();
                    atyDoRecordRefreshLayout.finishRefresh();
                    atyDoRecordRefreshLayout.finishLoadmore();
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        EverydayPunchTestBean mySelfBean = GsonUtil.GsonToBean(response, EverydayPunchTestBean.class);
                        atyEverydayPunchClockTestTitle.setText(mySelfBean.getData().getContent().getPoetry());
                        atyEverydayPunchClockTestTime.setText("打卡日期:" + mySelfBean.getData().getContent().getDate());
                        atyEverydayPunchClockTestName.setText("——"+mySelfBean.getData().getContent().getAuthor());
                        if (mySelfBean.getData().getSign() == 1) {
                            atyEverydayPunchClockTestDk.setText("立即分享");
                        }
                        try {
                            if (page == 1) {
                                if (mySelfBean.getData().getContent() == null || mySelfBean.getData().getList().size() == 0) {
                                    showErrorTip("暂无数据");
//                                    return;
                                } else {
                                    adapter.setNewData(mySelfBean.getData().getList());
                                }
                            } else {
                                if (mySelfBean.getData().getList() == null || mySelfBean.getData().getList().size() == 0) {
                                    toast("没有更多数据了");
                                    return;
                                }
                                adapter.addData(mySelfBean.getData().getList());
                            }
                        }catch (NullPointerException e){
                            Log.d("epctaerror", "onResponse: =========="+e.getMessage());
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        toast(objectMap.get("message"));
                    }
                }
            }
        });
    }

}