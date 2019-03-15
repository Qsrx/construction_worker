package com.txunda.construction_worker.base;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.util.Preferences;
import com.txunda.construction_worker.ui.view.ToastTip;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/22 022 11:29:17.
 * 功能描述：Fragment基类
 * 联系方式： win_hzy@163.com
 */
public abstract class BaseFgt extends BaseFragment{
    public String token = "";
    @Override
    public void initViews() {
        token = Preferences.getInstance().getString(me, "construction", "token");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
    private ToastTip tipsToast;
    /**
     * 自定义toast
     * @param tips      提示文字
     */
    protected void showTips(String tips) {
        if (tipsToast == null) {
            tipsToast = ToastTip.makeText(me, tips, ToastTip.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setText(tips);
    }
}