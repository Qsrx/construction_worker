package com.txunda.construction_worker.ui.aty;

import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.adp.DownAdapter;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpDownOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDownUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 正在缓存
 */
@Layout(R.layout.aty_caching)
public class CachingAty extends BaseAty {
    private HttpDownManager manager;
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_caching_state)
    TextView atyCachingState;
    @BindView(R.id.aty_caching_rv)
    RecyclerView atyCachingRv;
    @BindView(R.id.aty_cache_size)
    TextView atyCacheSize;
//    private CachingRvAdapter adapter;
    List<DownInfo> listData;
    private String id;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    DbDownUtil dbUtil;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("正在缓存");
        atyCachingRv.setLayoutManager(new LinearLayoutManager(this));
        id = getIntent().getStringExtra("id");
        manager = HttpDownManager.getInstance();
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+id;
//        adapter = new CachingRvAdapter(R.layout.item_caching_layout);
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i + "");
//        }
//        atyCachingRv.setAdapter(adapter);
//        adapter.setNewData(list);
//        adapter.notifyDataSetChanged();
        dbUtil = DbDownUtil.getInstance();
        List<DownInfo> data = dbUtil.queryDownAll();
        listData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String subjectId = data.get(i).getSubjectId();



            if (id.equals(subjectId)){
                DownInfo downInfo = data.get(i);
                listData.add(downInfo);
            }
        }
        initWidget();
        File file = Environment.getExternalStorageDirectory();
        long totalSpace = file.getTotalSpace();//总大小
        long usableSpace = file.getUsableSpace();//可用
        long aleayuse = totalSpace - usableSpace;
        String formatFileSize = Formatter.formatFileSize(me, aleayuse);
        String formatUsable = Formatter.formatFileSize(me, usableSpace);
        Log.d("inkelrewnrlew", "initViews: ==========已使用"+formatFileSize+"可用"+formatUsable);
        atyCacheSize.setText("已使用"+formatFileSize+",可用空间"+formatUsable);
//        show();

//        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(me, "aaaaaaa", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
    @OnClick({R.id.header_iv_back, R.id.aty_caching_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_caching_state:
                log("=======点击了=======");
                Log.d("zdl", "onViewClicked: 点击了");
                //全部下载
                for (int i = 0; i < listData.size(); i++) {
                    adapter.clear();
                    log("=======运行了======="+i);
                    Log.d("zdl", "onViewClicked: 运行了"+i);
                    listData.get(i).setListener(httpProgressOnNextListener);
                    listData.get(i).setState(DownState.DOWN);
                    manager.startDown(listData.get(i));
                    adapter.add(listData.get(i));
                    adapter.notifyDataSetChanged();
                }
                break;
                default:
        }
    }

//    /**
//     * 查看本地缓存
//     */
//    public void show(){
//        Method _readProclines = null;
//        try {
//            Class procClass;
//            procClass = Class.forName("android.os.Process");
//            Class parameterTypes[]= new Class[] {String.class, String[].class, long[].class };
//            _readProclines = procClass.getMethod("readProcLines", parameterTypes);
//            Object arglist[] = new Object[3];
//            final String[] mMemInfoFields = new String[] {"MemTotal:",
//                    "MemFree:", "Buffers:", "Cached:"};
//            long[] mMemInfoSizes = new long[mMemInfoFields.length];
//            mMemInfoSizes[0] = 30;
//            mMemInfoSizes[1] = -30;
//            arglist[0] = new String("/proc/meminfo");
//            arglist[1] = mMemInfoFields;
//            arglist[2] = mMemInfoSizes;
//            if(_readProclines!=null){
//                _readProclines.invoke(null, arglist);
//                for (int i=0; i<mMemInfoSizes.length; i++) {
//                    Log.d("GetFreeMem", mMemInfoFields[i]+" : "+mMemInfoSizes[i]/1024);
//                    atyCacheSize.setText("总内存: " + this.getTotalMemory() + ", " + "可用内存: "
//                            + this.getAvailMemory());
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private String getAvailMemory() {// 获取android当前可用内存大小
//
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
//        am.getMemoryInfo(mi);
//        //mi.availMem; 当前系统的可用内存
//
//        return Formatter.formatFileSize(getBaseContext(), mi.availMem);// 将获取的内存大小规格化
//    }
//
//    private String getTotalMemory() {
//        String str1 = "/proc/meminfo";// 系统内存信息文件
//        String str2;
//        String[] arrayOfString;
//        long initial_memory = 0;
//
//        try {
//            FileReader localFileReader = new FileReader(str1);
//            BufferedReader localBufferedReader = new BufferedReader(
//                    localFileReader, 8192);
//            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
//
//            arrayOfString = str2.split("\\s+");
//            for (String num : arrayOfString) {
//                Log.i(str2, num + "\t");
//            }
//
//            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
//            localBufferedReader.close();
//
//        } catch (IOException e) {
//        }
//        return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
//    }
    private DownAdapter adapter;
    private void initWidget() {
        adapter = new DownAdapter(this);
        atyCachingRv.setAdapter(adapter);
        adapter.addAll(listData);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*记录退出时下载任务的状态-复原用*/
        for (DownInfo downInfo : listData) {
            dbUtil.update(downInfo);
        }
    }
    /*下载回调*/
    HttpDownOnNextListener<DownInfo> httpProgressOnNextListener = new HttpDownOnNextListener<DownInfo>() {
        @Override
        public void onNext(DownInfo baseDownEntity) {
//            tvMsg.setText("提示：下载完成/文件地址->" + baseDownEntity.getSavePath());
            Toast.makeText(CachingAty.this, baseDownEntity.getSavePath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
//            tv_state.setText("开始下载");
            toast("开始下载");
            Log.d("zdl","============httpProgressOnNextListener==============开始下载了");
            Toast.makeText(CachingAty.this, "开始缓存", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            Toast.makeText(CachingAty.this, "提示：下载结束", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
//            tvMsg.setText("失败:" + e.toString());
            Toast.makeText(CachingAty.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onPuase() {
            super.onPuase();
//            tvMsg.setText("提示:暂停");
//            tv_state.setText("暂停");
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
//            tv_state.setText("下载中");
//            progressBar.setMax((int) countLength);
//            progressBar.setProgress((int) readLength);
        }
    };

}