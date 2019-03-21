package com.txunda.construction_worker.ui.fgt;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.HomeBean;
import com.txunda.construction_worker.ui.aty.ChoiceProfessionAty;
import com.txunda.construction_worker.ui.aty.LoginAty;
import com.txunda.construction_worker.ui.aty.ShardAty;
import com.txunda.construction_worker.ui.aty.WebDetailsAty;
import com.txunda.construction_worker.ui.aty.WritingProblemsAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.GlideImageLoader;
import com.txunda.construction_worker.utils.ShareBeBackListener;
import com.txunda.construction_worker.utils.ShareForApp;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

@Layout(R.layout.fgt_home)
public class HomeFgt extends BaseFgt implements View.OnClickListener,ShareBeBackListener{
    ImageView iv_mune,iv_share,iv_wx;
    TextView tv_title,tv_exam;
    RadioGroup rg;
    RadioButton rb_xz,rb_dt,rb_point,rb_lt;
    Banner home_banner;
    FrameLayout fl;
    NestedScrollView scrollView;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    RadioButton[] rb;
    RadioGroup aty_rg;
    RadioButton[] aty_rb;
    LinearLayout ll_ask;
    SmartRefreshLayout smartRefreshLayout;
    private int index = 0;
    private Dialog dialog;
    public static String Industry_ID;
    private List<Integer> path_list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    ImageView dialog_iv_close;
    RelativeLayout dialog_rl_jump;
    Fragment xz_rv;
    private int page = 1;
    private HomeBean homeBean;
    @Override
    public void initViews() {
        super.initViews();
        home_banner = findViewById(R.id.fgt_home_banner);
        rb_xz = findViewById(R.id.fgt_home_rb_xz);
        rb_dt = findViewById(R.id.fgt_home_rb_dt);
        iv_wx = findViewById(R.id.fgt_home_iv_wx);
        rb_point = findViewById(R.id.fgt_home_rb_point);
        rb_lt = findViewById(R.id.fgt_home_rb_lt);
        rg = findViewById(R.id.aty_fgt_home_rg);
        tv_title = findViewById(R.id.fgt_home_tv_title);
        tv_exam = findViewById(R.id.fgt_home_tv_exam);
        iv_mune = findViewById(R.id.fgt_home_list);
        fl = findViewById(R.id.fgt_home_fl);
        ll_ask = findViewById(R.id.fgt_home_ll_ask);
        iv_share = findViewById(R.id.fgt_home_tv_share);
        scrollView = findViewById(R.id.fgt_home_scrollview);
        smartRefreshLayout = findViewById(R.id.fgt_home_smartrefresh);
        aty_rg = me.findViewById(R.id.aty_main_rg);
        aty_rb = new RadioButton[aty_rg.getChildCount()];
        for (int i = 0; i < aty_rb.length; i++) {
            aty_rb[i] = (RadioButton) aty_rg.getChildAt(i);
        }
        Industry_ID = Preferences.getInstance().getString(me, "construction", "industry_id");
//        setRbSize(rb_dt);
//        setRbSize(rb_lt);
//        setRbSize(rb_point);
//        setRbSize(rb_xz);
        fragments.add(new XzFgt("1"));
        fragments.add(new XzFgt("2"));
        fragments.add(new XzFgt("3"));
        fragments.add(new LtFgt());
        path_list.add(R.mipmap.img_test1);
        path_list.add(R.mipmap.img_test2);
        path_list.add(R.mipmap.img_test3);
//        home_banner.setImages(path_list);
        home_banner.setImageLoader(new GlideImageLoader());
        home_banner.setDelayTime(2000);
        home_banner.start();
        rb = new RadioButton[rg.getChildCount()];
        for (int i = 0; i < rb.length; i++) {
            rb[i] = (RadioButton) rg.getChildAt(i);
        }
        rb[0].setChecked(true);
        fragmentManager = getFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.add(R.id.fgt_home_fl,fragments.get(0));
        ft.commit();
        scrollView.smoothScrollTo(0, 0);
//        EventBus.getDefault().register(this);
    }

    /**
     * 设置radiobutton按钮drawble大小
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[1].setBounds(0,0,150,150);
        rb.setCompoundDrawables(null,compoundDrawables[1],null,null);
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
        iv_share.setOnClickListener(this);
        iv_mune.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        ll_ask.setOnClickListener(this);
//        //刷新
//        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//            }
//        });
//        //加载更多
//        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                //发送事件
//                EventBus.getDefault().postSticky(String.valueOf(page++));
//            }
//        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                for (int j = 0; j < rb.length; j++) {
                    if (rb[j].getId() == i) {
                        if (fragments.get(j).isAdded()) {
                            ft.show(fragments.get(j)).hide(fragments.get(index)).commit();
                        } else {
                            ft.add(R.id.fgt_home_fl, fragments.get(j)).hide(fragments.get(index)).commit();
                        }
                        if (j == 3){
                            ll_ask.setVisibility(View.VISIBLE);
                        }else {
                            ll_ask.setVisibility(View.GONE);
                        }
                        index = j;
                    }else {
                    }

                }
                scrollView.smoothScrollTo(0, 0);
            }
        });
        home_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(me, WebDetailsAty.class);
                intent.putExtra("url",homeBean.getData().getDetails().getAdvert().get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){
            Industry_ID = Preferences.getInstance().getString(me, "construction", "industry_id");
            //TODO:接口调用
            httpData();
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
        dialog_iv_close = window.findViewById(R.id.home_dialog_layout_iv_close);
        dialog_rl_jump = window.findViewById(R.id.home_dialog_rl_bg);
        dialog_iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog_rl_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SubscribeMessage.Req req = new SubscribeMessage.Req();
//                IWXAPI api = WXAPIFactory.createWXAPI(me, Constants.APP_ID);
//                // 将该app注册到微信
//                api.registerApp(Constants.APP_ID);
//                req.templateID = "Ea8eGs9rLl45fV5cjRwPewSlyM1Nbe54FN3yR8xE8kQ";
//                req.scene = 889;
//                api.sendReq(req);
//                Resources res = me.getResources();
//                Bitmap bmp= BitmapFactory.decodeResource(res, R.drawable.gz_wx_gzh);
//                /**
//                 * logurl  标题
//                 * contenturl分享地址
//                 * title 标题
//                 **/
//                ShareForApp shareForApp = new ShareForApp(Wechat.NAME, bmp, "建工邦", "邀请您关注建工邦公众号", "https://www.baidu.com/", null);
//                /**
//                 * tyep 分享类型
//                 **/
//                shareForApp.toShareWithPicUrl(3);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_home_tv_share:
                jump(ShardAty.class);
                break;
            case R.id.fgt_home_iv_wx:
                showDialog();
                break;
            case R.id.fgt_home_list:
                jump(ChoiceProfessionAty.class);
                break;
            case R.id.fgt_home_ll_ask:
                jump(WritingProblemsAty.class);
                break;
                default:break;
        }
    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/index", new Parameter()
                        .add("token", MainAty.token)
                        .add("industry_id", Industry_ID)
                        .add("type", "1")
                        .add("page", "1")
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                Log.d("hzyyyyy", "onResponse: ============="+response);
                                homeBean = GsonUtil.GsonToBean(response,HomeBean.class);
                                tv_title.setText(homeBean.getData().getDetails().getMajor());
                                aty_rb[0].setText(homeBean.getData().getDetails().getMajor());
//                                tv_exam.setText(matcherSearchText(homeBean.getData().getDetails().getExam(),getNumber(homeBean.getData().getDetails().getExam())), TextView.BufferType.SPANNABLE);
                                if (!homeBean.getData().getDetails().getDays().equals("0")){
                                    String exam = homeBean.getData().getDetails().getExam()+homeBean.getData().getDetails().getDays()+"天";
                                    tv_exam.setText(matcherSearchText(exam,getNumber(String.valueOf(homeBean.getData().getDetails().getDays()))), TextView.BufferType.SPANNABLE);
                                }else {
                                    tv_exam.setText(homeBean.getData().getDetails().getExam());
                                }

                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < homeBean.getData().getDetails().getAdvert().size(); i++) {
                                    list.add(homeBean.getData().getDetails().getAdvert().get(i).getFile_id());
                                }
                                home_banner.setImages(list);
                                home_banner.start();
                            }else {
                                toast(objectMap.get("message"));
                                //清除token
                                Preferences.getInstance().cleanAll(me, "construction");
                                jump(LoginAty.class);
                                //退出所有Activity
                                AppManager.getInstance().killAllActivity();
                            }
                        }
                    }
                });
    }

    /**
     * 将字符串里的关键字改变style
     * @param text
     * @param keyword
     * @return
     */
    public SpannableString matcherSearchText(String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new TextAppearanceSpan(me,R.style.home_big_tv_style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    /**
     * 获取string中的int
     * @param str
     * @return
     */
    public String getNumber(String str){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        m.find();
        try {
            return m.group();
        }catch (IllegalStateException e){
            return "0";
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().unregister(this);
    }
    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void userStop(boolean b){
        toast(b);
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();
    }

    @Override
    public void beBack(ShareForApp.PlatformForShare platformForShare, ShareForApp.StatusForShare statusForShare, int code) {

    }


}