package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/13 013 9:59:04.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class PointTestBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"questions_id":"1","title":"单选","sub_title":"法律法规单选题（）","option":[{"question":"请问请问1","answer":"A"},{"question":"请问请问2","answer":"B"},{"question":"请问请问3","answer":"C"}],"analysis":"法律法规题很简单","key":"法律法规","is_fee":"1","collection":1,"pay":1}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * questions_id : 1
             * title : 单选
             * sub_title : 法律法规单选题（）
             * option : [{"question":"请问请问1","answer":"A"},{"question":"请问请问2","answer":"B"},{"question":"请问请问3","answer":"C"}]
             * analysis : 法律法规题很简单
             * key : 法律法规
             * is_fee : 1
             * collection : 1
             * pay : 1
             */

            private String questions_id;
            private String title;
            private String sub_title;
            private String analysis;
            private String key;
            private String is_fee;
            private int collection;
            private int pay;
            private List<OptionBean> option;

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

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public String getAnalysis() {
                return analysis;
            }

            public void setAnalysis(String analysis) {
                this.analysis = analysis;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getIs_fee() {
                return is_fee;
            }

            public void setIs_fee(String is_fee) {
                this.is_fee = is_fee;
            }

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            public static class OptionBean {
                /**
                 * question : 请问请问1
                 * answer : A
                 */

                private String question;
                private String answer;

                public String getQuestion() {
                    return question;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }
            }
        }
    }
}
