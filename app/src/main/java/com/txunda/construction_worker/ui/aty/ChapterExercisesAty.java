package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ChapterExercisesBean;
import com.txunda.construction_worker.ui.adapter.ChapterExercisesRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 章节练习题
 */
@Layout(R.layout.aty_chapter_exercises)
public class ChapterExercisesAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_chapter_exercises_rv)
    RecyclerView atyChapterExercisesRv;
    private ChapterExercisesRvAdapter adapter;
    private ChapterExercisesBean exercisesBean;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("章节练习题");
        atyChapterExercisesRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChapterExercisesRvAdapter(R.layout.item_chapter_exercises_layout);
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i < 21; i++) {
//            list.add(String.valueOf(i));
//        }
//        adapter.setNewData(list);
        atyChapterExercisesRv.setAdapter(adapter);
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(me, SpecialExercisesAty.class);
                intent.putExtra("type","2");
                intent.putExtra("chapter_id",exercisesBean.getData().get(position).getClass_type());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/chapter_exercises", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                exercisesBean = GsonUtil.GsonToBean(response, ChapterExercisesBean.class);
                                adapter.setNewData(exercisesBean.getData());
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}