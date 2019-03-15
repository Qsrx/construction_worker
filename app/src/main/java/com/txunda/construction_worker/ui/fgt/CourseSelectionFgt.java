package com.txunda.construction_worker.ui.fgt;


import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.CourseSelectionBean;
import com.txunda.construction_worker.ui.adapter.CoureseRvAdapter;
import com.txunda.construction_worker.ui.adapter.MyExtendableListViewAdapter;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.ui.view.ExpandableListViewForScrollView;
import com.txunda.construction_worker.utils.AllStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 课程选集fragment
 */
@Layout(R.layout.fgt_course_selection)
public class CourseSelectionFgt extends BaseFgt {
    RecyclerView h_rv,v_rv;
    CoureseRvAdapter h_adapter;
    List<CourseSelectionBean.DataBean.CourseListBean> list = new ArrayList<>();
    ExpandableListViewForScrollView listView;
    public static int index = 0;
    private CourseSelectionBean selectionBean;
    JzvdStd jzvdStd;
    private String [] fatherbean = null;
    private String [][] childtitle = null;
    private String [][] childlock = null;
    private String [][] childtime = null;
    private String [][] childmulu = null;
    MyExtendableListViewAdapter listViewAdapter;
    @Override
    public void initViews() {
        super.initViews();
        h_rv = findViewById(R.id.fgt_course_selection_hor_rv);
        listView = findViewById(R.id.expend_list);
        h_rv.setLayoutManager(new LinearLayoutManager(me, LinearLayoutManager.HORIZONTAL, false));
        h_adapter = new CoureseRvAdapter(list,me);
        h_rv.setAdapter(h_adapter);
//        listViewAdapter = new MyExtendableListViewAdapter();
//        listView.setAdapter(listViewAdapter);
        jzvdStd = me.findViewById(R.id.aty_course_selection_jz);

    }

    @Override
    public void setEvents() {
        super.setEvents();
        if (h_adapter!=null){
            h_adapter.setOnItemClick(new CoureseRvAdapter.MyItemClick() {
                @Override
                public void onItemClick(View view, int postion) {
                    if (list.get(postion).getType().equals("2")){
                        showTips("购买后才能观看");
                    }else {
                        index = postion;
                        h_adapter.notifyDataSetChanged();
                        //TODO:后台接口待添加视频名称
                        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/"+selectionBean.getData().getShipin().get(postion).getMulu()+"."+selectionBean.getData().getShipin().get(postion).getName() + ".mp4";
                        if (isFileExit(s)){
                            showTips("本地播放");
                            Log.d("jiaozistate", "onResponse: ===========本地播放");
                            jzvdStd.setUp(s,null,Jzvd.SCREEN_WINDOW_NORMAL);
                        }else {
                            Log.d("jiaozistate", "onResponse: ===========在线播放");
                            jzvdStd.setUp(selectionBean.getData().getShipin().get(postion).getVideo_path(), null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        }
//                        jzvdStd.setUp(selectionBean.getData().getShipin().get(postion).getVideo_path()
//                        ,null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        Glide.with(me).load(selectionBean.getData().getShipin().get(postion).getVideo_pic()).into(jzvdStd.thumbImageView);
                        jzvdStd.startVideo();
                    }
                }
            });
        }
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (childlock[i][i1].equals("2")){
                    showTips("购买后才能观看");
                }else {
                    if (i == 0){
                        index = i1;
                        h_adapter.notifyDataSetChanged();
                        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/"+selectionBean.getData().getShipin().get(i1).getMulu()+"."+selectionBean.getData().getShipin().get(i1).getName() + ".mp4";
                        if (isFileExit(s)){
                            showTips("本地播放");
                            Log.d("jiaozistate", "onResponse: ===========本地播放");
                            jzvdStd.setUp(s,null,Jzvd.SCREEN_WINDOW_NORMAL);
                        }else {
                            Log.d("jiaozistate", "onResponse: ===========在线播放");
                            jzvdStd.setUp(selectionBean.getData().getShipin().get(i1).getVideo_path(), null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        }
//                        jzvdStd.setUp(selectionBean.getData().getShipin().get(i1).getVideo_path()
//                                , null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        Glide.with(me).load(selectionBean.getData().getShipin().get(i1).getVideo_pic()).into(jzvdStd.thumbImageView);
                    }else {
                        int i2 = i1 + childtitle[0].length ;
                        index = i2;
                        h_adapter.notifyDataSetChanged();
                        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/"+selectionBean.getData().getShipin().get(i2).getMulu()+"."+selectionBean.getData().getShipin().get(i2).getName() + ".mp4";
                        if (isFileExit(s)){
                            showTips("本地播放");
                            Log.d("jiaozistate", "onResponse: ===========本地播放");
                            jzvdStd.setUp(s,null,Jzvd.SCREEN_WINDOW_NORMAL);
                        }else {
                            Log.d("jiaozistate", "onResponse: ===========在线播放");
                            jzvdStd.setUp(selectionBean.getData().getShipin().get(i2).getVideo_path(), null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        }
//                        jzvdStd.setUp(selectionBean.getData().getShipin().get(i2).getVideo_path()
//                                , null, JzvdStd.SCREEN_WINDOW_NORMAL);
                        Glide.with(me).load(selectionBean.getData().getShipin().get(i2).getVideo_pic()).into(jzvdStd.thumbImageView);
                    }
                    jzvdStd.startVideo();
                }
                return true;
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        //TODO:接口请求
//        httpData();
    }

    @Override
    public void onResume() {
        super.onResume();
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Course_selection", new Parameter()
                        .add("token", token)
                        .add("subject_id", CourseSelectionAty.subject_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.d("cousebean", "onResponse: ============" + response);
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    selectionBean = GsonUtil.GsonToBean(response, CourseSelectionBean.class);
                                    list.clear();
                                    list.addAll(selectionBean.getData().getCourse_list());
                                    h_adapter.notifyDataSetChanged();
                                    fatherbean = new String[selectionBean.getData().getDirectory().size()];
                                    childtitle = new String[selectionBean.getData().getDirectory().size()][];
                                    childlock = new String[selectionBean.getData().getDirectory().size()][];
                                    childmulu = new String[selectionBean.getData().getDirectory().size()][];
                                    childtime = new String[selectionBean.getData().getDirectory().size()][];
                                    for (int i = 0; i < selectionBean.getData().getDirectory().size(); i++) {
                                        fatherbean[i] = selectionBean.getData().getDirectory().get(i).getTitle();
                                        childtitle[i] = new String[selectionBean.getData().getDirectory().get(i).getList().size()];
                                        childtime[i] = new String[selectionBean.getData().getDirectory().get(i).getList().size()];
                                        childlock[i] = new String[selectionBean.getData().getDirectory().get(i).getList().size()];
                                        childmulu[i] = new String[selectionBean.getData().getDirectory().get(i).getList().size()];
                                        for (int j = 0; j < selectionBean.getData().getDirectory().get(i).getList().size(); j++) {
                                            childtitle[i][j] = selectionBean.getData().getDirectory().get(i).getList().get(j).getMulu()+"."+selectionBean.getData().getDirectory().get(i).getList().get(j).getName();
                                            childlock[i][j] = selectionBean.getData().getDirectory().get(i).getList().get(j).getType();
                                            childtime[i][j] = selectionBean.getData().getDirectory().get(i).getList().get(j).getTime();
                                        }
                                    }
//                                    childtitle[0] = new String[selectionBean.getData().getDirectory().getChuan().size()];
//                                    childtitle[1] = new String[selectionBean.getData().getDirectory().getJing().size()];
//                                    childtime[0] = new String[selectionBean.getData().getDirectory().getChuan().size()];
//                                    childtime[1] = new String[selectionBean.getData().getDirectory().getJing().size()];
//                                    childlock[0] = new String[selectionBean.getData().getDirectory().getChuan().size()];
//                                    childlock[1] = new String[selectionBean.getData().getDirectory().getJing().size()];
//                                    childmulu[0] = new String[selectionBean.getData().getDirectory().getChuan().size()];
//                                    childmulu[1] = new String[selectionBean.getData().getDirectory().getJing().size()];
//                                    for (int i = 0; i < selectionBean.getData().getDirectory().getChuan().size(); i++) {
//                                        childtitle[0][i] = selectionBean.getData().getDirectory().getChuan().get(i).getMulu()+"."+selectionBean.getData().getDirectory().getChuan().get(i).getName();
//                                        childlock[0][i] = selectionBean.getData().getDirectory().getChuan().get(i).getType();
//                                        childtime[0][i] = selectionBean.getData().getDirectory().getChuan().get(i).getTime();
//                                    }
//                                    for (int i = 0; i < selectionBean.getData().getDirectory().getJing().size(); i++) {
//                                        childtitle[1][i] = selectionBean.getData().getDirectory().getJing().get(i).getMulu()+"."+selectionBean.getData().getDirectory().getJing().get(i).getName();
//                                        childlock[1][i] = selectionBean.getData().getDirectory().getJing().get(i).getType();
//                                        childtime[1][i] = selectionBean.getData().getDirectory().getJing().get(i).getTime();
//                                    }
                                    listViewAdapter = new MyExtendableListViewAdapter(fatherbean,childtitle,childtime,childlock);
                                    listView.setAdapter(listViewAdapter);
                                }
                            } catch (NullPointerException e) {
                                toast(e.getMessage());
                            }
                        }
                    }
                }
        );
    }
    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
    public static boolean isFileExit(String path){
        if(path == null){
            return false;
        }
        try{
            File f = new File(path);
            if(!f.exists()){
                return false;
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }
}