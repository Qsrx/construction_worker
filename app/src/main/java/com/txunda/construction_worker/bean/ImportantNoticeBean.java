package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/27 027 21:34:17.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ImportantNoticeBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"read_msg":6}
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
         * read_msg : 6
         */

        private int read_msg;

        public int getRead_msg() {
            return read_msg;
        }

        public void setRead_msg(int read_msg) {
            this.read_msg = read_msg;
        }
    }
}
