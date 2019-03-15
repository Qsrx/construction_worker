package com.txunda.construction_worker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.tips.MikyouCommonDialog;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.UpdateBean;
import com.txunda.construction_worker.ui.aty.LoginAty;
import com.txunda.construction_worker.ui.fgt.ClassFgt;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.ui.fgt.ItemBankFgt;
import com.txunda.construction_worker.ui.fgt.MyFgt;
import com.txunda.construction_worker.ui.view.DownLoadManager;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.txunda.construction_worker.utils.VersionTools;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑时间：2018.12.22
 * 开发者：hzy
 * 功能模块：主界面
 * 联系方式：win_hzy@163.com
 */
@Layout(R.layout.aty_main)
public class MainAty extends BaseAty {

    @BindView(R.id.aty_main_vp)
    FrameLayout atyMainVp;
    @BindView(R.id.aty_main_rg)
    RadioGroup atyMainRg;
    RadioButton[] rb;
    FragmentTransaction ft;
    ArrayList<Fragment> list;
    FragmentManager fragmentManager;
    public static String token = "";
    int index;
    @BindView(R.id.aty_main_v)
    View atyMainV;

    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        //动态申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        }
        token = Preferences.getInstance().getString(this, "construction", "token");
        Log.d("maintoken", "initViews: ==========" + token);
        //koten为无数据则表示是游客登录
        if (token.equals("")){
            atyMainV.setVisibility(View.VISIBLE);
        }
        list = new ArrayList<>();
        //添加Fragment
        list.add(new HomeFgt());
        list.add(new ClassFgt());
        list.add(new ItemBankFgt());
        list.add(new MyFgt());
        int childCount = atyMainRg.getChildCount();
        rb = new RadioButton[atyMainRg.getChildCount()];
        for (int i = 0; i < rb.length; i++) {
            rb[i] = (RadioButton) atyMainRg.getChildAt(i);
        }
        rb[0].setChecked(true);
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.add(R.id.aty_main_vp, list.get(0));
        ft.commit();
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                rb[0].setTextColor(Color.parseColor("#FFDA0F"));
            } else {
                rb[i].setTextColor(Color.parseColor("#999999"));
            }
        }

    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpUpdata();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyMainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                for (int j = 0; j < rb.length; j++) {
                    if (rb[j].getId() == i) {
                        rb[j].setTextColor(Color.parseColor("#FFDA0F"));
                        if (list.get(j).isAdded()) {
                            ft.show(list.get(j)).hide(list.get(index)).commit();
                        } else {
                            ft.add(R.id.aty_main_vp, list.get(j)).hide(list.get(index)).commit();
                        }
                        index = j;
                    } else {
                        rb[j].setTextColor(Color.parseColor("#999999"));
                    }

                }
            }
        });

    }

    /**
     * 动态申请权限
     */
    private void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getApplicationContext(), "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(me, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
        }
    }

    @OnClick(R.id.aty_main_v)
    public void onViewClicked() {
        jump(LoginAty.class);
        finish();
    }
    // ----------------------------------版本更新--------------------------------
    private int versionCode=1;
    private String app_url="";
    private void httpUpdata() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Upgrade/updateAndroid", new Parameter()
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("updatabean", "onResponse: ==========="+response);
                        UpdateBean bean = GsonUtil.GsonToBean(response, UpdateBean.class);
                        versionCode = VersionTools.getVersionName(me);
                        int num= Integer.parseInt(bean.getData().getApp_version());
                        if (num>versionCode){
                            app_url = bean.getData().getApp_logo();
                            showDialogToUpDataApp();
                         }
                    }
                });

    }
    /**
     * 提示更新弹窗
     */
    private void showDialogToUpDataApp() {
        new MikyouCommonDialog(this, "有新版本请更新", "提示", "取消", "更新").setOnDiaLogListener(
                new MikyouCommonDialog.OnDialogListener() {
                    @Override
                    public void dialogListener(int btnType, View customView, DialogInterface dialogInterface,
                                               int which) {
                        switch (btnType) {
                            case MikyouCommonDialog.NO:// 更新
                                downLoadApk(app_url);
                                break;
                            case MikyouCommonDialog.OK:// 取消
                                break;
                            default:
                                break;
                        }
                    }
                }).showDialog();
    }

    /**
     * 下载App
     *
     * @param url 下载链接
     */
    protected void downLoadApk(final String url) {
        final ProgressDialog pd; // 进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = DownLoadManager.getFileFromServer(url, pd);
                    sleep(3000);
                    installApk(file); // 安装apk
                    pd.dismiss(); // 结束掉进度条对话框
                } catch (Exception e) {
                    showErrorTip("安装出错");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 安装App
     *
     * @param file App文件
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        // 执行动作
        intent.setAction(Intent.ACTION_VIEW);
        // 执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        startActivity(intent);
    }
    //
}