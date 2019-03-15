package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/24 024 10:24:12.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class PunchClockSuccessBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"right":2,"answer":"A"}
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
         * right : 2
         * answer : A
         */

        private int right;
        private String answer;

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
