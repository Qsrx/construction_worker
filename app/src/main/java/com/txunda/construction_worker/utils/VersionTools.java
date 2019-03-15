package com.txunda.construction_worker.utils;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/11 011 10:20:26.
 * 功能描述：检查版本号
 * 联系方式： win_hzy@163.com
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class VersionTools {
    /**
     * 获取版本号
     *
     * @return String
     */
    public static String getVersion(Context context) {
        String version = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            version = null;
        }
        return version;
    }

    /**
     * 获取版本号
     *
     * @return int
     */
    public static int getVersionName(Context context) {
        int version = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionCode;
        } catch (Exception e) {
            version = 0;
        }
        return version;
    }

    /**
     * 版本号---剔除掉"."之后的版本号
     *
     * @return String
     */
    public static String getVersionString(String version) {
        return version.replace(".", "");
    }
}