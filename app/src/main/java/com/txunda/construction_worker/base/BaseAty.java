package com.txunda.construction_worker.base;

import android.widget.Toast;

import com.ants.theantsgo.tips.ToastTip;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.txunda.construction_worker.R;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/22 022 11:29:05.
 * 功能描述：Activity基类
 * 联系方式： win_hzy@163.com
 */
public abstract class BaseAty extends BaseActivity{
    public String token = "";
    @Override
    public void initViews() {
        token = Preferences.getInstance().getString(this, "construction", "token");
    }

    @Override
    public void initDatas(JumpParameter paramer) {

    }

    @Override
    public void setEvents() {

    }

    private ToastTip tipsToast;

    public void showTip(int iconResId, String tips) {
        if (tipsToast == null) {
            tipsToast = ToastTip.makeText(this, tips, Toast.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }
    /**
     * 错误提示
     *
     * @param tips 提示内容
     */
    public void showErrorTip(String tips) {
        showTip(R.mipmap.icon_error_tip, tips);
    }
}
