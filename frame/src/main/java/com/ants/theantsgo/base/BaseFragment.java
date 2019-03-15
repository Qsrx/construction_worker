package com.ants.theantsgo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.ants.theantsgo.R;
import com.ants.theantsgo.WeApplication;
import com.ants.theantsgo.systemBarUtil.ImmersionFragment;
import com.ants.theantsgo.tips.ToastTip;
import com.ants.theantsgo.util.L;
import com.lidroid.xutils.ViewUtils;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Map;


/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/5/29 0029
 * 时间：15:41
 * 描述：所有Fragment的父类(可以设置沉浸式)
 * immersionInit方法是初始化沉浸式的方法，
 * 如果不需要使用沉浸式，设置immersionEnabled方法返回值为flase即可
 * 此外还需注意，如果在碎片中使用沉浸式，需在Activity中先初始化，否则报错
 * ===============Txunda===============
 */
public abstract class BaseFragment extends ImmersionFragment implements BaseView {

    private FrameLayout content;
    private View progress;
    private View error;
    private ProgressDialog progressDialog;

    protected WeApplication application;

    private boolean isShowContent; // 当出错时标识是否显示错误页面
    protected boolean hasAnimiation = true; // 启动页面时是否带启动动画
    private ImageView loading_gifview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 向用户展示信息前的准备工作在这个方法里处理
        preliminary();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.aty_base, container, false);
        initControls(layout);
        setBasicContentView(getLayoutResId());
        ViewUtils.inject(this, layout);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 请求数据
        requestData();
    }

    @Override
    public void onResume() {
        super.onResume();
        showStatus("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        showStatus("onStop");
    }

    private void showStatus(String status) {
        Log.d("=====BaseFragment=====", String.format("%s %s", this.getClass().getName(), status));
    }

    /**
     * 初始化控件
     */
    private void initControls(View layout) {
        content = (FrameLayout) layout.findViewById(R.id.content);
        // 给最底层的layout设置一个点击监听防止切换页面之后还会点击到别的页面的BUG
        content.setOnClickListener(null);
        progress = View.inflate(getActivity(), R.layout.loading_content, null);
//        loading_gifview = (ImageView) progress.findViewById(R.id.loading_gifview);
        error = View.inflate(getActivity(), R.layout.layout_error, null);
        error.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                requestData();
                content.removeView(error);
            }
        });
        progressDialog = new ProgressDialog(getActivity(), R.style.loading_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 设置content的子布局
     */
    private void setBasicContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResId, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        content.addView(view);
    }

    /**
     * 向用户展示信息前的准备工作在这个方法里处理
     */
    private void preliminary() {
        application = (WeApplication) getActivity().getApplication();
        // 初始化数据
        initialized();
    }

    /**
     * 用于设置页面布局
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化数据
     */
    protected abstract void initialized();

    protected abstract void requestData();

    public BaseActivity getContext() {
        return (BaseActivity) getActivity();
    }

    /**
     * 点击事件
     *
     * @param v 被点击的View
     */
    public void onClick(View v) {
    }

    /**
     * ListView,GridView的Item点击事件
     *
     * @param parent   AdapterView
     * @param view     View
     * @param position 下标
     * @param id       id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    // =========== 启动Activity =============

    /**
     * 启动一个Activity
     *
     * @param className 将要启动的Activity的类名
     * @param options   传到将要启动Activity的Bundle，不传时为null
     */
    protected void startActivity(Class<?> className, Bundle options) {
        Intent intent = new Intent(getActivity(), className);
        if (options != null) {
            intent.putExtras(options);
        }
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (hasAnimiation) {
            getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        }
    }

    // ================ 信息提示方式 ===================

    /**
     * 提示信息
     *
     * @param text 提示内容
     */
    protected void showToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }

    private ToastTip tipsToast;

    /**
     * 自定义toast
     *
     * @param iconResId 图片
     * @param tips      提示文字
     */
    protected void showTips(int iconResId, String tips) {
        if (tipsToast == null) {
            tipsToast = ToastTip.makeText(getActivity(), tips, ToastTip.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }

    /**
     * 正确提示
     *
     * @param tips 信息
     */
    protected void showRightTip(String tips) {
        showTips(R.mipmap.icon_right_tip, tips);
    }

    /**
     * 错误提示
     *
     * @param tips 信息
     */
    protected void showErrorTip(String tips) {
        showTips(R.mipmap.icon_error_tip, tips);
    }

    // ===================== 加载条 ==================

    private boolean isShowing = false;

    protected void showProgressContent() {
        isShowContent = true;
        isShowing = true;
        content.addView(progress);
    }

    protected void removeProgressContent() {
        if (isShowing) {
            isShowing = false;
            content.removeView(progress);
        }
    }

    protected void showProgressDialog() {
        isShowContent = false;
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.loading_dialog);
        }
    }

    /**
     * 进度条
     *
     * @param title 设置进度条底部的文字
     */
    protected void showProgressDialog(String title) {
        isShowContent = false;
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            View views = LayoutInflater.from(getActivity()).inflate(R.layout.loading_dialog, null);
            TextView loading_tv_content = (TextView) views.findViewById(R.id.loading_tv_content);
            loading_tv_content.setText(title);
            progressDialog.setContentView(views);
        }
    }

    protected void removeProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    // ========== API回调方法 ==========

    @Override
    public void onStarted() {

    }

    @Override
    public void onCancelled() {
        removeContent();
        removeDialog();
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void showDialog(String text) {
        showProgressDialog(text);
    }

    @Override
    public void showContent() {
        showProgressContent();
    }

    @Override
    public void removeDialog() {
        removeProgressDialog();
    }

    @Override
    public void removeContent() {
        removeProgressContent();
    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        removeContent();
        removeDialog();
    }

    @Override
    public void onError(String requestUrl, Map<String, String> error) {
        String tips = error.get("message");
        showErrorTip(tips);
        removeContent();
        removeDialog();
    }

    @Override
    public void onException(Exception exception) {
        if (isShowContent) {
            content.addView(error);
        } else {
            if (exception instanceof SocketException || exception instanceof InterruptedIOException || exception
                    instanceof UnknownHostException || exception instanceof UnknownServiceException) {
                showToast(getString(R.string.network_anomaly));
            } else if (exception instanceof NullPointerException) {
                L.e("===exception===",exception.getMessage().toString());
                showToast(getString(R.string.string_null_error));
            } else {
                showToast(getString(R.string.server_unknow_error));
            }
        }
        exception.printStackTrace();
        removeProgressContent();
        removeProgressDialog();
    }

    @Override
    public void onLoading(long total, long current, boolean isUploading) {

    }
}