package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/25 025 16:58:14.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ConfirmOrder2Bean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","title":"一级建造师-法律法规","price":"800.00","sub_title":"可获得本科目全部试题","balance":"10.00"}
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
         * pic : http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
         * title : 一级建造师-法律法规
         * price : 800.00
         * sub_title : 可获得本科目全部试题
         * balance : 10.00
         */

        private String pic;
        private String title;
        private String price;
        private String sub_title;
        private String balance;
        private String agreement;

        public String getAgreement() {
            return agreement;
        }

        public void setAgreement(String agreement) {
            this.agreement = agreement;
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

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
