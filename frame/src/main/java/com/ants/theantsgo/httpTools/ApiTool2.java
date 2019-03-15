package com.ants.theantsgo.httpTools;

import com.ants.theantsgo.base.BaseView;
import com.ants.theantsgo.util.JSONUtils;
import com.ants.theantsgo.util.L;
import com.ants.theantsgo.util.MapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/8/8 0008
 * 时间：09:52
 * 描述：
 * ===============Txunda===============
 */

public class ApiTool2 {

    /**
     * 默认的缓存超时时间(缓存时间)
     */
    private final int DEFULT_CURRENT_HTTP_CACHE_EXPIRY = 1000;

    /**
     * 通过GET方式联网请求接口
     *
     * @param url         链接
     * @param params      请求参数
     * @param apiListener 回调监听
     */
    public void getApi(String url, RequestParams params, final BaseView apiListener) {
        HttpUtils httpUtils = new HttpUtils();
        // 设置缓存超时时间
        httpUtils.configCurrentHttpCacheExpiry(DEFULT_CURRENT_HTTP_CACHE_EXPIRY);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, params, new DefaultRequestCallBack(apiListener));
    }

    /**
     * 通过POST方式联网请求接口
     *
     * @param url         链接
     * @param params      参数
     * @param apiListener 回调
     */
    public void postApi(String url, RequestParams params, final BaseView apiListener) {
        L.e(params.toString());
        HttpUtils httpUtils = new HttpUtils();
        // 设置缓存超时时间
        httpUtils.configCurrentHttpCacheExpiry(DEFULT_CURRENT_HTTP_CACHE_EXPIRY);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new DefaultRequestCallBack(apiListener));
    }

    /**
     * 解析返回的error串
     *
     * @param json 要解析的JSON字符串
     * @return Map
     */
    private Map<String, String> parseError(String json) {
        JSONObject jsonObject;
        if (json.startsWith("[") && json.endsWith("]")) {
            return null;
        }
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            return null;
        }
        // 如果jsonObject有flag属性则返回flag对应的值，否则返回""
        String flag = jsonObject.optString("code");
        // code的值是null或者不为1的时候，返回map,否则返回null
        if (flag != null && flag.equals("1"))
            return null;

        return JSONUtils.parseKeyAndValueToMap(json);
    }

    /**
     * Http请求回调<br>
     * version 2.0<br>
     * 2017.06.05 09:41
     */
    private class DefaultRequestCallBack extends RequestCallBack<String> {

        private BaseView baseView;

        // 该够造函数是将请求到的String类型数据转成继承BaseMode的Mode
        private DefaultRequestCallBack(BaseView apiListener) {
            this.baseView = apiListener;
        }

        @Override
        public void onStart() {
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
        public void onSuccess(ResponseInfo<String> responseInfo) {
            try {
                Map<String, String> map = parseError(responseInfo.result);
                if (MapUtils.isEmpty(map)) {// 返回的Map为null的时候，执行OnComplete方法
                    baseView.onComplete(getRequestUrl(), responseInfo.result);
                } else {// 返回的Map不为null执行onError方法
                    baseView.onError(getRequestUrl(), map);
                }
            } catch (Exception e) {
                baseView.onException(e);
            }
        }

        @Override
        public void onFailure(HttpException error, String msg) {
            baseView.onException(error);
        }
    }

}
