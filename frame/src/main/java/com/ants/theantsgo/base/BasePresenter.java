package com.ants.theantsgo.base;

import com.ants.theantsgo.httpTools.ApiListener;

import java.util.Map;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/5/30 0030
 * 时间：15:37
 * 描述：所有Presenter的基类
 * ===============Txunda===============
 */

public class BasePresenter implements ApiListener {

    public BaseView baseView;

    public BasePresenter(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onStarted() {
        baseView.onStarted();
    }

    @Override
    public void onCancelled() {
        baseView.onCancelled();
    }

    @Override
    public void onLoading(long total, long current, boolean isUploading) {
        baseView.onLoading(total, current, isUploading);
    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        baseView.onComplete(requestUrl, jsonStr);
    }

    @Override
    public void onError(String requestUrl, Map<String, String> error) {
        baseView.onError(requestUrl, error);
    }

    @Override
    public void onException(Exception exception) {
        baseView.onException(exception);
    }
}
