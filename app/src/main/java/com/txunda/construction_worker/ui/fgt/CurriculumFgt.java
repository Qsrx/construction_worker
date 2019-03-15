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
import com.txunda.construction_worker.bean.CurriculumBean;
import com.txunda.construction_worker.ui.adapter.CurriculumAdp;
import com.txunda.construction_worker.ui.adapter.CurriculumRvAdapter;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.List;
import java.util.Map;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * 课程fragment
 */
@Layout(R.layout.fgt_curriculum)
public class CurriculumFgt extends BaseFgt {
    SmartRefreshLayout refreshLayout;
//    RecyclerView recyclerView;
    SwipeMenuListView swipeMenuListview;
    private CurriculumRvAdapter adapter;
    private int page = 1;
    private CurriculumBean bean;
    private CurriculumAdp adp;
    @Override
    public void initViews() {
        super.initViews();
//        recyclerView = findViewById(R.id.fgt_curriculum_rv);
        swipeMenuListview = findViewById(R.id.swipe_menu_listview);
        refreshLayout = findViewById(R.id.fgt_curriculum_refreshLayout);
//        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        adapter = new CurriculumRvAdapter(R.layout.slection_subjects_content_layout);
//        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
    }

    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/collection", new Parameter()
                        .add("token", token)
                        .add("type", "1")
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                bean = GsonUtil.GsonToBean(response, CurriculumBean.class);
                                if (page == 1) {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        showTips("暂无数据");
                                        adp = new CurriculumAdp(bean.getData(),me);
                                    }else {
                                        adp = new CurriculumAdp(bean.getData(),me);
                                    }
                                } else {
                                    if (bean.getData() == null || bean.getData().size() == 0) {
                                        page = 1;
                                        httpData();
                                        toast("没有更多数据了");
                                        return;
                                    }
                                    adp = new CurriculumAdp(bean.getData(),me);
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
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CurriculumBean.DataBean> data = adapter.getData();
                Intent intent = new Intent(me, CourseSelectionAty.class);
                intent.putExtra("subject_id", data.get(position).getCourse_id());
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                final List<CurriculumBean.DataBean> data = adapter.getData();
                new AlertDialog(me).builder().setTitle("提示").setMsg("您是否确认取消收藏该课程?")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                httpCollection(data.get(position).getCourse_id());
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
//                        toast(position);
                        httpCollection(bean.getData().get(position).getCourse_id());
                        break;
                }
                return true;
            }
        });
        swipeMenuListview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        swipeMenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(me, CourseSelectionAty.class);
                intent.putExtra("subject_id", bean.getData().get(i).getCourse_id());
                startActivity(intent);
            }
        });
    }
    /**
     * 收藏/取消收藏
     */
    private void httpCollection(String subject_id) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/collection", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                page = 1;
                                httpData();
                            } else {
                                showTips(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}