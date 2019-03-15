package com.ants.theantsgo.tools;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

/**
 * 获取手机的一些信息
 * 
 * @author Txunda_HZj
 *
 *         2017年3月13日下午4:53:18
 */
public class GetPhoneInfo {

	/**
	 * 是不是有浏览器
	 * 
	 * @param context
	 * @return
	 */
	public static ActivityInfo getBrowserApp(Context context) {
		String default_browser = "android.intent.category.DEFAULT";
		String browsable = "android.intent.category.BROWSABLE";
		String view = "android.intent.action.VIEW";

		Intent intent = new Intent(view);
		intent.addCategory(default_browser);
		intent.addCategory(browsable);
		Uri uri = Uri.parse("http://");
		intent.setDataAndType(uri, null);

		// 找出手机当前安装的所有浏览器程序
		List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent,
				PackageManager.GET_INTENT_FILTERS);
		if (resolveInfoList.size() > 0) {
			ActivityInfo activityInfo = resolveInfoList.get(0).activityInfo;
			String packageName = activityInfo.packageName;
			String className = activityInfo.name;
			return activityInfo;
		} else {
			return null;
		}
	}

}
