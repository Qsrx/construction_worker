package com.txunda.construction_worker.ui.aty;

import android.util.Log;
import android.widget.ImageView;
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
import com.txunda.construction_worker.bean.AboutUsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们
 */
@Layout(R.layout.aty_about_us)
public class AboutUsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_about_us_pic)
    ImageView atyAboutUsPic;
    @BindView(R.id.aty_about_us_name)
    TextView atyAboutUsName;
    @BindView(R.id.aty_about_us_com_name)
    TextView atyAboutUsComName;
    @BindView(R.id.aty_about_us_gw)
    TextView atyAboutUsGw;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("关于我们");
    }
    @OnClick(R.id.header_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        HttpRequest.POST(this, AllStatus.BASE_URL + "Myinfo/aboutus", new Parameter()
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Log.d("aboutus", "initDatas: =============="+response);
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                AboutUsBean usBean = GsonUtil.GsonToBean(response, AboutUsBean.class);
//                                headerTvTitle.setText(usBean.getData().getTitle());
                                Glide.with(me).load(usBean.getData().getCover()).into(atyAboutUsPic);
                                atyAboutUsComName.setText(usBean.getData().getDown());
                                atyAboutUsName.setText(usBean.getData().getTitle());
                                atyAboutUsGw.setText(usBean.getData().getCoins());
                            }
                        }
                    }
                });
    }
}
