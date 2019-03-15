package com.ants.theantsgo.util;

import android.os.Environment;

import java.io.File;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/7/1 0001
 * 时间：上午 10:06
 * 描述：文件管理
 * ===============Txunda===============
 */
public class FileManager {

    /**
     * 项目在SD卡的根目录下创建的文件夹
     */
    public static final String SDCARD_FOLDER_NAME = "Txunda";

    /**
     * 获取缓存文件路径
     *
     * @return String
     */
    public static String getSaveFilePath() {
        return getRootFilePath() + SDCARD_FOLDER_NAME + File.separator + "picture_cache" + File.separator;
    }

    /**
     * 获取压缩文件临时存储路径
     *
     * @return String
     */
    public static String getCompressFilePath() {
        return getRootFilePath() + SDCARD_FOLDER_NAME + File.separator + "compress_cache" + File.separator;
    }

    /**
     * 获取压缩文件临时存储路径
     *
     * @return String
     */
    public static String getCrashLogFilePath() {
        return getRootFilePath() + SDCARD_FOLDER_NAME + File.separator + "crash_log" + File.separator;
    }

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    public static String getRootFilePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath:
            // /data/data/
        }
    }

    /**
     * 获取系统相册路径
     *
     * @return String 相册路径
     */
    public static String getDCIMPath() {
        if (hasSDCard())
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        else
            return null;
    }

}
