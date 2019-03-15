package com.txunda.construction_worker.ui.aty;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.ui.adapter.CommentDetailsRvAdapter;
import com.txunda.construction_worker.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的评论&评论我的
 * 2019年1月2日14:15:16
 */
@Layout(R.layout.aty_comment_details)
public class CommentDetailsAty extends BaseAty {
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
    @BindView(R.id.aty_comment_details_rv)
    RecyclerView atyCommentDetailsRv;
    @BindView(R.id.aty_comment_rb_great)
    RadioButton atyCommentRbGreat;
    @BindView(R.id.aty_comment_details_et_post)
    EditText atyCommentDetailsEtPost;
    @BindView(R.id.item_order_right)
    TextView itemOrderRight;
    private CommentDetailsRvAdapter adapter;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        StatusBarUtil.StatusBarLightMode(this);
        String type = getIntent().getStringExtra("type");
        atyCommentDetailsEtPost.setInputType(InputType.TYPE_NULL);
        setRbSize(atyCommentRbGreat);
        if (type.equals("1")) {
            headerTvTitle.setText("我的评论");
        } else {
            headerTvTitle.setText("评论我的");
        }
        atyCommentDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentDetailsRvAdapter(R.layout.item_comment_details_layout);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i+"");
        }
        atyCommentDetailsRv.setAdapter(adapter);
//        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
    }
    @OnClick({R.id.header_iv_back, R.id.aty_comment_rb_great,R.id.aty_comment_details_et_post, R.id.item_order_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.aty_comment_rb_great:
                atyCommentRbGreat.setTextColor(Color.parseColor("#FF7800"));
                break;
            case R.id.item_order_right:
                break;
            case R.id.aty_comment_details_et_post:
                showSoftInputFromWindow(atyCommentDetailsEtPost);
                break;
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
}