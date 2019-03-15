package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/9 009 17:34:06.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MyBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"id":"159","account":"138****5862","nickname":"王根基","head_pic":"http://jgb.cn/Uploads/Member/default.png","ask":"10","collection":"5","comment":2,"read_msg":0}
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
         * id : 159
         * account : 138****5862
         * nickname : 王根基
         * head_pic : http://jgb.cn/Uploads/Member/default.png
         * ask : 10
         * collection : 5
         * comment : 2
         * read_msg : 0
         */

        private String id;
        private String account;
        private String nickname;
        private String head_pic;
        private String ask;
        private String collection;
        private int comment;
        private int read_msg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getAsk() {
            return ask;
        }

        public void setAsk(String ask) {
            this.ask = ask;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getRead_msg() {
            return read_msg;
        }

        public void setRead_msg(int read_msg) {
            this.read_msg = read_msg;
        }
    }
}
