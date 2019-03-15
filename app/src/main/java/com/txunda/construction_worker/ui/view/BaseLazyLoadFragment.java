package com.txunda.construction_worker.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/25 025 14:03:17.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public abstract class BaseLazyLoadFragment extends Fragment {

    private boolean isFirstLoad = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = initView(inflater, container);//让子类实现初始化视图

        initEvent();//初始化事件

        isFirstLoad = true;//视图创建完成，将变量置为true

        if (getUserVisibleHint()) {//如果Fragment可见进行数据加载
            onLazyLoad();
            isFirstLoad = false;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = false;//视图销毁将变量置为false
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoad && isVisibleToUser) {//视图变为可见并且是第一次加载
            onLazyLoad();
            isFirstLoad = false;
        }

    }
    //数据加载接口，留给子类实现
    public abstract void onLazyLoad();

    //初始化视图接口，子类必须实现
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    //初始化事件接口，留给子类实现
    public abstract void initEvent();

}