package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/30 030 17:14:24.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class VerifyBean {

    /**
     * code : 1
     * message : 登录成功
     * data : {"m_id":"140","account":"13523726523","token":"14bb5792f2c698cf883bc0dac9c1053e"}
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
         * m_id : 140
         * account : 13523726523
         * token : 14bb5792f2c698cf883bc0dac9c1053e
         */

        private String m_id;
        private String account;
        private String token;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
