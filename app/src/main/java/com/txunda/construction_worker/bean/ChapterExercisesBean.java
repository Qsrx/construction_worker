package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/26 026 10:20:43.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ChapterExercisesBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"title":"第一章:法律法规第一章","class_type":"1","count":"2"},{"title":"第二章:法律法规第二章","class_type":"2","count":"3"},{"title":"第三章:法律法规第三章","class_type":"3","count":"2"},{"title":"第四章:法律法规第四章","class_type":"4","count":"1"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 第一章:法律法规第一章
         * class_type : 1
         * count : 2
         */

        private String title;
        private String class_type;
        private String count;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getClass_type() {
            return class_type;
        }

        public void setClass_type(String class_type) {
            this.class_type = class_type;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
