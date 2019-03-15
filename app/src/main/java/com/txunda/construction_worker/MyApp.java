package com.txunda.construction_worker;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.kongzue.dialog.v2.DialogSettings;
import com.liulishuo.filedownloader.FileDownloader;
import com.mob.MobSDK;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_IOS;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/26 026 8:58:11.
 * 功能描述 : Application
 * 联系方式： win_hzy@163.com
 */
public class MyApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //Mob初始化
        MobSDK.init(this);
        RxRetrofitApp.init(this);
        DialogSettings.use_blur = true;                 //设置是否启用模糊
        //初始化FileDownLoader
        FileDownloader.setup(this);
        DialogSettings.type = TYPE_IOS;
        DialogSettings.tip_theme = THEME_DARK;
        DialogSettings.dialog_theme = THEME_LIGHT;
//        DialogSettings.tip_theme = THEME_LIGHT;


        DialogSettings.dialog_title_text_size = -1;     //设置对话框标题文字大小，<=0不启用
        DialogSettings.dialog_message_text_size = -1;   //设置对话框内容文字大小，<=0不启用
        DialogSettings.dialog_button_text_size = -1;    //设置对话框按钮文字大小，<=0不启用
        DialogSettings.tip_text_size = -1;              //设置提示框文字大小，<=0不启用
        DialogSettings.dialog_menu_text_size = -1;    //设置菜单默认字号，<=0不启
        DialogSettings.ios_normal_button_color = -1;
        initIM();
    }
    private void initIM(){
        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("1499190221061212#kefuchannelapp66263");//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId("66263");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”
        try {
            // Kefu SDK 初始化
            if (!ChatClient.getInstance().init(this, options)){
                return;
            }
            // Kefu EaseUI的初始化
            UIProvider.getInstance().init(this);
        }catch (UnsatisfiedLinkError e){
            Log.d("环信", "initIM: =========="+e.getMessage());
        }

    }
}