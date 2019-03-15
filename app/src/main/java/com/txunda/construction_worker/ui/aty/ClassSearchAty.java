package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ClassSearchBean;
import com.txunda.construction_worker.ui.adapter.ClassSearchRvAdapter;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_class_search)
public class ClassSearchAty extends BaseAty {
    @BindView(R.id.fgt_class_kf)
    ImageView fgtClassKf;
    @BindView(R.id.fgt_class_header_tv_title)
    TextView fgtClassHeaderTvTitle;
    @BindView(R.id.fgt_class_header_rl_title)
    RelativeLayout fgtClassHeaderRlTitle;
    @BindView(R.id.fgt_class_ls)
    ImageView fgtClassLs;
    @BindView(R.id.aty_class_search_rv)
    RecyclerView atyClassSearchRv;
    private String key_name = "";
    private ClassSearchRvAdapter adapter;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        key_name = getIntent().getStringExtra("name");
        fgtClassHeaderTvTitle.setText(key_name);
        atyClassSearchRv.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new ClassSearchRvAdapter(R.layout.system_class_rv_layout);
        atyClassSearchRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ClassSearchBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, SelectionSubjectsAty.class);
                intent.putExtra("taocan_id",data.get(position).getTaocan_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @OnClick(R.id.fgt_class_kf)
    public void onViewClicked() {
        finish();
    }
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/course_search", new Parameter()
                        .add("key_name", key_name)
                        .add("industry_id", HomeFgt.Industry_ID)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            ClassSearchBean classSearchBean = GsonUtil.GsonToBean(response, ClassSearchBean.class);
                            adapter.setNewData(classSearchBean.getData());
                            adapter.notifyDataSetChanged();
                        }else {
                            showErrorTip(objectMap.get("message").toString());
                        }
                    }
                });
    }
}