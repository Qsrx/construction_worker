package com.txunda.construction_worker.ui.fgt;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.CourseSelectionBean;
import com.txunda.construction_worker.ui.aty.CourseSelectionAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.Map;

/**
 * 课堂笔记
 */
@Layout(R.layout.fgt_classroom_notes)
public class ClassroomNotesFgt extends BaseFgt {
    EditText editText;
    public static String tv_note = "";
    @Override
    public void initViews() {
        super.initViews();
        editText = findViewById(R.id.fgt_classroom_notes_tv);
    }
    @Override
    public void initDatas() {
        super.initDatas();
        HttpRequest.POST(me, AllStatus.BASE_URL + "Course/Course_selection", new Parameter()
                        .add("token", token)
                        .add("subject_id", CourseSelectionAty.subject_id), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.d("cousebean", "onResponse: ============" + response);
                            Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            try {
                                if (objectMap.get("code").equals("1")) {
                                    CourseSelectionBean selectionBean = GsonUtil.GsonToBean(response, CourseSelectionBean.class);
                                    editText.setText(selectionBean.getData().getNotes());
                                    tv_note = selectionBean.getData().getNotes();
                                }
                            } catch (NullPointerException e) {
                            }

                        }
                    }
                }
        );
    }

    @Override
    public void setEvents() {
        super.setEvents();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_note = editText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_note = editText.getText().toString();
            }
        });
    }
}
