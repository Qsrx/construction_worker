package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/29 029 10:57:02.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ExercisesBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"questions_id":"1","title":"法律法规单选题（）"}]
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
         * questions_id : 1
         * title : 法律法规单选题（）
         */

        private String questions_id;
        private String title;

        public String getQuestions_id() {
            return questions_id;
        }

        public void setQuestions_id(String questions_id) {
            this.questions_id = questions_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
