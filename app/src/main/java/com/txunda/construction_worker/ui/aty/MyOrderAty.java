package com.txunda.construction_worker.ui.aty;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.fgt.MyOrderFgt;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的订单
 */
@Layout(R.layout.aty_my_order)
public class MyOrderAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_my_order_tab)
    SlidingTabLayout atyMyOrderTab;
    @BindView(R.id.aty_my_order_vp)
    ViewPager atyMyOrderVp;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("我的订单");
        titles = new String[]{"全部","待付款","已购买"};
        mFragmentList.add(new MyOrderFgt("0"));
        mFragmentList.add(new MyOrderFgt("1"));
        mFragmentList.add(new MyOrderFgt("2"));
        atyMyOrderTab.setViewPager(atyMyOrderVp, titles, me, (ArrayList<Fragment>) mFragmentList);

    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}