package com.txunda.construction_worker.ui.aty;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PublicationEvaluationBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品 - 发表评价
 */
@Layout(R.layout.aty_publication_evaluation)
public class PublicationEvaluationAty extends BaseAty {
    @BindView(R.id.aty_publication_evauation_back)
    ImageView atyPublicationEvauationBack;
    @BindView(R.id.aty_publication_evauation_post)
    TextView atyPublicationEvauationPost;
    @BindView(R.id.aty_publication_evauation_img)
    ImageView atyPublicationEvauationImg;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.aty_publication_evauation_status)
    TextView atyPublicationEvauationStatus;
    @BindView(R.id.aty_publication_evauation_content)
    EditText atyPublicationEvauationContent;
    private String order_id = "";
    private String star = "10";
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        order_id = getIntent().getStringExtra("order_id");
    }
    @OnClick({R.id.aty_publication_evauation_back, R.id.aty_publication_evauation_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_publication_evauation_back:
                finish();
                break;
            case R.id.aty_publication_evauation_post:
                postData();
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @Override
    public void setEvents() {
        super.setEvents();
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                star = String.valueOf(v*2);
            }
        });
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/order_commentPage", new Parameter()
                        .add("token",token)
                        .add("order_id",order_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        PublicationEvaluationBean evaluationBean = GsonUtil.GsonToBean(response, PublicationEvaluationBean.class);
                        Glide.with(me).load(evaluationBean.getData().getPic()).into(atyPublicationEvauationImg);
                    }
                });
    }

    /**
     * 发表评价
     */
    private void postData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/order_comment", new Parameter()
                        .add("token",token)
                        .add("order_id",order_id)
                        .add("star",star)
                        .add("content",atyPublicationEvauationContent.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                        if (objectMap.get("code").equals("1")){
                            finish();
                        }
                        toast(objectMap.get("message"));
                    }
                });
    }
}