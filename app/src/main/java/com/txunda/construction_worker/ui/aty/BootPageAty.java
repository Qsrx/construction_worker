package com.txunda.construction_worker.ui.aty;

import android.view.KeyEvent;

import com.ants.theantsgo.util.PreferencesUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 启动页
 */
@Layout(R.layout.aty_boot_page)
public class BootPageAty extends BaseAty {
    private boolean is_first_time = true;//首次进入App
    private Timer timer=new Timer();

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        is_first_time = PreferencesUtils.getBoolean(me, "is_first_time", true);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        initEvent();
    }
    private void initEvent() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toDoNext();
            }
        }, 1200);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            timer.cancel();
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 进入引导页或者主页
     */
    private void toDoNext() {
        if (is_first_time) {
            jump(GuidePageAty.class);
            finish();
            return;
        }
        jump(LoginAty.class);
        finish();
    }
}