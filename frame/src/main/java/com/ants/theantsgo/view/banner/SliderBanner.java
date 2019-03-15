package com.ants.theantsgo.view.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ants.theantsgo.R;

/**
 * 自动滚动Banner <br/>
 * <p>
 * <pre>
 *  &lt;com.ants.theantsgo.view.banner.SliderBanner
 *    android:layout_width="match_parent"
 *    android:layout_height="wrap_content"
 *    app:slider_banner_indicator="@+id/dotview"
 *    app:slider_banner_pager="@+id/viewpager"
 *    app:slider_banner_time_interval="8000" &gt;
 *
 *    &lt;android.support.v4.view.ViewPager
 *      android:id="@+id/viewpager"
 *      android:layout_width="match_parent"
 *      android:layout_height="wrap_content" &gt;
 *    &lt;/android.support.v4.view.ViewPager&gt;
 *
 *      &lt;com.ants.theantsgo.view.banner.DotView
 *          android:id="@+id/dotview"
 *          android:layout_width="match_parent"
 *          android:layout_height="wrap_content"
 *          android:layout_alignParentBottom="true"
 *          android:gravity="center"
 *          app:dot_radius="5dp"
 *          app:dot_selected_color="#ffffff"
 *          app:dot_span="8dp"
 *          app:dot_unselected_color="#80ffff" &gt;
 *      &lt;/com.ants.theantsgo.view.banner.DotView&gt;
 * &lt;/com.ants.theantsgo.view.banner.SliderBanner&gt;
 *
 * </pre>
 *
 * @author Zero
 *         <p>
 *         2015年1月7日
 */
public class SliderBanner extends RelativeLayout {

    private ViewPager mViewPager;
    private BannerAdapter mBannerAdapter;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    private PagerIndicator mPagerIndicator;
    private AutoPlayer mAutoPlayer;

    protected int mIdForViewPager;
    protected int mIdForIndicator;
    protected int mTimeInterval = 2000;

    public SliderBanner(Context context) {
        this(context, null);
    }

    public SliderBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.SliderBanner, 0, 0);
        if (arr != null) {
            if (arr.hasValue(R.styleable.SliderBanner_slider_banner_pager)) {
                mIdForViewPager = arr.getResourceId(R.styleable.SliderBanner_slider_banner_pager, 0);
            }
            if (arr.hasValue(R.styleable.SliderBanner_slider_banner_indicator)) {
                mIdForIndicator = arr.getResourceId(R.styleable.SliderBanner_slider_banner_indicator, 0);
            }
            mTimeInterval = arr.getInt(R.styleable.SliderBanner_slider_banner_time_interval, mTimeInterval);
            arr.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        mViewPager = (ViewPager) findViewById(mIdForViewPager);
        mPagerIndicator = (DotView) findViewById(mIdForIndicator);

        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(i, v, i2);
                }
            }

            @Override
            public void onPageSelected(int position) {

                if (mPagerIndicator != null) {
                    mPagerIndicator.setSelected(mBannerAdapter.getPositionForIndicator(position));
                }
                mAutoPlayer.skipNext();

                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        });

        mAutoPlayer = new AutoPlayer(mGalleryPlayable).setPlayRecycleMode(AutoPlayer.PlayRecycleMode.play_back);
        mAutoPlayer.setTimeInterval(mTimeInterval);
        super.onFinishInflate();
    }

    public void setTimeInterval(int interval) {
        mAutoPlayer.setTimeInterval(interval);
    }

    public void setAdapter(BannerAdapter adapter) {
        mBannerAdapter = adapter;
        mViewPager.setAdapter(adapter);
    }

    public void beginPlay() {
        mAutoPlayer.play();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    public void setDotNum(int num) {
        if (mPagerIndicator != null) {
            mPagerIndicator.setNum(num);
        }
    }

    private AutoPlayer.Playable mGalleryPlayable = new AutoPlayer.Playable() {

        @Override
        public void playTo(int to) {
            mViewPager.setCurrentItem(to, true);
        }

        @Override
        public void playNext() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }

        @Override
        public void playPrevious() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        }

        @Override
        public int getTotal() {
            return mBannerAdapter.getCount();
        }

        @Override
        public int getCurrent() {
            return mViewPager.getCurrentItem();
        }
    };

}
