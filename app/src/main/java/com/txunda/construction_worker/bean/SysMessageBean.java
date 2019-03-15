package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/6 006 21:40:11.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SysMessageBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"act_msg_id":"3","act_msg_title":"标题","act_msg_content":"内容"}
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
         * act_msg_id : 3
         * act_msg_title : 标题
         * act_msg_content : 内容
         */

        private String act_msg_id;
        private String act_msg_title;
        private String act_msg_content;

        public String getAct_msg_id() {
            return act_msg_id;
        }

        public void setAct_msg_id(String act_msg_id) {
            this.act_msg_id = act_msg_id;
        }

        public String getAct_msg_title() {
            return act_msg_title;
        }

        public void setAct_msg_title(String act_msg_title) {
            this.act_msg_title = act_msg_title;
        }

        public String getAct_msg_content() {
            return act_msg_content;
        }

        public void setAct_msg_content(String act_msg_content) {
            this.act_msg_content = act_msg_content;
        }
    }
}
