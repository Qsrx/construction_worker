package com.txunda.construction_worker.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/27 027 15:05:27.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class WxPayBean {

    /**
     * code : 1
     * message : 微信支付
     * data : {"appid":"wxac1846250f4a6c39","partnerid":"1523826841","prepayid":"wx271504367128817aab7489be0961640348","package":"Sign=WXPay","noncestr":"kAT7LjUhkH2c2WDG","timestamp":"1551251076","sign":"F92A901ACE9557A847B9779F01A52C6D"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * prepayid : wx271504367128817aab7489be0961640348
         * package : Sign=WXPay
         * noncestr : kAT7LjUhkH2c2WDG
         * timestamp : 1551251076
         * sign : F92A901ACE9557A847B9779F01A52C6D
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;

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
    }
}
