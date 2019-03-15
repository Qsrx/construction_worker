package com.txunda.construction_worker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/27 027 15:33:16.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ExpandableListViewForScrollView extends ExpandableListView {
    public ExpandableListViewForScrollView(Context context) {
        super(context);
    }

    public ExpandableListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableListViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写onMeasure方法，重新计算高度，达到使ExpandableListView适应ScrollView的效果
     *
     * @param widthMeasureSpec  宽度测量规则
     * @param heightMeasureSpec 高度测量规则
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Integer.MAX_VALUE:表示int类型能够表示的最大值，值为2的31次方-1
        //>>2:右移N位相当于除以2的N的幂
        //MeasureSpec.AT_MOST：子布局可以根据自己的大小选择任意大小的模式
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }
}
