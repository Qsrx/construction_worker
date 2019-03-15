package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/23 023 11:37:00.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DoWorkBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"questions_id":"61","title":"每日打卡练习-单项选择题","sub_title":"章节单选题（）","option":[{"question":"这是第一个大难","answer":"A"},{"question":"二哥啊大大","answer":"B"},{"question":"c奥术大师多说的","answer":"C"}],"analysis":"法律法规题很简单","key":"法律法规题","collection":2}
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
         * questions_id : 61
         * title : 每日打卡练习-单项选择题
         * sub_title : 章节单选题（）
         * option : [{"question":"这是第一个大难","answer":"A"},{"question":"二哥啊大大","answer":"B"},{"question":"c奥术大师多说的","answer":"C"}]
         * analysis : 法律法规题很简单
         * key : 法律法规题
         * collection : 2
         */

        private String questions_id;
        private String title;
        private String sub_title;
        private String analysis;
        private String key;
        private int collection;
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

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public List<OptionBean> getOption() {
            return option;
        }

        public void setOption(List<OptionBean> option) {
            this.option = option;
        }

        public static class OptionBean {
            /**
             * question : 这是第一个大难
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

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"id":"5","title":"专项第一节单选题（）","option_a":"a","option_b":"b","option_c":"c","option_d":"f","analysis":"法律法规题很简单","key":"法律法规题","sub_title":"每日打卡练习-单项选择题","collection":1}
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
//         * id : 5
//         * title : 专项第一节单选题（）
//         * option_a : a
//         * option_b : b
//         * option_c : c
//         * option_d : f
//         * analysis : 法律法规题很简单
//         * key : 法律法规题
//         * sub_title : 每日打卡练习-单项选择题
//         * collection : 1
//         */
//
//        private String id;
//        private String title;
//        private String option_a;
//        private String option_b;
//        private String option_c;
//        private String analysis;
//        private String key;
//        private String sub_title;
//        private int collection;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
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
//        public String getOption_a() {
//            return option_a;
//        }
//
//        public void setOption_a(String option_a) {
//            this.option_a = option_a;
//        }
//
//        public String getOption_b() {
//            return option_b;
//        }
//
//        public void setOption_b(String option_b) {
//            this.option_b = option_b;
//        }
//
//        public String getOption_c() {
//            return option_c;
//        }
//
//        public void setOption_c(String option_c) {
//            this.option_c = option_c;
//        }
//
//        public String getAnalysis() {
//            return analysis;
//        }
//
//        public void setAnalysis(String analysis) {
//            this.analysis = analysis;
//        }
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getSub_title() {
//            return sub_title;
//        }
//
//        public void setSub_title(String sub_title) {
//            this.sub_title = sub_title;
//        }
//
//        public int getCollection() {
//            return collection;
//        }
//
//        public void setCollection(int collection) {
//            this.collection = collection;
//        }
//    }
}
