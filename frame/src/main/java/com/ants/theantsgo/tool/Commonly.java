package com.ants.theantsgo.tool;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：下午 4:55
 * 描述：一些常用方法
 * ===============Txunda===============
 */
public class Commonly {

    /**
     * 当字符串为null时所要显示的字符串
     *
     * @param oldText
     * @param newText
     * @return String
     */
    public static String getTextByNull(String oldText, String newText) {
        return TextUtils.isEmpty(oldText) ? newText : oldText;
    }

    /**
     * 获取edittext的文字
     *
     * @param editText
     * @return <p>
     * String
     */
    public static String getViewText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static Spanned stringToSpan(String src) {
        return src == null ? null : Html.fromHtml(src.replace("\n", "<br />"));
    }

    /**
     * 给字符串添加颜色
     *
     * @param src   字符串
     * @param color 颜色
     * @return String   改变颜色之后的字符串
     */
    public static String colorFont(String src, String color) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("<font color=").append(color).append(">").append(src).append("</font>");
        return strBuf.toString();
    }

    public static String makeHtmlNewLine() {
        return "<br />";
    }

    /**
     * 给字符串加空格
     *
     * @param number
     * @return String
     */
    public static String makeHtmlSpace(int number) {
        final String space = "&nbsp;";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number; i++) {
            result.append(space);
        }
        return result.toString();
    }

    /**
     * 获取str字符串中还有多少个key
     *
     * @param str
     * @param key
     * @return
     */
    public static int getSubString(String str, String key) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }

}
