package com.ants.theantsgo;

import android.content.Context;

import com.ants.theantsgo.config.Settings;
import com.ants.theantsgo.util.L;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 2:52
 * 描述：Glide缓存管理
 * ===============Txunda===============
 */
public class GlideCache implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));//设置内存缓存大小

        // 设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        String downloadDirectoryPath = Settings.cacheCompressPath;// 设置缓存路径
        int cacheSize = 100 * 1000 * 1000;// 设置缓存的大小为100M
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

        L.e("=====设置的缓存路径=====", downloadDirectoryPath);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, downloadDirectoryPath, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

}
