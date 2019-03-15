package com.txunda.construction_worker.ui.adapter.adp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ants.theantsgo.util.JSONUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpDownOnNextListener;

import java.util.Map;

import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.DOWN;

/**
 * 下载item
 * Created by WZG on 2016/10/21.
 */

public class DownHolder extends BaseViewHolder<DownInfo> implements View.OnClickListener {
    //    private TextView tvMsg;
    private ProgressBar progressBar;
    private DownInfo apkApi;
    private HttpDownManager manager;
    private TextView tv_state;
    private TextView tv_title;
    private ImageView iv_icon;
    private TextView tv_time;
    private LinearLayout ll_onclick,ll_falfasf;

    public DownHolder(ViewGroup parent) {
        super(parent, R.layout.item_caching_layout);
        manager = HttpDownManager.getInstance();
        $(R.id.item_cachi_iv_state).setOnClickListener(this);
        ll_onclick = $(R.id.ll_onclick);
        $(R.id.ll_onclick).setOnClickListener(this);
        progressBar = $(R.id.item_cachi_pb);
        tv_state = $(R.id.item_caching_tv_state);
        tv_time = $(R.id.item_caching_tv_time);
        tv_title = $(R.id.item_caching_tv_title);
        $(R.id.item_caching_tv_title).setOnClickListener(this);
        $(R.id.ll_falfasf).setOnClickListener(this);
        $(R.id.ll_1111).setOnClickListener(this);
        iv_icon = $(R.id.item_cachi_iv_state);
//        tvMsg = $(R.id.tv_msg);


    }

    @Override
    public void setData(DownInfo data) {
        super.setData(data);
        data.setListener(httpProgressOnNextListener);
        apkApi = data;
        tv_title.setText(apkApi.getDownTitle());
        tv_time.setText(apkApi.getDownTiem());
        progressBar.setMax((int) apkApi.getCountLength());
        progressBar.setProgress((int) apkApi.getReadLength());

        ll_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnHolderClickListener != null) {
                    mOnHolderClickListener.onHolderClick(view, "", "");
                }
            }
        });


//        tv_state
        /*第一次恢复 */
        switch (apkApi.getState()) {
            case START:
                /*起始状态*/
                tv_state.setText("未开始");
                manager.startDown(apkApi);
                iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
                break;
            case PAUSE:
                tv_state.setText("暂停中");
                manager.startDown(apkApi);
                iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
                break;
            case DOWN:
                tv_state.setText("下载中");
                Log.d("zdl", "=================" + apkApi.getState().toString() + "=====下载中DOWN==" + DOWN);
                manager.startDown(apkApi);
                manager.pause(apkApi);
                iv_icon.setImageResource(R.mipmap.icon_cache_zt);
                break;
            case STOP:
                tv_state.setText("下载停止");
                break;
            case ERROR:
                tv_state.setText("下载错误");
                break;
            case FINISH:
                tv_state.setText("下载完成");
                iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_cachi_iv_state:
                if (apkApi.getState() != DownState.FINISH) {
                    manager.startDown(apkApi);
                    iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
                } else {
                    manager.pause(apkApi);
                    iv_icon.setImageResource(R.mipmap.icon_cache_zt);
                }
                break;
            default:
                break;
            case R.id.ll_onclick:
            case R.id.ll_falfasf:
            case R.id.item_caching_tv_title:
            case R.id.ll_1111:
                Intent intent = new Intent(getContext(), CourseSelectionAty.class);
                intent.putExtra("subject_id",apkApi.getSubjectId());
                getContext().startActivity(intent);
                break;
        }
    }

    /*下载回调*/
    HttpDownOnNextListener<DownInfo> httpProgressOnNextListener = new HttpDownOnNextListener<DownInfo>() {
        @Override
        public void onNext(DownInfo baseDownEntity) {
//            tvMsg.setText("提示：下载完成/文件地址->" + baseDownEntity.getSavePath());
            Toast.makeText(getContext(), baseDownEntity.getSavePath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
            iv_icon.setImageResource(R.mipmap.icon_cache_zt);
            tv_state.setText("开始下载");
            Toast.makeText(getContext(), "开始缓存", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
            tv_state.setText("下载完成");
            addChace(apkApi.getSubjectId());
            Toast.makeText(getContext(), "提示：下载结束", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
//            tvMsg.setText("失败:" + e.toString());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onPuase() {
            super.onPuase();
//            tvMsg.setText("提示:暂停");
            iv_icon.setImageResource(R.mipmap.icon_my_cache_start);
            tv_state.setText("暂停");
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            tv_state.setText("下载中");
            iv_icon.setImageResource(R.mipmap.icon_cache_zt);
            progressBar.setMax((int) countLength);
            progressBar.setProgress((int) readLength);
        }
    };

    //点击事件的接口回调 private OnHolderClickListener mOnHolderClickListener;
//点击事件的接口回调
    private OnHolderClickListener mOnHolderClickListener;

    public interface OnHolderClickListener {
        void onHolderClick(View view, String path, String id);
    }

    public void setOnHolderClickListener(OnHolderClickListener listener) {
        mOnHolderClickListener = listener;
    }
    private void addChace(String id){
        HttpRequest.POST(getContext(), AllStatus.BASE_URL + "/Course/cache", new Parameter()
                        .add("token", MainAty.token)
                        .add("course_id", id)
                        .add("type","1")
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("nirenterter", "onResponse: ============"+response);
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                        }else {
                            Log.d("缓存完毕", "onResponse: ==="+objectMap.get("message"));
                        }
                    }
                });
    }
}