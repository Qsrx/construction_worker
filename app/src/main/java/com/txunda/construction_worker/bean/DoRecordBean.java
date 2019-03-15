package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/22 022 15:39:37.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class DoRecordBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"questions_type":[{"questions_typeid":"1","name":"专项练习题"},{"questions_typeid":"2","name":"章节练习题"},{"questions_typeid":"3","name":"模拟考试题（2019）"},{"questions_typeid":"4","name":"真题考试题（2018）"}],"record":[{"questions_typeid":"2","ty_name":"章节练习题(第一章第一节)","create_time":"2019/01/20","count":"2","right":"1","done":"2"},{"questions_typeid":"3","ty_name":"模拟考试题（2019）","create_time":"2019/01/20","count":"1","right":"0","done":"2"},{"questions_typeid":"1","ty_name":"专项练习题(多选)","create_time":"2019/01/20","count":"4","right":"0","done":"2"}]}
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
        private List<QuestionsTypeBean> questions_type;
        private List<RecordBean> record;

        public List<QuestionsTypeBean> getQuestions_type() {
            return questions_type;
        }

        public void setQuestions_type(List<QuestionsTypeBean> questions_type) {
            this.questions_type = questions_type;
        }

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class QuestionsTypeBean {
            /**
             * questions_typeid : 1
             * name : 专项练习题
             */

            private String questions_typeid;
            private String name;

            public String getQuestions_typeid() {
                return questions_typeid;
            }

            public void setQuestions_typeid(String questions_typeid) {
                this.questions_typeid = questions_typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class RecordBean {
            /**
             * questions_typeid : 2
             * ty_name : 章节练习题(第一章第一节)
             * create_time : 2019/01/20
             * count : 2
             * right : 1
             * done : 2
             */

            private String questions_typeid;
            private String ty_name;
            private String create_time;
            private String exercise_type;
            private String class_type;
            private String chapter_num;
            private String count;
            private String right;
            private String done;

            @Override
            public String toString() {
                return "RecordBean{" +
                        "questions_typeid='" + questions_typeid + '\'' +
                        ", ty_name='" + ty_name + '\'' +
                        ", create_time='" + create_time + '\'' +
                        ", count='" + count + '\'' +
                        ", right='" + right + '\'' +
                        ", done='" + done + '\'' +
                        '}';
            }

            public String getExercise_type() {
                return exercise_type;
            }

            public void setExercise_type(String exercise_type) {
                this.exercise_type = exercise_type;
            }

            public String getClass_type() {
                return class_type;
            }

            public void setClass_type(String class_type) {
                this.class_type = class_type;
            }

            public String getChapter_num() {
                return chapter_num;
            }

            public void setChapter_num(String chapter_num) {
                this.chapter_num = chapter_num;
            }

            public String getQuestions_typeid() {
                return questions_typeid;
            }

            public void setQuestions_typeid(String questions_typeid) {
                this.questions_typeid = questions_typeid;
            }

            public String getTy_name() {
                return ty_name;
            }

            public void setTy_name(String ty_name) {
                this.ty_name = ty_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getRight() {
                return right;
            }

            public void setRight(String right) {
                this.right = right;
            }

            public String getDone() {
                return done;
            }

            public void setDone(String done) {
                this.done = done;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "questions_type=" + questions_type +
                    ", record=" + record +
                    '}';
        }
    }
}