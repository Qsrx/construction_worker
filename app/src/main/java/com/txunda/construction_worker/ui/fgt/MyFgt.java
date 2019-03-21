package com.txunda.construction_worker.ui.fgt;


import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.MyBean;
import com.txunda.construction_worker.ui.aty.HelpAty;
import com.txunda.construction_worker.ui.aty.ImportantNoticeAty;
import com.txunda.construction_worker.ui.aty.InvitingFriendsAty;
import com.txunda.construction_worker.ui.aty.MyClassAty;
import com.txunda.construction_worker.ui.aty.MyCollectsAty;
import com.txunda.construction_worker.ui.aty.MyMessageAty;
import com.txunda.construction_worker.ui.aty.MyOrderAty;
import com.txunda.construction_worker.ui.aty.MyQuizAty;
import com.txunda.construction_worker.ui.aty.OfflineCacheAty;
import com.txunda.construction_worker.ui.aty.PersonalDataAty;
import com.txunda.construction_worker.ui.aty.RedPackageAty;
import com.txunda.construction_worker.ui.aty.ShardAty;
import com.txunda.construction_worker.ui.aty.SystemSettingAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import de.hdodenhof.circleimageview.CircleImageView;

@Layout(R.layout.fgt_my)
public class MyFgt extends BaseFgt implements View.OnClickListener{
    LinearLayout ll_setting,ll_friends,ll_notice,ll_package,ll_offline,ll_quiz,ll_order,ll_help,ll_class,ll_message,ll_collects;
    CircleImageView pic;
    ImageView iv_wx,iv_share;
    private Dialog dialog;
    RelativeLayout dialog_rl_jump;
    ImageView dialog_iv_close;
    TextView tv_name,tv_tel,tv_quiz,tv_collects,tv_message;
    @Override
    public void initViews() {
        super.initViews();
        ll_setting = findViewById(R.id.fgt_my_ll_setting);
        ll_friends = findViewById(R.id.fgt_my_ll_priends);
        ll_notice = findViewById(R.id.fgt_my_important_notice_ll_notice);
        ll_package = findViewById(R.id.fgt_my_red_package_ll);
        ll_order = findViewById(R.id.fgt_my_ll_order);
        ll_help = findViewById(R.id.fgt_my_ll_help);
        ll_message = findViewById(R.id.fgt_my_ll_message);
        ll_collects = findViewById(R.id.fgt_my_ll_collects);
        ll_quiz = findViewById(R.id.fgt_my_ll_quiz);
        ll_offline = findViewById(R.id.fgt_my_ll_offline_cache);
        ll_class = findViewById(R.id.fgt_my_ll_class);
        iv_wx = findViewById(R.id.fgt_my_iv_wx);
        iv_share = findViewById(R.id.fgt_my_iv_share);
        pic = findViewById(R.id.fgt_my_pic);
        tv_name = findViewById(R.id.fgt_my_tv_name);
        tv_tel = findViewById(R.id.fgt_my_tv_phone);
        tv_quiz = findViewById(R.id.fgt_my_tv_quiz);
        tv_collects = findViewById(R.id.fgt_my_tv_collects);
        tv_message = findViewById(R.id.fgt_my_tv_message);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        //TODO:接口调用
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        ll_setting.setOnClickListener(this);
        ll_friends.setOnClickListener(this);
        ll_notice.setOnClickListener(this);
        ll_package.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        ll_help.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_collects.setOnClickListener(this);
        ll_quiz.setOnClickListener(this);
        ll_offline.setOnClickListener(this);
        ll_class.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        pic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_my_ll_priends:
                jump(InvitingFriendsAty.class);
                break;
            case R.id.fgt_my_ll_setting:
                jump(SystemSettingAty.class);
                break;
            case R.id.fgt_my_pic:
                jump(PersonalDataAty.class);
                break;
            case R.id.fgt_my_important_notice_ll_notice:
                jump(ImportantNoticeAty.class);
                break;
            case R.id.fgt_my_red_package_ll:
                jump(RedPackageAty.class);
                break;
            case R.id.fgt_my_ll_order:
                jump(MyOrderAty.class);
                break;
            case R.id.fgt_my_ll_help:
                jump(HelpAty.class);
                break;
            case R.id.fgt_my_ll_message:
                jump(MyMessageAty.class);
                break;
            case R.id.fgt_my_ll_collects:
                jump(MyCollectsAty.class);
                break;
            case R.id.fgt_my_ll_quiz:
                jump(MyQuizAty.class);
                break;
            case R.id.fgt_my_ll_offline_cache:
                jump(OfflineCacheAty.class);
//                jump(CachingAty.class);
                break;
            case R.id.fgt_my_iv_share:
                jump(ShardAty.class);
                break;
            case R.id.fgt_my_iv_wx:
                showDialog();
                break;
            case R.id.fgt_my_ll_class:
                jump(MyClassAty.class);
                break;
                default:break;
        }
    }
    private void showDialog(){
        dialog = new android.app.AlertDialog.Builder(getContext()).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        window.setContentView(R.layout.home_dialog_layout);
        window.setBackgroundDrawableResource(R.color.transparent);
        dialog_rl_jump = window.findViewById(R.id.home_dialog_rl_bg);
        dialog_iv_close = window.findViewById(R.id.home_dialog_layout_iv_close);
        dialog_iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog_rl_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:微信发一年粘性消息
//                SubscribeMessage.Req req = new SubscribeMessage.Req();
//                IWXAPI api = WXAPIFactory.createWXAPI(me, Constants.APP_ID);
//                // 将该app注册到微信
//                api.registerApp(Constants.APP_ID);
//                req.templateID = "Ea8eGs9rLl45fV5cjRwPewSlyM1Nbe54FN3yR8xE8kQ";
//                req.scene = 889;
//                api.sendReq(req);
                //TODO:微信朋友分享图片
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                Resources res = me.getResources();
                Bitmap bmp= BitmapFactory.decodeResource(res, R.drawable.gz_wx_gzh);
                sp.setImageData(bmp);
                sp.setTitle("建工邦");
                sp.setText("");// 分享文本
                // 3、非常重要：获取平台对象
                Platform wechathy = ShareSDK.getPlatform(Wechat.NAME);
                // 执行分享
                wechathy.share(sp);
            }
        });
    }
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/memberCenter", new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                MyBean bean = GsonUtil.GsonToBean(response,MyBean.class);
                                tv_name.setText(bean.getData().getNickname());
                                tv_tel.setText("TEL: "+bean.getData().getAccount());
                                tv_collects.setText(bean.getData().getCollection());
                                tv_message.setText(String.valueOf(bean.getData().getComment()));
                                tv_quiz.setText(bean.getData().getAsk());
                                Glide.with(me).load(bean.getData().getHead_pic()).into(pic);
                            }
                        }
                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){
            //TODO:接口调用
            httpData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        httpData();
    }
}