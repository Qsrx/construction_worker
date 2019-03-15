package com.ants.theantsgo.payDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ants.theantsgo.R;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:46
 * 描述：支付方式选择的Dialog
 * ===============Txunda===============
 */
public class PayDialog extends Dialog implements View.OnClickListener {

    private DialogClickListener dialogClickListener;

    public PayDialog(@NonNull Context context, DialogClickListener dialogClickListener) {
        super(context, R.style.dialog_style);
        this.dialogClickListener = dialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_dialog_layout);

        TextView pay_by_balance_tv = (TextView) findViewById(R.id.pay_by_balance_tv);
        TextView pay_by_wechart_tv = (TextView) findViewById(R.id.pay_by_wechat_tv);
        TextView pay_by_ali_tv = (TextView) findViewById(R.id.pay_by_ali_tv);
        TextView cancel_pay_tv = (TextView) findViewById(R.id.cancel_pay_tv);
        pay_by_balance_tv.setOnClickListener(this);
        pay_by_wechart_tv.setOnClickListener(this);
        pay_by_ali_tv.setOnClickListener(this);
        cancel_pay_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (dialogClickListener != null) {
            dialogClickListener.onClick(v);
        }
    }
}
