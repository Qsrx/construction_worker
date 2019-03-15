package com.ants.theantsgo.base;

import java.util.Map;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/5/29 0029
 * 时间：11:35
 * 描述：MVP模式中，所有View的顶级接口
 * ===============Txunda===============
 */

public interface BaseView {

    void showDialog();

    void showDialog(String text);

    void showContent();

    void removeDialog();

    void removeContent();

    void onStarted();

    void onCancelled();

    void onLoading(long total, long current, boolean isUploading);

    void onException(Exception exception);

    void onComplete(String requestUrl, String jsonStr);

    void onError(String requestUrl, Map<String, String> error);
}
