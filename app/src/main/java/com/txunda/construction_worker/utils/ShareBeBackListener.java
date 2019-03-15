package com.txunda.construction_worker.utils;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:48
 * 描述：分享回调接口
 * ===============Txunda===============
 */
public interface ShareBeBackListener {

    void beBack(ShareForApp.PlatformForShare platformForShare, ShareForApp.StatusForShare statusForShare, int code);
}
