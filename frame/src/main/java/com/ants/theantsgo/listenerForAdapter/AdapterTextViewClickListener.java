package com.ants.theantsgo.listenerForAdapter;

import android.view.View;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:37
 * 描述：本接口适用于适配器写在外面(即适配器不是内部类的形式)
 * 而且适配器中有TextView或者Bottom等View的点击事件
 * ===============Txunda===============
 */
public interface AdapterTextViewClickListener {
    void onTextViewClick(View v, int position);
}
