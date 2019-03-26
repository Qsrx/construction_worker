package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/24 024 13:23:19.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ViewParsingBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"每日打卡练习-单项选择题","questions_id":120,"sub_title":"法律法规单选题（）","option":[{"question":"请问请问1","answer":"A"},{"question":"请问请问2","answer":"B"},{"question":"请问请问3","answer":"C"}],"answer1":"A","choose_answer":"C","analysis":"法律法规题很简单","key":"法律法规","collection":1}
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
         * title : 每日打卡练习-单项选择题
         * questions_id : 120
         * sub_title : 法律法规单选题（）
         * option : [{"question":"请问请问1","answer":"A"},{"question":"请问请问2","answer":"B"},{"question":"请问请问3","answer":"C"}]
         * answer1 : A
         * choose_answer : C
         * analysis : 法律法规题很简单
         * key : 法律法规
         * collection : 1
         */

        private String title;
        private int questions_id;
        private String sub_title;
        private String answer_pic;
        private String answer1;
        private String choose_answer;
        private String analysis;
        private String key;
        private int collection;
        private List<OptionBean> option;

        public String getAnswer_pic() {
            return answer_pic;
        }

        public void setAnswer_pic(String answer_pic) {
            this.answer_pic = answer_pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getQuestions_id() {
            return questions_id;
        }

        public void setQuestions_id(int questions_id) {
            this.questions_id = questions_id;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getAnswer1() {
            return answer1;
        }

        public void setAnswer1(String answer1) {
            this.answer1 = answer1;
        }

        public String getChoose_answer() {
            return choose_answer;
        }

        public void setChoose_answer(String choose_answer) {
            this.choose_answer = choose_answer;
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

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"title":"每日打卡练习-单项选择题","sub_title":"法律法规单选题（）","A":"请问请问1","B":"请问请问2","C":"请问请问3","D":"请问请问4","answer1":"A","choose_answer":"C","analysis":"法律法规题很简单","key":"法律法规"}
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
//         * title : 每日打卡练习-单项选择题
//         * sub_title : 法律法规单选题（）
//         * A : 请问请问1
//         * B : 请问请问2
//         * C : 请问请问3
//         * D : 请问请问4
//         * answer1 : A
//         * choose_answer : C
//         * analysis : 法律法规题很简单
//         * key : 法律法规
//         */
//
//        private String title;
//        private String sub_title;
//        private String A;
//        private String B;
//        private String C;
//        private String D;
//        private String answer1;
//        private String choose_answer;
//        private String analysis;
//        private String key;
//        private int collection;
//
//        public int getCollection() {
//            return collection;
//        }
//
//        public void setCollection(int collection) {
//            this.collection = collection;
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
//        public String getSub_title() {
//            return sub_title;
//        }
//
//        public void setSub_title(String sub_title) {
//            this.sub_title = sub_title;
//        }
//
//        public String getA() {
//            return A;
//        }
//
//        public void setA(String A) {
//            this.A = A;
//        }
//
//        public String getB() {
//            return B;
//        }
//
//        public void setB(String B) {
//            this.B = B;
//        }
//
//        public String getC() {
//            return C;
//        }
//
//        public void setC(String C) {
//            this.C = C;
//        }
//
//        public String getD() {
//            return D;
//        }
//
//        public void setD(String D) {
//            this.D = D;
//        }
//
//        public String getAnswer1() {
//            return answer1;
//        }
//
//        public void setAnswer1(String answer1) {
//            this.answer1 = answer1;
//        }
//
//        public String getChoose_answer() {
//            return choose_answer;
//        }
//
//        public void setChoose_answer(String choose_answer) {
//            this.choose_answer = choose_answer;
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
//    }
}
