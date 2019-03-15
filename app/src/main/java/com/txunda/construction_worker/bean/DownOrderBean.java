package com.txunda.construction_worker.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/27 027 13:20:03.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DownOrderBean {

    /**
     * code : 1
     * message : 下单成功
     * data : {"appid":"wxac1846250f4a6c39","partnerid":"1523826841","prepayid":"wx2518040646914925334bd0a63559282867","package":"Sign=WXPay","noncestr":"EyCvCdcq5kfaRc9k","timestamp":"1551089039","sign":"C7ECE44F62E3024B4521599E0E08AD6F","order_id":"186"}
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
         * appid : wxac1846250f4a6c39
         * partnerid : 1523826841
         * prepayid : wx2518040646914925334bd0a63559282867
         * package : Sign=WXPay
         * noncestr : EyCvCdcq5kfaRc9k
         * timestamp : 1551089039
         * sign : C7ECE44F62E3024B4521599E0E08AD6F
         * order_id : 186
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;
        private String order_id;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
