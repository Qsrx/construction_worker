package com.txunda.construction_worker.ui.fgt;


import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.PointTestBean;
import com.txunda.construction_worker.bean.WorkStartBean;
import com.txunda.construction_worker.ui.adapter.DoWorkFgtRvAdapter;
import com.txunda.construction_worker.ui.aty.MoreDoWorkAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.WorkStartUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@SuppressLint("ValidFragment")
@Layout(R.layout.fgt_do_work)
public class DoWorkFgt extends BaseFgt {
    private DoWorkFgtRvAdapter adapter;
    RecyclerView rv;
    public static int index = -1;
    private int point;
    List<String> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public DoWorkFgt(int point) {
        this.point = point;
    }

    @Override
    public void initViews() {
        super.initViews();
        rv = findViewById(R.id.aty_do_work_rv);
        rv.setLayoutManager(new LinearLayoutManager(me));
        adapter = new DoWorkFgtRvAdapter(list, titles, me);
        rv.setAdapter(adapter);
        index = point;
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClick(new DoWorkFgtRvAdapter.MyItemClick() {
            @Override
            public void onItemClick(View view, int postion) {
//                index = postion;
//                adapter.notifyDataSetChanged();
                MoreDoWorkAty.hashMap.put(String.valueOf(point), postion);
                index = postion;
                WorkStartBean workStartBean = new WorkStartBean();
                workStartBean.tiHao = point;
                workStartBean.zhiValue = postion;
                WorkStartUtils.worList.add(workStartBean);

                if (WorkStartUtils.worList != null) {
                    for (int i = 0; i < WorkStartUtils.worList.size(); i++) {
                        log("=======集合======" + WorkStartUtils.worList.get(i).toString());
                    }
                }

//                for (String key : MoreDoWorkAty.hashMap.keySet()) {
//                    Log.d("hashmapkeyvalue", "Key: " + key + " Value: " + MoreDoWorkAty.hashMap.get(key));
//                }
                adapter.notifyDataSetChanged();
//                index = -1;
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
            httpData();
        }
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/practice", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("type", MoreDoWorkAty.type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> stringObjectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (stringObjectMap.get("code").equals("1")) {
                                if (MoreDoWorkAty.type.equals("1")) {
                                    list.clear();
                                    titles.clear();
                                    PointTestBean pointTestBean = GsonUtil.GsonToBean(response, PointTestBean.class);
                                    for (int i = 0; i < pointTestBean.getData().getList().get(point).getOption().size(); i++) {
                                        list.add(pointTestBean.getData().getList().get(point).getOption().get(i).getAnswer());
                                        titles.add(pointTestBean.getData().getList().get(point).getOption().get(i).getQuestion());
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                                return;
                            }
                            stringObjectMap.get("message");
                            return;
                        }
                        showTips(error.getMessage());
                    }
                });
    }

}