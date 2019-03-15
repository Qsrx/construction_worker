package com.ants.theantsgo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.ants.theantsgo.config.Settings;
import com.ants.theantsgo.tool.ToolKit;
import com.ants.theantsgo.util.FileManager;
import com.ants.theantsgo.util.ListUtils;
import com.ants.theantsgo.util.PreferencesUtils;

import org.litepal.LitePalApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 2:30
 * 描述：Application
 * ===============Txunda===============
 */
public class WeApplication extends LitePalApplication {

    private final String PREF_USERINFO = "user_info";

    // APP管理类
    private AppManager appManager;
    // 用户信息
    private Map<String, String> userInfo;

    private static WeApplication application = null;

//    public static SharedPreferences sharedPreferences = null;

    public static WeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appManager = AppManager.getInstance();
//        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);            // 初始化 JPush
        start();
        initUserInfo();
    }

    /**
     * 解决找不到某些jar的静态方法
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    /**
     * 设置全局变量
     */
    private void start() {
        // 获得屏幕宽度（像素）
        Settings.displayWidth = ToolKit.getScreenWidth(this);
        // 获得屏幕高度（像素）
        Settings.displayHeight = ToolKit.getScreenHeight(this);
        // 文件路径设置
        Settings.cacheCompressPath = FileManager.getCompressFilePath();
        // 创建路径
        new File(Settings.cacheCompressPath).mkdirs();
    }

    /**
     * 退出系统时把所有的Activity清掉
     */
    private void finish() {
        // 关掉所有的运行中的Activity
        appManager.AppExit(this);
        // 调用系统的垃圾处理机制
        System.gc();
    }

    /**
     * 初始化用户信息
     */
    private void initUserInfo() {
        userInfo = new HashMap<>();
        // 获取存在本地的用户信息(key)
        String keys = PreferencesUtils.getString(this, PREF_USERINFO);
        if (!TextUtils.isEmpty(keys)) {
            // 按照","分割keys
            String[] userInfos = keys.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            for (String key : userInfos) {// 获取用户信息，将其保存到Map中
                userInfo.put(key, PreferencesUtils.getString(this, key));
            }
        }
    }

    /**
     * 获取用户信息
     *
     * @return Map
     */
    public Map<String, String> getUserInfo() {
        return userInfo;
    }

    /**
     * 设置用户信息
     */
    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
        // 把用户信息存进Preferences
        // 将userInfo Map的所有key存到列表中
        List<String> keys = new ArrayList<>();
        // 迭代。。。获取所有key
        Iterator<String> iterator = this.userInfo.keySet().iterator();
        // 迭代遍历Key
        while (iterator.hasNext()) {
            String key = iterator.next();
            keys.add(key);
            String value = this.userInfo.get(key);
            // 将key和value保存到本地
            PreferencesUtils.putString(this, key, value);
        }
        // 将keys转成字符串(key1,key2...)
        PreferencesUtils.putString(this, PREF_USERINFO, ListUtils.join(keys));
    }

    /**
     * 获取Application对象
     *
     * @return
     */
    public static Application getApplication() {
        return application;
    }

}
