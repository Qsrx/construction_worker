package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ImportantNoticeBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.MessageHelper;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 重要通知
 */
@Layout(R.layout.aty_important_notice)
public class ImportantNoticeAty extends BaseAty {

    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_important_notice_tv_system)
    TextView atyImportantNoticeTvSystem;
    @BindView(R.id.aty_important_notice_tv_system_time)
    TextView atyImportantNoticeTvSystemTime;
    @BindView(R.id.aty_important_notice_ll_system)
    LinearLayout atyImportantNoticeLlSystem;
    @BindView(R.id.aty_important_notice_tv_teacher)
    TextView atyImportantNoticeTvTeacher;
    @BindView(R.id.aty_important_notice_tv_teacher_time)
    TextView atyImportantNoticeTvTeacherTime;
    @BindView(R.id.aty_important_notice_ll_teacher)
    LinearLayout atyImportantNoticeLlTeacher;
    @BindView(R.id.aty_important_notice_tv_kf)
    TextView atyImportantNoticeTvKf;
    @BindView(R.id.aty_important_notice_tv_kf_time)
    TextView atyImportantNoticeTvKfTime;
    @BindView(R.id.aty_important_notice_ll_kf)
    LinearLayout atyImportantNoticeLlKf;
    @BindView(R.id.item_system_message_point)
    CircleImageView itemSystemMessagePoint;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("重要通知");

        File file = Environment.getExternalStorageDirectory();
        long totalSpace = file.getTotalSpace();//总大小
        long usableSpace = file.getUsableSpace();//可用
        long aleayuse = totalSpace - usableSpace;
        String formatFileSize = Formatter.formatFileSize(me, aleayuse);
        String formatUsable = Formatter.formatFileSize(me, usableSpace);
        Log.d("inkelrewnrlew", "initViews: ==========已使用"+formatFileSize+"可用"+formatUsable);
    }

    @OnClick({R.id.header_iv_back, R.id.aty_important_notice_ll_system, R.id.aty_important_notice_ll_teacher, R.id.aty_important_notice_ll_kf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_important_notice_ll_system:
                jump(SystemMessageAty.class);
                break;
            case R.id.aty_important_notice_ll_teacher:
                intoIm();
                break;
            case R.id.aty_important_notice_ll_kf:
                intoIm();
                break;
                default:break;
        }
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/msgIndex", new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        ImportantNoticeBean noticeBean = GsonUtil.GsonToBean(response, ImportantNoticeBean.class);
                        try {
                            if (noticeBean.getData().getRead_msg() > 0) {
                                itemSystemMessagePoint.setVisibility(View.VISIBLE);
                            }
                        } catch (NullPointerException e) {
                            showErrorTip("数据解析失败");
                        }

                    }
                });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
//        httpData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpData();
    }

    /**
     * 进入IM客服
     */
    private void intoIm(){
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
            // 进入主页面
            Intent intent = new IntentBuilder(me)
                    .setVisitorInfo(MessageHelper.createVisitorInfo())
                    .setServiceIMNumber("kefuchannelimid_716578")
                    .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                    .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                    .setShowUserNick(true)
                    .setBundle(bundle)
                    .build();
            startActivity(intent);
        } else {
            //未登录，需要登录后，再进入会话界面
            showErrorTip("暂未登陆");
        }
    }
}