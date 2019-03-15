package com.ants.theantsgo.base;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/5/29 0029
 * 时间：15:41
 * 描述：TabLayout的实体类
 * ===============Txunda===============
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
