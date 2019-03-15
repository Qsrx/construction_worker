package com.txunda.construction_worker.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Txd_lienchao on 2017/12/5 0005 下午 3:33.
 * 功能描述:微信支付广播接收器
 * email:470360046@qq.com
 */

public class MyReceiver extends BroadcastReceiver {
    private Message message;
    @Override
    public void onReceive(Context context, Intent intent) {
        String errCode=intent.getStringExtra("errCode");
        try {
            message.getMsg(errCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface Message {
        public void getMsg(String str);
    }
    public void setMessage(Message message) {
        this.message = message;
    }

}
