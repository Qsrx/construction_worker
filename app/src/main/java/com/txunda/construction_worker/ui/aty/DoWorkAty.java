package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.txunda.construction_worker.bean.DoWorkBean;
import com.txunda.construction_worker.ui.adapter.DoWorkRvAdapter;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 每天打卡练习 单选题
 */
@Layout(R.layout.aty_do_work)
public class DoWorkAty extends BaseAty {
    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_do_work_tv_post)
    TextView atyDoWorkTvPost;
    @BindView(R.id.aty_do_work_rb_sc)
    ImageView atyDoWorkRbSc;
    @BindView(R.id.aty_do_work_tv_title)
    TextView atyDoWorkTvTitle;
    @BindView(R.id.aty_do_work_tv_content)
    TextView atyDoWorkTvContent;
    @BindView(R.id.aty_do_work_rv)
    RecyclerView atyDoWorkRv;
    @BindView(R.id.aty_do_work_tv_see)
    TextView atyDoWorkTvSee;
    @BindView(R.id.aty_do_work_ll_jx)
    LinearLayout atyDoWorkLlJx;
    @BindView(R.id.aty_do_work_tv_jx)
    TextView atyDoWorkTvJx;
    @BindView(R.id.aty_do_work_tv_point)
    TextView atyDoWorkTvPoint;
    private DoWorkRvAdapter adapter;
    public static int index = -1;
    private List<String> titles = new ArrayList<>();
    private String questions_id = "";
    private List<String> list = new ArrayList<>();
    private boolean is_select = false;
    private int collect ;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
//        setRbSize(atyDoWorkRbSc);
        atyDoWorkRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoWorkRvAdapter(list, titles, this);
        atyDoWorkRv.setAdapter(adapter);
    }

    /**
     * 设置radiobutton按钮drawble大小
     *
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[0].setBounds(0, 0, 70, 70);
        rb.setCompoundDrawables(compoundDrawables[0], null, null, null);
    }

    @OnClick({R.id.aty_do_work_iv_back, R.id.aty_do_work_tv_post, R.id.aty_do_work_rb_sc, R.id.aty_do_work_tv_see})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                if (is_select == false){
                    new AlertDialog(me).builder().setTitle("题目还未做完,").setMsg("是否确认退出？")
                            .setPositiveButton("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                        }
                    }).show();
                    return;
                }
                finish();
                break;
            case R.id.aty_do_work_tv_post:
                if (is_select == true) {
                    commintPw(list.get(index));
                }else {
                    showErrorTip("答案不能为空");
                }
                break;
            case R.id.aty_do_work_rb_sc:
                checkCollect();
                break;
            case R.id.aty_do_work_tv_see:
                atyDoWorkLlJx.setVisibility(View.VISIBLE);
                atyDoWorkTvSee.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClick(new DoWorkRvAdapter.MyItemClick() {
            @Override
            public void onItemClick(View view, int postion) {
                is_select = true;
                index = postion;
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/punch", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                DoWorkBean gsonToBean = GsonUtil.GsonToBean(response, DoWorkBean.class);
//                                list.add("A");
//                                list.add("B");
//                                list.add("C");
//                                titles.add(gsonToBean.getData().getOption_a());
//                                titles.add(gsonToBean.getData().getOption_b());
//                                titles.add(gsonToBean.getData().getOption_c());
                                for (int i = 0; i < gsonToBean.getData().getOption().size(); i++) {
                                    list.add(gsonToBean.getData().getOption().get(i).getAnswer());
                                    titles.add(gsonToBean.getData().getOption().get(i).getQuestion());
                                }
                                adapter.notifyDataSetChanged();
                                atyDoWorkTvTitle.setText(gsonToBean.getData().getTitle());
                                atyDoWorkTvContent.setText(gsonToBean.getData().getSub_title());
                                atyDoWorkTvJx.setText(gsonToBean.getData().getAnalysis());
                                atyDoWorkTvPoint.setText(gsonToBean.getData().getKey());
                                questions_id = gsonToBean.getData().getQuestions_id();
                                collect = gsonToBean.getData().getCollection();
                                if (gsonToBean.getData().getCollection() == 1) {
//                                    atyDoWorkRbSc.setChecked(true);
                                    atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                                }else {
//                                    atyDoWorkRbSc.setChecked(false);
                                    atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                                }
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    /**
     * 收藏/取消收藏
     */
    private void checkCollect(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/questions_collection", new Parameter()
                .add("token", token)
                .add("questions_id", questions_id)
                , new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        changeSc(collect);
                    }else {
                        toast(objectMap.get("message").toString());
                    }
                }
            }
        });
    }

    /**
     * 交卷
     * @param str
     */
    private void commintPw(final String str){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Exercises/Submission", new Parameter()
                        .add("token", token)
                        .add("questions_id", questions_id)
                        .add("answer", str)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                Intent intent = new Intent(me, PunchClockSuccessAty.class);
                                intent.putExtra("answer",str);
                                intent.putExtra("questions_id",questions_id);
                                startActivity(intent);
                                index = -1;
                                finish();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
    private void changeSc(int num){
        if (num == 1){
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
            collect = 2;
        }else {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
            collect = 1;
        }
    }
}