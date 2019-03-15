package com.txunda.construction_worker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/11/10 010 11:36:31.
 * 功能描述：禁止滑动
 * 联系方式： jingjie.office@qq.com
 */
public class NoScrollListview extends ListView {
    public NoScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
