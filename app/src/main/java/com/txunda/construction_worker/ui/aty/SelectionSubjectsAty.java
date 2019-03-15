package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.SelectionSubjectBean;
import com.txunda.construction_worker.ui.adapter.SelectionSubjectsAdapter;
import com.txunda.construction_worker.ui.view.NoScrollListview;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.MessageHelper;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择科目
 */
@Layout(R.layout.aty_selection_subjects)
public class SelectionSubjectsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_selection_subjects_tv_title)
    TextView atySelectionSubjectsTvTitle;
    @BindView(R.id.aty_selection_subjects_tv_content)
    TextView atySelectionSubjectsTvContent;
    @BindView(R.id.aty_selection_subjects_lv)
    NoScrollListview atySelectionSubjectsLv;
    @BindView(R.id.aty_selection_subjects_iv_bottom)
    ImageView atySelectionSubjectsIvBottom;
    @BindView(R.id.aty_selection_subjects_rl_kf)
    RelativeLayout atySelectionSubjectsRlKf;
    @BindView(R.id.aty_selection_subjects_rl_buy)
    RelativeLayout atySelectionSubjectsRlBuy;
    @BindView(R.id.aty_selection_subjects_sv)
    ScrollView atySelectionSubjectsSv;
    @BindView(R.id.aty_selection_subjects_tv_sum_price)
    TextView atySelectionSubjectsTvSumPrice;
    @BindView(R.id.aty_selection_subjects_ll_buy)
    LinearLayout atySelectionSubjectsLlBuy;
    private String taocan_id = "";
    private SelectionSubjectsAdapter subjectsAdapter;
    private List<SelectionSubjectBean.DataBean.ListBean> list = new ArrayList<>();

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("选择科目");
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(i);
//        }
        taocan_id = getIntent().getStringExtra("taocan_id");
//        subjectsAdapter = new SelectionSubjectsAdapter(list, this);
//        atySelectionSubjectsLv.setAdapter(subjectsAdapter);
        atySelectionSubjectsSv.smoothScrollTo(0, 0);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atySelectionSubjectsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addHistory(list.get(i).getTaocan_id());
                Intent intent = new Intent(me, CourseSelectionAty.class);
                intent.putExtra("subject_id", list.get(i).getTaocan_id());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.header_iv_back, R.id.aty_selection_subjects_iv_bottom, R.id.aty_selection_subjects_rl_kf, R.id.aty_selection_subjects_rl_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_selection_subjects_iv_bottom:
//                jump(LiveDetailsAty.class);
                Intent intent = new Intent(me, LiveDetailsAty.class);
                intent.putExtra("taocan_id", taocan_id);
                startActivity(intent);
                break;
            case R.id.aty_selection_subjects_rl_kf:
//                new AlertDialog(me).builder().setTitle("建工邦教育").setMsg("400 875 6958")
//                        .setPositiveButton("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                toast("点击了确认");
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//
//                    }
//                }).show();
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
                    // 进入主页面
                    Intent intent2 = new IntentBuilder(me)
                            .setVisitorInfo(MessageHelper.createVisitorInfo())
                            .setServiceIMNumber("kefuchannelimid_716578")
                            .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                            .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                            .setShowUserNick(true)
                            .setBundle(bundle)
                            .build();
                    startActivity(intent2);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    showErrorTip("暂未登陆");
                }
                break;
            case R.id.aty_selection_subjects_rl_buy:
//                jump(ConfirmOrderAty.class);
                Intent intent2 = new Intent(me, ConfirmOrderAty.class);
                intent2.putExtra("taocan_id", taocan_id);
                intent2.putExtra("type", "1");
                startActivity(intent2);
                break;
        }
    }

    private void httpData() {
        HttpRequest.POST(this, AllStatus.BASE_URL + "Course/selectSubject", new Parameter()
                        .add("token", token)
                        .add("taocan_id", taocan_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("selectsubecjbean", "onResponse: =============" + response);
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    SelectionSubjectBean subjectBean = GsonUtil.GsonToBean(response, SelectionSubjectBean.class);
                                    list = subjectBean.getData().getList();
                                    subjectsAdapter = new SelectionSubjectsAdapter(list, me);
                                    atySelectionSubjectsLv.setAdapter(subjectsAdapter);
//                                subjectsAdapter.notifyDataSetChanged();
                                    atySelectionSubjectsTvTitle.setText(subjectBean.getData().getMiaoshu().getMain_title());
                                    atySelectionSubjectsTvContent.setText(subjectBean.getData().getMiaoshu().getDesc());
                                    Glide.with(me).load(subjectBean.getData().getMiaoshu().getLive_pic()).into(atySelectionSubjectsIvBottom);
                                    atySelectionSubjectsTvSumPrice.setText("全科购买 ¥" + subjectBean.getData().getMiaoshu().getPrice_count());
                                    if (subjectBean.getData().getIs_pay() == 1) {
                                        atySelectionSubjectsLlBuy.setVisibility(View.GONE);
                                    }
                                } else {
                                    showErrorTip(objectMap.get("message").toString());
                                }
                            } catch (NullPointerException e) {
                                showErrorTip("数据解析失败");
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

    /**
     * 添加观看历史
     */
    private void addHistory(String course_id) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Histroy/addhistroy", new Parameter()
                        .add("token", token)
                        .add("course_id", course_id)
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