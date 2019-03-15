package com.ants.theantsgo.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ants.theantsgo.AppManager;
import com.ants.theantsgo.R;
import com.ants.theantsgo.WeApplication;
import com.ants.theantsgo.systemBarUtil.ImmersionBar;
import com.ants.theantsgo.tips.ToastTip;
import com.ants.theantsgo.tool.GlideCacheUtil;
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
 * 描述：所有Activity的父类
 * ===============Txunda===============
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private FrameLayout content;
    private View progress;
    private View error;
    private ProgressDialog progressDialog;

    /**
     * 项目的application
     */
    protected WeApplication application;

    /**
     * 当出错时标识是否显示错误页面
     */
    private boolean isShowContent;
    /**
     * 启动页面时是否带启动动画
     */
    protected boolean hasAnimiation = true;

    /**
     * 是否使用沉浸式,如果不使用，需在
     * super.onCreate(savedInstanceState)之前设置为flase
     * 默认设置
     */
    public boolean changeStatusBar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.aty_base);
        //添加该activity到栈中(管理activity)
        AppManager.getInstance().addActivity(this);
        //初始化控件
        initControls();
        setBasicContentView(getLayoutResId());
        ViewUtils.inject(this);

        //向用户展示信息前的准备工作在这个方法中处理
        preliminary();
        // 清掉图片缓存
        GlideCacheUtil.getInstance().clearImageAllCache(getApplicationContext());
        if (changeStatusBar) {
            ImmersionBar.with(this).init();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().killActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 点击页面空白处时，让键盘消失
     *
     * @param event 事件
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null) {
                if (getCurrentFocus().getWindowToken() != null) {
                    mInputMethodManager.hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 初始化控件
     */
    private void initControls() {
        content = (FrameLayout) findViewById(R.id.content);
        // 全屏遮盖的网路提示框
        progress = View.inflate(this, R.layout.loading_content, null);
        // 错误提示框
        error = View.inflate(this, R.layout.layout_error, null);
        error.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestData();
                content.removeView(error);
            }
        });
        progressDialog = new ProgressDialog(this, R.style.loading_dialog);// 初始化progressDialog，设置样式
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);// 点击返回消失
        progressDialog.setCanceledOnTouchOutside(false);// 点击外部消失
    }

    /**
     * 设置content的子布局<br>子布局在基础布局中的显示宽高</br>
     */
    private void setBasicContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inflater.inflate(layoutResId, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                .MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        main.setLayoutParams(layoutParams);
        content.addView(main);
    }

    /**
     * 向用户展示信息前的准备工作在这个方法里处理
     */
    private void preliminary() {
        application = (WeApplication) getApplication();
        // 初始化数据
        initialized();
        // 请求数据
        requestData();
    }

    /**
     * 用于设置页面布局
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化数据
     */
    protected abstract void initialized();

    /**
     * 请求数据，需要写 showProgressContent / showProgressDialog <br/>
     * 若调用showProgressContent的情况下出现联网失败则会显示ERROR界面
     */
    protected abstract void requestData();

    /**
     * view的点击事件
     *
     * @param v View
     */
    public void onClick(View v) {
    }

    /**
     * listView,Gridview的点击事件
     *
     * @param parent   Adapter
     * @param view     View
     * @param position 下标
     * @param id       id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * 页面以外销毁时保存本页面当前状态
     *
     * @param outState 保存的数据
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // ============================== 启动Activity ==============================

    /**
     * 启动一个Activity
     *
     * @param className 将要启动的Activity的类名
     * @param options   传到将要启动Activity的Bundle，不传时为null
     */
    protected void startActivity(Class<?> className, Bundle options) {
        Intent intent = new Intent(this, className);
        if (options != null) {
            intent.putExtras(options);
        }
        startActivity(intent);
    }

    /**
     * 启动一个有会返回值的Activity
     *
     * @param className   将要启动的Activity的类名
     * @param options     传到将要启动Activity的Bundle，不传时为null
     * @param requestCode 请求码
     */
    protected void startActivityForResult(Class<?> className, Bundle options, int requestCode) {
        Intent intent = new Intent(this, className);
        if (options != null) {
            intent.putExtras(options);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 启动一个Activity
     *
     * @param intent intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (hasAnimiation) {//根据hasAnimiation的值来判断数否带有转换动画
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        }
    }

    /**
     * 页面销毁时动画
     */
    @Override
    public void finish() {
        super.finish();
        if (hasAnimiation) {//根据hasAnimiation的值来判断数否带有转换动画
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        }
    }

// ====================== 加载条 ==========================

    /**
     * 是否显示错误页面
     */
    private boolean isShowing = false;

    /**
     * 调用此方法，这个页面会被覆盖
     */
    protected void showProgressContent() {
        isShowContent = true;
        isShowing = true;
        content.addView(progress);
    }

    /**
     * 移除上面的进度页面
     */
    protected void removeProgressContent() {
        if (isShowing) {
            isShowing = false;
            isShowContent = false;
            content.removeView(progress);
        }
    }

    /**
     * 弹出一个显示进度的dialog
     */
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
            View views = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null);
            TextView loading_tv_content = (TextView) views.findViewById(R.id.loading_tv_content);
            loading_tv_content.setText(title);
            progressDialog.setContentView(views);
        }
    }

    /**
     * 移除掉上面那个dialog
     */
    protected void removeProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    //==============API调取=================

    @Override
    public void onStarted() {

    }

    /**
     * 网络请求取消
     */
    @Override
    public void onCancelled() {
        removeContent();
        removeDialog();
    }

    /**
     * 请求中
     *
     * @param total       总大小
     * @param current     已完成大小
     * @param isUploading isUploading
     */
    @Override
    public void onLoading(long total, long current, boolean isUploading) {

    }

    @Override
    public void showContent() {
        showProgressContent();
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
    public void removeContent() {
        removeProgressContent();
    }

    @Override
    public void removeDialog() {
        removeProgressDialog();
    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        removeContent();
        removeDialog();
    }

    @Override
    public void onError(String requestUrl, Map<String, String> error) {
        removeContent();
        removeDialog();
        String tips = error.get("message");
        showErrorTip(tips);
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
                L.e("===exception===",exception.toString());
                showToast(getString(R.string.server_unknow_error));
            }
        }
        L.e("=====网络未连接=====", exception.toString());
        exception.printStackTrace();
        removeContent();
        removeDialog();
    }

    // =======提示框，并非Dialog================ToastTip===========
    protected void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }
    protected void showToast(int text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }
    private ToastTip tipsToast;

    protected void showTip(int iconResId, String tips) {
        if (tipsToast == null) {
            tipsToast = ToastTip.makeText(this, tips, Toast.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }

    /**
     * 正确提示
     *
     * @param tips 提示内容
     */
    protected void showRightTip(String tips) {
        showTip(R.mipmap.icon_right_tip, tips);
    }

    /**
     * 错误提示
     *
     * @param tips 提示内容
     */
    protected void showErrorTip(String tips) {
        showTip(R.mipmap.icon_error_tip, tips);
    }

    // ==========EditView长按事件=============
    final int Menu_1 = Menu.FIRST;
    final int Menu_2 = Menu.FIRST + 1;
    final int Menu_3 = Menu.FIRST + 2;

    private ClipboardManager mClipboard = null;

    /**
     * 创建ContextMenu菜单的回调方法
     *
     * @param m        ContextMenu
     * @param v        View
     * @param menuInfo ContextMenuInfo
     */
    public void onCreateContextMenu(ContextMenu m, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(m, v, menuInfo);
        // 在上下文菜单中添加选项内容
        // add方法参数:add(分组Id,itemid,排序,菜单文字)
        m.add(1, Menu_1, 0, "复制");
        m.add(1, Menu_2, 0, "粘贴");
        m.add(1, Menu_3, 0, "全选");
    }

    /**
     * 复制文字
     */
    public void copyFromEditText1(EditText Et) {
        // Gets a handle to the clipboard service.
        // 获取剪切板
        if (null == mClipboard) {
            mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        // Creates a new text clip to put on the clipboard
        // 创建一个新的文本剪辑放在剪贴板上
        ClipData clip = ClipData.newPlainText("simple text", Et.getText());
        // Set the clipboard's primary clip.
        // 设置剪贴板的主剪辑
        mClipboard.setPrimaryClip(clip);
    }

    /**
     * 黏贴
     */
    @SuppressLint("NewApi")
    public void pasteToResult(Context context, EditText Et) {
        // Gets a handle to the clipboard service.
        // 获取剪切板
        if (null == mClipboard) {
            mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        String resultString = "";
        // 检查剪贴板是否有内容
        if (!mClipboard.hasPrimaryClip()) {
            showTip(R.mipmap.icon_error_tip, "内容为空");
        } else {
            ClipData clipData = mClipboard.getPrimaryClip();
            int count = clipData.getItemCount();
            for (int i = 0; i < count; ++i) {
                ClipData.Item item = clipData.getItemAt(i);
                CharSequence str = item.coerceToText(context);
                resultString += str;
            }
        }
        showTip(R.mipmap.icon_right_tip, "粘贴成功");
        Et.setText(resultString);
    }




}
