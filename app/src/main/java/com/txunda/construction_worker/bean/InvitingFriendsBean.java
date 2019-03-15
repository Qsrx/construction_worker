package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/15 015 15:46:57.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class InvitingFriendsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"share_code":"4183056","count":"12","all_award":12}
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
         * share_code : 4183056
         * count : 12
         * all_award : 12
         */

        private String share_code;
        private String count;
        private String rule;
        private int all_award;

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getShare_code() {
            return share_code;
        }

        public void setShare_code(String share_code) {
            this.share_code = share_code;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getAll_award() {
            return all_award;
        }

        public void setAll_award(int all_award) {
            this.all_award = all_award;
        }
    }
}
