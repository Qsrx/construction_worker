package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.SystemMessageBean;
import com.txunda.construction_worker.ui.adapter.SystemMessageRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 系统消息
 */
@Layout(R.layout.aty_system_message)
public class SystemMessageAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_system_message_rv)
    RecyclerView atySystemMessageRv;
    @BindView(R.id.aty_system_message_refreshLayout)
    SmartRefreshLayout atySystemMessageRefreshLayout;
    private SystemMessageRvAdapter adapter;
    private int page = 1;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("系统消息");
        atySystemMessageRv.setLayoutManager(new LinearLayoutManager(me));
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i+"");
//        }
        adapter = new SystemMessageRvAdapter(R.layout.item_system_message_layout);
//        adapter.setNewData(list);
        atySystemMessageRv.setAdapter(adapter);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
//        httpData();
    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        //刷新
        atySystemMessageRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        atySystemMessageRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                jump(SystemMessageDetailsAty.class);
                List<SystemMessageBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, SystemMessageDetailsAty.class);
                intent.putExtra("act_msg_id",data.get(position).getAct_msg_id());
                startActivity(intent);
            }
        });
    }
    private void httpData(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Myinfo/myMsgList", new Parameter()
                        .add("token", token)
                        .add("page", String.valueOf(page)), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                SystemMessageBean messageBean = GsonUtil.GsonToBean(response,SystemMessageBean.class);
                                if (page == 1) {
                                    if (messageBean.getData() == null || messageBean.getData().size() == 0) {
                                        toast("暂无数据");
                                        return;
                                    } else {
                                        adapter.setNewData(messageBean.getData());
                                    }
                                } else {
                                    if (messageBean.getData() == null || messageBean.getData().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(messageBean.getData());
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpData();
    }
}