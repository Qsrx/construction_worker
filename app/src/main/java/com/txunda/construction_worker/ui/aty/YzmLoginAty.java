package com.txunda.construction_worker.ui.aty;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.tools.MyCount;
import com.ants.theantsgo.util.JSONUtils;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.LoginBean;
import com.txunda.construction_worker.bean.RegisterBean;
import com.txunda.construction_worker.bean.VerifyBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑时间：2018.12.25
 * 开发者：hzy
 * 功能模块：验证码登录&密码登录&找回密码&设置新密码&注册&设置密码&绑定手机号&验证身份&修改密码
 * 联系方式：win_hzy@163.com
 */
@Layout(R.layout.aty_yzm_login)
public class YzmLoginAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_yzm_login_et_phone)
    EditText atyYzmLoginEtPhone;
    @BindView(R.id.aty_yzm_login_tv_getcode)
    TextView atyYzmLoginTvGetcode;
    @BindView(R.id.aty_yzm_login_et_pw)
    EditText atyYzmLoginEtPw;
    @BindView(R.id.aty_yzm_login_tv)
    TextView atyYzmLoginTv;
    @BindView(R.id.aty_yzm_login_iv_wx)
    ImageView atyYzmLoginIvWx;
    @BindView(R.id.aty_yzm_login_iv_phone)
    ImageView atyYzmLoginIvPhone;
    @BindView(R.id.aty_yzm_login_line)
    View atyYzmLoginLine;
    @BindView(R.id.aty_yzm_login_iv_pw)
    ImageView atyYzmLoginIvPw;
    @BindView(R.id.aty_yzm_login_tv_forget)
    TextView atyYzmLoginTvForget;
    @BindView(R.id.aty_yzm_login_rl)
    RelativeLayout atyYzmLoginRl;
    @BindView(R.id.aty_yzm_login_rl_bottom)
    RelativeLayout atyYzmLoginRlBottom;
    @BindView(R.id.aty_yzm_login_rb_read)
    RadioButton atyYzmLoginRbRead;
    @BindView(R.id.aty_yzm_login_tv_rule)
    TextView atyYzmLoginTvRule;
    @BindView(R.id.aty_yzm_login_ll_read)
    LinearLayout atyYzmLoginLlRead;
    @BindView(R.id.aty_yzm_login_iv_pw_old)
    ImageView atyYzmLoginIvPwOld;
    @BindView(R.id.aty_yzm_login_et_pw_old)
    EditText atyYzmLoginEtPwOld;
    @BindView(R.id.aty_yzm_login_ll_old)
    LinearLayout atyYzmLoginLlOld;
    @BindView(R.id.aty_yzm_login_v_line)
    View atyYzmLoginVLine;
    @BindView(R.id.aty_yzm_login_iv_yqm)
    ImageView atyYzmLoginIvYqm;
    @BindView(R.id.aty_yzm_login_et_yqm)
    EditText atyYzmLoginEtYqm;
    @BindView(R.id.aty_yzm_login_ll_yqm)
    LinearLayout atyYzmLoginLlYqm;
    @BindView(R.id.aty_yzm_login_v_line2)
    View atyYzmLoginVLine2;
    //type为1是验证码  2 密码登录  3注册
    private String type;
    private String bind_id;
    //验证码
    private String VerifyStr = "";
    //手机号
    private String Account = "";
    //判断是否点击发送验证码 true点击过 false未点击过
    private boolean flag = false;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        type = getIntent().getStringExtra("type");
        if (type == null){
            type = "1";
        }
        if (type.equals("1")) {
            headerTvTitle.setText("验证码登录");
            atyYzmLoginEtPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            atyYzmLoginTvForget.setVisibility(View.GONE);
        } else if (type.equals("2")) {
            headerTvTitle.setText("密码登录");
            atyYzmLoginLine.setVisibility(View.GONE);
            atyYzmLoginTvGetcode.setVisibility(View.GONE);
            atyYzmLoginEtPw.setHint("请输入密码");
            atyYzmLoginIvPhone.setImageResource(R.mipmap.icon_login_account);
            atyYzmLoginIvPw.setImageResource(R.mipmap.icon_login_pw);
        } else if (type.equals("3")) {
            headerTvTitle.setText("注册");
            atyYzmLoginEtPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            atyYzmLoginLlRead.setVisibility(View.VISIBLE);
            atyYzmLoginRl.setVisibility(View.GONE);
            atyYzmLoginIvWx.setVisibility(View.GONE);
            atyYzmLoginTvForget.setVisibility(View.GONE);
            atyYzmLoginTv.setText("下一步");
        } else if (type.equals("4")) {
            headerTvTitle.setText("修改密码");
            atyYzmLoginRl.setVisibility(View.GONE);
            atyYzmLoginIvWx.setVisibility(View.GONE);
            atyYzmLoginTvForget.setVisibility(View.GONE);
            atyYzmLoginTv.setText("确认修改");
            atyYzmLoginLine.setVisibility(View.GONE);
            atyYzmLoginTvGetcode.setVisibility(View.GONE);
            atyYzmLoginEtPw.setHint("请再次输入新密码");
            atyYzmLoginEtPhone.setHint("请设置新密码");
            atyYzmLoginVLine.setVisibility(View.VISIBLE);
            atyYzmLoginLlOld.setVisibility(View.VISIBLE);
            atyYzmLoginIvPhone.setImageResource(R.mipmap.icon_login_pw);
            atyYzmLoginIvPw.setImageResource(R.mipmap.icon_login_pw);
            atyYzmLoginEtPhone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else if (type.equals("5")) {
            headerTvTitle.setText("验证身份");
            atyYzmLoginRl.setVisibility(View.GONE);
            atyYzmLoginIvWx.setVisibility(View.GONE);
            atyYzmLoginTvForget.setVisibility(View.GONE);
            atyYzmLoginTv.setText("验证");
        }else if (type.equals("6")){
            atyYzmLoginEtPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            bind_id = getIntent().getStringExtra("bind_id");
            atyYzmLoginTv.setText("立即绑定");
            headerTvTitle.setText("绑定手机号");
            atyYzmLoginRl.setVisibility(View.GONE);
            atyYzmLoginIvWx.setVisibility(View.GONE);
            atyYzmLoginTvForget.setVisibility(View.GONE);
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
    }


    @OnClick({R.id.aty_yzm_login_rb_read, R.id.aty_yzm_login_tv_rule, R.id.aty_yzm_login_tv_forget, R.id.header_iv_back, R.id.aty_yzm_login_tv_getcode, R.id.aty_yzm_login_tv, R.id.aty_yzm_login_iv_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_yzm_login_rb_read:
                break;
            case R.id.aty_yzm_login_tv_rule:
                jump(RuleDetailsAty.class);
                break;
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_yzm_login_tv_getcode:
                if (isChinaPhoneLegal(atyYzmLoginEtPhone.getText().toString())) {
                    if (type.equals("3")){
                        //TODO:接口调用
                        sendVerify("register");
                    }else if (type.equals("2")){
                        sendVerify("retrieve");
                    }else if (type.equals("1")){
                        sendVerify("login");
                    }else if (type.equals("6")){
                        sendVerify("re_bind");
                    }
                    MyCount myCount = new MyCount(60000, 1000, atyYzmLoginTvGetcode);
                    myCount.start();
                    flag = true;
//                    new CountDownUtil(atyYzmLoginTvGetcode)
//                            .setCountDownMillis(60_000L)//倒计时60000ms
//                            .setCountDownColor(R.color.main_color, android.R.color.darker_gray)//不同状态字体颜色
//                            .setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    //TODO:点击事件
//                                }
//                            })
//                            .start();
                } else {
                    toast("请输入中国大陆手机号");
                }
                break;
            case R.id.aty_yzm_login_tv:
                if (atyYzmLoginTv.getText().toString().equals("下一步")) {
                    if (type.equals("2")) {
//                        headerTvTitle.setText("设置新密码");
//                        atyYzmLoginEtPhone.setHint("请设置新密码");
//                        viewNotice();
                        checkVerify("retrieve");
                    } else if (type.equals("3")) {
                        //TODO:接口调用
                        checkVerify("register");
//                        jump(ChoiceProfessionAty.class);
//                        finish();
                    }
                } else if (atyYzmLoginTv.getText().toString().equals("完成")) {
                    if (type.equals("3")){
                        //TODO:接口调用
                        userRegister();
                    }else if (type.equals("2")){
                        //TODO:接口调用
                        resetPw();
                    }
//                    jump(ChoiceProfessionAty.class);
//                    finish();
                } else if (atyYzmLoginTv.getText().toString().equals("验证")) {
                    atyYzmLoginTv.setText("立即绑定");
                    headerTvTitle.setText("绑定手机号");
                } else if (atyYzmLoginTv.getText().toString().equals("立即绑定")) {
//                    finish();
                    httpWxLogin();
                } else if (atyYzmLoginTv.getText().toString().equals("登录")) {
                    if (type.equals("2")){
                        //TODO:接口调用
                        userLogin();
                    }else if (type.equals("1")){
                        //TODO:接口调用
                        verifyLogin();
                    }else {
                        jump(ChoiceProfessionAty.class);
                        finish();
                    }
                    atyYzmLoginTv.setEnabled(false);
//                    jump(ChoiceProfessionAty.class);
//                    finish();
                }else if (atyYzmLoginTv.getText().toString().equals("确认修改")){
                    //TODO:接口调用
                    modifyPw();
                }
                break;
            case R.id.aty_yzm_login_iv_wx:
                break;
            case R.id.aty_yzm_login_tv_forget:
                atyYzmLoginEtPw.setText("");
                headerTvTitle.setText("找回密码");
                atyYzmLoginIvPhone.setImageResource(R.mipmap.icon_login_phone);
                atyYzmLoginIvPw.setImageResource(R.mipmap.icon_yzm);
                atyYzmLoginLine.setVisibility(View.VISIBLE);
                atyYzmLoginTvGetcode.setVisibility(View.VISIBLE);
                atyYzmLoginTv.setText("下一步");
                atyYzmLoginRl.setVisibility(View.GONE);
                atyYzmLoginIvWx.setVisibility(View.GONE);
                atyYzmLoginTvForget.setVisibility(View.GONE);
                atyYzmLoginEtPw.setHint("请输入验证码");
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否为大陆手机号
     *
     * @return
     * @throws PatternSyntaxException
     * @paramtr
     */
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
//TODO:-----------------------------------------接口请求-------------------------------------------------
    /**
     * 注册
     */
    private void userRegister(){
        HttpRequest.POST(this, AllStatus.BASE_URL + AllStatus.URL_USER + "register",
                new Parameter()
                        .add("account", Account)
                        .add("verify", VerifyStr)
                        .add("password", atyYzmLoginEtPhone.getText().toString())
                        .add("repassword", atyYzmLoginEtPw.getText().toString())
                        .add("share_code", atyYzmLoginEtYqm.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")){
                                    RegisterBean registerBean = GsonUtil.GsonToBean(response,RegisterBean.class);
                                    Preferences.getInstance().set(YzmLoginAty.this,"construction","token",registerBean.getData().getToken());
                                    Preferences.getInstance().set(YzmLoginAty.this,"construction","account",registerBean.getData().getAccount());

                                    if (!isNull(registerBean.getData().getIm_account())||!isNull(registerBean.getData().getIm_password())){
                                        ChatClient.getInstance().login(registerBean.getData().getIm_account(), registerBean.getData().getIm_password(), new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError(int i, String s) {
                                                toast("环信登录Error:"+s);
                                            }

                                            @Override
                                            public void onProgress(int i, String s) {
                                                toast("环信登录Progress:"+s);
                                            }
                                        });
                                    }else {
                                        showErrorTip("未获取到环信账号");
                                    }
                                    toast(registerBean.getMessage());
                                    jump(ChoiceProfessionAty.class);
                                    finish();
                                }
                                toast(objectMap.get("message"));
                            }catch (NullPointerException e){
                                toast("注册失败");
                            }
                        }
                    }
                }
        );
    }

    /**
     * 发送验证码
     * send_type： login(登录/验证码登录)，register（注册）,retrieve（找回密码/修改密码） mod_bind（修改绑定账号），re_bind（绑定新账号，三方登录绑定手机号）
     */
    private void sendVerify(String type){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Sms/sendVerify", new Parameter()
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("send_type", type), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            toast(objectMap.get("message"));
                        }
                    }
                }
        );
    }

    /**
     * 验证码验证
     * (send_type): login(登录/快捷登录)， register（注册）, retrieve（找回密码、修改密码） mod_bind[（修改绑定账号）， re_bind（绑定新账号，三方登录绑定手机号）
     */
    private void checkVerify(final String type){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Sms/checkVerify", new Parameter()
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("verify", atyYzmLoginEtPw.getText().toString())
                        .add("send_type", type), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                if (type.equals("register")){
                                    Account = atyYzmLoginEtPhone.getText().toString();
                                    VerifyStr = atyYzmLoginEtPw.getText().toString();
                                    headerTvTitle.setText("设置密码");
                                    atyYzmLoginLlRead.setVisibility(View.GONE);
                                    atyYzmLoginEtPhone.setHint("请设置密码");
                                    atyYzmLoginLlYqm.setVisibility(View.VISIBLE);
                                    atyYzmLoginVLine2.setVisibility(View.VISIBLE);
                                    viewNotice();
                                }else if (type.equals("retrieve")){
                                    Account = atyYzmLoginEtPhone.getText().toString();
                                    VerifyStr = atyYzmLoginEtPw.getText().toString();
                                    headerTvTitle.setText("设置新密码");
                                    atyYzmLoginEtPhone.setHint("请设置新密码");
                                    viewNotice();
                                }
                                toast(objectMap.get("message"));
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }

    /**
     * 账号登录
     */
    private void userLogin(){
        HttpRequest.POST(this, AllStatus.BASE_URL +  AllStatus.URL_USER + "login", new Parameter()
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("password", atyYzmLoginEtPw.getText().toString()), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        toast(response);
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                try {
                                    LoginBean loginBean = GsonUtil.GsonToBean(response,LoginBean.class);
                                    Preferences.getInstance().set(YzmLoginAty.this,"construction","token",loginBean.getData().getToken());
                                    Preferences.getInstance().set(YzmLoginAty.this,"construction","account",loginBean.getData().getAccount());
                                    toast(loginBean.getMessage());
                                    Log.d("yzmimdata", "onResponse: =================="+loginBean.getData().getIm_account());
                                    Log.d("yzmimdata", "onResponse: =================="+loginBean.getData().getIm_password());
                                    if (!isNull(loginBean.getData().getIm_account())||!isNull(loginBean.getData().getIm_password())){
                                        ChatClient.getInstance().login(loginBean.getData().getIm_account(), loginBean.getData().getIm_password(), new Callback() {
                                            @Override
                                            public void onSuccess() {
                                            }

                                            @Override
                                            public void onError(int i, String s) {
                                                toast("环信登录Error:"+s);
                                            }

                                            @Override
                                            public void onProgress(int i, String s) {
                                                toast("环信登录Progress:"+s);
                                            }
                                        });
                                    }else {
                                        showErrorTip("未获取到环信账号");
                                    }

                                }catch (NullPointerException e){
                                    showErrorTip(e.getMessage());
                                    atyYzmLoginTv.setEnabled(true);
                                }
                                jump(ChoiceProfessionAty.class);
                                atyYzmLoginTv.setEnabled(true);
                                finish();
                            }else {
                                toast(objectMap.get("message"));
                                atyYzmLoginTv.setEnabled(true);
                            }
                        }
                    }
                }
        );
    }

    /**
     * 修改密码
     */
    private void modifyPw(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/modPassword", new Parameter()
                        .add("token", token)
                        .add("old_password", atyYzmLoginEtPwOld.getText().toString())
                        .add("password", atyYzmLoginEtPhone.getText().toString())
                        .add("repassword", atyYzmLoginEtPw.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                toast(objectMap.get("message"));
                                finish();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
    /**
     * 换绑手机号
     */
    private void exchangePhone(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/bindAccount", new Parameter()
                        .add("token", token)
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("verify", atyYzmLoginEtPw.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                toast(objectMap.get("message"));
                                finish();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    /**
     * 设置密码
     */
    private void setPw(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Member/setPassword", new Parameter()
                .add("password", atyYzmLoginEtPw.getText().toString()), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> stringObjectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (stringObjectMap.get("code").equals("1")) {

                        return;
                    }
                    showErrorTip(stringObjectMap.get("message").toString());
                    return;
                }
                showErrorTip(error.getMessage());
            }
        });
    }

    /**
     * 找回密码
     */
    private void resetPw(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/resetPassword", new Parameter()
                        .add("account", Account)
                        .add("verify", VerifyStr)
                        .add("password", atyYzmLoginEtPhone.getText().toString())
                        .add("repassword", atyYzmLoginEtPw.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                jump(LoginAty.class);
                                finish();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    /**
     * 验证码登录
     */
    private void verifyLogin(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Member/verifyLogin", new Parameter()
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("verify", atyYzmLoginEtPw.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                VerifyBean verifyBean = GsonUtil.GsonToBean(response, VerifyBean.class);
                                Preferences.getInstance().set(me,"construction","token",verifyBean.getData().getToken());
                                Preferences.getInstance().set(me,"construction","account",verifyBean.getData().getAccount());
                                jump(ChoiceProfessionAty.class);
                                atyYzmLoginTv.setEnabled(true);
                                finish();
                                return;
                            }
                            showErrorTip(objectMap.get("message").toString());
                        }
                    }
                });
    }
    /**
     * 微信绑定账号请求
     */
    private void httpWxLogin() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Member/threeLoginBind", new Parameter()
                        .add("bind_id", bind_id)
                        .add("account", atyYzmLoginEtPhone.getText().toString())
                        .add("verify", atyYzmLoginEtPw.getText().toString()), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("yzmloginbean", "onResponse: =========="+response);
                        Map<String, String> objectMap = JSONUtils.parseKeyAndValueToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(objectMap.get("data"));
                            Preferences.getInstance().set(YzmLoginAty.this,"construction","token",map.get("token"));
                            Preferences.getInstance().set(YzmLoginAty.this,"construction","account",map.get("account"));
                            if (!isNull(map.get("im_account"))||!isNull(map.get("im_password"))){
                                ChatClient.getInstance().login(map.get("im_account"),map.get("im_password"), new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError(int i, String s) {
                                        toast("环信登录Error:"+s);
                                    }

                                    @Override
                                    public void onProgress(int i, String s) {
                                        toast("环信登录Progress:"+s);
                                    }
                                });
                            }else {
                                showErrorTip("未获取到环信账号");
                            }
                            jump(ChoiceProfessionAty.class);
                            atyYzmLoginTv.setEnabled(true);
                            finish();
                            return;
                        }
                        toast(objectMap.get("message"));
                    }
                }
        );
    }
    private void viewNotice(){
        atyYzmLoginEtPhone.setText("");
        atyYzmLoginEtPw.setText("");
        atyYzmLoginTv.setText("完成");
        atyYzmLoginRl.setVisibility(View.GONE);
        atyYzmLoginIvWx.setVisibility(View.GONE);
        atyYzmLoginTvForget.setVisibility(View.GONE);
        atyYzmLoginLine.setVisibility(View.GONE);
        atyYzmLoginTvGetcode.setVisibility(View.GONE);
        atyYzmLoginEtPw.setHint("请再次输入设置密码");
        atyYzmLoginIvPhone.setImageResource(R.mipmap.icon_login_pw);
        atyYzmLoginIvPw.setImageResource(R.mipmap.icon_login_pw);
        atyYzmLoginEtPhone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        atyYzmLoginEtPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}