package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/30 030 11:14:38.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class OrderDetailsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"课程购买","sub_title":"【全科】一级建造师中级班","pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"1088.00","son_title":"赠一级建造师全科题库","balance":"22.00","true_price":"010","ordenum":"WX2019012555485054","pay_way":"微信支付","create_time":"2019/01/29 16:48:32","update_time":"2019/01/29 16:48:32","pay_status":"1","evaluate":2}
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
         * title : 课程购买
         * sub_title : 【全科】一级建造师中级班
         * pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
         * price : 1088.00
         * son_title : 赠一级建造师全科题库
         * balance : 22.00
         * true_price : 010
         * ordenum : WX2019012555485054
         * pay_way : 微信支付
         * create_time : 2019/01/29 16:48:32
         * update_time : 2019/01/29 16:48:32
         * pay_status : 1
         * evaluate : 2
         */

        private String title;
        private String sub_title;
        private String pic;
        private String price;
        private String son_title;
        private String balance;
        private String true_price;
        private String ordenum;
        private String pay_way;
        private String create_time;
        private String update_time;
        private String pay_status;
        private int evaluate;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSon_title() {
            return son_title;
        }

        public void setSon_title(String son_title) {
            this.son_title = son_title;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTrue_price() {
            return true_price;
        }

        public void setTrue_price(String true_price) {
            this.true_price = true_price;
        }

        public String getOrdenum() {
            return ordenum;
        }

        public void setOrdenum(String ordenum) {
            this.ordenum = ordenum;
        }

        public String getPay_way() {
            return pay_way;
        }

        public void setPay_way(String pay_way) {
            this.pay_way = pay_way;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
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
