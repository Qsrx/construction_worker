package com.txunda.construction_worker.ui.aty;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ants.theantsgo.util.PreferencesUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 引导页
 */
@Layout(R.layout.aty_guide_page)
public class GuidePageAty extends BaseAty {
    @BindView(R.id.vp)
    ViewPager vp;
    private List<ImageView> list;
    // 滑动
    private int lastValut = -1;
    private boolean isScrolling = false;
    // 判断只走一次
    private int count = 0;
    private ViewPagerAdapter adapter;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        int[] pic = new int[]{R.drawable.img_guide_page1, R.drawable.img_guide_page2, R.drawable.img_guide_page3};
        list = new ArrayList<>();
        adapter = new ViewPagerAdapter();
        for (Integer i : pic) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(i);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        list.get(list.size()-1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtils.putBoolean(me, "is_first_time", false);
                jump(LoginAty.class);
                finish();
            }
        });
        vp.setAdapter(adapter);
    }
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 初始化posotion位置
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);
            return list.get(position);
        }

        /**
         * 判断是不是对象生成
         *
         * @param view
         * @param o
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }
}