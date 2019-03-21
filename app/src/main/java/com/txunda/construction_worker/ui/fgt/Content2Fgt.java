package com.txunda.construction_worker.ui.fgt;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.ui.adapter.ContentAdp;
import com.txunda.construction_worker.ui.aty.ViewPger2Aty;
import com.txunda.construction_worker.utils.PanDuanUtils;

import java.util.ArrayList;
import java.util.Map;

import static com.txunda.construction_worker.ui.aty.ViewPger2Aty.STYLE;


/**
 * A simple {@link Fragment} subclass.
 */
@Layout(R.layout.fgt_content2)
public class Content2Fgt extends BaseFgt{
    private TextView aty_do_work_tv_title;
    private TextView aty_do_work_tv_content;
    private TextView fgt_content_see_all;
    private TextView aty_do_work_tv_jx;
    private TextView fgt_do_work_kd;
    private LinearLayout fgt_content_jx;
    private RecyclerView recyclerView;
    private LinearLayout ll_select;
    private LinearLayout ll_anli;
    private WebView fgt_web;
    private ImageView iv_img;
    private TextView al_et;
    private int index;
    private ContentAdp contentAdp;
    private ArrayList<Map<String, String>> option;

    @Override
    public void initViews() {
        index = getArguments().getInt("index");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        aty_do_work_tv_content = findViewById(R.id.aty_do_work_tv_content);
        aty_do_work_tv_title = findViewById(R.id.aty_do_work_tv_title);
        fgt_content_see_all = findViewById(R.id.aty_do_work_tv_see);
        aty_do_work_tv_jx = findViewById(R.id.fgt_do_work_jx);
        fgt_do_work_kd = findViewById(R.id.fgt_do_work_kd);
        fgt_content_jx = findViewById(R.id.aty_do_work_ll_jx);

        ll_select = findViewById(R.id.fgt_content2_ll_select);
        ll_anli = findViewById(R.id.fgt_content2_ll_anl);
        fgt_web = findViewById(R.id.fgt_content2_web);
        iv_img = findViewById(R.id.fgt_content2_iv);
        al_et = findViewById(R.id.fgt_content2_et);
        contentAdp = new ContentAdp(R.layout.item_do_work_small_layout);
        recyclerView.setAdapter(contentAdp);

        WebSettings settings = fgt_web.getSettings();
        settings.setJavaScriptEnabled(true);
        fgt_web.requestFocusFromTouch();
        fgt_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        fgt_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });

        if (STYLE == 3){
            fgt_content_see_all.setVisibility(View.GONE);
        }
    }

    @Override
    public void initDatas() {
        Map<String, String> map = ViewPger2Aty.list.get(index);
        try {
            //id和标题
            aty_do_work_tv_title.setText(map.get("title"));
            aty_do_work_tv_content.setText(map.get("sub_title"));
            option = JSONUtils.parseKeyAndValueToMapList(map.get("option"));
            aty_do_work_tv_jx.setText(map.get("analysis"));
            fgt_do_work_kd.setText(map.get("key"));
            for (int i = 0; i < option.size(); i++) {
                option.get(i).put("xunze", index + "");
            }
            contentAdp.setNewData(option);
            contentAdp.notifyDataSetChanged();
        }catch (Exception e){
            ll_select.setVisibility(View.GONE);
            ll_anli.setVisibility(View.VISIBLE);
            fgt_content_see_all.setVisibility(View.GONE);
            fgt_content_jx.setVisibility(View.VISIBLE);
//            al_et.setText(map.get("answer1"));
            fgt_web.loadDataWithBaseURL(null,map.get("sub_title")+map.get("answer1"), "text/html", "utf-8",null);
            Glide.with(me).load(map.get("fenxi_pic")).into(iv_img);
        }
    }

    @Override
    public void setEvents() {
        contentAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Map<String, String> map = contentAdp.getData().get(position);
                try {
                    if (ViewPger2Aty.type.equals("2")){
                        if (PanDuanUtils.list.get(index).user.contains(map.get("answer"))) {
                            PanDuanUtils.list.get(index).user = deleteCharString(PanDuanUtils.list.get(index).user,map.get("answer").charAt(0));
                        }else {
                            PanDuanUtils.list.get(index).user = PanDuanUtils.list.get(index).user+map.get("answer");
                        }
//                    PanDuanUtils.list.get(index).user = map.get("answer");
                    }else {
                        PanDuanUtils.list.get(index).user = map.get("answer");
                    }
                    contentAdp.notifyDataSetChanged();
                }catch (NullPointerException e){
                    if (PanDuanUtils.list.get(index).type.equals("2")){
                        if (PanDuanUtils.list.get(index).user.contains(map.get("answer"))) {
                            PanDuanUtils.list.get(index).user = deleteCharString(PanDuanUtils.list.get(index).user,map.get("answer").charAt(0));
                        }else {
                            PanDuanUtils.list.get(index).user = PanDuanUtils.list.get(index).user+map.get("answer");
                        }
//                    PanDuanUtils.list.get(index).user = map.get("answer");
                    }else {
                        PanDuanUtils.list.get(index).user = map.get("answer");
                    }
                    contentAdp.notifyDataSetChanged();
                }

//                Map<String, String> map = contentAdp.getData().get(position);
//                PanDuanUtils.list.get(index).user = map.get("answer");
//                contentAdp.notifyDataSetChanged();
            }
        });
        fgt_content_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fgt_content_see_all.setVisibility(View.GONE);
                fgt_content_jx.setVisibility(View.VISIBLE);
            }
        });
    }
    public String deleteCharString(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }
        }
        return deleteString;
    }
}