package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/24 024 15:44:53.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SpecialExercisesBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"content":[{"title":"1","count":"2","is_fee":"1"},{"title":"2","count":"1","is_fee":"1"},{"title":"3","count":"1","is_fee":"2"},{"title":"4","count":"1","is_fee":"2"}],"pay":2}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : [{"title":"1","count":"2","is_fee":"1"},{"title":"2","count":"1","is_fee":"1"},{"title":"3","count":"1","is_fee":"2"},{"title":"4","count":"1","is_fee":"2"}]
         * pay : 2
         */

        private int pay;
        private List<ContentBean> content;

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * title : 1
             * count : 2
             * is_fee : 1
             */

            private String title;
            private String try_questions;
            private String count;
            private String is_fee;

            public String getTry_questions() {
                return try_questions;
            }

            public void setTry_questions(String try_questions) {
                this.try_questions = try_questions;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getIs_fee() {
                return is_fee;
            }

            public void setIs_fee(String is_fee) {
                this.is_fee = is_fee;
            }
        }
    }
}
