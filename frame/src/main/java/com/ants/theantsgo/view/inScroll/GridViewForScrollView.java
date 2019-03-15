package com.ants.theantsgo.view.inScroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by DUKE_HwangZj
 * 2017/4/19 0019  13:47
 * 解决ScollView嵌套GridView的滑动冲突
 */

public class GridViewForScrollView extends GridView {
    public GridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScrollView(Context context) {
        super(context);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}