package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.HomeDetailsBean;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_home_details)
public class HomeDetailsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_home_details_title)
    TextView atyHomeDetailsTitle;
    @BindView(R.id.aty_home_details_tv_name)
    TextView atyHomeDetailsTvName;
    @BindView(R.id.aty_home_details_tv_time)
    TextView atyHomeDetailsTvTime;
    @BindView(R.id.aty_home_details_iv)
    ImageView atyHomeDetailsIv;
    @BindView(R.id.aty_home_details_web)
    WebView atyHomeDetailsWeb;
    @BindView(R.id.header_iv_share)
    ImageView headerIvShare;
    private String type = "";
    private String article_id = "";

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        type = getIntent().getStringExtra("type");
        article_id = getIntent().getStringExtra("article_id");
        headerTvTitle.setText("详情");
        WebSettings settings = atyHomeDetailsWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        atyHomeDetailsWeb.requestFocusFromTouch();
        atyHomeDetailsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyHomeDetailsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
//        atyHomeDetailsWeb.loadUrl("https://www.baidu.com");
//        atyHomeDetailsWeb.loadDataWithBaseURL(null, bean.getData().getContent(), "text/html", "utf-8", null);
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        headerIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(me, ShardAty.class);
                intent.putExtra("id",article_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        //TODO:接口调用
        httpData();
    }

    private void httpData() {
        HttpRequest.POST(this, AllStatus.BASE_URL + "Index/detail", new Parameter()
                        .add("token", MainAty.token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("type", type)
                        .add("article_id", article_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                HomeDetailsBean detailsBean = GsonUtil.GsonToBean(response, HomeDetailsBean.class);
                                atyHomeDetailsTitle.setText(detailsBean.getData().getTitle());
                                Glide.with(me).load(detailsBean.getData().getCover()).into(atyHomeDetailsIv);
                                atyHomeDetailsTvName.setText(detailsBean.getData().getCreate_time());
                                atyHomeDetailsWeb.loadDataWithBaseURL(null, detailsBean.getData().getContent(), "text/html", "utf-8", null);
                            }
                        }
                    }
                });
    }

}