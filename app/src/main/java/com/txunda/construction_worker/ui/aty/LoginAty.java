package com.txunda.construction_worker.ui.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.bean.BindBean;
import com.txunda.construction_worker.bean.NBindBean;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

@Layout(R.layout.aty_login)
public class LoginAty extends Activity implements PlatformActionListener {
    @BindView(R.id.aty_login_tv_see)
    TextView atyLoginTvSee;
    @BindView(R.id.aty_login_tv_yzm)
    TextView atyLoginTvYzm;
    @BindView(R.id.aty_login_tv_pw)
    TextView atyLoginTvPw;
    @BindView(R.id.aty_login_register)
    TextView atyLoginRegister;
    @BindView(R.id.aty_login_tv_wx)
    TextView atyLoginTvWx;
    @BindView(R.id.aty_login_tv_rule)
    TextView atyLoginTvRule;
    private String uid = "";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(LoginAty.this, "授权登录成功", Toast.LENGTH_SHORT).show();
                    Platform platform = (Platform) msg.obj;
                    Log.d("第三方", "handleMessage: ============"+platform.getDb().get("unionid"));
                    uid = platform.getDb().get("unionid");
                    httpWxLogin();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        ButterKnife.bind(this);
        String string = Preferences.getInstance().getString(this, "construction", "token");
        if (string.length()>0){
            startActivity(new Intent(this,MainAty.class));
            finish();
        }
    }

    @OnClick({R.id.aty_login_tv_see, R.id.aty_login_tv_yzm, R.id.aty_login_tv_pw, R.id.aty_login_register, R.id.aty_login_tv_wx, R.id.aty_login_tv_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_login_tv_see:
//                startActivity(new Intent(this, MainAty.class));
                startActivity(new Intent(this, ChoiceProfessionAty.class));
                finish();
                break;
            case R.id.aty_login_tv_yzm:
                Intent intent = new Intent(this, YzmLoginAty.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.aty_login_tv_pw:
                Intent intent2 = new Intent(this, YzmLoginAty.class);
                intent2.putExtra("type","2");
                startActivity(intent2);
                break;
            case R.id.aty_login_register:
                Intent intent3 = new Intent(this, YzmLoginAty.class);
                intent3.putExtra("type","3");
                startActivity(intent3);
                break;
            case R.id.aty_login_tv_wx:
                loginByWeixin();
                break;
            case R.id.aty_login_tv_rule:
                Intent intent1 = new Intent(this, RuleDetailsAty.class);
                startActivity(intent1);
                break;
        }
    }
    /**
     * 微信登陆
     */
    private void loginByWeixin() {
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(this);
        wechat.SSOSetting(false);
        if (!wechat.isClientValid()) {
            Toast.makeText(this, "微信未安装,请先安装微信", Toast.LENGTH_SHORT).show();
        }
        authorize(wechat);
    }
    /**
     * 授权
     *
     * @param platform
     */
    private void authorize(Platform platform) {
        if (platform == null) {
            return;
        }
        if (platform.isAuthValid()) {  //如果授权就删除授权资料
            platform.removeAccount(true);
        }

        platform.showUser(null); //授权并获取用户信息
    }
    /**
     * 授权成功的回调
     *
     * @param platform
     * @param i
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    /**
     * 授权错误的回调
     *
     * @param platform
     * @param i
     * @param throwable
     */
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Message message = Message.obtain();
        message.what = 2;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    /**
     * 授权取消的回调
     *
     * @param platform
     * @param i
     */
    @Override
    public void onCancel(Platform platform, int i) {
        Message message = Message.obtain();
        message.what = 3;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    /**
     * 微信登录请求
     */
    private void httpWxLogin(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/threeLogin", new Parameter()
                .add("openid", uid), new
                ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        Log.d("WXLOGINID", "handleMessage: ============"+response);
                        if (objectMap.get("code").equals("1")){
                            //TODO:try是已绑定，catch走未绑定
//                            try {
                                BindBean bindBean = GsonUtil.GsonToBean(response, BindBean.class);
                                if (!bindBean.getData().getAccount().equals("")){
                                    Preferences.getInstance().set(LoginAty.this,"construction","token",bindBean.getData().getToken());
                                    Preferences.getInstance().set(LoginAty.this,"construction","account",bindBean.getData().getAccount());
                                    startActivity(new Intent(LoginAty.this,ChoiceProfessionAty.class));
                                    finish();
                                    return;
                                }
//                                Preferences.getInstance().set(LoginAty.this,"construction","token",bindBean.getData().getToken());
//                                Preferences.getInstance().set(LoginAty.this,"construction","account",bindBean.getData().getAccount());
//                                startActivity(new Intent(LoginAty.this,ChoiceProfessionAty.class));
//                                finish();
//                            }catch (RuntimeException e){
                                NBindBean nBindBean = GsonUtil.GsonToBean(response, NBindBean.class);
                                Intent intent = new Intent(LoginAty.this, YzmLoginAty.class);
                                intent.putExtra("bind_id",nBindBean.getData().getBind_id());
                                intent.putExtra("type","6");
                                startActivity(intent);
                                finish();
//                            }
                        }else {
                            Toast.makeText(LoginAty.this, objectMap.get("message").toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}