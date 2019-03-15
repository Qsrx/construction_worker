package com.ants.theantsgo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：上午 11:20
 * 描述：图片压缩
 * ===============Txunda===============
 */
public class CompressionUtil {

    /**
     * 图片压缩
     *
     * @param originalPath SD卡上的图片路径
     * @return String 压缩后的图片路径
     */
    public static String compressionBitmap(String originalPath) {
        try {
            Bitmap bmp = decodeSampledBitmapFromFile(originalPath);
            String newName = DigestUtils.md5(String.valueOf(SystemClock.currentThreadTimeMillis())) + ".jpg";
            File f = new File(FileManager.getCompressFilePath() + newName);
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            if (bmp != null)
                bmp.compress(Bitmap.CompressFormat.JPEG, 50, fOut);
            fOut.flush();
            fOut.close();
            return f.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片压缩
     *
     * @param pathName 图片路径
     * @return Bitmap 图片压缩成Bitmap
     */
    private static Bitmap decodeSampledBitmapFromFile(String pathName) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calcilateInSampleSizeTwo(options);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return src;
    }

    private static int calcilateInSampleSize(BitmapFactory.Options options) {
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (width > 4000) {
            inSampleSize = 9;
        } else if (width > 3000 && width <= 4000) {
            inSampleSize = 7;
        } else if (width > 2000 && width <= 3000) {
            inSampleSize = 5;
        } else if (width > 1000 && width <= 2000) {
            inSampleSize = 3;
        } else if (width > 800 && width < 1000) {
            inSampleSize = 2;
        }
        return inSampleSize;
    }
    private static int calcilateInSampleSizeTwo(BitmapFactory.Options options) {
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (width > 4000) {
            inSampleSize = 3;
        } else if (width > 3000 && width <= 4000) {
            inSampleSize = 3;
        } else if (width > 2000 && width <= 3000) {
            inSampleSize = 3;
        } else if (width > 1000 && width <= 2000) {
            inSampleSize = 3;
        } else if (width > 800 && width < 1000) {
            inSampleSize = 2;
        }
        L.e("===inSampleSize===",inSampleSize+"");
        return inSampleSize;
    }

}
