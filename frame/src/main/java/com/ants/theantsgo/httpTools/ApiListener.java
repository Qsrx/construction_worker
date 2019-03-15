package com.ants.theantsgo.httpTools;

import java.util.Map;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:36
 * 描述：API调用的事件监听器
 * ===============Txunda===============
 */
public interface ApiListener {

    /**
     * API调用之前的回调方法
     */
    void onStarted();

    /**
     * API调用取消之后的回调方法
     */
    void onCancelled();

    /**
     * API调用中的回调方法
     *
     * @param total       总进度
     * @param current     当前进度
     * @param isUploading 是否上传
     */
    void onLoading(long total, long current, boolean isUploading);

    /**
     * API调用成功后返回值以json对象方式通知监听器
     *
     * @param requestUrl 请求连接
     * @param jsonStr    返回的字符串
     */
    void onComplete(String requestUrl, String jsonStr);

    /**
     * 出现业务错误时通知监听器错误码及字错误码等信息
     *
     * @param requestUrl 请求连接
     * @param error      错误消息Map
     */
    void onError(String requestUrl, Map<String, String> error);

    /**
     * 出现网络问题等未知异常时会回调此方法
     *
     * @param exception 异常
     */
    void onException(Exception exception);

}
