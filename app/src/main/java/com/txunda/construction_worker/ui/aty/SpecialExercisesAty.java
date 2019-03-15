package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
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
import com.kongzue.dialog.v2.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ChapterBean;
import com.txunda.construction_worker.bean.SpecialExercisesBean;
import com.txunda.construction_worker.ui.adapter.SpecialChapterRvAdapter;
import com.txunda.construction_worker.ui.adapter.SpecialExercisesRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@Layout(R.layout.aty_special_exercises)
public class SpecialExercisesAty extends BaseAty {

    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_special_exercises_rv)
    RecyclerView atySpecialExercisesRv;
    @BindView(R.id.aty_special_exercises_refreshLayout)
    SmartRefreshLayout atySpecialExercisesRefreshLayout;
    private SpecialExercisesRvAdapter adapter;
    private SpecialChapterRvAdapter adapter2;
    public static int is_pay = -1;
    public static String chapter_id = "";
    private SpecialExercisesBean exercisesBean;
    ChapterBean bean;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        String type = getIntent().getStringExtra("type");
        chapter_id = getIntent().getStringExtra("chapter_id");
        if (type == null){
            headerTvTitle.setText("专项练习题");
        }else {
            headerTvTitle.setText("章节练习题");
        }
//        if (type.equals("2")){
//
//        }else {
//
//        }

        atySpecialExercisesRv.setLayoutManager(new LinearLayoutManager(this));

        if (type == null){
            adapter = new SpecialExercisesRvAdapter(R.layout.item_special_exercises_layout);
            atySpecialExercisesRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    Intent intent = new Intent(me, ViewPger2Aty.class);
                    intent.putExtra("type",exercisesBean.getData().getContent().get(position).getTitle());
                    startActivity(intent);
//                    jump(MoreDoWorkAty.class);
                }
            });
        }else {
            adapter2 = new SpecialChapterRvAdapter(R.layout.item_special_exercises_layout);
            atySpecialExercisesRv.setAdapter(adapter2);
            adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    jump(MoreDoWorkAty.class);
                    Intent intent = new Intent(me, ViewPger2Aty.class);
                    intent.putExtra("chapter_num",String.valueOf(position+1));
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        if (chapter_id == null){
            httpData();
            return;
        }
        httpData2();
    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/special_exercises", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                exercisesBean = GsonUtil.GsonToBean(response, SpecialExercisesBean.class);
                                adapter.setNewData(exercisesBean.getData().getContent());
                                is_pay = exercisesBean.getData().getPay();
                                adapter.notifyDataSetChanged();
                            }else {
                                toast(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
    private void httpData2(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/chapter_practices", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("chapter_id", chapter_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            Log.d("iwejrioewbean", "onResponse: ==="+response);
                            if (objectMap.get("code").equals("1")) {
                                bean = GsonUtil.GsonToBean(response, ChapterBean.class);
                                adapter2.setNewData(bean.getData().getList());
                                is_pay = bean.getData().getPay();
                                adapter2.notifyDataSetChanged();
                            }else {
                                toast(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}