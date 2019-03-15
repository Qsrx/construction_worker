package com.txunda.construction_worker.ui.aty;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@DarkStatusBarTheme(false) //开启顶部状态栏图标、文字暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//透明颜色   设置底部导航栏背景颜色（a = 255,r = 255,g = 255,b = 255 黑色的）
@DarkNavigationBarTheme(true) //开启底部导航栏按钮暗色
@Layout(R.layout.aty_image_details)
public class ImageDetailsAty extends BaseAty {
    @BindView(R.id.aty_img_details_iv)
    ImageView atyImgDetailsIv;
    private String httpURl = "";

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        httpURl = getIntent().getStringExtra("url");
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        Glide.with(me).load(httpURl).into(atyImgDetailsIv);
    }

    @Override
    public void setEvents() {
        super.setEvents();
    }


    @OnClick(R.id.aty_img_details_iv)
    public void onViewClicked() {
        finish();
    }
}