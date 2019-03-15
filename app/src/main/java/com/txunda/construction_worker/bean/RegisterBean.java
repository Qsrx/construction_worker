package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/8 008 16:42:30.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class RegisterBean {

    /**
     * code : 1
     * message : 注册成功
     * data : {"token":"e8d91dc308c9bb5739ce23444fd775eb","account":"13102098997"}
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
         * token : e8d91dc308c9bb5739ce23444fd775eb
         * account : 13102098997
         */

        private String token;
        private String account;
        private String im_account;
        private String im_password;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getIm_account() {
            return im_account;
        }

        public void setIm_account(String im_account) {
            this.im_account = im_account;
        }

        public String getIm_password() {
            return im_password;
        }

        public void setIm_password(String im_password) {
            this.im_password = im_password;
        }
    }
}
