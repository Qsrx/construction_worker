package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/26 026 14:23:23.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class WatchingHistoryBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"course_id":"1","pic":"http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg","title":"二级建造师-市政工程","mulu":"第1节","time":"2019-02-26 10:15:47"},{"course_id":"2","pic":"http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg","title":"二级建造师-市政工程","mulu":"第2节","time":"2019-02-26 10:15:47"},{"course_id":"3","pic":"http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg","title":"二级建造师-市政工程","mulu":"第3节","time":"2019-02-26 10:15:47"}]
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
         * pic : http://jiangongbang.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg
         * title : 二级建造师-市政工程
         * mulu : 第1节
         * time : 2019-02-26 10:15:47
         */

        private String course_id;
        private String pic;
        private String title;
        private String mulu;
        private String time;

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

        public String getMulu() {
            return mulu;
        }

        public void setMulu(String mulu) {
            this.mulu = mulu;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
