package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.UnlockDetailsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.MessageHelper;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@Layout(R.layout.aty_unlock_details)
public class UnlockDetailsAty extends BaseAty {
    @BindView(R.id.aty_unlock_details_header_bg)
    ImageView atyUnlockDetailsHeaderBg;
    @BindView(R.id.aty_unlock_details_back)
    ImageView atyUnlockDetailsBack;
    @BindView(R.id.aty_unlock_details_kf)
    RelativeLayout atyUnlockDetailsKf;
    @BindView(R.id.aty_unlock_details_buy)
    RelativeLayout atyUnlockDetailsBuy;
    @BindView(R.id.aty_unlock_details_web)
    WebView atyUnlockDetailsWeb;
    @BindView(R.id.aty_unlock_details_title)
    TextView atyUnlockDetailsTitle;
    @BindView(R.id.aty_unlock_details_price)
    TextView atyUnlockDetailsPrice;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.transparencyBar(this);
        WebSettings settings = atyUnlockDetailsWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        atyUnlockDetailsWeb.requestFocusFromTouch();
        atyUnlockDetailsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyUnlockDetailsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }

    @OnClick({R.id.aty_unlock_details_back, R.id.aty_unlock_details_kf, R.id.aty_unlock_details_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_unlock_details_back:
                finish();
                break;
            case R.id.aty_unlock_details_kf:
//                new AlertDialog(me).builder().setTitle("建工邦教育").setMsg("400 875 6958")
//                        .setPositiveButton("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                toast("点击了确认");
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//
//                    }
//                }).show();
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
                    // 进入主页面
                    Intent intent = new IntentBuilder(me)
                            .setVisitorInfo(MessageHelper.createVisitorInfo())
                            .setServiceIMNumber("kefuchannelimid_716578")
                            .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                            .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                            .setShowUserNick(true)
                            .setBundle(bundle)
                            .build();
                    startActivity(intent);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    showErrorTip("暂未登陆");
                }
                break;
            case R.id.aty_unlock_details_buy:
//                jump(ConfirmOrderAty.class);
                Intent intent = new Intent(me, ConfirmOrderAty.class);
                intent.putExtra("subject_id",subject_id);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/buy_exercises", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                UnlockDetailsBean detailsBean = GsonUtil.GsonToBean(response, UnlockDetailsBean.class);
                                atyUnlockDetailsWeb.loadDataWithBaseURL(null, detailsBean.getData().getDesc(), "text/html", "utf-8", null);
                                Glide.with(me).load(detailsBean.getData().getPic()).into(atyUnlockDetailsHeaderBg);
                                atyUnlockDetailsTitle.setText(detailsBean.getData().getTitle());
                                atyUnlockDetailsPrice.setText("¥ "+detailsBean.getData().getPrice());
                            } else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}