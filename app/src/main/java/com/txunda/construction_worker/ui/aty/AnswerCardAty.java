package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.DtkRvAdapter;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.PanDuanUtils;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.aty.ViewPger2Aty.list;
import static com.txunda.construction_worker.ui.aty.ViewPger2Aty.questions_type;
import static com.txunda.construction_worker.ui.aty.ViewPger2Aty.type;
import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 答题卡
 */
@Layout(R.layout.activity_answer_card_aty)
public class AnswerCardAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_answer_card_tv_test)
    TextView atyAnswerCardTvTest;
    @BindView(R.id.aty_answer_card_rv)
    RecyclerView atyAnswerCardRv;
    @BindView(R.id.aty_answer_card_tv_post)
    TextView atyAnswerCardTvPost;
    private DtkRvAdapter adapter;
//    private String type = "";
    private String type_id = "";
    private String chapter_num = "";
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("答题卡");
        adapter = new DtkRvAdapter(R.layout.item_dtk_layout);
        atyAnswerCardRv.setLayoutManager(new GridLayoutManager(me, 6, LinearLayoutManager.VERTICAL, false));
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i < 21; i++) {
//            list.add(String.valueOf(i));
//        }
//        adapter.setNewData(list);
        atyAnswerCardRv.setAdapter(adapter);
    }

    @OnClick({R.id.header_iv_back, R.id.aty_answer_card_tv_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_answer_card_tv_post:
//                jump(ExerciseReportAty.class);
                try {
                    if (list.get(0).get("pay").equals("2")) {
                        new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做完并交卷哦")
                                .setPositiveButton("购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) { jump(UnlockDetailsAty.class);
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).show();
                    }else {
                        httpPostRes();
                    }
                }catch (NullPointerException e){
                    httpPostRes();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("point",position);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        ArrayList<Map<String, String>> list = ViewPger2Aty.list;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("xunze", i + "");
        }
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
    }
    /**
     * 交卷
     */
    private void httpPostRes() {
        chapter_num = getIntent().getStringExtra("chapter_num");
        type_id = getIntent().getStringExtra("type_id");
        Log.d("viewpager2data", "httpPostRes: ============="+PanDuanUtils.list.toString());
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/hand", new Parameter()
                        .add("token", MainAty.token)
                        .add("subject_id", subject_id)
                        .add("questions_type", questions_type)
                        .add("card", PanDuanUtils.list.toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (!objectMap.get("code").equals("1")){
                            return;
                        }
                        toast(objectMap.get("message"));
                        Intent intent = new Intent(me, ExerciseReportAty.class);
                        intent.putExtra("type","1");
                        intent.putExtra("exercise_type",type);
                        intent.putExtra("chapter_num",chapter_num);
                        intent.putExtra("type_id",type_id);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }
}