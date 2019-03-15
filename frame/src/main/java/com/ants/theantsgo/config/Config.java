package com.ants.theantsgo.config;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ants.theantsgo.AppManager;
import com.ants.theantsgo.util.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class Config {

    /**
     * 登录状态
     */
    private static final String PREF_KEY_LOGIN_STATE = "PREF_KEY_LOGIN_STATE";

    private static final String PREF_KEY_CHECK_STATE = "PREF_KEY_CHECK_STATE";

    private static View[] aty = new View[1];

    /**
     * 主URL
     */
//    public final static String BASE_URL = "http://master.txunda.com/index.php/Api/";
    public final static String BASE_URL = "http://wurenxiche.txunda.com/index.php/Api/";
    public static List<Activity> list = new ArrayList<>();

    /**
     * 判断是否登录
     *
     * @return boolean
     */
    public static boolean isLogin() {
        // AppManager.getInstance().getTopActivity()获取栈顶的Activity对象
        return PreferencesUtils.getBoolean(AppManager.getInstance().getTopActivity(), PREF_KEY_LOGIN_STATE);
    }

    public static boolean isCheck() {
        // AppManager.getInstance().getTopActivity()获取栈顶的Activity对象
        return PreferencesUtils.getBoolean(AppManager.getInstance().getTopActivity(), PREF_KEY_CHECK_STATE);
    }

    public static void AddAty(View view) {
        aty[0] = view;
    }

    /**
     * 设置登录状态
     *
     * @param isLogin true登录状态，flase未登录状态
     */
    public static void setLoginState(boolean isLogin) {
        PreferencesUtils.putBoolean(AppManager.getInstance().getTopActivity(), PREF_KEY_LOGIN_STATE, isLogin);
    }

    public static void setCheckState(boolean isCheck) {
        PreferencesUtils.putBoolean(AppManager.getInstance().getTopActivity(), PREF_KEY_CHECK_STATE, isCheck);
    }

    /**
     * 添加Activity
     *
     * @param activity Activity
     */
    public static void AddAty(Activity activity) {
        list.add(activity);
    }

    /**
     * 销毁Activity
     */
    public static void FinishAty() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).finish();
        }
    }

    /**
     * 屏幕密度(0.75 / 1.0 / 1.5)
     *
     * @param activity Activity
     * @return float
     */
    public static float screenDensity(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.density;
    }

    /**
     * 屏幕密度DPI(120 / 160 / 240)
     *
     * @param activity Activity
     * @return  int
     */
    public static int getScreenDensityDpi(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.densityDpi;
    }

    /**
     * 屏幕的宽
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 屏幕的高
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    /**
     * 设置标签和别名
     *
     * @param context 上下文
     * @param set     Tag集合
     * @param alias   别名
     */
//    public static void setTagAndAlias(Context context, Set<String> set, String alias) {
//        JPushInterface.setAliasAndTags(context, alias, set,
//                new TagAliasCallback() {
//                    @Override
//                    public void gotResult(int i, String s, Set<String> set) {
//                    }
//                });
//    }
}
