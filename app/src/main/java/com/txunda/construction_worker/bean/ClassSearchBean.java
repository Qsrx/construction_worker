package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/26 026 19:07:17.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ClassSearchBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"taocan_id":"1","type":"高级班","price":"1288.00","taocan_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】一级建造师高级班","num":"2人已付费"},{"taocan_id":"2","type":"中级班","price":"1088.00","taocan_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】一级建造师中级班","num":"2人已付费"}]
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
         * taocan_id : 1
         * type : 高级班
         * price : 1288.00
         * taocan_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
         * title : 【全科】一级建造师高级班
         * num : 2人已付费
         */

        private String taocan_id;
        private String type;
        private String price;
        private String taocan_pic;
        private String title;
        private String num;

        public String getTaocan_id() {
            return taocan_id;
        }

        public void setTaocan_id(String taocan_id) {
            this.taocan_id = taocan_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTaocan_pic() {
            return taocan_pic;
        }

        public void setTaocan_pic(String taocan_pic) {
            this.taocan_pic = taocan_pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
