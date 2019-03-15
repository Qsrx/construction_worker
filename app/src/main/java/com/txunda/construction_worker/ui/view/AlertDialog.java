package com.txunda.construction_worker.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ants.theantsgo.tool.ToolKit;
import com.ants.theantsgo.util.StringUtils;
import com.txunda.construction_worker.R;

/**
 * 开发者： Txd_WangJJ
 * 创建时间： 2018/11/20 020 9:59:52.
 * 功能描述：
 * 联系方式： jingjie.office@qq.com
 */
public class AlertDialog {
    private Context context;
    /**
     * Dialog
     */
    private Dialog dialog;
    /**
     * 整体布局
     */
    private LinearLayout lLayout_bg;
    /**
     * 标题
     */
    private TextView txt_title;
    /**
     * 提示消息
     */
    private TextView txt_msg;
    /**
     * 取消按钮
     */
    private Button btn_neg;
    /**
     * 确定按钮
     */
    private Button btn_pos;
    /**
     * 两个按钮就之间的分割线
     */
    private ImageView img_line;
    private ImageView addImage;
    /**
     * 是否显示图标题
     */
    private boolean showTitle = false;
    /**
     * 是否显示提示内容
     */
    private boolean showMsg = false;
    /**
     * 是否显示确定按钮
     */
    private boolean showPosBtn = false;
    /**
     * 是否显示取消按钮
     */
    private boolean showNegBtn = false;
    /**
     * 添加自定义布局
     */
    private LinearLayout addlayout;

    /**
     * 够造函数
     *
     * @param context 上下文
     */
    public AlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 初始化自定义Dialog布局参数
     *
     * @return AlertDialog
     */
    public AlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);
        addImage = (ImageView) view.findViewById(R.id.addImage);
        addlayout = (LinearLayout) view.findViewById(R.id.addlayout);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(
                new FrameLayout.LayoutParams((int) (ToolKit.getScreenWidth(context) * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return AlertDialog
     */
    public AlertDialog setTitle(String title) {
        showTitle = true;
//        String mTitle = StringUtils.nullStrToEmpty(title);
        String mTitle = StringUtils.nullStrToEmpty(title);
        if ("".equals(mTitle)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(mTitle);
        }
        return this;
    }

    /**
     * 设置提示信息
     *
     * @param msg 提示信息
     * @return AlertDialog
     */
    public AlertDialog setMsg(String msg) {
        showMsg = true;
        String mMsg = StringUtils.nullStrToEmpty(msg);
        if ("".equals(mMsg)) {
            txt_msg.setText("确定么?");
        } else {
            txt_msg.setText(mMsg);
        }
        return this;
    }

    /**
     * 设置图片
     *
     * @param i 资源id
     * @return AlertDialog
     */
    public AlertDialog setImage(int i) {
        addImage.setImageResource(i);
        return this;
    }

    /**
     * 设置自定义布局
     *
     * @param v 布局
     * @return AlterDialog
     */
    public AlertDialog setView(View v) {
        if (v != null) {
            addlayout.addView(v);
        }
        return this;
    }

    /**
     * 设置是否可取消
     *
     * @param cancel 是否可以取消
     * @return AlertDialog
     */
    public AlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param text     确定文本
     * @param listener 监听事件
     * @return AliertDialog
     */
    public AlertDialog setPositiveButton(String text, final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
                addlayout.removeAllViews();
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param text     取消文本
     * @param listener 监听事件
     * @return AliertDialog
     */
    public AlertDialog setNegativeButton(String text, final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
                addlayout.removeAllViews();
            }
        });
        return this;
    }

    /**
     * 设置布局
     */
    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {// 显示标题
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {// 显示提示信息
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {// 不显示"确定"和"取消"按钮的时候，只显示"确定"按钮
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {// 两个按钮都显示
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {// 显示确定按钮
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {// 显示取消按钮
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    /**
     * 显示Dialog
     */
    public void show() {
        setLayout();
        dialog.show();
    }
}