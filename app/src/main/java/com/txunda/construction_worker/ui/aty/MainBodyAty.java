package com.txunda.construction_worker.ui.aty;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v2.WaitDialog;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.MainBodyBean;
import com.txunda.construction_worker.ui.adapter.CommentDetailsRvAdapter;
import com.txunda.construction_worker.ui.adapter.MainBodyImageAdapter;
import com.txunda.construction_worker.ui.fgt.HomeFgt;
import com.txunda.construction_worker.utils.AllStatus;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 正文
 */
@Layout(R.layout.aty_main_body)
public class MainBodyAty extends BaseAty {
    @BindView(R.id.header_iv_back)
    ImageView headerIvBack;
    @BindView(R.id.header_tv_title)
    TextView headerTvTitle;
    @BindView(R.id.iv_order_head)
    CircleImageView ivOrderHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.aty_comment_details_rv)
    RecyclerView atyCommentDetailsRv;
    @BindView(R.id.aty_comment_rb_great)
    RadioButton atyCommentRbGreat;
    @BindView(R.id.aty_comment_details_et_post)
    EditText atyCommentDetailsEtPost;
    @BindView(R.id.item_order_right)
    TextView itemOrderRight;
    private CommentDetailsRvAdapter adapter;
    private int page = 1;
    private String bbs_id;
    private MainBodyImageAdapter imageAdapter;
    @Override
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        headerTvTitle.setText("正文");
        bbs_id = getIntent().getStringExtra("bbs_id");
        atyCommentDetailsEtPost.setInputType(InputType.TYPE_NULL);
//        setRbSize(atyCommentRbGreat);
        atyCommentDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentDetailsRvAdapter(R.layout.item_comment_details_layout);
        atyCommentDetailsRv.setAdapter(adapter);
        rvImg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imageAdapter = new MainBodyImageAdapter(R.layout.item_shop_pic);
        rvImg.setAdapter(imageAdapter);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        super.initDatas(paramer);
        httpData();
    }

    @OnClick({R.id.header_iv_back, R.id.aty_comment_rb_great, R.id.item_order_right,R.id.aty_comment_details_et_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_comment_rb_great:
                pointGreat();
                break;
            case R.id.item_order_right:
                httpPostData();
                atyCommentDetailsEtPost.setText("");
                break;
            case R.id.aty_comment_details_et_post:
                showSoftInputFromWindow(atyCommentDetailsEtPost);
                break;
                default:break;
        }
    }
    /**
     * 设置radiobutton按钮drawble大小
     * @param rb
     */
    private void setRbSize(RadioButton rb) {
        Drawable[] compoundDrawables = rb.getCompoundDrawables();
        compoundDrawables[1].setBounds(0,0,70,70);
        rb.setCompoundDrawables(null,compoundDrawables[1],null,null);
    }

    public void showSoftInputFromWindow(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
        //设置光标显示
        editText.setCursorVisible(true);
    }

    @Override
    public void setEvents() {
        super.setEvents();
        imageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> data = adapter.getData();
                Intent intent = new Intent(me, ImageDetailsAty.class);
                intent.putExtra("url",data.get(position));
                startActivity(intent);
            }
        });
    }

    //TODO:---------------------------------------接口请求--------------------------------------------------
    //请求本页数据
    private void httpData(){
        WaitDialog.show(me,"正在加载...");
        HttpRequest.POST(this, AllStatus.BASE_URL + "Index/bbs_info", new Parameter()
                        .add("token", token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("bbs_id", bbs_id)
                        .add("page", String.valueOf(page)), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                MainBodyBean bodyBean = GsonUtil.GsonToBean(response,MainBodyBean.class);
                                Glide.with(me).load(bodyBean.getData().getDetails().getHead_pic()).into(ivOrderHead);
                                tvName.setText(bodyBean.getData().getDetails().getNickname());
                                tvTime.setText(bodyBean.getData().getDetails().getCreate_time());
                                tvContent.setText(bodyBean.getData().getDetails().getContent());
                                atyCommentRbGreat.setText(bodyBean.getData().getDetails().getSupport());
                                if (bodyBean.getData().getDetails().getIs_like() == 1){
                                    atyCommentRbGreat.setChecked(true);
                                    atyCommentRbGreat.setTextColor(Color.parseColor("#FF7800"));
                                }else if (bodyBean.getData().getDetails().getIs_like() == 2){
                                    atyCommentRbGreat.setChecked(false);
                                    atyCommentRbGreat.setTextColor(Color.parseColor("#666666"));
                                }
                                if (bodyBean.getData().getDetails().getContent_pic().size()>0){
                                    llImg.setVisibility(View.VISIBLE);
                                    List<String> path_list = new ArrayList<>();
                                    for (int i = 0; i < bodyBean.getData().getDetails().getContent_pic().size(); i++) {
                                        path_list.add(bodyBean.getData().getDetails().getContent_pic().get(i).getPath());
                                    }
//                                    ImageAdapter imageAdapter = new ImageAdapter(me, path_list);
//                                    rvImg.setAdapter(imageAdapter);
                                    imageAdapter.setNewData(path_list);
                                    imageAdapter.notifyDataSetChanged();
                                }else {
                                    llImg.setVisibility(View.GONE);
                                }
                                adapter.setNewData(bodyBean.getData().getComment());
                                adapter.notifyDataSetChanged();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }
    //提交评论
    private void httpPostData(){
        HttpRequest.POST(this, AllStatus.BASE_URL + "Index/publish", new Parameter()
                        .add("token", token)
                        .add("industry_id", HomeFgt.Industry_ID)
                        .add("bbs_id", bbs_id)
                        .add("replay_content", atyCommentDetailsEtPost.getText().toString())
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                httpData();
                                toast(objectMap.get("message"));
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                });
    }
    //点赞/取消点赞
    private void pointGreat(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Index/like", new Parameter()
                        .add("token", token)
                        .add("bbs_id", bbs_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (objectMap.get("code").equals("1")){
                                httpData();
                            }else {
                                showErrorTip(objectMap.get("message").toString());
                            }
                        }
                    }
                }
        );
    }
}