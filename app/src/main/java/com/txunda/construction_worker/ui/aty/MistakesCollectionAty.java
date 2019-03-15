package com.txunda.construction_worker.ui.aty;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.MistakesCollectionBean;
import com.txunda.construction_worker.ui.adapter.MistakesCollectionRvAdapter;
import com.txunda.construction_worker.ui.fgt.ItemBankFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑时间：2018.12.28
 * 开发者：hzy
 * 功能模块：错题集
 * 联系方式：win_hzy@163.com
 */
@Layout(R.layout.aty_mistakes_collection)
public class MistakesCollectionAty extends BaseAty {
    @BindView(R.id.aty_mistakes_collection_back)
    ImageView atyMistakesCollectionBack;
    @BindView(R.id.aty_mistakes_collection_menu)
    ImageView atyMistakesCollectionMenu;
    @BindView(R.id.aty_mistakes_collection_rv)
    RecyclerView atyMistakesCollectionRv;
    @BindView(R.id.aty_mistakes_collection_refreshLayout)
    SmartRefreshLayout atyMistakesCollectionRefreshLayout;
    private MistakesCollectionRvAdapter adapter;
    private AlertDialog dialog_phone;
    private ListView lv;
    private List<String> lists = new ArrayList<>();
    private int page = 1;
    private MistakesCollectionBean recordBean;
    private String questions_typeid = "0";
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        adapter = new MistakesCollectionRvAdapter(R.layout.item_miss_layout);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 199; i++) {
            list.add(i+"");
        }
        dialog_phone = new AlertDialog.Builder(this).create();
        atyMistakesCollectionRv.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setNewData(list);
        atyMistakesCollectionRv.setAdapter(adapter);
//        lists = new ArrayList<>();
//        lists.add("全部");
//        lists.add("专项练习题");
//        lists.add("章节练习题");
//        lists.add("模拟考试题");
//        lists.add("真题考试提");
    }


    @OnClick({R.id.aty_mistakes_collection_back, R.id.aty_mistakes_collection_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_mistakes_collection_back:
                finish();
                break;
            case R.id.aty_mistakes_collection_menu:
                showPop();
                break;
        }
    }
    private void  showPop() {
        View popWiew = LayoutInflater.from(this).inflate(R.layout.pop_add, null);
        dialog_phone.show();
        dialog_phone.setCanceledOnTouchOutside(true);
        Window window2 = dialog_phone.getWindow();
        window2.setGravity(Gravity.BOTTOM);
        window2.setContentView(popWiew);
        window2.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window2.setBackgroundDrawableResource(R.color.transparent);
        lv = window2.findViewById(R.id.pop_add_list);
        lv.setAdapter(new PopAdapter(lists,me));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog_phone.dismiss();
                questions_typeid = recordBean.getData().getType().get(i).getQuestions_typeid();
                httpData();
            }
        });
    }
    /**
     * dialog列表适配器
     */
    private class PopAdapter extends BaseAdapter {
        private List<String> list;
        private Context context;

        public PopAdapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            PopAdapter.ViewHolder vh = null;
            if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.pop_tv_layout,null);
                vh = new PopAdapter.ViewHolder();
                vh.tv = view.findViewById(R.id.pop_tv_content);
                view.setTag(vh);
            }else {
                vh = (PopAdapter.ViewHolder) view.getTag();
            }
            vh.tv.setText(list.get(i));
            return view;
        }
        class ViewHolder{
            TextView tv;
        }
    }

    @Override
    public void setEvents() {
        super.setEvents();
        //刷新
        atyMistakesCollectionRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        atyMistakesCollectionRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<MistakesCollectionBean.DataBean.ListBean> data = adapter.getData();
                Intent intent = new Intent(me, ViewParsingAty.class);
                intent.putExtra("questions_id",data.get(position).getId());
                intent.putExtra("type","2");
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
        WaitDialog.show(me,"加载数据中");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/wrongs", new Parameter()
                        .add("token", token)
                        .add("subject_id", ItemBankFgt.subject_id)
                        .add("page", String.valueOf(page))
                        .add("questions_typeid", questions_typeid)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("iwoeriewhnorne", "onResponse: ====================="+response);
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                WaitDialog.dismiss();
                                atyMistakesCollectionRefreshLayout.finishRefresh();
                                atyMistakesCollectionRefreshLayout.finishLoadmore();
                                recordBean = GsonUtil.GsonToBean(response, MistakesCollectionBean.class);
                                lists.clear();
                                for (int i = 0; i < recordBean.getData().getType().size(); i++) {
                                    lists.add(recordBean.getData().getType().get(i).getName());
                                }
                                if (page == 1) {
                                    if (recordBean.getData().getList() == null || recordBean.getData().getList().size() == 0) {
                                        showErrorTip("暂无数据");
                                        adapter.setNewData(recordBean.getData().getList());
                                        adapter.notifyDataSetChanged();
                                        return;
                                    }
                                    adapter.setNewData(recordBean.getData().getList());
//                                    for (int i = 0; i < recordBean.getData().getList().size(); i++) {
//                                        int a = 0;
//                                        if (recordBean.getData().getList().get(i).getQuestions_typeid().equals(questions_typeid)){
//                                            int b = a++;
//                                            adapter.setData(b,recordBean.getData().getList().get(i));
//                                        }else {
//                                            adapter.setNewData(recordBean.getData().getList());
//                                        }
//                                    }
                                } else {
                                    if (recordBean.getData().getList() == null || recordBean.getData().getList().size() == 0) {
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adapter.addData(recordBean.getData().getList());
//                                    for (int i = 0; i < recordBean.getData().getList().size(); i++) {
//                                        if (recordBean.getData().getList().get(i).getQuestions_typeid().equals(questions_typeid)){
//                                            adapter.addData(recordBean.getData().getList().get(i));
//                                        }else {
//                                            adapter.addData(recordBean.getData().getList());
//                                        }
//                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }
}