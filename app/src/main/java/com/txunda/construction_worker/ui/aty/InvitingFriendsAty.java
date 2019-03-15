package com.txunda.construction_worker.ui.aty;

import android.util.Log;
import android.view.View;
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
import com.txunda.construction_worker.bean.InvitingFriendsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请好友
 */
@Layout(R.layout.aty_inviting_friends)
public class InvitingFriendsAty extends BaseAty {

    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_inviting_friends_invi)
    TextView atyInvitingFriendsInvi;
    @BindView(R.id.aty_inviting_friends_success)
    TextView atyInvitingFriendsSuccess;
    @BindView(R.id.aty_login_tv_login)
    TextView atyLoginTvLogin;
    @BindView(R.id.aty_inviting_friends_web)
    WebView atyInvitingFriendsWeb;
    @BindView(R.id.aty_inviting_friends_code)
    TextView atyInvitingFriendsCode;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("邀请好友");
        atyInvitingFriendsWeb.requestFocusFromTouch();
        atyInvitingFriendsWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyInvitingFriendsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }


    @OnClick({R.id.header_iv_back, R.id.aty_login_tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_login_tv_login:
                jump(ShardAty.class);
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        postData();
    }

    private void postData(){
//        Myinfo/invitation
        HttpRequest.POST(this, AllStatus.BASE_URL + "Myinfo/invitation", new Parameter()
                .add("token", token), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        InvitingFriendsBean friendsBean = GsonUtil.GsonToBean(response,InvitingFriendsBean.class);
                        Log.d("invitingbean", "onResponse: ===================="+response);
                        atyInvitingFriendsCode.setText(friendsBean.getData().getShare_code());
                        atyInvitingFriendsInvi.setText(String.valueOf(friendsBean.getData().getAll_award()));
                        atyInvitingFriendsSuccess.setText(friendsBean.getData().getCount());
                        atyInvitingFriendsWeb.loadDataWithBaseURL(null, friendsBean.getData().getRule(), "text/html", "utf-8", null);
                    }else {
                        showErrorTip(objectMap.get("message").toString());
                    }
                }
            }
        });
    }
}