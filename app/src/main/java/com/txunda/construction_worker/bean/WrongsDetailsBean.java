package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/3 003 17:45:04.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class WrongsDetailsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"章节练习题","sub_title":"在编制施工组织设计文件时，施工部署及施工方案的内容应当包括（）。","questions_id":"994","option":[{"question":"合理安排施工顺序","answer":"A"},{"question":"对可能的施工方案进行评价并决策","answer":"B"},{"question":"确定主要施工方法","answer":"C"},{"question":"绘制施工平面图","answer":"D"},{"question":"编制资源需求计划","answer":"E"}],"answer":"A,B,C","analysis":"本题考查施工组织设计中施工部署及施工方案的内容一施工部署及施工方案包括合理安排施工顺序、确定主要工程的施工方案以及对可能的施工方案进行评价并决策。D选项绘制施工平面图是施工组织设计中与施工部署及施工方案平行的另一项工作；E选项为施工进度计划中的工作。所以正确选项为A、B、C。","key":"施工组织设计的内容和编制方法","collection":2}
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
         * title : 章节练习题
         * sub_title : 在编制施工组织设计文件时，施工部署及施工方案的内容应当包括（）。
         * questions_id : 994
         * option : [{"question":"合理安排施工顺序","answer":"A"},{"question":"对可能的施工方案进行评价并决策","answer":"B"},{"question":"确定主要施工方法","answer":"C"},{"question":"绘制施工平面图","answer":"D"},{"question":"编制资源需求计划","answer":"E"}]
         * answer : A,B,C
         * analysis : 本题考查施工组织设计中施工部署及施工方案的内容一施工部署及施工方案包括合理安排施工顺序、确定主要工程的施工方案以及对可能的施工方案进行评价并决策。D选项绘制施工平面图是施工组织设计中与施工部署及施工方案平行的另一项工作；E选项为施工进度计划中的工作。所以正确选项为A、B、C。
         * key : 施工组织设计的内容和编制方法
         * collection : 2
         */

        private String title;
        private String sub_title;
        private String questions_id;
        private String answer_pic;
        private String answer;
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

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getQuestions_id() {
            return questions_id;
        }

        public void setQuestions_id(String questions_id) {
            this.questions_id = questions_id;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
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
             * question : 合理安排施工顺序
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
