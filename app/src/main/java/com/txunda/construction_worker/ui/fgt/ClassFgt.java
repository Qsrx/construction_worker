package com.txunda.construction_worker.ui.fgt;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.ui.aty.SearchAty;
import com.txunda.construction_worker.ui.aty.WatchingHistoryAty;
import com.txunda.construction_worker.ui.view.AlertDialog;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.fgt_class)
public class ClassFgt extends BaseFgt implements View.OnClickListener{
    SlidingTabLayout slidingTabLayout;
    RelativeLayout rl_title;
    ViewPager viewPager;
    private String[] titles;
    ImageView iv_kf,iv_ls;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        slidingTabLayout = findViewById(R.id.fgt_class_tab);
        iv_ls = findViewById(R.id.fgt_class_ls);
        iv_kf = findViewById(R.id.fgt_class_kf);
        viewPager = findViewById(R.id.fgt_class_vp);
        rl_title = findViewById(R.id.fgt_class_header_rl_title);
        titles = new String[]{"系统班","我的课程"};
        mFragmentList.add(new SystemClassFgt());
        mFragmentList.add(new MyClassFgt());
        slidingTabLayout.setViewPager(viewPager, titles, me, (ArrayList<Fragment>) mFragmentList);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        rl_title.setOnClickListener(this);
        iv_ls.setOnClickListener(this);
        iv_kf.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_class_header_rl_title:
                jump(SearchAty.class);
                break;
            case R.id.fgt_class_ls:
                jump(WatchingHistoryAty.class);
                break;
            case R.id.fgt_class_kf:
                new AlertDialog(me).builder().setTitle("建工邦教育").setMsg("400 875 6958")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toast("点击了确认");
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                    }
                }).show();
                break;
                default:break;
        }
    }
}