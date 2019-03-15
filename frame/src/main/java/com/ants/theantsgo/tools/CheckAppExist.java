package com.ants.theantsgo.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;
/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/7/1 0001
 * 时间：上午 9:38
 * 描述：检测指定的app知否存在
 * ===============Txunda===============
 */
public class CheckAppExist {

    private static CheckAppExist checkAppExist;

    /**
     * 私有的够造方法
     */
    private CheckAppExist() {
    }

    /**
     * 获取单一实例
     *
     * @return CheckAppExist
     */
    public static CheckAppExist getInstancei() {
        if (null == checkAppExist) {
            checkAppExist = new CheckAppExist();
        }
        return checkAppExist;
    }

    /**
     * 是否安装制定App
     * <p>
     * "com.tencent.mm" 微信
     * "com.tencent.mobileqq"   QQ
     * "com.baidu.BaiduMap"     百度地图
     * "com.autonavi.minimap"   高德地图
     *
     * @param context Ui上下文
     * @return boolean 返回是否安装了应用程序
     */
    public boolean isAppAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (PackageInfo info : pinfo) {
                String pn = info.packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
