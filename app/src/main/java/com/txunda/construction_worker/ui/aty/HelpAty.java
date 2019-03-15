package com.txunda.construction_worker.ui.aty;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帮助与反馈
 */
@Layout(R.layout.aty_help)
public class HelpAty extends BaseAty {

    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.aty_help_et_content)
    EditText atyHelpEtContent;
    @BindView(R.id.aty_help_tv_post)
    TextView atyHelpTvPost;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        headerTvTitle.setText("帮助与反馈");
    }


    @OnClick({R.id.header_iv_back, R.id.aty_help_tv_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_help_tv_post:
                postData();
                break;
        }
    }
    private void postData(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Myinfo/Feedback", new Parameter()
                        .add("token", token)
                        .add("content",atyHelpEtContent.getText().toString() ), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")) {
                                toast(objectMap.get("message"));
                                finish();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }
}
