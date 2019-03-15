package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PersonalDataBean;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 个人资料
 */
@Layout(R.layout.aty_personal_data)
public class PersonalDataAty extends BaseAty {
    @BindView(R.id.aty_personal_data_back)
    ImageView atyPersonalDataBack;
    @BindView(R.id.aty_personal_data_save)
    TextView atyPersonalDataSave;
    @BindView(R.id.aty_personal_data_pic)
    CircleImageView atyPersonalDataPic;
    @BindView(R.id.aty_personal_data_name)
    TextView atyPersonalDataName;
    @BindView(R.id.aty_personal_data_et_name)
    EditText atyPersonalDataEtName;
    @BindView(R.id.aty_personal_data_rl_name)
    RelativeLayout atyPersonalDataRlName;
    @BindView(R.id.aty_personal_data_rl_xg_pw)
    RelativeLayout atyPersonalDataRlXgPw;
    @BindView(R.id.aty_personal_data_tv_phone)
    TextView atyPersonalDataTvPhone;
    @BindView(R.id.aty_personal_data_ll_xg_phone)
    LinearLayout atyPersonalDataLlXgPhone;
    private PopupWindow pop;
    //true=头像修改
    private boolean is_change = false;
    //true=昵称修改
    private boolean name_is_change = false;
    private List<LocalMedia> images;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        //TODO:接口调用
        httpData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    is_change = true;
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    Glide.with(me).load(images.get(0).getPath()).into(atyPersonalDataPic);
//                    pathFromURI = AdvertisingPositionPurchaseActivity.getRealPathFromURI(this, data1);
//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
                    default:break;
            }
        }
    }
    @OnClick({R.id.aty_personal_data_back, R.id.aty_personal_data_save, R.id.aty_personal_data_pic, R.id.aty_personal_data_rl_name, R.id.aty_personal_data_rl_xg_pw, R.id.aty_personal_data_ll_xg_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_personal_data_back:
                finish();
                break;
            case R.id.aty_personal_data_save:
                //如果头像和昵称都修改过则全部提交
                if (is_change == true && name_is_change == true){
                    changeData();
                }else if (name_is_change == true){
                    changeNikeName();
                }else if (is_change == true){
                    changePhoto();
                }
                break;
            case R.id.aty_personal_data_pic:
                showPop();
                break;
            case R.id.aty_personal_data_rl_name:
                break;
            case R.id.aty_personal_data_rl_xg_pw:
                Intent intent = new Intent(this, YzmLoginAty.class);
                intent.putExtra("type","4");
                startActivity(intent);
                break;
            case R.id.aty_personal_data_ll_xg_phone:
                Intent intent2 = new Intent(this, YzmLoginAty.class);
                intent2.putExtra("type","5");
                startActivity(intent2);
                break;
                default:break;
        }
    }
    /**
     * 弹出popupwindow以及事件处理
     */
    private void showPop() {
        View bottomView = View.inflate(me, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(me)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(1)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(me)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        closePopupWindow();
                        break;
                        default:break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }
    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }
    private void httpData(){
        WaitDialog.show(me,"正在加载...");
        HttpRequest.POST(this, AllStatus.BASE_URL + "Member/memberBaseData", new Parameter()
                .add("token", token), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null){
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")){
                        PersonalDataBean dataBean = GsonUtil.GsonToBean(response, PersonalDataBean.class);
                        atyPersonalDataName.setText(dataBean.getData().getNickname());
                        atyPersonalDataEtName.setText(dataBean.getData().getNickname());
                        atyPersonalDataTvPhone.setText(dataBean.getData().getAccount());
                        Glide.with(me).load(dataBean.getData().getHead_pic()).into(atyPersonalDataPic);
                    }else {
                        showErrorTip(objectMap.get("message").toString());
                    }
                }

            }
        });
    }

    @Override
    public void setEvents() {
        super.setEvents();
        atyPersonalDataEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name_is_change = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 修改头像
     */
    private void changePhoto(){
        WaitDialog.show(me,"正在保存...");
        OkHttpUtils.post().url(AllStatus.BASE_URL + "Member/modBaseData")
                .addParams("token",token)
                .addFile("head_pic",images.get(0).getPath(),new File(images.get(0).getPath()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        toast(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        WaitDialog.dismiss();
                        Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(response);
                        if (stringMap.get("code").equals("1")) {
                            finish();
                        }
                        toast(stringMap.get("message"));
                    }
                });
    }

    /**
     * 修改昵称
     */
    private void changeNikeName(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Member/modBaseData", new Parameter()
                .add("token", token)
                .add("nickname", atyPersonalDataEtName.getText().toString()), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(response);
                if (stringMap.get("code").equals("1")) {
                    finish();
                }
                toast(stringMap.get("message"));
            }
        });
    }
    /**
     * 修改头像以及昵称
     */
    private void changeData(){
        WaitDialog.show(me,"正在保存...");
        OkHttpUtils.post().url(AllStatus.BASE_URL + "Member/modBaseData")
                .addParams("token",token)
                .addParams("nickname",atyPersonalDataEtName.getText().toString())
                .addFile("head_pic",images.get(0).getPath(),new File(images.get(0).getPath()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        toast(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        WaitDialog.dismiss();
                        Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(response);
                        if (stringMap.get("code").equals("1")) {
                            finish();
                        }
                        toast(stringMap.get("message"));
                    }
                });
    }
}
