package com.txunda.construction_worker.ui.aty;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ChoiceProfessionBean;
import com.txunda.construction_worker.ui.adapter.ChoiceProfessionRvAdapter;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择您的考试行业
 */
@Layout(R.layout.aty_choice_profession)
public class ChoiceProfessionAty extends BaseAty {

//    @BindView(R.id.aty_choice_profession_rg)
//    MyRadioGroup atyChoiceProfessionRg;
        @BindView(R.id.aty_choice_profession_rv)
        RecyclerView atyChoiceProfessionRv;
    private ChoiceProfessionRvAdapter adapter;
    private String token;
    private ChoiceProfessionBean bean;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    jump(MainAty.class);
                    finish();
                    break;
            }
        }
    };
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        adapter = new ChoiceProfessionRvAdapter(R.layout.item_select_class_layout);
        atyChoiceProfessionRv.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        atyChoiceProfessionRv.setAdapter(adapter);
    }

    @Override
    public void setEvents() {
        super.setEvents();
//        atyChoiceProfessionRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                jump(MainAty.class);
//                finish();
//            }
//        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ChoiceProfessionBean.DataBean> data = adapter.getData();
                Preferences.getInstance().set(me, "construction", "industry_id", data.get(position).getId());
                Message message = new Message();
                message.what = 1;
                handler.sendMessageDelayed(message,500);
                HomeFgt.Industry_ID = data.get(position).getId();
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        //TODO:接口调用
        token = Preferences.getInstance().getString(this, "construction", "token");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/choose_major", new Parameter()
                        .add("token", token), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Log.d("choicedata", "initDatas: ======================"+response);
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                bean = GsonUtil.GsonToBean(response,ChoiceProfessionBean.class);
                                adapter.setNewData(bean.getData());
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