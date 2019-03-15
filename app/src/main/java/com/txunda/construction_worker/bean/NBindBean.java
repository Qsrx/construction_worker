package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/25 025 15:35:54.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class NBindBean {

    /**
     * code : 1
     * message : 登录成功
     * data : {"bind_id":"1","account":""}
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
         * bind_id : 1
         * account :
         */

        private String bind_id;
        private String account;

        public String getBind_id() {
            return bind_id;
        }

        public void setBind_id(String bind_id) {
            this.bind_id = bind_id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
