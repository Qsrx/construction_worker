package com.txunda.construction_worker.ui.aty;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.utils.ShareBeBackListener;
import com.txunda.construction_worker.utils.ShareForApp;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/9/20 10:00
 * 功能描述：分享界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_shard)
@DarkStatusBarTheme(false) //开启顶部状态栏图标、文字暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//透明颜色   设置底部导航栏背景颜色（a = 255,r = 255,g = 255,b = 255 黑色的）
@DarkNavigationBarTheme(true) //开启底部导航栏按钮暗色
public class ShardAty extends BaseAty implements View.OnClickListener, ShareBeBackListener {
    private ImageView image_wx, image_qq, image_wb, image_py, imageview;
    private TextView text_finish;
    private String platFormName;
    private View viewnull;
    private String price;
    private Platform platform;
    private String logurl;
//    private String contenturl = "http://jgb.txunda.com/index.php/home/share/index2?token="+token;
    private String contenturl = "http://jgb.txunda.com/index.php/share/index2?token="+token;
    private String id = null;
    private String homeUrl = "http://jgb.txunda.com/index.php/Share/index3?id="+id;
    private String title = "建工邦";
    private String content = "邀请您加入建工邦";

    @Override
    public void initViews() {
        text_finish = findViewById(R.id.text_finish);
        image_wx = findViewById(R.id.image_wx);
        image_py = findViewById(R.id.image_py);
        viewnull = findViewById(R.id.aty_shard_null);
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initDatas(JumpParameter paramer) {
//        contenturl = paramer.getString("share_content_url");

//        title =  paramer.getString("title");
//        content =  paramer.getString("content");
//        if (contenturl.isEmpty()){
//        logurl = paramer.getString("share_logo");
//        String s = "http://jgb.txunda.com/index.php/home/share/index2?token=1d0734379c027d0af4ea2c02ab9f1e98";
//        contenturl = getIntent().getStringExtra("share_content_url");
//        title = getIntent().getStringExtra("title");
//        content = getIntent().getStringExtra("content");
//        }
    }

    @Override
    public void setEvents() {
        text_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        image_wx.setOnClickListener(this);
        image_py.setOnClickListener(this);
        viewnull.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_wx:
                if (id == null){
                    platFormName = Wechat.NAME;
                    price = contenturl;
                    platform = ShareSDK.getPlatform(platFormName);
                    if (!platform.isClientValid()) {
                        toast("请安装微信");
                        return;
                    }
                    toShare(platFormName);
                }else {
                    platFormName = Wechat.NAME;
                    price = homeUrl+id;
                    platform = ShareSDK.getPlatform(platFormName);
                    if (!platform.isClientValid()) {
                        toast("请安装微信");
                        return;
                    }
                    toShare2(platFormName);
                }

                break;
            case R.id.image_py:
                if (id == null){
                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setText(getString(R.string.app_name));
                    sp.setUrl(contenturl);
                    sp.setTitle("您的好友邀请您一起加入建工邦");
                    Platform wechat = ShareSDK.getPlatform(WechatMoments.NAME);
                    wechat.share(sp);
                }else {
                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setText(getString(R.string.app_name));
                    sp.setUrl(homeUrl+id);
                    sp.setTitle("您的好友邀请您一起加入建工邦");
                    Platform wechat = ShareSDK.getPlatform(WechatMoments.NAME);
                    wechat.share(sp);
                }
                break;
            case R.id.aty_shard_null:
                finish();
                break;
            default:
        }
//        toShare(platFormName);
    }

    private void toShare(String platFormName) {
//        /**
//         * logurl  标题
//         * contenturl分享地址
//         * title 标题
//         **/
//        Resources res = me.getResources();
//        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.logo);
//        ShareForApp shareForApp = new ShareForApp(platFormName, bmp, title, "分享来自导航聚宝盆", contenturl, (ShareBeBackListener) me);
//        /**
//         * tyep 分享类型
//         **/
//        shareForApp.toShareWithPicUrl();
        Resources res = me.getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.icon_logo);
        /**
         * logurl  标题
         * contenturl分享地址
         * title 标题
         **/
        ShareForApp shareForApp = new ShareForApp(platFormName, bmp, title, "分享来自建工邦", contenturl, (ShareBeBackListener) me);
//        ShareForApp shareForApp = new ShareForApp(platFormName, "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg", "导航聚宝盆", "分享来自导航聚宝盆", "https://www.baidu.com", (ShareBeBackListener) me);
        /**
         * tyep 分享类型
         **/
        shareForApp.toShareWithPicUrl(1);
    }
    private void toShare2(String platFormName) {
//        /**
//         * logurl  标题
//         * contenturl分享地址
//         * title 标题
//         **/
//        Resources res = me.getResources();
//        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.logo);
//        ShareForApp shareForApp = new ShareForApp(platFormName, bmp, title, "分享来自导航聚宝盆", contenturl, (ShareBeBackListener) me);
//        /**
//         * tyep 分享类型
//         **/
//        shareForApp.toShareWithPicUrl();
        Resources res = me.getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.icon_logo);
        /**
         * logurl  标题
         * contenturl分享地址
         * title 标题
         **/
        ShareForApp shareForApp = new ShareForApp(platFormName, bmp, title, "分享来自建工邦", homeUrl+id, (ShareBeBackListener) me);
//        ShareForApp shareForApp = new ShareForApp(platFormName, "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg", "导航聚宝盆", "分享来自导航聚宝盆", "https://www.baidu.com", (ShareBeBackListener) me);
        /**
         * tyep 分享类型
         **/
        shareForApp.toShareWithPicUrl(1);
    }
    @Override
    public void beBack(ShareForApp.PlatformForShare platformForShare, ShareForApp.StatusForShare statusForShare, int code) {

    }
    /**
     * URL分享到微信朋友圈
     * @param url 跳转的URL
     * @param title 分享简介的title
     * @param content 分享简介上内容
     */
    public void shareWeChatMomments(String url, String title, String content) {
        HashMap<String,Object> optionMap = new HashMap<>();
        optionMap.put("Id","5");
        optionMap.put("SortId","5");
        optionMap.put("AppId","wxac1846250f4a6c39");
        optionMap.put("AppSecret","7f9b00251ff194acba567fda31db3e65");
        optionMap.put("BypassApproval",false);
        optionMap.put("Enable",true);
        ShareSDK.setPlatformDevInfo(WechatMoments.NAME,optionMap);

        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        Platform.ShareParams shareParams = new Platform.ShareParams();
        Resources res = me.getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.icon_logo);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setTitle(title);
        shareParams.setTitleUrl(url);
        shareParams.setText(content);
        shareParams.setUrl(url);
        shareParams.setImageData(bmp);
    }
}