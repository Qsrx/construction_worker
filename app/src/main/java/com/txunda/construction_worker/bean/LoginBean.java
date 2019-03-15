package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/9 009 9:15:44.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class LoginBean {

    /**
     * code : 1
     * message : 登录成功
     * data : {"m_id":"1","account":"17210213617","nickname":"多发点发","head_pic":"http://taoback.txunda.com/Uploads/Member/2018-05-24/5b061cd9c55ca.jpg","token":"152747691430774","expired_time":"1528081714"}
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
         * m_id : 1
         * account : 17210213617
         * nickname : 多发点发
         * head_pic : http://taoback.txunda.com/Uploads/Member/2018-05-24/5b061cd9c55ca.jpg
         * token : 152747691430774
         * expired_time : 1528081714
         */

        private String m_id;
        private String account;
        private String nickname;
        private String head_pic;
        private String token;
        private String expired_time;
        private String im_account;
        private String im_password;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(String expired_time) {
            this.expired_time = expired_time;
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
