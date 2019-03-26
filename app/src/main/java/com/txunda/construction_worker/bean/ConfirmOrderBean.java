package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/18 018 14:06:45.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ConfirmOrderBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"taocan_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班","sub_title":"赠法律法规全科题库","price":"1288.00","balance":"10.00"}
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
         * taocan_id : 1
         * zu_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
         * title : 【全科】法律法规高级班
         * sub_title : 赠法律法规全科题库
         * price : 1288.00
         * balance : 10.00
         */

        private String taocan_id;
        private String zu_pic;
        private String title;
        private String sub_title;
        private String price;
        private String balance;
        private String agreement;

        public String getAgreement() {
            return agreement;
        }

        public void setAgreement(String agreement) {
            this.agreement = agreement;
        }

        public String getTaocan_id() {
            return taocan_id;
        }

        public void setTaocan_id(String taocan_id) {
            this.taocan_id = taocan_id;
        }

        public String getZu_pic() {
            return zu_pic;
        }

        public void setZu_pic(String zu_pic) {
            this.zu_pic = zu_pic;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班","price":"1288.00","balance":"10.00"}
//     */
//
//    private String code;
//    private String message;
//    private DataBean data;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * zu_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
//         * title : 【全科】工程项目管理高级班
//         * price : 1288.00
//         * balance : 10.00
//         */
//
//        private String zu_pic;
//        private String title;
//        private String sub_title;
//        private String price;
//        private String balance;
//
//        public String getSub_title() {
//            return sub_title;
//        }
//
//        public void setSub_title(String sub_title) {
//            this.sub_title = sub_title;
//        }
//
//        public String getZu_pic() {
//            return zu_pic;
//        }
//
//        public void setZu_pic(String zu_pic) {
//            this.zu_pic = zu_pic;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public String getBalance() {
//            return balance;
//        }
//
//        public void setBalance(String balance) {
//            this.balance = balance;
//        }
//    }
}