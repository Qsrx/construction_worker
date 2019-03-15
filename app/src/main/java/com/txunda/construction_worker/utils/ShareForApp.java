package com.txunda.construction_worker.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.zhy.http.okhttp.utils.L;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:49
 * 描述：分享
 * ===============Txunda===============
 */
public class ShareForApp implements PlatformActionListener {

//    /**
//     * 分享平台
//     */
//    public enum PlatformForShare {
//        QQ, Qzon, Sine, Wechat, WechatMoments, FACEBOOK, WHATSAPP, INSRAGRAM, SinaWeibo
//    }
//
//    /**
//     * 分享之后的状态
//     */
//    public enum StatusForShare {
//        Success, Error, Cancel
//    }
//
//    /**
//     * 分享平台的名称
//     */
//    private String platFormName;
//    /**
//     * 分享的图片
//     */
//    private Bitmap bitmap;
//    /**
//     * 图片链接
//     */
//    private String picUrl;
//
//    /**
//     * 标题
//     */
//    private String title;
//    /**
//     * 分享文本
//     */
//    private String text;
//    /**
//     * 标题链接
//     */
//    private String titleUrl;
//
//    private ShareBeBackListener shareBeBackListener;
//
//    /**
//     * 够造函数
//     *
//     * @param platFormName 平台名称
//     * @param bitmap       分享的图片
//     * @param title        标题
//     * @param text         文本
//     * @param titleUrl     标题连接
//     */
//    public ShareForApp(String platFormName, Bitmap bitmap, String title, String text, String titleUrl,
//                       ShareBeBackListener shareBeBackListener) {
//        this.platFormName = platFormName;
//        this.bitmap = bitmap;
//        this.title = title;
//        this.text = text;
//        this.titleUrl = titleUrl;
//        this.shareBeBackListener = shareBeBackListener;
//    }
//
//    public ShareForApp(String platFormName, String picUrl, String title, String text, String titleUrl,
//                       ShareBeBackListener shareBeBackListener) {
//        this.platFormName = platFormName;
//        this.picUrl = picUrl;
//        this.title = title;
//        this.text = text;
//        this.titleUrl = titleUrl;
//        this.shareBeBackListener = shareBeBackListener;
//    }
//
//
//    public void toShare() {
//        Platform.ShareParams sp = new Platform.ShareParams();
//        sp.setTitle(title);
//        sp.setText(text);// 分享文本
//
//        if (platFormName.equals(Wechat.NAME)) {
//            sp.setUrl(titleUrl);
//        }
////        else if (platFormName.equals(SinaWeibo.NAME) || platFormName.equals(QQ.NAME)) {
////            sp.setTitleUrl(titleUrl);
////        }
//        sp.setImageData(bitmap);
//        // 3、非常重要：获取平台对象
//        Platform wechathy = ShareSDK.getPlatform(platFormName);
//        wechathy.setPlatformActionListener(this); // 设置分享事件回调
//        // 执行分享
//        wechathy.share(sp);
//        Log.d("zdl", "toShare: 分享");
//    }
//
//    //    SHARE_WEBPAGE  分享网页
////    SHARE_TEXT    分享文本
////    SHARE_IMAGE  分享图片
//    public void toShareWithPicUrl() {
//        Platform.ShareParams sp = new Platform.ShareParams();
//        if (platFormName.equals(Wechat.NAME)) {// 微信
////            if (type == 1) {
////                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
////                sp.setImageUrl(picUrl);
////                sp.setUrl(titleUrl);
////            }else if (type == 3){
////
////            } else if (type == 4){
////                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////                sp.setImagePath(picUrl);
////            }
////            sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////            sp.setImageData(bitmap);
//            sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
//            sp.setImageUrl(picUrl);
////            sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////            sp.setImageData(bitmap);
//            sp.setTitleUrl(titleUrl);
//        }
//        else if (platFormName.equals(QQ.NAME)) {// QQ
////            if (type == 1) {
//                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
//                sp.setImageUrl(picUrl);
////            } else if (type == 3){
////
////                sp.setImageUrl(picUrl);
////            } else if (type == 4){
////                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////                sp.setImagePath(picUrl);
////            }
////            sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////            sp.setImageData(bitmap);
//            sp.setTitleUrl(titleUrl);
//        } else if (platFormName.equals(WechatMoments.NAME)) {//朋友圈
//            sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
//            sp.setImageUrl(picUrl);
////            sp.setShareType(Platform.SHARE_IMAGE);//分享图片
////            sp.setImageData(bitmap);
//            sp.setTitleUrl(titleUrl);
//        }
//        sp.setTitle(title);
//        sp.setText(text);// 分享文本
//        // 3、非常重要：获取平台对象
//        Platform wechathy = ShareSDK.getPlatform(platFormName);
//        wechathy.setPlatformActionListener(this); // 设置分享事件回调
//        // 执行分享
//        wechathy.share(sp);
//        L.e("分享");
//    }
//
//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        Log.e("=====成功=====", hashMap.toString());
////        if (platform.getName().equals(SinaWeibo.NAME)) {// 微博
////            shareBeBackListener.beBack(SinaWeibo, StatusForShare.Success, i);
////        } else
//            if (platform.getName().equals(Wechat.NAME)) {// 微信
//            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Success, i);
//        } else if (platform.getName().equals(QQ.NAME)) {// QQ
//            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Success, i);
//        } else if (platform.getName().equals(WechatMoments.NAME)) {
//            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Success, i);
//        }
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//        Log.e("=====失败====", String.valueOf(i) + "=====" + throwable.getMessage());
////        if (platform.getName().equals(SinaWeibo.NAME)) {// 微博
////            shareBeBackListener.beBack(SinaWeibo, StatusForShare.Error, i);
////        } else
//            if (platform.getName().equals(Wechat.NAME)) {// 微信
//            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Error, i);
//        } else if (platform.getName().equals(QQ.NAME)) {// QQ
//            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Error, i);
//        } else if (platform.getName().equals(WechatMoments.NAME)) {
//            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Error, i);
//        }
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {
//        Log.e("=====取消====", String.valueOf(i));
////        if (platform.getName().equals(SinaWeibo.NAME)) {// 微博
////            shareBeBackListener.beBack(SinaWeibo, StatusForShare.Cancel, i);
////        } else
//            if (platform.getName().equals(Wechat.NAME)) {// 微信
//            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Cancel, i);
//        } else if (platform.getName().equals(QQ.NAME)) {// QQ
//            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Cancel, i);
//        } else if (platform.getName().equals(WechatMoments.NAME)) {//微信朋友圈
//            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Cancel, i);
//        }
//    }
    /**
     * 分享平台
     */
    public enum PlatformForShare {
        QQ, Qzon, Sine, Wechat, WechatMoments, FACEBOOK, WHATSAPP, INSRAGRAM, SinaWeibo
    }

    /**
     * 分享之后的状态
     */
    public enum StatusForShare {
        Success, Error, Cancel
    }

    /**
     * 分享平台的名称
     */
    private String platFormName;
    /**
     * 分享的图片
     */
    private Bitmap bitmap;
    /**
     * 图片链接
     */
    private String picUrl;

    /**
     * 标题
     */
    private String title;
    /**
     * 分享文本
     */
    private String text;
    /**
     * 标题链接
     */
    private String titleUrl;

    private ShareBeBackListener shareBeBackListener;

    /**
     * 够造函数
     *
     * @param platFormName 平台名称
     * @param bitmap       分享的图片
     * @param title        标题
     * @param text         文本
     * @param titleUrl     标题连接
     */
    public ShareForApp(String platFormName, Bitmap bitmap, String title, String text, String titleUrl,
                       ShareBeBackListener shareBeBackListener) {
        this.platFormName = platFormName;
        this.bitmap = bitmap;
        this.title = title;
        this.text = text;
        this.titleUrl = titleUrl;
        this.shareBeBackListener = shareBeBackListener;
    }

    public ShareForApp(String platFormName, String picUrl, String title, String text, String titleUrl,
                       ShareBeBackListener shareBeBackListener) {
        this.platFormName = platFormName;
        this.picUrl = picUrl;
        this.title = title;
        this.text = text;
        this.titleUrl = titleUrl;
        this.shareBeBackListener = shareBeBackListener;
    }


    public void toShare() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setText(text);// 分享文本

        if (platFormName.equals(Wechat.NAME)) {
            sp.setUrl(titleUrl);
        } else if (platFormName.equals(QQ.NAME)) {
            sp.setTitleUrl(titleUrl);
        }
        sp.setImageData(bitmap);
        // 3、非常重要：获取平台对象
        Platform wechathy = ShareSDK.getPlatform(platFormName);
        wechathy.setPlatformActionListener(this); // 设置分享事件回调
        // 执行分享
        wechathy.share(sp);
        Log.d("zdl", "toShare: 分享");
    }

    //    SHARE_WEBPAGE  分享网页
//    SHARE_TEXT    分享文本
//    SHARE_IMAGE  分享图片
    public void toShareWithPicUrl(int type) {
        Platform.ShareParams sp = new Platform.ShareParams();
        if (platFormName.equals(Wechat.NAME)) {// 微信
            if (type == 1) {
                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
                sp.setImageUrl(picUrl);
                sp.setUrl(titleUrl);
            }else if (type == 3){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImageUrl(picUrl);
            } else if (type == 4){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImagePath(picUrl);
            }
        } else if (platFormName.equals(QQ.NAME)) {// QQ
            if (type == 1) {
                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
                sp.setImageUrl(picUrl);
            } else if (type == 3){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImageUrl(picUrl);
            } else if (type == 4){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImagePath(picUrl);
            }
            sp.setTitleUrl(titleUrl);
        } else if (platFormName.equals(WechatMoments.NAME)) {//朋友圈
            if (type == 1) {
                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
                sp.setImageUrl(picUrl);
            } else if (type == 3){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImageUrl(picUrl);
            } else if (type == 4){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImagePath(picUrl);
            }
            sp.setTitleUrl(titleUrl);
        } else {
            if (type == 1) {
                sp.setShareType(Platform.SHARE_WEBPAGE);//分享网页
                sp.setImageUrl(picUrl);
            } else if (type == 3){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImageUrl(picUrl);
            } else if (type == 4){
                sp.setShareType(Platform.SHARE_IMAGE);//分享图片
                sp.setImagePath(picUrl);
            }
            sp.setTitleUrl(titleUrl);
        }
        sp.setTitle(title);
        sp.setText(text);// 分享文本
        // 3、非常重要：获取平台对象
        Platform wechathy = ShareSDK.getPlatform(platFormName);
        wechathy.setPlatformActionListener(this); // 设置分享事件回调
        // 执行分享
        wechathy.share(sp);
        L.e("分享");
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.e("=====成功=====", hashMap.toString());
        if (platform.getName().equals(Wechat.NAME)) {// 微信
            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Success, i);
        } else if (platform.getName().equals(QQ.NAME)) {// QQ
            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Success, i);
        } else if (platform.getName().equals(WechatMoments.NAME)) {
            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Success, i);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.e("=====失败====", String.valueOf(i) + "=====" + throwable.getMessage());
        if (platform.getName().equals(Wechat.NAME)) {// 微信
            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Error, i);
        } else if (platform.getName().equals(QQ.NAME)) {// QQ
            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Error, i);
        } else if (platform.getName().equals(WechatMoments.NAME)) {
            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Error, i);
        }
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.e("=====取消====", String.valueOf(i));
        if (platform.getName().equals(Wechat.NAME)) {// 微信
            shareBeBackListener.beBack(PlatformForShare.Wechat, StatusForShare.Cancel, i);
        } else if (platform.getName().equals(QQ.NAME)) {// QQ
            shareBeBackListener.beBack(PlatformForShare.QQ, StatusForShare.Cancel, i);
        } else if (platform.getName().equals(WechatMoments.NAME)) {//微信朋友圈
            shareBeBackListener.beBack(PlatformForShare.WechatMoments, StatusForShare.Cancel, i);
        }
    }

}