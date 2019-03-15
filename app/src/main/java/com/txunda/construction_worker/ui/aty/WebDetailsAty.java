package com.txunda.construction_worker.ui.aty;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_web_details)
public class WebDetailsAty extends BaseAty {
    @BindView(R.id.aty_web_details_web)
    WebView atyWebDetailsWeb;
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    private String url = "";

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        url = getIntent().getStringExtra("url");
        WebSettings settings = atyWebDetailsWeb.getSettings();
        initWeb(settings, atyWebDetailsWeb);
        headerTvTitle.setText("建工邦");
//        settings.setJavaScriptEnabled(true);
//        atyWebDetailsWeb.requestFocusFromTouch();
//        atyWebDetailsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//
//        /**覆盖调用系统或自带浏览器行为打开网页*/
//        atyWebDetailsWeb.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                view.loadUrl(url);
//                return true;
//            }
//        });
        atyWebDetailsWeb.loadUrl(url);
    }

    protected void initWeb(WebSettings webSettings, WebView tvWeb) {
//        webSettings = tvWeb.getSettings();

        webSettings.setPluginState(WebSettings.PluginState.ON);

        webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //其他细节操作
        //        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultFontSize((int) 15);
    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}