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
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.TrueQuestionsBean;
import com.txunda.construction_worker.ui.adapter.TestQuestionsRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 真题考试题
 */
@Layout(R.layout.aty_true_test_questions)
public class TrueTestQuestionsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_true_questions_rv)
    RecyclerView atyTrueQuestionsRv;
    private TestQuestionsRvAdapter adapter;
    private TrueQuestionsBean gsonToBean;
    private String type;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            headerTvTitle.setText("模拟测试题");
        }else {
            headerTvTitle.setText("真题考试题");
        }

        atyTrueQuestionsRv.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new TestQuestionsRvAdapter(R.layout.item_test_layout);
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i < 21; i++) {
//            list.add(String.valueOf(i));
//        }
//        adapter.setNewData(list);
        atyTrueQuestionsRv.setAdapter(adapter);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                jump(RealExamAty.class);
                List<TrueQuestionsBean.DataBean.ListBean> data = adapter.getData();
                if (data.get(position).getIs_fee() == 1){
                    Intent intent = new Intent(me, ViewPger2Aty.class);
                    intent.putExtra("type_id",data.get(position).getType_id());
                    startActivity(intent);
                    return;
                }
//                showErrorTip("请购买后再尝试");
                jump(UnlockDetailsAty.class);
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
        if (type.equals("1")){
            httpData();
        }else {
            httpData2();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type.equals("1")){
            httpData();
        }else {
            httpData2();
        }
    }

    /**
     * 模拟
     */
    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/simulated_questions", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                TrueQuestionsBean gsonToBean = GsonUtil.GsonToBean(response, TrueQuestionsBean.class);
                                adapter.setNewData(gsonToBean.getData().getList());
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    /**
     * 真题
     */
    private void httpData2(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/true_question", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                gsonToBean = GsonUtil.GsonToBean(response, TrueQuestionsBean.class);
                                adapter.setNewData(gsonToBean.getData().getList());
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}