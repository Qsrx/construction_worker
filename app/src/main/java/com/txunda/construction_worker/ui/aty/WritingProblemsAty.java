package com.txunda.construction_worker.ui.aty;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ants.theantsgo.base.BaseView;
import com.ants.theantsgo.httpTools.ApiTool2;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.lidroid.xutils.http.RequestParams;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.GridImageAdapter;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.ui.view.FullyGridLayoutManager;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 开发者： Hzy
 * 创建时间： 2018/12/22  11:29:05.
 * 功能描述：写问题
 * 联系方式： win_hzy@163.com
 */
@Layout(R.layout.aty_writing_problems)
public class WritingProblemsAty extends BaseAty implements BaseView{
    @BindView(R.id.aty_writing_cancel)
    TextView atyWritingCancel;
    @BindView(R.id.aty_writing_post)
    TextView atyWritingPost;
    @BindView(R.id.aty_writing_et_content)
    EditText atyWritingEtContent;
    @BindView(R.id.aty_writing_recycler)
    RecyclerView atyWritingRecycler;
    private int maxSelectNum = 5;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private PopupWindow pop;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        initWidget();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(me).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(me).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(me).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    /**
     * 对recyclerview进行一些基本修改
     */
    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        atyWritingRecycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        atyWritingRecycler.setAdapter(adapter);
    }

    /**
     * recyclerview点击事件
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            //第一种方式，弹出选择和拍照的dialog
            showPop();
        }
    };

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
                                .maxSelectNum(maxSelectNum)
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
    @OnClick({R.id.aty_writing_cancel, R.id.aty_writing_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_writing_cancel:
                finish();
                break;
            case R.id.aty_writing_post:
                //TODO:发布事件
                httpaddEvaluation();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
//                    pathFromURI = AdvertisingPositionPurchaseActivity.getRealPathFromURI(this, data1);
//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    /**
     * 发表评价请求
     */
    private void httpaddEvaluation() {
        WaitDialog.show(me,"发表中…");
        ApiTool2 apiTool = new ApiTool2();
        RequestParams params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("industry_id", HomeFgt.Industry_ID);
        params.addBodyParameter("content", atyWritingEtContent.getText().toString());
        for (int i = 0; i < selectList.size(); i++) {
            params.addBodyParameter("content_pic["+i+"]", new File(selectList.get(i).getPath()));
        }
        apiTool.postApi(AllStatus.BASE_URL + "Index/ask", params, this);
    }

    @Override
    public void showDialog() {
//        WaitDialog.show(me,"正在提交中……");
    }

    @Override
    public void showDialog(String text) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void removeDialog() {
//        WaitDialog.dismiss();
    }

    @Override
    public void removeContent() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onLoading(long total, long current, boolean isUploading) {

    }

    @Override
    public void onException(Exception exception) {

    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(jsonStr);
        if (stringMap.get("code").equals("1")){
            WaitDialog.dismiss();
            finish();
        }
        toast(stringMap.get("message"));
    }

    @Override
    public void onError(String requestUrl, Map<String, String> error) {
        showErrorTip(error.get("message"));
    }
}