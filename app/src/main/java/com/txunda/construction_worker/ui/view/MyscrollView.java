package com.txunda.construction_worker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/24 024 14:15:40.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MyscrollView extends ScrollView {
    public MyscrollView(Context context) {
        super(context);
    }

    public MyscrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyscrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    View view;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            LinearLayout v = (LinearLayout) getChildAt(0);
            if (v != null) {
                for (int i = 0; i < v.getChildCount(); i++) {
                    if (v.getChildAt(i).getTag() != null && ((String) v.getChildAt(i).getTag()).equals("aaa")) {
                        view = v.getChildAt(i);
                        break;
                    }
                }
            }
        }
    }
}