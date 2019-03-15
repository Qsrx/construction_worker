package com.txunda.construction_worker.utils;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/19 019 11:59:57.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DownloadObserver implements Observer<DownloadInfo> {

    public Disposable d;//可以用于取消注册的监听者
    public DownloadInfo downloadInfo;

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(DownloadInfo value) {
        this.downloadInfo = value;
        downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD);
        EventBus.getDefault().post(downloadInfo);
    }

    @Override
    public void onError(Throwable e) {
        Log.d("My_Log","onError");
        if (DownloadManager.getInstance().getDownloadUrl(downloadInfo.getUrl())){
            DownloadManager.getInstance().pauseDownload(downloadInfo.getUrl());
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_ERROR);
            EventBus.getDefault().post(downloadInfo);
        }else{
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_PAUSE);
            EventBus.getDefault().post(downloadInfo);
        }

    }

    @Override
    public void onComplete() {
        Log.d("My_Log","onComplete");
        if (downloadInfo != null){
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_OVER);
            EventBus.getDefault().post(downloadInfo);
        }
    }
}