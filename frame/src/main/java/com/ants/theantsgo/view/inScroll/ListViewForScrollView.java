package com.ants.theantsgo.view.inScroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by DUKE_HwangZj
 * 2017/4/19 0019  12:47
 * 解决ScrollView嵌套ListView的滑动冲突
 */

public class ListViewForScrollView extends ListView  {
    public ListViewForScrollView(Context context) {
        super(context);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        super.setOnScrollListener(l);
    }
}
