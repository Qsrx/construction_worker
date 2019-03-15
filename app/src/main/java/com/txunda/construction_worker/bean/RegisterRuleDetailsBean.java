package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/15 015 10:18:16.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class RegisterRuleDetailsBean {

    /**
     * code : 1
     * message : 成功
     * data : {"content":"注册规则接口","title":"注册规则接口","cover":"http://elife.txunda.com/Uploads/Index/2018-09-25/5ba9e6fa34576.png"}
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
         * content : 注册规则接口
         * title : 注册规则接口
         * cover : http://elife.txunda.com/Uploads/Index/2018-09-25/5ba9e6fa34576.png
         */

        private String content;
        private String title;
        private String cover;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
