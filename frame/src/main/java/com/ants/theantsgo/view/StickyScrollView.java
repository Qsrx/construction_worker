package com.ants.theantsgo.view;

import java.util.LinkedList;
import java.util.List;

import com.ants.theantsgo.R;
import com.ants.theantsgo.tool.ToolKit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

@SuppressLint({ "Recycle", "ClickableViewAccessibility" })
public class StickyScrollView extends ScrollView implements Pullable {
	private static final String STICKY = "sticky";
	private View mCurrentStickyView;
	private Drawable mShadowDrawable;
	/**
	 * 添加了Tag的控件列表
	 */
	private List<View> mStickyViews;
	private int mStickyViewTopOffset;
	private int defaultShadowHeight = 10;
	private float density;
	private boolean redirectTouchToStickyView;
	private int offset = 0;
	/**
	 * 当点击Sticky的时候，实现某些背景的渐变
	 */
	private Runnable mInvalidataRunnable = new Runnable() {
		@Override
		public void run() {
			if (mCurrentStickyView != null) {
				int left = mCurrentStickyView.getLeft();
				int top = mCurrentStickyView.getTop();
				int right = mCurrentStickyView.getRight();
				int bottom = getScrollY() + (mCurrentStickyView.getHeight() + mStickyViewTopOffset);
				invalidate(left, top, right, bottom);
			}
			postDelayed(this, 16);
		}
	};

	public StickyScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	@SuppressWarnings("deprecation")
	public StickyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		offset = ToolKit.dip2px(context, 48);
		mShadowDrawable = context.getResources().getDrawable(R.drawable.sticky_shadow_default);
		mStickyViews = new LinkedList<View>();
		density = context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 找到设置tag的View
	 * 
	 * @param viewGroup
	 */
	private void findViewByStickyTag(ViewGroup viewGroup) {
		int childCount = ((ViewGroup) viewGroup).getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);
			if (getStringTagForView(child).contains(STICKY)) {
				mStickyViews.add(child);
			}
			// 如果设置了Tag的子控件时布局，回调findViewByStickyTag()方法
			if (child instanceof ViewGroup) {
				findViewByStickyTag((ViewGroup) child);
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			findViewByStickyTag((ViewGroup) getChildAt(0));
		}
		showStickyView();
	}

	/**
	 * 滚动监听
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
		}
		showStickyView();
	}

	/**
	 * 显示设置了Tag的控件
	 */
	private void showStickyView() {
		View curStickyView = null;
		View nextStickyView = null;
		for (View v : mStickyViews) {
			int topOffset = v.getTop() - getScrollY();
			if (topOffset <= 0) {
				if (curStickyView == null || topOffset > curStickyView.getTop() - getScrollY()) {
					curStickyView = v;
				}
			} else {
				if (nextStickyView == null || topOffset < nextStickyView.getTop() - getScrollY()) {
					nextStickyView = v;
				}
			}
		}

		if (curStickyView != null) {
			mStickyViewTopOffset = nextStickyView == null ? 0
					: Math.min(0, nextStickyView.getTop() - getScrollY() - curStickyView.getHeight());
			mCurrentStickyView = curStickyView;
			post(mInvalidataRunnable);
		} else {
			mCurrentStickyView = null;
			removeCallbacks(mInvalidataRunnable);
		}
	}

	/**
	 * 获取控件的Tag
	 * 
	 * @param v
	 * @return
	 */
	private String getStringTagForView(View v) {
		Object tag = v.getTag();
		return String.valueOf(tag);
	}

	/**
	 * 将sticky画出来
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mCurrentStickyView != null) {
			// 先保存起来
			canvas.save();
			// 将坐标原点移动到(0, getScrollY() + mStickyViewTopOffset)
			canvas.translate(0, getScrollY() + mStickyViewTopOffset);

			if (mShadowDrawable != null) {
				int left = 0;
				int top = mCurrentStickyView.getHeight() + mStickyViewTopOffset;
				int right = mCurrentStickyView.getWidth();
				int bottom = top + (int) (density * defaultShadowHeight + 0.5f);
				mShadowDrawable.setBounds(left, top, right, bottom);
				mShadowDrawable.draw(canvas);
			}
			canvas.clipRect(0, mStickyViewTopOffset, mCurrentStickyView.getWidth(), mCurrentStickyView.getHeight());
			mCurrentStickyView.draw(canvas);

			// 重置坐标原点参数
			canvas.restore();
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			redirectTouchToStickyView = true;
		}

		if (redirectTouchToStickyView) {
			redirectTouchToStickyView = mCurrentStickyView != null;

			if (redirectTouchToStickyView) {
				redirectTouchToStickyView = ev.getY() <= (mCurrentStickyView.getHeight() + mStickyViewTopOffset)
						&& ev.getX() >= mCurrentStickyView.getLeft() && ev.getX() <= mCurrentStickyView.getRight();
			}
		}

		if (redirectTouchToStickyView) {
			ev.offsetLocation(0, -1 * ((getScrollY() + mStickyViewTopOffset) - mCurrentStickyView.getTop()));
		}
		return super.dispatchTouchEvent(ev);
	}

	private boolean hasNotDoneActionDown = true;

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (redirectTouchToStickyView) {
			ev.offsetLocation(0, ((getScrollY() + mStickyViewTopOffset) - mCurrentStickyView.getTop()));
		}

		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			hasNotDoneActionDown = false;
		}

		if (hasNotDoneActionDown) {
			MotionEvent down = MotionEvent.obtain(ev);
			down.setAction(MotionEvent.ACTION_DOWN);
			super.onTouchEvent(down);
			hasNotDoneActionDown = false;
		}

		if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
			hasNotDoneActionDown = true;
		}
		return super.onTouchEvent(ev);
	}

	// =====上拉加载下拉刷新===========��
	@Override
	public boolean canPullDown() {
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp() {
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

	// =====上拉加载下拉刷新===========��

	// 滑动监听
	public interface ScrollViewListener {
		void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy);
	}

	private ScrollViewListener scrollViewListener = null;

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();
			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {
				return false;
			}
		}
		return super.onInterceptTouchEvent(ev);
	}
}
