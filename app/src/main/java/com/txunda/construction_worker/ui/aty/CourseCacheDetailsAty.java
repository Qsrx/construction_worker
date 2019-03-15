package com.txunda.construction_worker.ui.aty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ClassMlBean;
import com.txunda.construction_worker.ui.adapter.MyExtendableListViewAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.DownloadUtil;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDownUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.txunda.construction_worker.ui.aty.CourseSelectionAty.subject_id;

/**
 * 课程缓存详情
 */
@Layout(R.layout.aty_course_cache_details)
public class CourseCacheDetailsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_course_cache_details_elv)
    ExpandableListView atyCourseCacheDetailsElv;
    @BindView(R.id.aty_course_cache_details_tv_list)
    TextView atyCourseCacheDetailsTvList;
    private String[] fatherbean = null;
    private String[][] childtitle = null;
    private String[][] childlock = null;
    private String[][] childtime = null;
    private String[][] childmulu = null;
    private String[][] childpath = null;
    private ClassMlBean classMlBean;
    private String is_cache = null;
    MyExtendableListViewAdapter listViewAdapter;
    private ProgressDialog progressDialog;
    private DbDownUtil dbUtil;
    private List<DownInfo> listData;
    private int sum = 7;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("课程缓存详情");
        dbUtil = DbDownUtil.getInstance();
        listData = dbUtil.queryDownAll();
    }

    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Course_directory", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("is_cache", is_cache)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            classMlBean = GsonUtil.GsonToBean(response, ClassMlBean.class);
                            fatherbean = new String[classMlBean.getData().size()];
                            childtitle = new String[classMlBean.getData().size()][];
                            childlock = new String[classMlBean.getData().size()][];
                            childmulu = new String[classMlBean.getData().size()][];
                            childtime = new String[classMlBean.getData().size()][];
                            childpath = new String[classMlBean.getData().size()][];
                            for (int i = 0; i < classMlBean.getData().size(); i++) {
                                fatherbean[i] = classMlBean.getData().get(i).getTitle();
                                childtitle[i] = new String[classMlBean.getData().get(i).getList().size()];
                                childtime[i] = new String[classMlBean.getData().get(i).getList().size()];
                                childlock[i] = new String[classMlBean.getData().get(i).getList().size()];
                                childmulu[i] = new String[classMlBean.getData().get(i).getList().size()];
                                childpath[i] = new String[classMlBean.getData().get(i).getList().size()];
                                for (int j = 0; j < classMlBean.getData().get(i).getList().size(); j++) {
                                    childtitle[i][j] = classMlBean.getData().get(i).getList().get(j).getMulu() + "." + classMlBean.getData().get(i).getList().get(j).getName();
                                    childlock[i][j] = classMlBean.getData().get(i).getList().get(j).getType();
                                    childtime[i][j] = classMlBean.getData().get(i).getList().get(j).getTime();
                                    childpath[i][j] = classMlBean.getData().get(i).getList().get(j).getPath();
                                    childmulu[i][j] = classMlBean.getData().get(i).getList().get(j).getMulu();
                                }
                            }
                            listViewAdapter = new MyExtendableListViewAdapter(fatherbean, childtitle, childtime, childlock);
                            atyCourseCacheDetailsElv.setAdapter(listViewAdapter);
                        } else {
                            try {
                                showErrorTip(error.getMessage());
                            }catch (NullPointerException e){

                            }

                        }
                    }
                });
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyCourseCacheDetailsTvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(me, CachingAty.class);
                intent.putExtra("id",subject_id);
                startActivity(intent);
            }
        });
        atyCourseCacheDetailsElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (childlock[i][i1].equals("2")) {
                    showErrorTip("购买后才能观看");
                } else {
                    File outputFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "建工邦", childtitle[i][i1] + ".mp4");
                    DownInfo apkApi = new DownInfo(childpath[i][i1]);
                    apkApi.setId(Integer.valueOf(subject_id + childmulu[i][i1]));
                    apkApi.setSubjectId(subject_id);
                    apkApi.setUpdateProgress(true);
                    apkApi.setSavePath(outputFile.getAbsolutePath());
                    apkApi.setDownTiem(childtime[i][i1]);//时间
                    apkApi.setDownTitle(childtitle[i][i1]);//头部标题
                    try {
                        dbUtil.save(apkApi);
                        listData = dbUtil.queryDownAll();
                        addChace(subject_id);
                    } catch (SQLiteConstraintException e) {
                        showErrorTip("该视频已缓存");
                    }

//                    downFile(childpath[i][i1],childtitle[i][i1]);
                }
                return true;
            }
        });
    }

    /**
     * 文件下载
     *
     * @param url
     */
    public void downFile(String url, String title) {
        progressDialog = new ProgressDialog(me);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍后...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        DownloadUtil.get().download(url, Environment.getExternalStorageDirectory().getAbsolutePath(), title + ".mp4", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                //下载完成进行相关逻辑操作
            }

            @Override
            public void onDownloading(int progress) {
                progressDialog.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                //下载异常进行相关提示操作
            }
        });
    }

    private void addChace(String id) {
        Log.d("courseiwoaij", "addChace: ======================"+id);
        Log.d("courseiwoaij", "addChace: ======================"+token);
        Log.d("courseiwoaij", "addChace: ======================"+id);
        HttpRequest.POST(me, AllStatus.BASE_URL + "/Course/cache", new Parameter()
                        .add("token", token)
                        .add("course_id", id)
                        .add("type", "3")
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")) {
                            toast("已经加入缓存列表");
                        } else {
                            showErrorTip(objectMap.get("message").toString());
                        }
                    }
                });
    }
}