package com.ants.theantsgo.tips;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:50
 * 描述：直接调取弹窗
 * ===============Txunda===============
 */
public class MikyouCommonDialog {

    private Context context;
    private int customeLayoutId;
    private String dialogTitle;
    private String dialogMessage;
    private String positiveText;
    private String negativeText;

    private View dialogView;
    private OnDialogListener listener;

    public static final int OK = 0x123;
    public static final int NO = 0x124;

    /**
     * 添加一个自定义布局
     *
     * @param context
     * @param customeLayoutId
     * @param dialogTitle
     * @param positiveText
     * @param negativeText
     */
    public MikyouCommonDialog(Context context, int customeLayoutId, String dialogTitle, String positiveText,
                              String negativeText) {
        this.context = context;
        this.customeLayoutId = customeLayoutId;
        this.dialogTitle = dialogTitle;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        this.dialogView = View.inflate(context, customeLayoutId, null);
    }

    /**
     * 不添加自定义布局
     *
     * @param context
     * @param dialogMessage 提示内容
     * @param dialogTitle   标题
     * @param positiveText  按钮
     * @param negativeText  按钮
     */
    public MikyouCommonDialog(Context context, String dialogMessage, String dialogTitle, String positiveText,
                              String negativeText) {
        this.context = context;
        this.dialogTitle = dialogTitle;
        this.dialogMessage = dialogMessage;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
    }

    public View getDialogView() {
        return dialogView;
    }

    public void setDialogView(View dialogView) {
        this.dialogView = dialogView;
    }

    public void showDialog() {
        CustomDialog.Builder dialog = new CustomDialog.Builder(context);
        dialog.setTitle(dialogTitle);
        if (dialogMessage != null) {
            dialog.setMessage(dialogMessage);
        } else {
            dialog.setContentView(dialogView);
        }
        dialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                if (listener != null) {
                    listener.dialogListener(OK, dialogView, dialogInterface, which);
                }
            }
        }).setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                if (listener != null) {
                    listener.dialogListener(NO, dialogView, dialogInterface, which);
                }
            }
        }).create().show();
    }

    /**
     * 设置按钮点击事件监听
     *
     * @param listener
     * @return
     */
    public MikyouCommonDialog setOnDiaLogListener(OnDialogListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnDialogListener {
        public void dialogListener(int btnType, View customView, DialogInterface dialogInterface, int which);
    }

}
