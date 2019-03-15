package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/10 010 10:49:48.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class RedPackageBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"balance":"22.00","content":[{"title":"购买一级建造师法律法规课程","create_time":"2019/01/04 17:54","money":"-128.00","type":2,"symbol":"-"},{"title":"邀请好友注册奖励红包","create_time":"2019/01/04 17:54","money":"+1.00","type":1,"symbol":"+"},{"title":"注册奖励红包","create_time":"2019/01/04 16:53","money":"+10.00","type":1,"symbol":"+"}]}
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
         * balance : 22.00
         * content : [{"title":"购买一级建造师法律法规课程","create_time":"2019/01/04 17:54","money":"-128.00","type":2,"symbol":"-"},{"title":"邀请好友注册奖励红包","create_time":"2019/01/04 17:54","money":"+1.00","type":1,"symbol":"+"},{"title":"注册奖励红包","create_time":"2019/01/04 16:53","money":"+10.00","type":1,"symbol":"+"}]
         */

        private String balance;
        private List<ContentBean> content;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * title : 购买一级建造师法律法规课程
             * create_time : 2019/01/04 17:54
             * money : -128.00
             * type : 2
             * symbol : -
             */

            private String title;
            private String create_time;
            private String money;
            private int type;
            private String symbol;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }
        }
    }
}