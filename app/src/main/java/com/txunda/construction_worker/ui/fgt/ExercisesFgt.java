package com.txunda.construction_worker.ui.fgt;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.ExercisesBean;
import com.txunda.construction_worker.ui.adapter.ExercisesRvAdapter;
import com.txunda.construction_worker.ui.adapter.adp.ExercisesAdp;
import com.txunda.construction_worker.ui.aty.ViewParsingAty;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.List;
import java.util.Map;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * 练习题fragment
 */
@Layout(R.layout.fgt_exercises)
public class ExercisesFgt extends BaseFgt {
    SmartRefreshLayout refreshLayout;
//    RecyclerView recyclerView;
    private ExercisesRvAdapter adapter;
    private int page = 1;
    private ExercisesAdp adp;
    private ExercisesBean bean;
    SwipeMenuListView swipeMenuListview;
    @Override
    public void initViews() {
        super.initViews();
//        recyclerView = findViewById(R.id.fgt_curriculum_rv);
        refreshLayout = findViewById(R.id.fgt_curriculum_refreshLayout);
//        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new ExercisesRvAdapter(R.layout.item_exercises_layout);
        swipeMenuListview = findViewById(R.id.swipe_menu_listview);
//        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
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
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                final List<ExercisesBean.DataBean> data = adapter.getData();
                new AlertDialog(me).builder().setTitle("提示").setMsg("您是否确认取消收藏该题?")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkCollect(data.get(position).getQuestions_id());
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                    }
                }).show();
                return false;
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ExercisesBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, ViewParsingAty.class);
                intent.putExtra("questions_id",data.get(position).getQuestions_id());
                intent.putExtra("type","2");
                startActivity(intent);
            }
        });
        /*
         * 创建滑动菜单
         * */

        SwipeMenuCreator creator  = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(me);
                // 背景颜色
                openItem.setBackground(new ColorDrawable(Color.RED));
                // 宽度
                openItem.setWidth(dp2px(90));
                // 标题
                openItem.setTitle("删除");
                // 标题大小
                openItem.setTitleSize(18);
                // 标题颜色
                openItem.setTitleColor(Color.WHITE);
                // 添加
                menu.addMenuItem(openItem);
            }
        };
//        设置creator
        swipeMenuListview.setMenuCreator(creator );
        swipeMenuListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        //删除事件
                        checkCollect(bean.getData().get(position).getQuestions_id());
                        break;
                }
                return true;
            }
        });
        swipeMenuListview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        swipeMenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(me, ViewParsingAty.class);
                intent.putExtra("questions_id",bean.getData().get(i).getQuestions_id());
                intent.putExtra("type","2");
                startActivity(intent);
            }
        });
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/collection", new Parameter()
                        .add("token", token)
                        .add("type", "2")
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                bean = GsonUtil.GsonToBean(response, ExercisesBean.class);
                                if (page == 1) {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        showTips("暂无数据");
                                        adp = new ExercisesAdp(bean.getData(),me);
                                    }else {
                                        adp = new ExercisesAdp(bean.getData(),me);
                                    }
                                } else {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        page = 1;
                                        httpData();
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adp = new ExercisesAdp(bean.getData(),me);
                                }
                                swipeMenuListview.setAdapter(adp);
                                adapter.notifyDataSetChanged();

//                                if (page == 1) {
//                                    if (bean.getData() == null || bean.getData().size() == 0) {
//                                        showTips("暂无数据");
//                                        return;
//                                    } else {
//                                        adapter.setNewData(bean.getData());
//                                    }
//                                } else {
//                                    if (bean.getData() == null || bean.getData().size() == 0) {
//                                        toast("没有更多数据了");
//                                        return;
//                                    }
//                                    adapter.addData(bean.getData());
//                                }
//                                adapter.notifyDataSetChanged();
                            }else {
                                showTips(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
    /**
     * 收藏/取消收藏
     */
    private void checkCollect(String str){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/questions_collection", new Parameter()
                        .add("token", token)
                        .add("questions_id", str)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                page = 1;
                                httpData();
                            }else {
                                toast(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

}