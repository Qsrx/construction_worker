package com.txunda.construction_worker.ui.aty;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PanDuanBean;
import com.txunda.construction_worker.bean.ViewPasingBean;
import com.txunda.construction_worker.ui.adapter.ViewPagerAdp;
import com.txunda.construction_worker.ui.fgt.ViewsPasingFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.PanDuanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@Layout(R.layout.aty_views_pasing)
public class ViewsPasingAty extends BaseAty {
    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_more_do_work_iv_dtk)
    ImageView atyMoreDoWorkIvDtk;
    @BindView(R.id.aty_do_work_rb_sc)
    ImageView atyDoWorkRbSc;
    @BindView(R.id.aty_views_pasing_vp)
    ViewPager atyViewsPasingVp;
    private String exercise_type = null;
    private String chapter_num = null;
    private String chapter_i = null;
    private String chapter_n = null;
    private String type_id = null;
    private String str = null;
    private String type = null;
    private ViewPagerAdp adapter;
    private List<Fragment> fragment = new ArrayList<>();
    private int point;
    private String questions_typeid;
    public static ArrayList<Map<String, String>> views_list = new ArrayList<>();
    private int index = 0;
    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        ButterKnife.bind(this);
//        setRbSize(atyDoWorkRbSc);
    }
    @OnClick({R.id.aty_do_work_iv_back, R.id.aty_more_do_work_iv_dtk, R.id.aty_do_work_rb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                finish();
                break;
            case R.id.aty_more_do_work_iv_dtk:
                break;
            case R.id.aty_do_work_rb_sc:
                try {
                    checkCollect(PanDuanUtils.views_list.get(index).id,PanDuanUtils.views_list.get(index).collection);
                }catch (NullPointerException e){
                    checkCollect(PanDuanUtils.list.get(index).id,PanDuanUtils.list.get(index).collection);
                }
                break;
                default:break;
        }
    }

    @Override
    public void initViews() {
        super.initViews();
        exercise_type = getIntent().getStringExtra("exercise_type");
        chapter_num = getIntent().getStringExtra("chapter_num");
        chapter_n = getIntent().getStringExtra("chapter_n");
        chapter_i = getIntent().getStringExtra("chapter_i");
        str = getIntent().getStringExtra("str");
        type_id = getIntent().getStringExtra("type_id");
        questions_typeid = getIntent().getStringExtra("questions_typeid");
        point = getIntent().getIntExtra("point",0);
        Log.d("viewspasingbean", "initViews: ==========="+exercise_type);
        Log.d("viewspasingbean", "initViews: ==========="+chapter_num);
        Log.d("viewspasingbean", "initViews: ==========="+type_id);
        if (!isNull(chapter_num)){
            httpData2();
            return;
        }
        if (!isNull(type_id)){
            httpData3();
            return;
        }
        if (!isNull(questions_typeid)){
            httpData5(questions_typeid);
            return;
        }
        httpData();
    }

    private void httpData(){
        type = getIntent().getStringExtra("type");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/done_see", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("type",type)
                        .add("exercise_type",exercise_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        if (map.get("code").equals("1")) {
                            ViewPasingBean reportBean = GsonUtil.GsonToBean(response, ViewPasingBean.class);
                            Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            views_list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                            if (views_list == null) {
                                return;
                            }
                            PanDuanUtils.views_list.clear();
                            for (int i = 0; i < views_list.size(); i++) {
                                PanDuanUtils.views_list.add(new PanDuanBean(views_list.get(i).get("questions_id"), views_list.get(i).get("answer"),type,views_list.get(i).get("collection")));
                                ViewsPasingFgt contentFgt = new ViewsPasingFgt();
                                Bundle bundle = new Bundle();
                                bundle.putInt("index", i);
                                contentFgt.setArguments(bundle);
                                fragment.add(contentFgt);
                            }
                            adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                            atyViewsPasingVp.setAdapter(adapter);
                            atyViewsPasingVp.setCurrentItem(point);
                            fristCollect();
                            return;
                        }
                        showErrorTip(map.get("message").toString());
                    }
                });
    }

    /**
     * 章节查看解析
     */
    private void httpData2(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/chapter_see", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("chapter_id",chapter_i)
                        .add("chapter_num",chapter_n)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        if (map.get("code").equals("1")) {
                            ViewPasingBean reportBean = GsonUtil.GsonToBean(response, ViewPasingBean.class);
                            Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            views_list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                            if (views_list == null) {
                                return;
                            }
                            PanDuanUtils.views_list.clear();
                            for (int i = 0; i < views_list.size(); i++) {
                                PanDuanUtils.views_list.add(new PanDuanBean(views_list.get(i).get("questions_id"), views_list.get(i).get("answer"),type,views_list.get(i).get("collection")));
                                ViewsPasingFgt contentFgt = new ViewsPasingFgt();
                                Bundle bundle = new Bundle();
                                bundle.putInt("index", i);
                                contentFgt.setArguments(bundle);
                                fragment.add(contentFgt);
                            }
                            adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                            atyViewsPasingVp.setAdapter(adapter);
                            atyViewsPasingVp.setCurrentItem(point);
                            fristCollect();
                            return;
                        }
                        showErrorTip(map.get("message").toString());
                    }
                });
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyViewsPasingVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
                try {
                    PanDuanBean panDuanBean = PanDuanUtils.views_list.get(i);
                    if (panDuanBean.collection.equals("1")) {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                    }else {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                    }
                }catch (NullPointerException e){
                    PanDuanBean panDuanBean = PanDuanUtils.list.get(i);
                    if (panDuanBean.collection.equals("1")) {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                    }else {
                        atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 模拟/真题查看解析
     */
    private void httpData3(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/examination_see", new Parameter()
                        .add("token",token)
                        .add("subject_id",subject_id)
                        .add("type_id",type_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        if (map.get("code").equals("1")) {
                            ViewPasingBean reportBean = GsonUtil.GsonToBean(response, ViewPasingBean.class);
                            Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            views_list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                            if (views_list == null) {
                                return;
                            }
                            PanDuanUtils.views_list.clear();
                            for (int i = 0; i < views_list.size(); i++) {
                                PanDuanUtils.views_list.add(new PanDuanBean(views_list.get(i).get("questions_id"), views_list.get(i).get("answer"),type,views_list.get(i).get("collection")));
                                ViewsPasingFgt contentFgt = new ViewsPasingFgt();
                                Bundle bundle = new Bundle();
                                bundle.putInt("index", i);
                                contentFgt.setArguments(bundle);
                                fragment.add(contentFgt);
                            }
                            adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                            atyViewsPasingVp.setAdapter(adapter);
                            atyViewsPasingVp.setCurrentItem(point);
                            fristCollect();
                            return;
                        }
                        showErrorTip(map.get("message").toString());
                    }
                });
    }
    private void httpData5(String tp){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/record_detail", new Parameter()
                        .add("token", token)
                        .add("questions_typeid", tp)
                        .add("exercise_type", str)
                        .add("class_type", chapter_i)
                        .add("chapter_num", chapter_n)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Log.d("233333324", "onResponse: ============"+response);
                        if (map.get("code").equals("1")) {
                            ViewPasingBean reportBean = GsonUtil.GsonToBean(response, ViewPasingBean.class);
                            Map<String, String> map1 = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            views_list = JSONUtils.parseKeyAndValueToMapList(map1.get("list"));
                            if (views_list == null) {
                                return;
                            }
                            PanDuanUtils.views_list.clear();
                            for (int i = 0; i < views_list.size(); i++) {
                                PanDuanUtils.views_list.add(new PanDuanBean(views_list.get(i).get("questions_id"), views_list.get(i).get("answer"),type,views_list.get(i).get("collection")));
                                ViewsPasingFgt contentFgt = new ViewsPasingFgt();
                                Bundle bundle = new Bundle();
                                bundle.putInt("index", i);
                                contentFgt.setArguments(bundle);
                                fragment.add(contentFgt);
                            }
                            adapter = new ViewPagerAdp(getSupportFragmentManager(), fragment);
                            atyViewsPasingVp.setAdapter(adapter);
                            atyViewsPasingVp.setCurrentItem(point);
                            fristCollect();
                            return;
                        }
                    }
                });
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
        try {
            PanDuanBean panDuanBean = PanDuanUtils.views_list.get(0);
            if (panDuanBean.collection.equals("1")) {
                atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
            }else {
                atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
            }
        }catch (IndexOutOfBoundsException e){
            atyDoWorkRbSc.setVisibility(View.GONE);
        }
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
            PanDuanUtils.views_list.get(index).collection = "2";
        }else {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
            PanDuanUtils.views_list.get(index).collection = "1";
        }
    }
}