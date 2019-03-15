package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.view.View;
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
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PunchClockSuccessBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 打卡成功
 */
@Layout(R.layout.aty_punch_clock_success)
public class PunchClockSuccessAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_punch_clock_success_finish)
    TextView atyPunchClockSuccessFinish;
    @BindView(R.id.aty_punch_clock_success_see)
    TextView atyPunchClockSuccessSee;
    @BindView(R.id.aty_punch_clock_success_tv_title)
    TextView atyPunchClockSuccessTvTitle;
    private String answer;
    private String questions_id;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("打卡成功");
        answer = getIntent().getStringExtra("answer");
        questions_id = getIntent().getStringExtra("questions_id");
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
                Intent intent = new Intent(me, ViewParsingAty.class);
                intent.putExtra("questions_id",questions_id);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(this, AllStatus.BASE_URL + "Exercises/punch_success", new Parameter()
                        .add("token", token)
                        .add("questions_id", questions_id)
                        .add("answer", answer)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                PunchClockSuccessBean jsonBean = GsonUtil.GsonToBean(response, PunchClockSuccessBean.class);
                                if (jsonBean.getData().getRight() == 1) {
                                    return;
                                } else {
                                    atyPunchClockSuccessTvTitle.setText("加油吧,答案是"+jsonBean.getData().getAnswer());
                                }
                            } else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

}