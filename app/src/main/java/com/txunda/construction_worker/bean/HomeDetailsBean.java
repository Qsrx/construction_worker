package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/9 009 15:15:02.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class HomeDetailsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"报考须知","cover":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg","content":"","create_time":"建工邦 2019/01/08"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * title : 报考须知
         * cover : http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg
         * content :
         * create_time : 建工邦 2019/01/08
         */

        private String title;
        private String cover;
        private String content;
        private String create_time;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
