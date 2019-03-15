package com.txunda.construction_worker.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-pc on 2019/2/12.
 */

public class ViewPagerAdp extends FragmentPagerAdapter{
    private List<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdp(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdp(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
