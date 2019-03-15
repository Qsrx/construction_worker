package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/30 030 8:34:53.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class OrderBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"order_id":"167","title":"题库购买","sub_title":"一级建造师-法律法规题库","pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","son_title":"可获得本科目全部课程","price":"1088.00","pay_status":"3","evaluate":2},{"order_id":"166","title":"课程购买","sub_title":"【全科】一级建造师中级班","pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","son_title":"赠一级建造师全科题库","price":"108.00","pay_status":"1","evaluate":2},{"order_id":"165","title":"题库购买","sub_title":"一级建造师-法律法规题库","pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","son_title":"可获得本科目全部课程","price":"10.00","pay_status":"2","evaluate":1}]
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
         * order_id : 167
         * title : 题库购买
         * sub_title : 一级建造师-法律法规题库
         * pic : http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
         * son_title : 可获得本科目全部课程
         * price : 1088.00
         * pay_status : 3
         * evaluate : 2
         */

        private String order_id;
        private String title;
        private String sub_title;
        private String pic;
        private String son_title;
        private String price;
        private String pay_status;
        private int evaluate;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSon_title() {
            return son_title;
        }

        public void setSon_title(String son_title) {
            this.son_title = son_title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public int getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(int evaluate) {
            this.evaluate = evaluate;
        }
    }
}
