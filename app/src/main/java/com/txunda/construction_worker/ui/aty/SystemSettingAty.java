package com.txunda.construction_worker.ui.aty;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.Preferences;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.DataCleanManager;
import com.txunda.construction_worker.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24
 * 功能描述：系统设置
 * 联系方式： win_hzy@163.com
 */
@Layout(R.layout.aty_system_setting)
public class SystemSettingAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_system_setup_size)
    TextView atySystemSetupSize;
    @BindView(R.id.aty_system_setup_load)
    LinearLayout atySystemSetupLoad;
    @BindView(R.id.aty_system_setup_setting)
    LinearLayout atySystemSetupSetting;
    @BindView(R.id.aty_system_setup_about_us)
    LinearLayout atySystemSetupAboutUs;
    @BindView(R.id.aty_system_setup_down_line)
    TextView atySystemSetupDownLine;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("系统设置");
        seeLoadData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
    }

    @OnClick({R.id.header_iv_back, R.id.aty_system_setup_load, R.id.aty_system_setup_setting, R.id.aty_system_setup_about_us, R.id.aty_system_setup_down_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_system_setup_load:
                new AlertDialog(me).builder().setTitle("提醒").setMsg("您是否确认清除缓存数据")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataCleanManager.clearAllCache(me);
                                toast("清除成功");
                                seeLoadData();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                    }
                }).show();
                break;
            case R.id.aty_system_setup_setting:
                //TODO:去评价
                jump(HelpAty.class);
                break;
            case R.id.aty_system_setup_about_us:
                //TODO:关于我们
                jump(AboutUsAty.class);
                break;
            case R.id.aty_system_setup_down_line:
                //清除token
                Preferences.getInstance().cleanAll(this, "construction");
                //第一个参数为是否解绑推送的devicetoken
                ChatClient.getInstance().logout(true, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(int code, String error) {
                        toast("环信注销失败");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
                jump(LoginAty.class);
                //退出所有Activity
                AppManager.getInstance().killAllActivity();
                break;
                default:break;
        }
    }
    //查看应用缓存大小
    private void seeLoadData(){
        try {
            atySystemSetupSize.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}