package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.stacklabelview.StackLabel;
import com.kongzue.stacklabelview.interfaces.OnLabelClickListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.SearchHotBean;
import com.txunda.construction_worker.ui.adapter.SearchRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.ListDataSave;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_search)
public class SearchAty extends BaseAty {
    @BindView(R.id.fgt_class_back)
    ImageView fgtClassBack;
    @BindView(R.id.fgt_class_header_et_title)
    EditText fgtClassHeaderEtTitle;
    @BindView(R.id.fgt_class_header_rl_title)
    RelativeLayout fgtClassHeaderRlTitle;
    //    @BindView(R.id.aty_search_hot)
//    StackLabel atySearchHot;
    @BindView(R.id.aty_search_clear)
    ImageView atySearchClear;
    @BindView(R.id.aty_search_ago)
    StackLabel atySearchAgo;
    @BindView(R.id.aty_search_rv)
    RecyclerView atySearchRv;
    SearchRvAdapter adapter;
    @BindView(R.id.aty_search_iv_search)
    ImageView atySearchIvSearch;
    private SearchHotBean hotBean;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        List<String> list = new ArrayList<>();
//        list.add("课程");
//        list.add("建造师");
//        list.add("8大人员");
//        list.add("工程师");
//        list.add("建造师");
//        list.add("课程");
//        list.add("课程");
//        list.add("课程");
//        list.add("课程");
//        atySearchHot.setLabels(list);
        FlexboxLayoutManager manager = new FlexboxLayoutManager();
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);
        atySearchRv.setLayoutManager(manager);
        adapter = new SearchRvAdapter(R.layout.item_search_hot_layout);
        atySearchRv.setAdapter(adapter);
    }

    @OnClick({R.id.fgt_class_back, R.id.aty_search_clear,R.id.aty_search_iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fgt_class_back:
                finish();
                break;
            case R.id.aty_search_clear:
                ListDataSave.ClearData(me);
                atySearchAgo.setLabels(ListDataSave.getSearchHistory(this));
                break;
            case R.id.aty_search_iv_search:
                ListDataSave.saveSearchHistory(me,fgtClassHeaderEtTitle.getText().toString());
                Intent intent = new Intent(me, ClassSearchAty.class);
                intent.putExtra("name",fgtClassHeaderEtTitle.getText().toString());
                startActivity(intent);
                break;
                default:break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ListDataSave.saveSearchHistory(me,fgtClassHeaderEtTitle.getText().toString());
                Intent intent = new Intent(me, ClassSearchAty.class);
                intent.putExtra("name",hotBean.getData().get(position).getName());
                startActivity(intent);
            }
        });
        atySearchAgo.setOnLabelClickListener(new OnLabelClickListener() {
            @Override
            public void onClick(int index, View v, String s) {
                ListDataSave.saveSearchHistory(me,fgtClassHeaderEtTitle.getText().toString());
                Intent intent = new Intent(me, ClassSearchAty.class);
                intent.putExtra("name",s);
                startActivity(intent);
            }
        });
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/hot_search", new Parameter()
                .add("token", token), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        hotBean = GsonUtil.GsonToBean(response, SearchHotBean.class);
                        adapter.setNewData(hotBean.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        showErrorTip(objectMap.get("message").toString());
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpData();
        atySearchAgo.setLabels(ListDataSave.getSearchHistory(this));
        fgtClassHeaderEtTitle.setText("");
    }
}