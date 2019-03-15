package com.ants.theantsgo.tool;

import android.content.Context;
import android.widget.ImageView;

import com.ants.theantsgo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/5 0005
 * 时间：10:03
 * 描述：Glide加载图片工具类
 * ===============Txunda===============
 */

public class GlideUtils {

    /**
     * 默认加载图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView ImageView
     */
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
    }

    /**
     * 加载指定大小的图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param width      宽度
     * @param height     高度
     * @param mImageView ImageView
     */
    public static void loadImageView(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).override(width,
                height).into(mImageView);
    }

    /**
     * 有占位图的图片加载
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView ImageView
     * @param isShow     是否显示占位图
     */
    public static void loadImageView(Context mContext, String path, ImageView mImageView, boolean isShow) {
        if (isShow) {
            Glide.with(mContext).load(path).placeholder(R.drawable.ic_default).error(R.drawable.ic_default)
                    .centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        } else {
            loadImageView(mContext, path, mImageView);
        }
    }

    /**
     * 加载Gif格式的图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView ImageView
     */
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asGif().placeholder(R.drawable.ic_default).error(R.drawable.ic_default)
                .thumbnail(0.1f).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
    }

    /**
     * 加载Gif格式的图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param width      宽度
     * @param height     高度
     * @param mImageView ImageView
     */
    public static void loadImageViewDynamicGif(Context mContext, String path, int width, int height, ImageView
            mImageView) {
        Glide.with(mContext).load(path).asGif().placeholder(R.drawable.ic_default).error(R.drawable.ic_default)
                .thumbnail(0.1f).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).override(width, height)
                .into(mImageView);
    }
}
