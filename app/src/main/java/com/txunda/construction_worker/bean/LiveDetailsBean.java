package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/18 018 10:29:29.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class LiveDetailsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"建造师直播","text":"\n\t这里事直播详情这里事直播详情\n\n\n\t这里事直播详情这里事直播详情\n\n\n\t这里事直播详情这里事直播详情\n\n\n\n\t\n\n","pic":"http//jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg","url":"http://www.baidu.com"}
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
         * title : 建造师直播
         * text :
         这里事直播详情这里事直播详情


         这里事直播详情这里事直播详情


         这里事直播详情这里事直播详情






         * pic : http//jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg
         * url : http://www.baidu.com
         */

        private String title;
        private String text;
        private String pic;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
