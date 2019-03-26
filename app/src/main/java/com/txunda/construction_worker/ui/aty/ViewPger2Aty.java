package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PanDuanBean;
import com.txunda.construction_worker.ui.adapter.ViewPagerAdp;
import com.txunda.construction_worker.ui.fgt.Content2Fgt;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.PanDuanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.aty.SpecialExercisesAty.chapter_id;
import static com.txunda.construction_worker.ui.aty.SpecialExercisesAty.try_questions;
import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;


@Layout(R.layout.aty_view_pger2)
@DarkStatusBarTheme(true)  //开启顶部状态栏图标、false 白色 true 黑色
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//透明颜色   设置底部导航栏背景颜色（a = 255,r = 255,g = 255,b = 255 黑色的）
public class ViewPger2Aty extends BaseAty {

    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_do_work_tv_post)
    TextView atyDoWorkTvPost;
    @BindView(R.id.aty_more_do_work_iv_dtk)
    ImageView atyMoreDoWorkIvDtk;
    @BindView(R.id.aty_do_work_rb_sc)
    ImageView atyDoWorkRbSc;
    private ViewPager viewPage;
    private LinearLayout llIndicator;
    private ViewPagerAdp adapter;
    private List<Fragment> fragment = new ArrayList<>();
    public static ArrayList<Map<String, String>> list = new ArrayList<>();
    public static String type = "-1";
    private String type_id = "";
    private String chapter_num = "";
    public static String questions_type = "1";
    //1:专项2:章节3:模拟&真题
    public static int STYLE = -1;
    private int index = 0;
    @Override
    public void initViews() {
        ButterKnife.bind(this);
//        setRbSize(atyDoWorkRbSc);
        viewPage = findViewById(R.id.viewPage);
        type = getIntent().getStringExtra("type");
        type_id = getIntent().getStringExtra("type_id");
        chapter_num = getIntent().getStringExtra("chapter_num");
        llIndicator = findViewById(R.id.ll_indicator);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        if (type == null){
            if (chapter_num == null){
                questions_type = "3";
                STYLE = 3;
                httpData3();
                return;
            }
            questions_type = "2";
            STYLE = 2;
            if (try_questions == 0){
                    new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做本科目全部试卷哦")
                            .setPositiveButton("购买", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    jump(UnlockDetailsAty.class);
                                    finish();
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    }).show();
            }
            httpData2();
            return;
        }
        STYLE = 1;
        if (try_questions == 0){
                new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做本科目全部试卷哦")
                        .setPositiveButton("购买", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jump(UnlockDetailsAty.class);
                                finish();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        finish();
                    }
                }).show();
        }
        practice();
    }

    @Override
    public void setEvents() {
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
                try {
                    PanDuanBean panDuanBean = PanDuanUtils.list.get(i);
                    if (panDuanBean.collection.equals("1")) {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                    }else {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                    }
                }catch (NullPointerException e){
                    PanDuanBean panDuanBean = PanDuanUtils.views_list.get(i);
                    if (panDuanBean.collection.equals("1")) {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                    }else {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                    }
                }

                if (i == try_questions) {
                    if (list.get(i).get("pay").equals("2")){
                        new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做本科目全部试卷哦")
                                .setPositiveButton("购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        jump(UnlockDetailsAty.class);
                                        finish();
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).show();
                    } else {

                    }
                    }
                }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void practice() {
        if (type.equals("4")){
            atyMoreDoWorkIvDtk.setVisibility(View.GONE);
            atyDoWorkTvPost.setVisibility(View.GONE);
        }
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/practice", new Parameter()
                        .add("token", MainAty.token)
                        .add("subject_id", subject_id)
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.d("viewpagedata", "onResponse: ===========" + response);
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            if (map.get("code").equals("1")) {
                                Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                                list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                                if (list == null) {
                                    return;
                                }
                                PanDuanUtils.list.clear();
                                for (int i = 0; i < list.size(); i++) {
                                    PanDuanUtils.list.add(new PanDuanBean(list.get(i).get("questions_id"), "",type,list.get(i).get("collection")));
                                    Content2Fgt contentFgt = new Content2Fgt();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("index", i);
                                    contentFgt.setArguments(bundle);
                                    fragment.add(contentFgt);
                                }
                                adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                                viewPage.setAdapter(adapter);
                                fristCollect();
                            } else {
                                showErrorTip(map.get("message"));
                            }
                        } else {
                            toast("网络连接失败");
                        }
                    }
                });
    }
    private void httpData2(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/chapter_questions", new Parameter()
                        .add("token", MainAty.token)
                        .add("subject_id", subject_id)
                        .add("chapter_id", chapter_id)
                        .add("chapter_num", chapter_num)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            if (map.get("code").equals("1")) {
                                Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                                list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                                if (list == null) {
                                    return;
                                }
                                PanDuanUtils.list.clear();
                                for (int i = 0; i < list.size(); i++) {
//                                    PanDuanUtils.list.add(new PanDuanBean(i + "", "",type));
                                    PanDuanUtils.list.add(new PanDuanBean(list.get(i).get("questions_id"), "",list.get(i).get("type"),list.get(i).get("collection")));
                                    Content2Fgt contentFgt = new Content2Fgt();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("index", i);
                                    contentFgt.setArguments(bundle);
                                    fragment.add(contentFgt);
                                }
                                adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                                viewPage.setAdapter(adapter);
                                fristCollect();
                            } else {
                                showErrorTip(map.get("message"));
                            }
                        } else {
                            toast("网络连接失败");
                        }
                    }
                });
    }
    private void httpData3(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/simulation_exercises", new Parameter()
                        .add("token", MainAty.token)
                        .add("subject_id", subject_id)
                        .add("type_id", type_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.d("viewpagedata", "onResponse: ===========" + response);
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            if (map.get("code").equals("1")) {
                                Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                                list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                                if (list == null) {
                                    return;
                                }
                                PanDuanUtils.list.clear();
                                for (int i = 0; i < list.size(); i++) {
//                                    PanDuanUtils.list.add(new PanDuanBean(i + "", "",type));
                                    PanDuanUtils.list.add(new PanDuanBean(list.get(i).get("questions_id"), "",list.get(i).get("type"),list.get(i).get("collection")));
                                    Content2Fgt contentFgt = new Content2Fgt();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("index", i);
                                    contentFgt.setArguments(bundle);
                                    fragment.add(contentFgt);
                                }
                                adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                                viewPage.setAdapter(adapter);
                                fristCollect();
                            } else {
                                showErrorTip(map.get("message"));
                            }
                        } else {
                            toast("网络连接失败");
                        }
                    }
                });
    }

    @OnClick({R.id.aty_do_work_iv_back, R.id.aty_do_work_tv_post, R.id.aty_more_do_work_iv_dtk, R.id.aty_do_work_rb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                finish();
                break;
            case R.id.aty_do_work_tv_post:
                try {
                    if (list.get(0).get("pay").equals("2")){
                        new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做完并交卷哦")
                                .setPositiveButton("购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        jump(UnlockDetailsAty.class);
                                        finish();
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).show();
                    }else {
                        new AlertDialog(me).builder().setTitle("交卷后不可修改答案,").setMsg("是否确定交卷")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        httpPostRes();
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
//                        finish();
                            }
                        }).show();
                    }
                }catch (NullPointerException e){
                    new AlertDialog(me).builder().setTitle("交卷后不可修改答案,").setMsg("是否确定交卷")
                            .setPositiveButton("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    httpPostRes();
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
//                        finish();
                        }
                    }).show();
                }



                break;
            case R.id.aty_more_do_work_iv_dtk:
//                jump(AnswerCardAty.class);
                Intent intent = new Intent(ViewPger2Aty.this, AnswerCardAty.class);
                intent.putExtra("chapter_num",chapter_num);
                intent.putExtra("type_id",type_id);
                startActivityForResult(intent,0);
                break;
            case R.id.aty_do_work_rb_sc:
                try {
                    checkCollect(PanDuanUtils.list.get(index).id,PanDuanUtils.list.get(index).collection);
                }catch (NullPointerException e){
                    checkCollect(PanDuanUtils.views_list.get(index).id,PanDuanUtils.views_list.get(index).collection);
                }
                break;
                default:break;
        }
    }

    /**
     * 交卷
     */
    private void httpPostRes() {
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
                        Intent intent = new Intent(ViewPger2Aty.this, ExerciseReportAty.class);
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
    /**
     * 收藏/取消收藏
     */
    private void checkCollect(String str, final String s){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/questions_collection", new Parameter()
                        .add("token", MainAty.token)
                        .add("questions_id", str)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                changeSc(s);
                            }else {
                                toast(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    private void changeSc(String num){
        if (num.equals("1")){
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
            PanDuanUtils.list.get(index).collection = "2";
        }else {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
            PanDuanUtils.list.get(index).collection = "1";
        }
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
    private void fristCollect(){
        PanDuanBean panDuanBean = PanDuanUtils.list.get(0);
        if (panDuanBean.collection.equals("1")) {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
        }else {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
        }
    }
    //结果处理函数，当从secondActivity中返回时调用此函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            int value;
            if (bundle != null) {
                value = bundle.getInt("point");
                // 切换到指定页面
                viewPage.setCurrentItem(value);
            }
        }
    }

}