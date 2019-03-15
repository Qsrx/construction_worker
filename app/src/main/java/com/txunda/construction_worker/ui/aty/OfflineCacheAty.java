package com.txunda.construction_worker.ui.aty;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.fgt.CompleteFgt;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 离线缓存
 */
@Layout(R.layout.aty_offline_cache)
public class OfflineCacheAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_offline_cache_tab)
    SlidingTabLayout atyOfflineCacheTab;
    @BindView(R.id.aty_offline_cache_vp)
    ViewPager atyOfflineCacheVp;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("离线缓存");
        titles = new String[]{"已完成","缓存中"};
        mFragmentList.add(new CompleteFgt("1"));
        mFragmentList.add(new CompleteFgt("3"));
        atyOfflineCacheTab.setViewPager(atyOfflineCacheVp, titles, me, (ArrayList<Fragment>) mFragmentList);
    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}