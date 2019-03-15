package com.txunda.construction_worker.ui.aty;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PointTestBean;
import com.txunda.construction_worker.ui.fgt.DoWorkFgt;
import com.txunda.construction_worker.ui.view.AlertDialog;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 多个试卷
 */
@Layout(R.layout.aty_more_do_work)
public class MoreDoWorkAty extends BaseAty {

    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_do_work_tv_post)
    TextView atyDoWorkTvPost;
    @BindView(R.id.aty_more_do_work_iv_dtk)
    ImageView atyMoreDoWorkIvDtk;
    @BindView(R.id.aty_do_work_rb_sc)
    RadioButton atyDoWorkRbSc;
    @BindView(R.id.aty_more_do_work_vp)
    ViewPager atyMoreDoWorkVp;
    @BindView(R.id.aty_more_do_work_tab)
    TabLayout atyMoreDoWorkTab;
    private List<View> list_view;
    private ViewPagerAdapter adapter;
    private List<Fragment> list = new ArrayList<>();
    public static String type;
    private PointTestBean pointTestBean;
    public static HashMap<String,Integer> hashMap = new HashMap<>();

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        list_view = new ArrayList<>();
        setRbSize(atyDoWorkRbSc);
        type = getIntent().getStringExtra("type");
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        atyMoreDoWorkVp.setAdapter(adapter);
        atyMoreDoWorkTab.setupWithViewPager(atyMoreDoWorkVp);
        atyMoreDoWorkTab.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 设置radiobutton按钮drawble大小
     *
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[0].setBounds(0, 0, 70, 70);
        rb.setCompoundDrawables(compoundDrawables[0], null, null, null);
    }

    @OnClick({R.id.aty_do_work_iv_back, R.id.aty_do_work_tv_post, R.id.aty_more_do_work_iv_dtk, R.id.aty_do_work_rb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                finish();
                break;
            case R.id.aty_do_work_tv_post:
                jump(ExerciseReportAty.class);
                break;
            case R.id.aty_more_do_work_iv_dtk:
                jump(AnswerCardAty.class);
                break;
            case R.id.aty_do_work_rb_sc:
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        //TODO:接口请求
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyMoreDoWorkVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (pointTestBean.getData().getList().get(0).getPay() == 1) {

                }else {
                    if (i == 4) {
                        new AlertDialog(me).builder().setTitle("您还未购买试卷").setMsg("购买之后可做本科目全部试卷哦")
                                .setPositiveButton("购买", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        jump(UnlockDetailsAty.class);
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub

                            }
                        }).show();
                    } else {

                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> lists;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> lists) {
            super(fm);
            this.lists = lists;
        }

        @Override
        public Fragment getItem(int i) {
            return lists.get(i);
        }

        @Override
        public int getCount() {
            return lists.size();
        }
    }
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/practice", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> stringObjectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (stringObjectMap.get("code").equals("1")) {
                                if (type.equals("1")){
                                    pointTestBean = GsonUtil.GsonToBean(response, PointTestBean.class);
                                    for (int i = 0; i < pointTestBean.getData().getList().size(); i++) {

                                        list.add(new DoWorkFgt(i));

                                    }
                                    adapter.notifyDataSetChanged();
                                }
                                return;
                            }
                            stringObjectMap.get("message");
                            return;
                        }
                        showErrorTip(error.getMessage());
                    }
                });
    }
}