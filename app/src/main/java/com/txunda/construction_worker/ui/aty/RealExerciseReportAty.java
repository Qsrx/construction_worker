package com.txunda.construction_worker.ui.aty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.LxBgAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@Layout(R.layout.aty_real_exercise_report)
public class RealExerciseReportAty extends BaseAty {
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
    private LxBgAdapter adapter;
    private String type = "";
    private String exercise_type = "";
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("练习报告");
        List<String> list = new ArrayList<>();
        type = getIntent().getStringExtra("type");
        exercise_type = getIntent().getStringExtra("exercise_type");
        if (type == null){

        }
        for (int i = 0; i < 10; i++) {
            list.add("1");
        }
        adapter = new LxBgAdapter(this,list);
        atyExerciseReportRv.setLayoutManager(new LinearLayoutManager(this));
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
                jump(ViewParsingAty.class);
                break;
        }
    }
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/report", new Parameter()
                .add("token",token)
                .add("subject_id",subject_id)
                .add("type",type)
                .add("exercise_type",exercise_type)
                , new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                if (objectMap.get("code").equals("1")) {

                    return;
                }
                showErrorTip(objectMap.get("message").toString());
            }
        });
    }
}