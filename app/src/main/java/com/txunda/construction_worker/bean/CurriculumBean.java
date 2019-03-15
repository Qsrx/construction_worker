package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/29 029 9:30:27.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class CurriculumBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"course_id":"1","pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","title":"一级建造师-法律法规","price":"300.00"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * course_id : 1
         * pic : http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
         * title : 一级建造师-法律法规
         * price : 300.00
         */

        private String course_id;
        private String pic;
        private String title;
        private String price;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
