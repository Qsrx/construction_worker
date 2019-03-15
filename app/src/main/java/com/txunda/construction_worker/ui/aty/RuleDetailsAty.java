package com.txunda.construction_worker.ui.aty;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.RegisterRuleDetailsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_rule_details)
public class RuleDetailsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_rule_details_web)
    WebView atyRuleDetailsWeb;

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        WebSettings settings = atyRuleDetailsWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        atyRuleDetailsWeb.requestFocusFromTouch();
        atyRuleDetailsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyRuleDetailsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/article", new Parameter(), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        RegisterRuleDetailsBean detailsBean = GsonUtil.GsonToBean(response,RegisterRuleDetailsBean.class);
                        headerTvTitle.setText(detailsBean.getData().getTitle());
                        atyRuleDetailsWeb.loadDataWithBaseURL(null, detailsBean.getData().getContent(), "text/html", "utf-8", null);
                    }
                }
            }
        });
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}