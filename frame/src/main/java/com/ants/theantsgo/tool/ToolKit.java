package com.ants.theantsgo.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.util.Log;

import com.ants.theantsgo.util.L;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author Zero @date 2014年8月10日
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class ToolKit {

    /**
     * 得到手机IP地址
     *
     * @return String
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取移动网络的IP,格式为0.0.0.0
     *
     * @return String
     */
    private static String getLocalIpAddress2() {
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = intToIp(inetAddress.getHostAddress().hashCode());
                    }
                }
            }
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return ip;
        }
    }

    /**
     * 获取wifi的IP地址，格式为221.238.193.163
     *
     * @param context 上下文
     * @return String
     */
    private static String getWifiIP(Context context) {
        try {
            // 获取wifi服务
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context
                    .WIFI_SERVICE);
            // 判断wifi是否开启
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            return intToIp(ipAddress);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将一个int类型的数字转换成0.0.0.0格式的IP地址
     *
     * @param i int
     * @return String
     */
    private static String intToIp(int i) {

        // 负数&步骤： 1.将负数的绝对值转成二进制 2.获得二进制的反码（将二进制---0变1，1变0） 3.获得补码（反码+1） 4.与运算
        // 负数位移步骤：获得补码后位移
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    /**
     * 根据网络状态获取IP地址，格式为0.0.0.0
     *
     * @param content 上下文
     * @return String
     */
    public static String ipForNetType(Context content) {
        String type = ToolKit.getCurrentNetType(content);
        if (type != null) {
            if ("wifi".equals(type)) {
                return getWifiIP(content);
            } else {
                return getLocalIpAddress2();
            }
        }
        return null;
    }

    /**
     * 实现文本复制功能
     *
     * @param context 上下文
     * @param content 内容
     */
    public static void copy(Context context, String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context 上下文
     * @return String  内容
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 判断字符串内容是否为数字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取屏幕的宽
     *
     * @param context 上下文
     * @return int
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高
     *
     * @param context 上下文
     * @return int
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取config.properties文件中的值 assets <br>
     * <font color="#ff0000">阿里支付中使用</font>
     *
     * @param key key
     * @return String   获取到的内容
     */
    public static String getConfigProperties(String key) {
        String value = "";
        Properties props = new Properties();
        InputStream iStream = ToolKit.class.getResourceAsStream("/assets/config.properties");

        try {
            props.load(iStream);
            value = props.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * <p>
     * 获取图片资源的id
     *
     * @param context 上下文
     * @param resName 图片资源的名称
     * @return int 图片资源id
     */
    public static int getBitmapRes(Context context, String resName) {
        try {
            String str = context.getPackageName();
            Class localClass = Class.forName(str + ".R$drawable");
            return getResId(localClass, resName);
        } catch (Throwable localThrowable) {
            L.e(ToolKit.class, "出错了");
        }
        return 0;
    }

    /**
     * <p>
     * 获取控件资源的id
     *
     * @param context 上下文
     * @param resName 图片资源的名称
     * @return int 图片资源id
     */
    public static int getViewRes(Context context, String resName) {
        try {
            String str = context.getPackageName();
            Class localClass = Class.forName(str + ".R$id");
            return getResId(localClass, resName);
        } catch (Throwable localThrowable) {
            // CommonlyLog.c(localThrowable);
        }
        return 0;
    }

    /**
     * <p>
     * 获取文字资源的id
     *
     * @param context 上下文
     * @param resName 文字资源的名称
     * @return int 文字资源的id
     */
    public static int getStringRes(Context context, String resName) {
        try {
            String str = context.getPackageName();
            Class localClass = Class.forName(str + ".R$string");
            return getResId(localClass, resName);
        } catch (Throwable localThrowable) {
            // CommonlyLog.c(localThrowable);
        }
        return 0;
    }

    private static int getResId(Class<?> paramClass, String paramString) {
        int i = 0;
        if (paramString != null) {
            String str = paramString.toLowerCase();
            try {
                Field localField = paramClass.getField(str);
                localField.setAccessible(true);
                i = ((Integer) localField.get(null)).intValue();
            } catch (Throwable localThrowable) {
                // CommonlyLog.c(localThrowable);
                i = 0;
            }
        }
        return i;
    }

    /**
     * 获取网络类型
     *
     * @param context 上下文
     * @return String 网络类型
     */
    public static String getCurrentNetType(Context context) {
        String network_type = "";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            network_type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            network_type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                network_type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                network_type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                network_type = "4g";
            }
        }
        return network_type;
    }

    /**
     * 获取运营商名字
     *
     * @param context 上下文
     * @return String 运行商名称
     */
    public String getOperatorName(Context context) {
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telManager.getSimOperator();
        String operator_name = null;
        if (operator != null) {
            switch (operator) {
                case "46000":
                case "46002":
                case "46007":
                    operator_name = "中国移动";
                    break;
                case "46001":
                    operator_name = "中国联通";
                    break;
                case "46003":
                    operator_name = "46003";
                    break;
            }
            return operator_name;
        }
        return null;
    }

    /**
     * 判断是否可解析成集合类型
     *
     * @param map  解析出的map集合
     * @param type map 的 key，例如：解析出的map为data，则type就是将data里的数据解析成list集合的key
     * @return boolean
     */
    public static boolean isList(Map<String, String> map, String type) {
        return checkList(map, type);
    }

    /**
     * 检查是否含有该字段
     *
     * @param map  Map
     * @param type Key
     * @return boolean
     */
    private static boolean checkList(Map<String, String> map, String type) {
        try{
            return !map.get(type).equals("") && !map.get(type).equals("null") && !map.get(type).equals("[]")
                    && map.get(type) != null && !map.get(type).equals("[null]");
        } catch (NullPointerException e){
            return  false;
        }
    }

    /**
     * 判断底部是否存在NavigationBar
     *
     * @param context 上下文
     * @return boolean
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            Log.w("一场", e);
        }

        return hasNavigationBar;

    }

    /**
     * 获取NavigationBar的高度
     *
     * @param context 上下文
     * @return int
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context 上下文
     * @param pxValue px
     * @return int
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context   上下文
     * @param pxValue   px
     * @return int
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;// 屏幕密度
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context 上下文
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return int
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取应用名称
     *
     * @param context 上下文
     * @return String
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /**
     * 日志输出文件------>>>>SD卡根目录
     *
     * @param context 上下文
     * @return File
     */
    public static File createFile(Context context) {

        // Environment.getExternalStorageDirectory() 获取的是SD卡的根目录
        // File.separator为分隔符
        return new File(
                Environment.getExternalStorageDirectory() + File.separator + getApplicationName(context) + ".txt");
    }


}