package com.txunda.construction_worker.ui.aty;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.fgt.CommentMySelfFgt;
import com.txunda.construction_worker.ui.fgt.MyCommentFgt;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的消息界面
 */
@Layout(R.layout.aty_my_message)
public class MyMessageAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_my_message_tab)
    SlidingTabLayout atyMyMessageTab;
    @BindView(R.id.aty_my_message_vp)
    ViewPager atyMyMessageVp;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("我的消息");
        titles = new String[]{"我的评论","评论我的"};
        mFragmentList.add(new MyCommentFgt());
        mFragmentList.add(new CommentMySelfFgt());
        atyMyMessageTab.setViewPager(atyMyMessageVp, titles, me, (ArrayList<Fragment>) mFragmentList);
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }
}