package com.txunda.construction_worker.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.txunda.construction_worker.R;


/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:50
 * 描述：自定义Toast
 * ===============Txunda===============
 */
public class ToastTip extends Toast {
    public ToastTip(Context context) {
        super(context);
    }

    // 设置String类型的文字
    public static ToastTip makeText(Context context, CharSequence text, int duration) {
        ToastTip resule = new ToastTip(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_toast_layout, null);
        TextView tv = (TextView) v.findViewById(R.id.view_toast_tv);
        tv.setText(text);

        resule.setView(v);
        // 设置位置，次处为垂直居中
        resule.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        resule.setDuration(duration);

        return resule;
    }

    // 设置资源文字
    public static ToastTip makeText(Context context, int resId, int duration) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    @Override
    public void setText(CharSequence s) {
        if (getView() == null) {
            throw new RuntimeException("Toast未被创建，请创建。。。");
        }
        TextView tv = (TextView) getView().findViewById(R.id.view_toast_tv);
        if (tv == null) {
            throw new RuntimeException("Toast未被创建，请创建。。。");
        }
        tv.setText(s);
    }
}
