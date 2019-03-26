package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.google.gson.JsonSyntaxException;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.ConfirmOrder2Bean;
import com.txunda.construction_worker.bean.ConfirmOrderBean;
import com.txunda.construction_worker.bean.DownOrderBean;
import com.txunda.construction_worker.bean.ItemBankBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.txunda.construction_worker.wxapi.CallBack;
import com.txunda.construction_worker.wxapi.GetPrepayIdTask;
import com.txunda.construction_worker.wxapi.WXPayEntryActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

import static com.txunda.construction_worker.ui.fgt.HomeFgt.Industry_ID;
import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

/**
 * 确认订单
 */
@Layout(R.layout.aty_confirm_order)
public class ConfirmOrderAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_confirm_order_pic)
    ShapedImageView atyConfirmOrderPic;
    @BindView(R.id.aty_confirm_order_title)
    TextView atyConfirmOrderTitle;
    @BindView(R.id.aty_confirm_order_price)
    TextView atyConfirmOrderPrice;
    @BindView(R.id.aty_confirm_order_s_title)
    TextView atyConfirmOrderSTitle;
    @BindView(R.id.aty_confirm_order_red_price)
    TextView atyConfirmOrderRedPrice;
    @BindView(R.id.aty_confirm_order_use)
    RadioButton atyConfirmOrderUse;
    @BindView(R.id.aty_confirm_order_cant_use)
    RadioButton atyConfirmOrderCantUse;
    @BindView(R.id.aty_confirm_order_one_price)
    TextView atyConfirmOrderOnePrice;
    @BindView(R.id.aty_confirm_order_dk)
    TextView atyConfirmOrderDk;
    @BindView(R.id.aty_confirm_order_real_price)
    TextView atyConfirmOrderRealPrice;
    @BindView(R.id.aty_confirm_order_price_sum)
    TextView atyConfirmOrderPriceSum;
    @BindView(R.id.aty_confirm_order_post)
    TextView atyConfirmOrderPost;
    @BindView(R.id.aty_confirm_order_no_pay)
    LinearLayout atyConfirmOrderNoPay;
    @BindView(R.id.aty_confirm_order_tv_finish)
    TextView atyConfirmOrderTvFinish;
    @BindView(R.id.aty_confirm_order_yj_pay)
    LinearLayout atyConfirmOrderYjPay;
    @BindView(R.id.aty_confirm_order_rl_dk)
    RelativeLayout atyConfirmOrderRlDk;
    @BindView(R.id.aty_confirm_order_web)
    WebView atyConfirmOrderWeb;
    @BindView(R.id.aty_confirm_order_tv_buy_rule)
    TextView atyConfirmOrderTvBuyRule;
    private String taocan_id;
    ConfirmOrderBean orderBean;
    ConfirmOrder2Bean order2Bean;
    double dk;
    private String price = "";
    private String type = "";
    private boolean is_use = true;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("确认订单");
        setRbSize(atyConfirmOrderCantUse);
        setRbSize(atyConfirmOrderUse);
        taocan_id = getIntent().getStringExtra("taocan_id");
        type = getIntent().getStringExtra("type");
        if (taocan_id != null) {
        }
        atyConfirmOrderUse.setChecked(true);
        WXPayEntryActivity.setCallBack(new CallBack() {
            @Override
            public void CallBack() {
                atyConfirmOrderNoPay.setVisibility(View.GONE);
                atyConfirmOrderYjPay.setVisibility(View.VISIBLE);
            }
        });
        WebSettings settings = atyConfirmOrderWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        atyConfirmOrderWeb.requestFocusFromTouch();
        atyConfirmOrderWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        /**覆盖调用系统或自带浏览器行为打开网页*/
        atyConfirmOrderWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }


    @OnClick({R.id.aty_confirm_order_tv_buy_rule, R.id.aty_confirm_order_tv_finish, R.id.header_iv_back, R.id.aty_confirm_order_use, R.id.aty_confirm_order_cant_use, R.id.aty_confirm_order_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_confirm_order_use:
                is_use = true;
                atyConfirmOrderRlDk.setVisibility(View.VISIBLE);
                atyConfirmOrderCantUse.setChecked(false);
                price = String.valueOf(dk);
                atyConfirmOrderRealPrice.setText("¥ " + dk);
                atyConfirmOrderPriceSum.setText("¥ " + dk);
                if (taocan_id != null) {
                    atyConfirmOrderDk.setText("- ¥ " + orderBean.getData().getBalance());
                    return;
                }
                atyConfirmOrderDk.setText("- ¥ " + order2Bean.getData().getBalance());
                break;
            case R.id.aty_confirm_order_cant_use:
                is_use = false;
                atyConfirmOrderRlDk.setVisibility(View.GONE);
                atyConfirmOrderUse.setChecked(false);
                atyConfirmOrderDk.setText("0.00");
                if (taocan_id != null) {
                    price = String.valueOf(orderBean.getData().getPrice());
                    atyConfirmOrderRealPrice.setText("¥ " + orderBean.getData().getPrice());
                    atyConfirmOrderPriceSum.setText("¥ " + orderBean.getData().getPrice());
                    return;
                }
                price = String.valueOf(order2Bean.getData().getPrice());
                atyConfirmOrderRealPrice.setText("¥ " + order2Bean.getData().getPrice());
                atyConfirmOrderPriceSum.setText("¥ " + order2Bean.getData().getPrice());
                break;
            case R.id.aty_confirm_order_post:
                downOrder();
//                atyConfirmOrderNoPay.setVisibility(View.GONE);
//                atyConfirmOrderYjPay.setVisibility(View.VISIBLE);
                break;
            case R.id.aty_confirm_order_tv_finish:
                finish();
                break;
            case R.id.aty_confirm_order_tv_buy_rule:
                if (taocan_id != null) {
                    Intent intent = new Intent(me, RuleDetailsAty.class);
                    intent.putExtra("strdata",orderBean.getData().getAgreement());
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(me, RuleDetailsAty.class);
                intent.putExtra("strdata",order2Bean.getData().getAgreement());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 设置radiobutton按钮drawble大小
     *
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[0].setBounds(0, 0, 50, 50);
        rb.setCompoundDrawables(compoundDrawables[0], null, null, null);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        if (taocan_id != null) {
            httpData();
            return;
        }
        httpData2();
    }

    /**
     * 请求本页数据
     */
    private void httpData() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/submitOrder", new Parameter()
                        .add("token", token)
                        .add("taocan_id", taocan_id)
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            Log.d("confirmbean", "onResponse: ===============" + response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    orderBean = GsonUtil.GsonToBean(response, ConfirmOrderBean.class);
                                    Glide.with(me).load(orderBean.getData().getZu_pic()).into(atyConfirmOrderPic);
                                    atyConfirmOrderTitle.setText(orderBean.getData().getTitle());
                                    atyConfirmOrderPrice.setText("¥ " + orderBean.getData().getPrice());
                                    atyConfirmOrderOnePrice.setText("¥ " + orderBean.getData().getPrice());
                                    atyConfirmOrderRedPrice.setText("可用红包：¥ " + orderBean.getData().getBalance());
                                    atyConfirmOrderDk.setText("- ¥ " + orderBean.getData().getBalance());
                                    dk = Double.valueOf(orderBean.getData().getPrice()) - Double.valueOf(orderBean.getData().getBalance());
                                    price = String.valueOf(dk);
                                    atyConfirmOrderRealPrice.setText("¥ " + dk);
                                    atyConfirmOrderSTitle.setText(orderBean.getData().getSub_title());
                                    atyConfirmOrderPriceSum.setText("¥ " + dk);
                                } else {
                                    showErrorTip(objectMap.get("message").toString());
                                }
                            } catch (NullPointerException e) {
                                showErrorTip("数据解析失败");
                            }
                        }
                    }
                }
        );
    }

    /**
     * 请求本页数据
     */
    private void httpData2() {
        WaitDialog.show(me, "数据加载中……");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/pay_sure", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            WaitDialog.dismiss();
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            Log.d("confirmbean", "onResponse: ===============" + response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    order2Bean = GsonUtil.GsonToBean(response, ConfirmOrder2Bean.class);
                                    Glide.with(me).load(order2Bean.getData().getPic()).into(atyConfirmOrderPic);
                                    atyConfirmOrderTitle.setText(order2Bean.getData().getTitle());
                                    atyConfirmOrderPrice.setText("¥ " + order2Bean.getData().getPrice());
                                    atyConfirmOrderOnePrice.setText("¥ " + order2Bean.getData().getPrice());
                                    atyConfirmOrderRedPrice.setText("可用红包：¥ " + order2Bean.getData().getBalance());
                                    atyConfirmOrderDk.setText("- ¥ " + order2Bean.getData().getBalance());
                                    dk = Double.valueOf(order2Bean.getData().getPrice()) - Double.valueOf(order2Bean.getData().getBalance());
                                    atyConfirmOrderRealPrice.setText("¥ " + dk);
                                    price = String.valueOf(dk);
                                    atyConfirmOrderSTitle.setText(order2Bean.getData().getSub_title());
                                    atyConfirmOrderPriceSum.setText("¥ " + dk);
                                } else {
                                    showErrorTip(objectMap.get("message").toString());
                                }
                            } catch (NullPointerException e) {
                                showErrorTip("数据解析失败");
                            }
                        }
                    }
                }
        );
    }

    private void downOrder() {
        String use = "";
        if (is_use == true) {
            use = "1";
        } else {
            use = "2";
        }
        if (type == null) {
            type = "3";
        }
        if (subject_id == null) {
            httpData3(use);
        } else {
            httpDown(use);
        }

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
                                Log.d("confirmorderbean", "onResponse: ============" + response);
                                Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));
                                GetPrepayIdTask wxPay = new GetPrepayIdTask(me, data.get("sign"), data.get("appid"), data.get("noncestr"), data.get("package"), data.get("timestamp"),
                                        data.get("prepayid"), data.get("partnerid"), "");
                                wxPay.execute();
                            } else {
                                showErrorTip(stringMap.get("message"));
                            }
                        }
                    }
                }
        );
    }

    /**
     * 加载本页数据
     */
    private void httpData3(final String num) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/index", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("industry_id", Industry_ID)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                try {
                                    ItemBankBean bankBean = GsonUtil.GsonToBean(response, ItemBankBean.class);
                                    subject_id = bankBean.getData().getList().get(0).getSubject_id();
                                    httpDown(num);
                                } catch (JsonSyntaxException e) {
                                    toast("数据类型异常");
                                }
                            } else {
                                toast(objectMap.get("message"));
                            }
                        }
                    }
                });
    }

    private void httpDown(String use) {
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/addorder", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("price", price)
                        .add("type", type)
                        .add("is_use", use)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> stringObjectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (stringObjectMap.get("code").equals("1")) {
                                DownOrderBean orderBean = GsonUtil.GsonToBean(response, DownOrderBean.class);
                                String order_id = orderBean.getData().getOrder_id();
                                httpWXPay(order_id, price);
                                return;
                            }
                            showErrorTip(stringObjectMap.get("message").toString());
                        }
                    }
                });
    }
}