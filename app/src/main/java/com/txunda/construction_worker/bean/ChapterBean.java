package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/26 026 14:44:54.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ChapterBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"title":"第一节前言","is_fee":"1","count":"1"},{"title":"第二节课程讲解","is_fee":"1","count":"1"}],"pay":1}
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
         * list : [{"title":"第一节前言","is_fee":"1","count":"1"},{"title":"第二节课程讲解","is_fee":"1","count":"1"}]
         * pay : 1
         */

        private int pay;
        private List<ListBean> list;

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : 第一节前言
             * is_fee : 1
             * count : 1
             */

            private String title;
            private String is_fee;
            private String count;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIs_fee() {
                return is_fee;
            }

            public void setIs_fee(String is_fee) {
                this.is_fee = is_fee;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}