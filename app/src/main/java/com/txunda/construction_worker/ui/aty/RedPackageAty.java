package com.txunda.construction_worker.ui.aty;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.RedPackageBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的红包
 */
@Layout(R.layout.aty_red_package)
public class RedPackageAty extends BaseAty {
    @BindView(R.id.aty_red_package_back)
    ImageView atyRedPackageBack;
    @BindView(R.id.aty_red_package_price)
    TextView atyRedPackagePrice;
    @BindView(R.id.aty_red_package_tab)
    SlidingTabLayout atyRedPackageTab;
    @BindView(R.id.aty_red_package_vp)
    ViewPager atyRedPackageVp;
    private String[] titles;
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        titles = new String[]{"全部","收入","支出"};
        for (int i = 0; i < 3; i++) {
            mFragmentList.add(new RedPackageFgt(String.valueOf(i)));
        }
        atyRedPackageTab.setViewPager(atyRedPackageVp, titles, me, (ArrayList<Fragment>) mFragmentList);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @OnClick(R.id.aty_red_package_back)
    public void onViewClicked() {
        finish();
    }
    private void httpData(){
        WaitDialog.show(me,"数据加载中...");
        HttpRequest.POST(me, AllStatus.BASE_URL + "Myinfo/moneyBag", new Parameter()
                        .add("token", token)
                        .add("type", "1")
                        .add("page", "1")
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                RedPackageBean packageBean = GsonUtil.GsonToBean(response, RedPackageBean.class);
                                atyRedPackagePrice.setText(packageBean.getData().getBalance());
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
}