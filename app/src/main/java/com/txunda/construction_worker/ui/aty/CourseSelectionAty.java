package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.CourseSelectionBean;
import com.txunda.construction_worker.ui.fgt.ClassroomNotesFgt;
import com.txunda.construction_worker.ui.fgt.CourseSelectionFgt;
import com.txunda.construction_worker.ui.fgt.LearningMaterialsFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.Constant;
import com.txunda.construction_worker.utils.LogUtil;
import com.txunda.construction_worker.utils.MessageHelper;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 课程选集
 * By ：Hzy  win_hzy@163.com
 */
@Layout(R.layout.aty_course_selection)
public class CourseSelectionAty extends BaseAty {
    @BindView(R.id.aty_course_selection_jz)
    JzvdStd atyCourseSelectionJz;
    @BindView(R.id.aty_course_selection_iv_down)
    ImageView atyCourseSelectionIvDown;
    @BindView(R.id.aty_course_selection_iv_share)
    ImageView atyCourseSelectionIvShare;
    @BindView(R.id.aty_course_selection_rb_sc)
    ImageView atyCourseSelectionRbSc;
    @BindView(R.id.aty_course_selection_tab)
    SlidingTabLayout atyCourseSelectionTab;
    @BindView(R.id.aty_course_selection_vp)
    ViewPager atyCourseSelectionVp;
    @BindView(R.id.aty_course_selection_back)
    ImageView atyCourseSelectionBack;
    @BindView(R.id.aty_course_selection_save)
    TextView atyCourseSelectionSave;
    @BindView(R.id.aty_selection_subjects_rl_kf)
    RelativeLayout atySelectionSubjectsRlKf;
    @BindView(R.id.aty_selection_subjects_rl_buy)
    RelativeLayout atySelectionSubjectsRlBuy;
    @BindView(R.id.aty_course_selection_ll_bottom)
    LinearLayout atyCourseSelectionLlBottom;
    @BindView(R.id.aty_course_selection_tv_price)
    TextView atyCourseSelectionTvPrice;
    @BindView(R.id.aty_course_selection_rg)
    RadioGroup atyCourseSelectionRg;
    @BindView(R.id.aty_course_selection_tv_definition)
    TextView atyCourseSelectionTvDefinition;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    public static String subject_id = "";
    public CourseSelectionBean selectionBean;
    private int collection;
    public static int DEFINITION = 1;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.transparencyBar(this);
        ButterKnife.bind(this);
        subject_id = getIntent().getStringExtra("subject_id");
        titles = new String[]{"课程选集", "学习资料", "课程笔记"};
        mFragmentList.add(new CourseSelectionFgt());
        mFragmentList.add(new LearningMaterialsFgt());
        mFragmentList.add(new ClassroomNotesFgt());
        atyCourseSelectionTab.setViewPager(atyCourseSelectionVp, titles, me, (ArrayList<Fragment>) mFragmentList);
    }


    @OnClick({R.id.aty_course_selection_tv_definition,R.id.aty_course_selection_save, R.id.aty_course_selection_back, R.id.aty_course_selection_iv_down, R.id.aty_course_selection_iv_share, R.id.aty_course_selection_rb_sc, R.id.aty_selection_subjects_rl_kf, R.id.aty_selection_subjects_rl_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_selection_subjects_rl_buy:
//                jump(ConfirmOrderAty.class);
                Intent intent = new Intent(me, ConfirmOrderAty.class);
                intent.putExtra("taocan_id", subject_id);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.aty_selection_subjects_rl_kf:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.INTENT_CODE_IMG_SELECTED_KEY, 0);
                    // 进入主页面
                    Intent intent2 = new IntentBuilder(me)
                            .setVisitorInfo(MessageHelper.createVisitorInfo())
                            .setServiceIMNumber("kefuchannelimid_716578")
                            .setScheduleQueue(MessageHelper.createQueueIdentity("客服"))
                            .setScheduleAgent(ContentFactory.createAgentIdentityInfo("2954030095@qq.com"))
                            .setShowUserNick(true)
                            .setBundle(bundle)
                            .build();
                    startActivity(intent2);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    showErrorTip("暂未登陆");
                }
                break;
            case R.id.aty_course_selection_back:
                finish();
                break;
            case R.id.aty_course_selection_iv_down:
                jump(CourseCacheDetailsAty.class);
                break;
            case R.id.aty_course_selection_iv_share:
                jump(ShardAty.class);
                break;
            case R.id.aty_course_selection_rb_sc:
                httpCollection();
                break;
            case R.id.aty_course_selection_save:
                saveNotes();
                break;
            case R.id.aty_course_selection_tv_definition:
                Jzvd.releaseAllVideos();
                if (DEFINITION == 1){
                    DEFINITION = 2;
                    atyCourseSelectionTvDefinition.setText("高清");
                    atyCourseSelectionJz.setUp(selectionBean.getData().getShipin().get(CourseSelectionFgt.index).getVideo_path_two(), null, JzvdStd.SCREEN_WINDOW_NORMAL);
                }else {
                    DEFINITION = 1;
                    atyCourseSelectionTvDefinition.setText("标清");
//                    String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/" + selectionBean.getData().getShipin().get(CourseSelectionFgt.index).getMulu() + "." + selectionBean.getData().getShipin().get(CourseSelectionFgt.index).getName() + ".mp4";
                    String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/" +  selectionBean.getData().getShipin().get(CourseSelectionFgt.index).getName() + ".mp4";
                    if (isFileExit(s)) {
                        toast("本地播放");
                        atyCourseSelectionJz.setUp(s, null, Jzvd.SCREEN_WINDOW_NORMAL);
                    } else {
                        atyCourseSelectionJz.setUp(selectionBean.getData().getShipin().get(CourseSelectionFgt.index).getVideo_path(), "", JzvdStd.SCREEN_WINDOW_NORMAL);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpData();
    }

    /**
     * 设置radiobutton按钮drawble大小
     *
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[0].setBounds(0, 0, 60, 60);
        rb.setCompoundDrawables(compoundDrawables[0], null, null, null);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyCourseSelectionVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 2) {
                    atyCourseSelectionSave.setVisibility(View.VISIBLE);
                } else {
                    atyCourseSelectionSave.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Course_selection", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            LogUtil.e("iwqjeiowqioeq",response);
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    selectionBean = GsonUtil.GsonToBean(response, CourseSelectionBean.class);
//                                    String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/" + selectionBean.getData().getDirectory().get(0).getList().get(0).getMulu() + "." + selectionBean.getData().getDirectory().get(0).getList().get(0).getName() + ".mp4";
                                    String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦" + "/" + selectionBean.getData().getDirectory().get(0).getList().get(0).getName() + ".mp4";
                                    if (isFileExit(s)) {
                                        toast("本地播放");
                                        atyCourseSelectionJz.setUp(s, null, Jzvd.SCREEN_WINDOW_NORMAL);
                                    } else {
                                        atyCourseSelectionJz.setUp(selectionBean.getData().getShipin().get(0).getVideo_path(), null, JzvdStd.SCREEN_WINDOW_NORMAL);
                                    }
//                                    toast(isFileExit(s));
                                    Glide.with(me).load(selectionBean.getData().getShipin().get(0).getVideo_pic()).into(atyCourseSelectionJz.thumbImageView);
                                    atyCourseSelectionTvPrice.setText("购买单科 ¥" + selectionBean.getData().getPrice());
                                    //Collection = 1已收藏else未收藏
                                    if (selectionBean.getData().getCollection() == 1) {
                                        atyCourseSelectionRbSc.setImageResource(R.mipmap.icon_class_sc);
                                    } else {
                                        atyCourseSelectionRbSc.setImageResource(R.mipmap.icon_unsc);
                                    }
                                    collection = selectionBean.getData().getCollection();
                                    //Is_Pay = 1已购买else未购买
                                    if (selectionBean.getData().getIs_pay() == 1) {
                                        atyCourseSelectionLlBottom.setVisibility(View.GONE);
                                    } else {
                                        atyCourseSelectionLlBottom.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    showErrorTip(objectMap.get("message").toString());
                                }
                            } catch (NullPointerException e) {
                                showErrorTip("数据解析失败");
                            }

                        }
                    }
                }
        );
    }

    /**
     * 保存笔记
     */
    private void saveNotes() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/savenotes", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("notes", ClassroomNotesFgt.tv_note)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                toast(objectMap.get("message"));
                            } else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    /**
     * 收藏/取消收藏
     */
    private void httpCollection() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/collection", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                changeSc(collection);
                            } else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    private void changeSc(int num) {
        if (num == 1) {
            atyCourseSelectionRbSc.setImageResource(R.mipmap.icon_unsc);
            collection = 2;
        } else {
            atyCourseSelectionRbSc.setImageResource(R.mipmap.icon_class_sc);
            collection = 1;
        }
    }

    public static boolean isFileExit(String path) {
        if (path == null) {
            return false;
        }
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

}