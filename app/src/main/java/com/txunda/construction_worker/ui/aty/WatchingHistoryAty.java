package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
import com.txunda.construction_worker.bean.WatchingHistoryBean;
import com.txunda.construction_worker.ui.adapter.WatchingHistoryLvAdp;
import com.txunda.construction_worker.ui.adapter.WatchingHistoryRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

@Layout(R.layout.aty_watching_history)
public class WatchingHistoryAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
//    @BindView(R.id.aty_watching_history_recycler)
//    RecyclerView atyWatchingHistoryRecycler;
    @BindView(R.id.aty_watching_history_smartrefresh)
    SmartRefreshLayout atyWatchingHistorySmartrefresh;
    @BindView(R.id.aty_watching_history_tv_clear)
    TextView atyWatchingHistoryTvClear;
    @BindView(R.id.swipe_menu_listview)
    SwipeMenuListView swipeMenuListview;
    private WatchingHistoryRvAdapter adapter;
    List<List<String>> list = new ArrayList<>();
    private List<String> lists = new ArrayList<>();
    private String course_id = "";
    private int page = 1;
    private WatchingHistoryBean historyBean;
    WatchingHistoryLvAdp adp;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("观看历史");
        adapter = new WatchingHistoryRvAdapter(R.layout.watching_history_rv_layout);
//        atyWatchingHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
//        atyWatchingHistoryRecycler.setAdapter(adapter);

    }

    @OnClick({R.id.header_iv_back, R.id.aty_watching_history_tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_watching_history_tv_clear:
                httpClearHistory(course_id,"1");
                break;
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
        //刷新
        atyWatchingHistorySmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                httpData();
            }
        });
        atyWatchingHistorySmartrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                httpData();
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
                        httpClearHistory(historyBean.getData().get(position).getCourse_id(),"2");
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
                intent.putExtra("subject_id", historyBean.getData().get(i).getCourse_id());
                startActivity(intent);
            }
        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                List<WatchingHistoryBean.DataBean> data = adapter.getData();
//                Intent intent = new Intent(me, CourseSelectionAty.class);
//                intent.putExtra("subject_id", data.get(position).getCourse_id());
//                startActivity(intent);
//            }
//        });
//        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
//                final List<WatchingHistoryBean.DataBean> data = adapter.getData();
//                new AlertDialog(me).builder().setTitle("提示").setMsg("您是否确认取消删除该记录?")
//                        .setPositiveButton("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                httpClearHistory(data.get(position).getCourse_id(),"2");
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//
//                    }
//                }).show();
//                return false;
//            }
//        });
    }

    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Histroy/history_list", new Parameter()
                        .add("token", token)
                        .add("page", String.valueOf(page))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        atyWatchingHistorySmartrefresh.finishRefresh();
                        atyWatchingHistorySmartrefresh.finishLoadmore();
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            historyBean = GsonUtil.GsonToBean(response, WatchingHistoryBean.class);
                            if (page == 1) {
                                if (historyBean.getData() == null || historyBean.getData().size() == 0) {
                                    showErrorTip("暂无数据");
                                    adp = new WatchingHistoryLvAdp(historyBean.getData(),me);
                                }else {
                                    adp = new WatchingHistoryLvAdp(historyBean.getData(),me);
                                }
                            } else {
                                if (historyBean.getData() == null || historyBean.getData().size() == 0) {
                                    page = 1;
                                    httpData();
                                    toast("没有更多数据了");
                                    return;
                                }
                                adp = new WatchingHistoryLvAdp(historyBean.getData(),me);
                            }
                            swipeMenuListview.setAdapter(adp);
                            adapter.notifyDataSetChanged();
//                            if (page == 1) {
//                                if (historyBean.getData() == null || historyBean.getData().size() == 0) {
//                                    showErrorTip("暂无数据");
//                                    adapter.setNewData(historyBean.getData());
//                                }else {
//                                    adapter.setNewData(historyBean.getData());
//                                }
//                            } else {
//                                if (historyBean.getData() == null || historyBean.getData().size() == 0) {
//                                    toast("没有更多数据了");
//                                    return;
//                                }
//                                adapter.addData(historyBean.getData());
//                            }
//                            adapter.notifyDataSetChanged();
                        } else {
                            toast(objectMap.get("message"));
                        }
                    }
                });
    }

    /**
     * 清空历史记录
     */
    private void httpClearHistory(String course_id,String is_all) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Histroy/del_history", new Parameter()
                        .add("token", token)
                        .add("course_id", course_id)
                        .add("is_all", is_all)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        try {
                            if (objectMap.get("code").equals("1")) {
                                toast(objectMap.get("message"));
                                page = 1;
                                httpData();
                                return;
                            }
                            showErrorTip(objectMap.get("message").toString());
                        }catch (NullPointerException e){
                            showErrorTip("清空失败");
                        }
                    }
                });
    }
}