package com.ants.theantsgo.view.drawabletextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 一个让图片资源和文本都居中的TextView
 *
 * @author Txunda_HZj
 *         <p>
 *         2017年3月24日上午9:57:41
 */
public class DrawableTextView extends TextView {
    public DrawableTextView(Context context) {
        this(context, null);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取到上下左右的图片资源
        Drawable[] drawables = getCompoundDrawables();

        if (null != drawables) {
            Drawable drawableLeft = drawables[0];
            Drawable drawableRight = drawables[2];
            // 文字宽度
            float textWidth = getPaint().measureText(getText().toString());
            if (null != drawableLeft) {
                setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                // 内容区域( 文本内容的宽度+间隔+图片资源的宽度)
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableLeft.getIntrinsicWidth();
                // 向X轴的正方向平移
                canvas.translate((getWidth() - contentWidth) / 2 - getCompoundDrawablePadding(), 0);
            }
            if (null != drawableRight) {
                // xml中不必设置Gravity，右边图片必须要设置Graviey为End，否则translate后文字看不到
                setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableRight.getIntrinsicWidth();
                // 向X轴的负方向平移
                canvas.translate(-(getWidth() - contentWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
