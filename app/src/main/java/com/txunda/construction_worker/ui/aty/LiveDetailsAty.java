package com.txunda.construction_worker.ui.aty;

import android.util.Log;
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
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.LiveDetailsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 直播详情
 */
@Layout(R.layout.aty_live_details)
public class LiveDetailsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_live_details_jz)
    JzvdStd atyLiveDetailsJz;
    @BindView(R.id.aty_live_details_title)
    TextView atyLiveDetailsTitle;
    @BindView(R.id.aty_live_details_web)
    WebView atyLiveDetailsWeb;
    private String taocan_id;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        taocan_id = getIntent().getStringExtra("taocan_id");
        headerTvTitle.setText("直播详情");
//        atyLiveDetailsJz.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
//                , "直播标题", JzvdStd.SCREEN_WINDOW_NORMAL);
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(atyLiveDetailsJz.thumbImageView);
//        atyLiveDetailsJz.startVideo();
        atyLiveDetailsWeb.requestFocusFromTouch();
        atyLiveDetailsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyLiveDetailsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
//        atyLiveDetailsWeb.loadUrl("https://www.baidu.com");
//        atyLiveDetailsWeb.loadDataWithBaseURL(null, bean.getData().getContent(), "text/html", "utf-8", null);
    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    private void httpData(){
        WaitDialog.show(me,"正在加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/live_detail", new Parameter()
                        .add("token", token)
                        .add("taocan_id", taocan_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                Log.d("livedetailsbean", "onResponse: ================="+response);
                                LiveDetailsBean detailsBean = GsonUtil.GsonToBean(response, LiveDetailsBean.class);
                                atyLiveDetailsJz.setUp(detailsBean.getData().getUrl()
                                        , detailsBean.getData().getTitle(), JzvdStd.SCREEN_WINDOW_NORMAL);
                                Glide.with(me).load(detailsBean.getData().getPic()).into(atyLiveDetailsJz.thumbImageView);
//                                atyLiveDetailsJz.startVideo();
                                atyLiveDetailsWeb.loadDataWithBaseURL(null, detailsBean.getData().getText(), "text/html", "utf-8", null);
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }
}