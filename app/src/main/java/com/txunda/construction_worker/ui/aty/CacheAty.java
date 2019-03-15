package com.txunda.construction_worker.ui.aty;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.view.ExpandableListViewForScrollView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JzvdStd;

/**
 * 缓存界面
 */
@Layout(R.layout.aty_cache)
public class CacheAty extends BaseAty {

    @BindView(R.id.aty_course_selection_jz)
    JzvdStd atyCourseSelectionJz;
    @BindView(R.id.aty_course_selection_back)
    ImageView atyCourseSelectionBack;
    @BindView(R.id.aty_course_selection_save)
    TextView atyCourseSelectionSave;
    @BindView(R.id.aty_course_selection_iv_down)
    ImageView atyCourseSelectionIvDown;
    @BindView(R.id.aty_course_selection_iv_share)
    ImageView atyCourseSelectionIvShare;
    @BindView(R.id.aty_course_selection_rb_sc)
    RadioButton atyCourseSelectionRbSc;
    @BindView(R.id.expend_list)
    ExpandableListViewForScrollView expendList;
    @BindView(R.id.aty_cache_size)
    TextView atyCacheSize;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
//        expendList.setAdapter(new MyExtendableListViewAdapter());
        show();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
//        atyCacheSize.setText("总内存"+getTotalMemory()+",可用空间"+getAvailMemory());
    }

    @OnClick({R.id.aty_course_selection_back, R.id.aty_course_selection_iv_down, R.id.aty_course_selection_iv_share, R.id.aty_course_selection_rb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_course_selection_back:
                finish();
                break;
            case R.id.aty_course_selection_iv_down:
                break;
            case R.id.aty_course_selection_iv_share:
                jump(ShardAty.class);
                break;
            case R.id.aty_course_selection_rb_sc:
                break;
        }
    }

    private String getAvailMemory() {// 获取android当前可用内存大小

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(getBaseContext(), mi.availMem);// 将获取的内存大小规格化
    }

    private String getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void show(){
        Method _readProclines = null;
        try {
            Class procClass;
            procClass = Class.forName("android.os.Process");
            Class parameterTypes[]= new Class[] {String.class, String[].class, long[].class };
            _readProclines = procClass.getMethod("readProcLines", parameterTypes);
            Object arglist[] = new Object[3];
            final String[] mMemInfoFields = new String[] {"MemTotal:",
                    "MemFree:", "Buffers:", "Cached:"};
            long[] mMemInfoSizes = new long[mMemInfoFields.length];
            mMemInfoSizes[0] = 30;
            mMemInfoSizes[1] = -30;
            arglist[0] = new String("/proc/meminfo");
            arglist[1] = mMemInfoFields;
            arglist[2] = mMemInfoSizes;
            if(_readProclines!=null){
                _readProclines.invoke(null, arglist);
                for (int i=0; i<mMemInfoSizes.length; i++) {
                    Log.d("GetFreeMem", mMemInfoFields[i]+" : "+mMemInfoSizes[i]/1024);
                    atyCacheSize.setText("总内存: " + this.getTotalMemory() + ", " + "可用内存: "
                            + this.getAvailMemory());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}