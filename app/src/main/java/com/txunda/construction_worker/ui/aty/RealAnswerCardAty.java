package com.txunda.construction_worker.ui.aty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.AllTalkAdapter;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 模拟和真题答题卡
 */
@Layout(R.layout.aty_real_answer_card)
public class RealAnswerCardAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_real_answer_card_small_title)
    TextView atyRealAnswerCardSmallTitle;
    @BindView(R.id.aty_real_answer_card_rv)
    RecyclerView atyRealAnswerCardRv;
    @BindView(R.id.aty_real_exam_tv_time)
    TextView atyRealExamTvTime;
    @BindView(R.id.aty_answer_card_tv_post)
    TextView atyAnswerCardTvPost;
    private AllTalkAdapter adapter;

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("答题卡");
        atyRealAnswerCardRv.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("1");
        }
        adapter = new AllTalkAdapter(this, list);
        atyRealAnswerCardRv.setAdapter(adapter);
    }

    @OnClick({R.id.header_iv_back, R.id.aty_answer_card_tv_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_answer_card_tv_post:
                jump(RealExerciseReportAty.class);
                break;
        }
    }
}