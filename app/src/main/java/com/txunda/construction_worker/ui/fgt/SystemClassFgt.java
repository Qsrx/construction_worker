package com.txunda.construction_worker.ui.fgt;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.ClassBean;
import com.txunda.construction_worker.ui.adapter.SystemClassRvAdapter;
import com.txunda.construction_worker.ui.aty.SelectionSubjectsAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.MessageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * A simple {@link Fragment} subclass.
 */
@Layout(R.layout.fgt_system_class)
public class SystemClassFgt extends BaseFgt implements View.OnClickListener{
    SystemClassRvAdapter adapter;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    SmartRefreshLayout refreshLayout;
    ShapedImageView siv;
    ImageView iv_kf;
    LinearLayout ll_hot;
    TextView tv_title,tv_price,tv_pay,tv_type;
    List<List<String>> list = new ArrayList<>();
    private List<String> lists = new ArrayList<>();
    private int page = 1;
    private AlertDialog dialog_phone;
    private ListView lv;
    private List<ClassBean.DataBean.SubjectBean> listss;
    private String subject_id = "";
    private ClassBean classBean;
    @Override
    public void initViews() {
        super.initViews();
        recyclerView = findViewById(R.id.fgt_system_class_recycler);
        siv = findViewById(R.id.fgt_system_class_hot_siv);
        tv_title = findViewById(R.id.fgt_system_class_hot_tv_title);
        tv_price = findViewById(R.id.fgt_system_class_hot_tv_price);
        tv_pay = findViewById(R.id.fgt_system_class_hot_tv_pay);
        iv_kf = findViewById(R.id.fgt_system_class_iv_kf);
        tv_type = findViewById(R.id.fgt_system_class_tv_type);
//        tv_type = findViewById(R.id.fgt_system_class_tv_type);
        nestedScrollView = findViewById(R.id.fgt_system_class_nsv);
        ll_hot = findViewById(R.id.fgt_system_class_ll_hot);
        refreshLayout = findViewById(R.id.fgt_system_class_smartrefresh);
        recyclerView.setLayoutManager(new GridLayoutManager(me, 2, LinearLayoutManager.VERTICAL, false));
        for (int i = 0; i < 10; i++) {
            lists.add(i+"");
            list.add(lists);
        }
        adapter = new SystemClassRvAdapter(R.layout.system_class_rv_layout);
//        adapter.setNewData(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ClassBean.DataBean.SystemBean> data = adapter.getData();
                Intent intent = new Intent(me, SelectionSubjectsAty.class);
                intent.putExtra("taocan_id",data.get(position).getTaocan_id());
                startActivity(intent);
            }
        });
        nestedScrollView.smoothScrollTo(0, 0);



        listss = new ArrayList<>();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){
            httpData();
        }
    }

    @Override
    public void setEvents() {
        super.setEvents();
        tv_type.setOnClickListener(this);
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
            }
        });
        ll_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(me, SelectionSubjectsAty.class);
                intent.putExtra("taocan_id",classBean.getData().getRecommend().getTaocan_id());
                startActivity(intent);
            }
        });
        iv_kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    
                    //已经登录，可以直接进入会话界面
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
                    // 进入主页面
                    Intent intent = new IntentBuilder(me)
                            .setVisitorInfo(MessageHelper.createVisitorInfo())
                            .setServiceIMNumber("kefuchannelimid_716578")
                            .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                            .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                            .setShowUserNick(true)
                            .setBundle(bundle)
                            .build();
                    startActivity(intent);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    showTips("暂未登陆");
                }
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        //TODO:接口请求
        httpData();
    }

    private void httpData(){
        WaitDialog.show(me,"数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/index", new Parameter()
                        .add("token", token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("subject_id",subject_id)
                        .add("page",String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    classBean = GsonUtil.GsonToBean(response, ClassBean.class);
                                    //侧边pop数据源
                                    listss = classBean.getData().getSubject();
                                    //实例化pop
                                    initSearchPopupWindow();
                                    try {
                                        tv_type.setText(listss.get(0).getCname());
                                    }catch (IndexOutOfBoundsException e){
                                        tv_type.setText("");
                                    }
                                    if (page == 1) {
                                        if (classBean.getData() == null || classBean.getData().getSystem().size() == 0) {
                                            showTips("暂无数据");
//                                            return;
                                        } else {
                                            adapter.setNewData(classBean.getData().getSystem());
                                        }
                                        Glide.with(me).load(classBean.getData().getRecommend().getTaocan_pic()).into(siv);
                                        tv_title.setText(classBean.getData().getRecommend().getTitle());
                                        tv_price.setText("¥ "+classBean.getData().getRecommend().getPrice());
                                        tv_pay.setText(classBean.getData().getRecommend().getCount());
                                    } else {
                                        if (classBean.getData() == null || classBean.getData().getSystem().size() == 0) {
                                            toast("没有更多数据了");
                                            return;
                                        }
                                        adapter.addData(classBean.getData().getSystem());
                                    }
                                    adapter.notifyDataSetChanged();
                                }else {
                                    toast(objectMap.get("message"));
                                }
                            }catch (NullPointerException e){
                                page = 1;
                                showTips("数据解析失败");
                            }

                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_system_class_tv_type:
                popupWindow.showAsDropDown(tv_type);
                break;
                default:break;
        }
    }
    private PopupWindow popupWindow;

    //初始化搜索popup (可以启动初始化)
    private void initSearchPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) me.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentview = inflater.inflate(R.layout.class_pop, null);//自己的弹框布局
        lv = contentview.findViewById(R.id.pop_add_list);//布局中的搜索按钮
        contentview.setFocusable(true); // 这个很重要
        contentview.setFocusableInTouchMode(true);
        popupWindow = new PopupWindow(contentview, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        contentview.setOnKeyListener(new View.OnKeyListener() {//监听系统返回键
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        lv.setAdapter(new PopAdapter(listss,me));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                subject_id = listss.get(i).getSubject_id();
                tv_type.setText(listss.get(i).getCname());
                httpData();
                popupWindow.dismiss();
            }
        });
    }


    /**
     * dialog列表适配器
     */
    private class PopAdapter extends BaseAdapter {
        private List<ClassBean.DataBean.SubjectBean> list;
        private Context context;

        public PopAdapter(List<ClassBean.DataBean.SubjectBean> list, Context context) {
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
            ViewHolder vh = null;
            if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.pop_tv_layout,null);
                vh = new ViewHolder();
                vh.tv = view.findViewById(R.id.pop_tv_content);
                view.setTag(vh);
            }else {
                vh = (ViewHolder) view.getTag();
            }
            vh.tv.setText(list.get(i).getCname());
            return view;
        }
        class ViewHolder{
            TextView tv;
        }
    }

}