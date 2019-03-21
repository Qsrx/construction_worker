package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ExerciseReportBean;
import com.txunda.construction_worker.ui.adapter.ExerciseReportRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.aty.SpecialExercisesAty.chapter_id;
import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 练习报告
 */
@Layout(R.layout.aty_exercise_report)
public class ExerciseReportAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_exercise_report_jg)
    TextView atyExerciseReportJg;
    @BindView(R.id.aty_exercise_report_rv)
    RecyclerView atyExerciseReportRv;
    @BindView(R.id.aty_punch_clock_success_finish)
    TextView atyPunchClockSuccessFinish;
    @BindView(R.id.aty_punch_clock_success_see)
    TextView atyPunchClockSuccessSee;
    private ExerciseReportRvAdapter adapter;
    private String type = "";
    private String exercise_type = "";
    private String chapter_num = "";
    private String type_id = "";
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("打卡报告");
        type = getIntent().getStringExtra("type");
        exercise_type = getIntent().getStringExtra("exercise_type");
        chapter_num = getIntent().getStringExtra("chapter_num");
        type_id = getIntent().getStringExtra("type_id");
        adapter = new ExerciseReportRvAdapter(R.layout.item_dtk_layout);
        atyExerciseReportRv.setLayoutManager(new GridLayoutManager(me, 6, LinearLayoutManager.VERTICAL, false));
        atyExerciseReportRv.setAdapter(adapter);
    }
    @OnClick({R.id.header_iv_back, R.id.aty_punch_clock_success_finish, R.id.aty_punch_clock_success_see})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_punch_clock_success_finish:
                finish();
                break;
            case R.id.aty_punch_clock_success_see:
                if (!isNull(chapter_num)){
                    Intent intent = new Intent(me, ViewsPasingAty.class);
                    intent.putExtra("chapter_num",chapter_num);
                    startActivity(intent);
                    return;
                }
                if (!isNull(type_id)){
                    Intent intent = new Intent(me, ViewsPasingAty.class);
                    intent.putExtra("type_id",type_id);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(me, ViewsPasingAty.class);
                intent.putExtra("exercise_type",exercise_type);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
                default:break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        if (!isNull(chapter_num)){
            httpData2();
            return;
        }
        if (!isNull(type_id)){
            httpData3();
            return;
        }
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!isNull(chapter_num)){
                    Intent intent = new Intent(me, ViewsPasingAty.class);
                    intent.putExtra("chapter_num",chapter_num);
                    intent.putExtra("point",position);
                    startActivity(intent);
                    return;
                }
                if (!isNull(type_id)){
                    Intent intent = new Intent(me, ViewsPasingAty.class);
                    intent.putExtra("type_id",type_id);
                    intent.putExtra("point",position);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(me, ViewsPasingAty.class);
                intent.putExtra("exercise_type",exercise_type);
                intent.putExtra("point",position);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });
    }

    /**
     * 专项练习报告
     */
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/report", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("type",type)
                        .add("exercise_type",exercise_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("eirtjierjwo", "onResponse: ==================="+response);
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            ExerciseReportBean reportBean = GsonUtil.GsonToBean(response, ExerciseReportBean.class);
                            atyExerciseReportJg.setText("答对"+reportBean.getData().getCount()+"题");
                            adapter.setNewData(reportBean.getData().getList());
                            adapter.notifyDataSetChanged();
                            return;
                        }

                        showErrorTip(objectMap.get("message").toString());
                    }
                });
    }

    /**
     * 章节练习报告
     */
    private void httpData2(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/chapter_report", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("chapter_id",chapter_id)
                        .add("chapter_num",chapter_num)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            ExerciseReportBean reportBean = GsonUtil.GsonToBean(response, ExerciseReportBean.class);
                            atyExerciseReportJg.setText("答对"+reportBean.getData().getCount()+"题");
                            adapter.setNewData(reportBean.getData().getList());
                            adapter.notifyDataSetChanged();
                            return;
                        }

                        showErrorTip(objectMap.get("message").toString());
                    }
                });
    }
    /**
     * 真题/模拟题练习报告
     */
    private void httpData3(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/examination_report", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("type_id",type_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> objectMap = JSONUtils.parseKeyAndValueToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            ExerciseReportBean reportBean = GsonUtil.GsonToBean(response, ExerciseReportBean.class);
                            Map<String, Object> map = JSONUtils.parseJsonObjectStrToMap(objectMap.get("data"));
//                            atyExerciseReportJg.setText("答对"+reportBean.getData().getCount()+"题");
                            atyExerciseReportJg.setText("正确率: "+map.get("accuracy")+"%");
                            adapter.setNewData(reportBean.getData().getList());
                            adapter.notifyDataSetChanged();
                            return;
                        }

                        showErrorTip(objectMap.get("message").toString());
                    }
                });
    }
}
