package com.ants.theantsgo.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 金额判断(允许输入到小数点后两位)
 * 
 * @author Txunda_HZj
 *
 *         2017年4月1日上午11:45:25
 */
public class MoneyUtils {
	public static void setPricePoint(final EditText editText) {

		/**
		 * 添加EdiText内容被改变的监听事件
		 */
		editText.addTextChangedListener(new TextWatcher() {

			/**
			 * 正在改变内容
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.toString().contains(".")) {// 判断输入的文本是不是包含小数点
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {// 判断师傅是小数点后面有两位有效数字(字符串的长度-1-小数点的位置)
						s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {// 剔除掉字符串头部和尾部的空白，并获取首尾字符，判断其是否是小数点
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);// 让光标移到尾部
				}

				// 字符串以"0"开始，并且剔除掉首尾的空白之后，长度大于1
				if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {// 判断第二位是不是小数点
						editText.setText(s.subSequence(0, 1));// 截取字符串，只保留第一位"0"
						editText.setSelection(1);// 光标移动到字符串结尾处
						return;
					}
				}
			}

			/**
			 * 内容改变之前
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			/**
			 * 内容改变之后
			 */
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
}
