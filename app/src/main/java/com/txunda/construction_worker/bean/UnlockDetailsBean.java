package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/25 025 15:36:38.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class UnlockDetailsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","title":"一级建造师-法律法规","price":"800.00","desc":" \r\n\t这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍\r\n  \r\n \r\n\t这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍\r\n  \r\n \r\n\t这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍\r\n  \r\n \r\n\t这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍 \r\n\r\n  "}
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
         * desc :
         这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍


         这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍


         这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍


         这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍这里是题库介绍


         */

        private String pic;
        private String title;
        private String price;
        private String desc;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
