package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/11 011 10:15:26.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class UpdateBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"app_logo":"","app_name":"建工邦","app_version":"v1.0.0","app_intro":"444"}
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
         * app_logo :
         * app_name : 建工邦
         * app_version : v1.0.0
         * app_intro : 444
         */

        private String app_logo;
        private String app_name;
        private String app_version;
        private String app_intro;

        public String getApp_logo() {
            return app_logo;
        }

        public void setApp_logo(String app_logo) {
            this.app_logo = app_logo;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getApp_intro() {
            return app_intro;
        }

        public void setApp_intro(String app_intro) {
            this.app_intro = app_intro;
        }
    }
}
