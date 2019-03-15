package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/25 025 18:21:51.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class PublicationEvaluationBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"pic":"http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg"}
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
         * pic : http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg
         */

        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
