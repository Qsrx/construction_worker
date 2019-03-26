package com.txunda.construction_worker.ui.aty;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.MainAty;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.AnliDataBean;
import com.txunda.construction_worker.bean.ViewParsingBean;
import com.txunda.construction_worker.bean.WrongsDetailsBean;
import com.txunda.construction_worker.ui.adapter.ViewParsingAdapter;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看解析界面
 */
@Layout(R.layout.aty_view_parsing)
@DarkStatusBarTheme(true)  //开启顶部状态栏图标、false 白色 true 黑色
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
public class ViewParsingAty extends BaseAty {

    @BindView(R.id.aty_do_work_iv_back)
    ImageView atyDoWorkIvBack;
    @BindView(R.id.aty_do_work_rb_sc)
    ImageView atyDoWorkRbSc;
    @BindView(R.id.aty_do_work_tv_title)
    TextView atyDoWorkTvTitle;
    @BindView(R.id.aty_do_work_tv_content)
    TextView atyDoWorkTvContent;
    @BindView(R.id.aty_do_work_rv)
    RecyclerView atyDoWorkRv;
    @BindView(R.id.fgt_my_ll_quiz)
    LinearLayout fgtMyLlQuiz;
    @BindView(R.id.fgt_my_ll_collects)
    LinearLayout fgtMyLlCollects;
    @BindView(R.id.aty_do_work_ll_jx)
    LinearLayout atyDoWorkLlJx;
    public static int select_index = -1;
    @BindView(R.id.aty_more_do_work_iv_dtk)
    ImageView atyMoreDoWorkIvDtk;
    @BindView(R.id.aty_view_parsing_ck)
    TextView atyViewParsingCk;
    @BindView(R.id.aty_view_parsing_myself)
    TextView atyViewParsingMyself;
    @BindView(R.id.aty_view_parsing_jx)
    TextView atyViewParsingJx;
    @BindView(R.id.aty_view_parsing_point)
    TextView atyViewParsingPoint;
    @BindView(R.id.aty_view_parsing_ll_select)
    LinearLayout atyViewParsingLlSelect;
    @BindView(R.id.fgt_content2_iv)
    ImageView fgtContent2Iv;
    @BindView(R.id.fgt_content2_web)
    WebView fgtContent2Web;
    @BindView(R.id.aty_view_parsing_ll_anli)
    LinearLayout atyViewParsingLlAnli;
    @BindView(R.id.fgt_content2_answer_pic)
    ImageView fgtContent2AnswerPic;
    private ViewParsingAdapter adapter;
    private List<String> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private String questions_id = "";
    private String type = "";
    public static String STR = "";
    private int collection;
    private WrongsDetailsBean detailsBean;
    private ViewParsingBean parsingBean;
    private AnliDataBean databean;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
//        setRbSize(atyDoWorkRbSc);
        atyDoWorkRv.setLayoutManager(new LinearLayoutManager(this));
        questions_id = getIntent().getStringExtra("questions_id");
        type = getIntent().getStringExtra("type");
        adapter = new ViewParsingAdapter(list, titles, this);
        atyDoWorkRv.setAdapter(adapter);
        if (type.equals("1")) {
            atyMoreDoWorkIvDtk.setVisibility(View.GONE);
            fgtMyLlCollects.setVisibility(View.VISIBLE);
        }
        WebSettings settings = fgtContent2Web.getSettings();
        settings.setJavaScriptEnabled(true);
        fgtContent2Web.requestFocusFromTouch();
        fgtContent2Web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        fgtContent2Web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 设置radiobutton按钮drawble大小
     *
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[0].setBounds(0, 0, 70, 70);
        rb.setCompoundDrawables(compoundDrawables[0], null, null, null);
    }


    @OnClick({R.id.aty_do_work_iv_back, R.id.aty_more_do_work_iv_dtk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_do_work_iv_back:
                finish();
                break;
            case R.id.aty_more_do_work_iv_dtk:
                jump(AnswerCardAty.class);
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        if (type.equals("1")) {
            httpData();
            return;
        }
        httpData2();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyDoWorkRbSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("1")) {
                    checkCollect(String.valueOf(parsingBean.getData().getQuestions_id()));
                    return;
                }
                try {
                    checkCollect(String.valueOf(detailsBean.getData().getQuestions_id()));
                } catch (NullPointerException e) {
                    checkCollect(String.valueOf(databean.getData().getQuestions_id()));
                }

            }
        });
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/view_answer", new Parameter()
                        .add("token", token)
                        .add("questions_id", questions_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                Log.d("viewparsingbean", "onResponse: =============" + response);
                                parsingBean = GsonUtil.GsonToBean(response, ViewParsingBean.class);
//                                list.add("A");
//                                list.add("B");
//                                list.add("C");
//                                titles.add(parsingBean.getData().getA());
//                                titles.add(parsingBean.getData().getB());
//                                titles.add(parsingBean.getData().getC());
//                                titles.add(parsingBean.getData().getD());
                                Glide.with(me).load(parsingBean.getData().getAnswer_pic()).into(fgtContent2AnswerPic);
                                for (int i = 0; i < parsingBean.getData().getOption().size(); i++) {
                                    list.add(parsingBean.getData().getOption().get(i).getAnswer());
                                    titles.add(parsingBean.getData().getOption().get(i).getQuestion());
                                }
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(i).equals(parsingBean.getData().getChoose_answer())) {
                                        select_index = i;
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                atyDoWorkTvTitle.setText(parsingBean.getData().getTitle());
                                atyDoWorkTvContent.setText(parsingBean.getData().getSub_title());
                                atyViewParsingCk.setText(parsingBean.getData().getAnswer1());
                                atyViewParsingMyself.setText(parsingBean.getData().getChoose_answer());
                                atyViewParsingJx.setText(parsingBean.getData().getAnalysis());
                                atyViewParsingPoint.setText(parsingBean.getData().getKey());
                                collection = parsingBean.getData().getCollection();
                                if (parsingBean.getData().getCollection() == 1) {
//                                    atyDoWorkRbSc.setChecked(true);
                                    atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                                } else {
                                    atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                                }
//                                atyDoWorkRbSc.setChecked(false);
                            } else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }

    private void httpData2() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/wrongs_detail", new Parameter()
                        .add("token", token)
                        .add("questions_id", questions_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        try {
                            detailsBean = GsonUtil.GsonToBean(response, WrongsDetailsBean.class);
                            for (int i = 0; i < detailsBean.getData().getOption().size(); i++) {
                                list.add(detailsBean.getData().getOption().get(i).getAnswer());
                                titles.add(detailsBean.getData().getOption().get(i).getQuestion());
                            }
                            Glide.with(me).load(detailsBean.getData().getAnswer_pic()).into(fgtContent2AnswerPic);
                            STR = detailsBean.getData().getAnswer();
                            atyViewParsingCk.setText(detailsBean.getData().getAnswer());
                            adapter.notifyDataSetChanged();
                            atyDoWorkTvTitle.setText(detailsBean.getData().getTitle());
                            atyDoWorkTvContent.setText(detailsBean.getData().getSub_title());
                            atyViewParsingJx.setText(detailsBean.getData().getAnalysis());
                            atyViewParsingPoint.setText(detailsBean.getData().getKey());
                            if (detailsBean.getData().getCollection() == 1) {
                                atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                            } else {
                                atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                            }
                        } catch (NullPointerException e) {
                            databean = GsonUtil.GsonToBean(response, AnliDataBean.class);
                            atyViewParsingLlSelect.setVisibility(View.GONE);
                            atyViewParsingLlAnli.setVisibility(View.VISIBLE);
                            fgtContent2Web.loadDataWithBaseURL(null, databean.getData().getSub_title() + databean.getData().getAnswer(), "text/html", "utf-8", null);
                            STR = databean.getData().getAnswer();
                            adapter.notifyDataSetChanged();
                            atyDoWorkTvTitle.setText(databean.getData().getTitle());
                            atyViewParsingJx.setText(databean.getData().getAnalysis());
                            atyViewParsingPoint.setText(databean.getData().getKey());
                            if (databean.getData().getCollection() == 1) {
                                atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
                            } else {
                                atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
                            }
                        }
                    }
                });
    }

    private void changeSc(int collect) {
        if (collect == 1) {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_unsc);
            collection = 2;
        } else {
            atyDoWorkRbSc.setImageResource(R.mipmap.icon_class_sc);
            collection = 1;
        }
    }

    /**
     * 收藏/取消收藏
     */
    private void checkCollect(String str) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/questions_collection", new Parameter()
                        .add("token", MainAty.token)
                        .add("questions_id", str)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                changeSc(collection);
                            } else {
                                toast(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}