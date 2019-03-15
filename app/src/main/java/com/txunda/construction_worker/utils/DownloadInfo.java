package com.txunda.construction_worker.utils;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/19 019 11:48:26.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DownloadInfo {
    /**
     * 下载状态
     */
    public static final String DOWNLOAD = "download";
    public static final String DOWNLOAD_PAUSE = "pause";
    public static final String DOWNLOAD_CANCEL = "cancel";
    public static final String DOWNLOAD_OVER = "over";
    public static final String DOWNLOAD_ERROR = "error";

    public static final long TOTAL_ERROR = -1;//获取进度失败

    private String url;
    private String fileName;
    private String downloadStatus;
    private long total;
    private long progress;

    public DownloadInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}
