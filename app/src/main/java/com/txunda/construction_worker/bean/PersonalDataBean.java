package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/10 010 9:38:02.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class PersonalDataBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"m_id":"144","account":"183****5005","head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"fdawsBi"}
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
         * m_id : 144
         * account : 183****5005
         * head_pic : http://jgb.cn/Uploads/Member/default.png
         * nickname : fdawsBi
         */

        private String m_id;
        private String account;
        private String head_pic;
        private String nickname;

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

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
