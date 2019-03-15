package com.txunda.construction_worker.ui.aty;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.fgt.CurriculumFgt;
import com.txunda.construction_worker.ui.fgt.ExercisesFgt;
import com.txunda.construction_worker.ui.view.NoScrollViewPager;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Layout(R.layout.aty_my_collects)
public class MyCollectsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_my_collects_tab)
    SlidingTabLayout atyMyCollectsTab;
    @BindView(R.id.aty_my_collects_vp)
    NoScrollViewPager atyMyCollectsVp;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("我的收藏");
        titles = new String[]{"课程","练习题"};
        mFragmentList.add(new CurriculumFgt());
        mFragmentList.add(new ExercisesFgt());
        atyMyCollectsTab.setViewPager(atyMyCollectsVp, titles, me, (ArrayList<Fragment>) mFragmentList);
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}
