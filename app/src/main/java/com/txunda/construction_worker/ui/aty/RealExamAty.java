package com.txunda.construction_worker.ui.aty;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.fgt.DoWorkFgt;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 真题/模拟题
 */
@Layout(R.layout.aty_real_exam)
public class RealExamAty extends BaseAty {
    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_more_do_work_iv_dtk)
    ImageView atyMoreDoWorkIvDtk;
    @BindView(R.id.aty_real_exam_tv_time)
    TextView atyRealExamTvTime;
    @BindView(R.id.aty_do_work_rb_sc)
    RadioButton atyDoWorkRbSc;
    @BindView(R.id.aty_more_do_work_tab)
    TabLayout atyMoreDoWorkTab;
    @BindView(R.id.aty_more_do_work_vp)
    ViewPager atyMoreDoWorkVp;
    @BindView(R.id.aty_do_work_tv_post)
    TextView atyDoWorkTvPost;
    private List<Fragment> list;

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        setRbSize(atyDoWorkRbSc);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new DoWorkFgt(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        atyMoreDoWorkVp.setAdapter(adapter);
        atyMoreDoWorkTab.setupWithViewPager(atyMoreDoWorkVp);
        atyMoreDoWorkTab.setTabMode(TabLayout.MODE_FIXED);
    }


    @OnClick({R.id.aty_do_work_tv_post,R.id.aty_do_work_iv_back, R.id.aty_more_do_work_iv_dtk, R.id.aty_do_work_rb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                finish();
                break;
            case R.id.aty_more_do_work_iv_dtk:
                jump(RealAnswerCardAty.class);
                break;
            case R.id.aty_do_work_rb_sc:
                break;
            case R.id.aty_do_work_tv_post:
                jump(RealExerciseReportAty.class);
                break;
                default:break;
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
}