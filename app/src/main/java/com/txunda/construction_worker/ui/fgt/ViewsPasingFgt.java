package com.txunda.construction_worker.ui.fgt;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.ui.adapter.ViewsPasingAdp;
import com.txunda.construction_worker.ui.aty.ViewsPasingAty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
@Layout(R.layout.fgt_views_pasing)
public class ViewsPasingFgt extends BaseFgt {
    private int index;
    private ViewsPasingAdp viewsPasingAdp;
    private ArrayList<Map<String, String>> option;
    private LinearLayout ll_select,ll_anli;
    private WebView webView;
    private ImageView iv_viewspasing;
    TextView tv_title,tv_s_title,tv_answer,tv_choose,tv_jx,tv_kd;
    RecyclerView rv;
    @Override
    public void initViews() {
        super.initViews();
        index = getArguments().getInt("index");
        tv_title = findViewById(R.id.aty_do_work_tv_title);
        tv_s_title = findViewById(R.id.aty_do_work_tv_content);
        tv_answer = findViewById(R.id.aty_view_parsing_ck);
        tv_choose = findViewById(R.id.aty_view_parsing_myself);
        tv_jx = findViewById(R.id.aty_view_parsing_jx);
        tv_kd = findViewById(R.id.aty_view_parsing_point);
        rv = findViewById(R.id.fgt_views_pasing_rv);
        ll_select = findViewById(R.id.fgt_views_pasing_ll_select);
        ll_anli = findViewById(R.id.fgt_views_pasing_ll_anl);
        webView = findViewById(R.id.fgt_content2_web);
        rv.setLayoutManager(new LinearLayoutManager(me));
        viewsPasingAdp = new ViewsPasingAdp(R.layout.item_do_work_small_layout);
        rv.setAdapter(viewsPasingAdp);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.requestFocusFromTouch();
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initDatas() {
        super.initDatas();
        Map<String, String> map = ViewsPasingAty.views_list.get(index);
        try {
            if (map.get("type").equals("4")){
                ll_anli.setVisibility(View.VISIBLE);
                ll_select.setVisibility(View.GONE);
                tv_title.setText(map.get("title"));
                webView.loadDataWithBaseURL(null,map.get("sub_title")+map.get("answer"), "text/html", "utf-8",null);
                tv_jx.setText(map.get("analysis"));
                tv_kd.setText(map.get("key"));
                return;
            }
            //id和标题
            tv_title.setText(map.get("title"));
            tv_s_title.setText(map.get("sub_title"));
            option = JSONUtils.parseKeyAndValueToMapList(map.get("option"));
            tv_jx.setText(map.get("analysis"));
            tv_kd.setText(map.get("key"));
            for (int i = 0; i < option.size(); i++) {
                option.get(i).put("xz", index + "");
            }
            viewsPasingAdp.setNewData(option);
            viewsPasingAdp.notifyDataSetChanged();
            List<Map<String, String>> data = viewsPasingAdp.getData();
            Log.d("viewpasingfgt", "initDatas: ============"+data.get(0).get("question"));
            tv_answer.setText(map.get("answer"));
            tv_choose.setText(map.get("choose_answer"));
        }catch (Exception e){
            showTips("出错");
//            ll_select.setVisibility(View.GONE);
//            ll_anli.setVisibility(View.VISIBLE);
//            fgt_web.loadDataWithBaseURL(null,map.get("sub_title"), "text/html", "utf-8",null);
//            Glide.with(me).load(map.get("fenxi_pic")).into(iv_img);
        }
    }
}