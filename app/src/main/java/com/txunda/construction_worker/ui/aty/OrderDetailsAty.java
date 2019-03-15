package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.OrderDetailsBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.txunda.construction_worker.wxapi.GetPrepayIdTask;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * 订单详情界面
 */
@Layout(R.layout.aty_order_details)
public class OrderDetailsAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_order_details_type)
    TextView atyOrderDetailsType;
    @BindView(R.id.aty_order_details_state)
    TextView atyOrderDetailsState;
    @BindView(R.id.aty_order_details_red_packge)
    TextView atyOrderDetailsRedPackge;
    @BindView(R.id.aty_order_details_price_real)
    TextView atyOrderDetailsPriceReal;
    @BindView(R.id.aty_order_details_order_code)
    TextView atyOrderDetailsOrderCode;
    @BindView(R.id.aty_order_details_pay_way)
    TextView atyOrderDetailsPayWay;
    @BindView(R.id.aty_order_details_create_time)
    TextView atyOrderDetailsCreateTime;
    @BindView(R.id.aty_order_details_confirm_time)
    TextView atyOrderDetailsConfirmTime;
    @BindView(R.id.aty_order_details_ll_confirm_time)
    LinearLayout atyOrderDetailsLlConfirmTime;
    @BindView(R.id.aty_order_details_left)
    TextView atyOrderDetailsLeft;
    @BindView(R.id.aty_order_details_right)
    TextView atyOrderDetailsRight;
    @BindView(R.id.aty_order_details_success)
    TextView atyOrderDetailsSuccess;
    @BindView(R.id.aty_order_details_ll_bottom)
    LinearLayout atyOrderDetailsLlBottom;
    @BindView(R.id.aty_order_details_pic)
    ShapedImageView atyOrderDetailsPic;
    @BindView(R.id.order_context_ll_bg)
    LinearLayout orderContextLlBg;
    @BindView(R.id.aty_order_details_name)
    TextView atyOrderDetailsName;
    @BindView(R.id.aty_order_details_price)
    TextView atyOrderDetailsPrice;
    @BindView(R.id.aty_order_details_s_name)
    TextView atyOrderDetailsSName;
    private String order_id;
    private OrderDetailsBean detailsBean;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("订单详情");
        order_id = getIntent().getStringExtra("order_id");
    }


    @OnClick({R.id.header_iv_back, R.id.aty_order_details_left, R.id.aty_order_details_right, R.id.aty_order_details_success})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_order_details_left:
                cancelOrder(order_id);
                break;
            case R.id.aty_order_details_right:
                if (atyOrderDetailsRight.getText().toString().equals("去评价")){
                    Intent intent = new Intent(me, PublicationEvaluationAty.class);
                    intent.putExtra("order_id",order_id);
                    startActivity(intent);
                    finish();
                }else if (atyOrderDetailsRight.getText().toString().equals("立即支付")){
                    httpWXPay(order_id,detailsBean.getData().getTrue_price());
                }
                break;
            case R.id.aty_order_details_success:
                break;
        }
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    /**
     * 请求本页数据
     */
    private void httpData(){
        WaitDialog.show(me,"加载中");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/order_details", new Parameter()
                .add("token", token)
                .add("order_id", order_id), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    WaitDialog.dismiss();
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        detailsBean = GsonUtil.GsonToBean(response, OrderDetailsBean.class);
                        atyOrderDetailsType.setText(detailsBean.getData().getTitle());
                        Glide.with(me).load(detailsBean.getData().getPic()).into(atyOrderDetailsPic);
                        atyOrderDetailsName.setText(detailsBean.getData().getSub_title());
                        atyOrderDetailsSName.setText(detailsBean.getData().getSon_title());
                        atyOrderDetailsPrice.setText("¥ "+detailsBean.getData().getPrice());
                        atyOrderDetailsRedPackge.setText("¥ "+detailsBean.getData().getBalance());
                        atyOrderDetailsPriceReal.setText("¥ "+detailsBean.getData().getTrue_price());
                        atyOrderDetailsOrderCode.setText(detailsBean.getData().getOrdenum());
                        atyOrderDetailsCreateTime.setText(detailsBean.getData().getCreate_time());
                        atyOrderDetailsConfirmTime.setText(detailsBean.getData().getUpdate_time());
                        if (detailsBean.getData().getPay_status().equals("1")){
                            atyOrderDetailsState.setText("待支付");
                            atyOrderDetailsLlConfirmTime.setVisibility(View.GONE);
                        }else if (detailsBean.getData().getPay_status().equals("2")){
                            atyOrderDetailsState.setText("已购买");
                            if (detailsBean.getData().getEvaluate() == 1){
                                atyOrderDetailsRight.setVisibility(View.GONE);
                                atyOrderDetailsLeft.setVisibility(View.GONE);
                                atyOrderDetailsSuccess.setVisibility(View.VISIBLE);
                            }else {
                                atyOrderDetailsRight.setText("去评价");
                                atyOrderDetailsLeft.setVisibility(View.GONE);
                            }
                        }else {
                            atyOrderDetailsState.setText("已取消");
                        }
                        return;
                    }
                    showErrorTip(objectMap.get("message").toString());
                }
            }
        });
    }
    /**
     * 取消订单请求
     * @param order_id
     */
    private void cancelOrder(String order_id){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/cancleorder", new Parameter()
                .add("token", token)
                .add("order_id", order_id), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        toast(objectMap.get("message"));
                        return;
                    }
                    showErrorTip(objectMap.get("message").toString());
                }
            }
        });
    }
    /**
     * 微信支付
     *
     * @param id
     * @param price
     */
    private void httpWXPay(String id, String price) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Pay/WechatPay", new Parameter()
                        .add("token", token)
                        .add("order_id", id)
                        .add("pay_price", price), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(response);
                            if (stringMap.get("code").equals("1")) {
                                Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));
                                GetPrepayIdTask wxPay = new GetPrepayIdTask(me, data.get("sign"), data.get("appid"), data.get("noncestr"), data.get("package"), data.get("timestamp"),
                                        data.get("prepayid"), data.get("partnerid"), "");
                                Log.d("confirmorderbean", "onResponse: ========" + wxPay.toString());
                                wxPay.execute();
                            } else {
                                showErrorTip(stringMap.get("message"));
                            }
                        }
                    }
                }
        );
    }
}