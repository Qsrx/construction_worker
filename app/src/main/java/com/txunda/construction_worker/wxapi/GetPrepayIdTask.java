package com.txunda.construction_worker.wxapi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.txunda.construction_worker.utils.Constants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author txunda_LH 2017-1-6
 * @version 1.0
 * @since ΢微信支付工具类
 */
@SuppressWarnings("deprecation")
public class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String, String>> {
    private static final String TAG = "MicroMsg.SDKSample.PayActivity";
    PayReq req;
    IWXAPI msgApi = null;
    String sign, appid, nonce_str, package_name, time_stamp, prepay_id, mch_id, order_sn = "";

    /**
     * 构造函数，初始化相关参数
     *
     * @param con
     * @param sign
     * @param appid
     * @param nonce_str
     * @param package_name
     * @param time_stamp
     * @param prepay_id
     * @param mch_id
     * @param order_sn
     */
    public GetPrepayIdTask(Context con, String sign, String appid, String nonce_str, String package_name,
                           String time_stamp, String prepay_id, String mch_id, String order_sn) {
        msgApi = WXAPIFactory.createWXAPI(con,null);
        req = new PayReq();
        this.sign = sign;
        this.appid = appid;
        this.nonce_str = nonce_str;
        this.package_name = package_name;
        this.time_stamp = time_stamp;
        this.prepay_id = prepay_id;
        this.mch_id = mch_id;
        this.order_sn = order_sn;
    }

    @Override
    protected Map<String, String> doInBackground(Void... params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onPostExecute(Map<String, String> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        genPayReq();

    }

    /**
     * 微信支付 第二步
     */
    private void genPayReq() {
        req.appId = appid;// 应用ID
        req.partnerId = mch_id;//商户号
        req.prepayId = prepay_id;//预支付交易会话ID
        req.packageValue = package_name;//扩展字段
        req.nonceStr = nonce_str;// 随机字符串
        req.sign = sign;
        req.timeStamp = time_stamp;
        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        signParams.add(new BasicNameValuePair("sign", req.sign));
        Log.e("1orion", signParams.toString());
        // 调起支付
        sendPayReq();
    }

    /**
     * ΢ 微信支付 调起支付
     */
    private void sendPayReq() {
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }
}
