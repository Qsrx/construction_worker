package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/23 023 9:43:20.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MistakesCollectionBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"type":[{"questions_typeid":"1","name":"专项练习题"},{"questions_typeid":"2","name":"章节练习题"},{"questions_typeid":"3","name":"模拟考试题（2019）"},{"questions_typeid":"4","name":"真题考试题（2018）"}],"list":[{"id":"1","questions_typeid":"1","title":"法律法规单选题","subtitle":"专项练习题错题集"}]}
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
        private List<TypeBean> type;
        private List<ListBean> list;

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class TypeBean {
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

        public static class ListBean {
            /**
             * id : 1
             * questions_typeid : 1
             * title : 法律法规单选题
             * subtitle : 专项练习题错题集
             */

            private String id;
            private String questions_typeid;
            private String title;
            private String subtitle;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQuestions_typeid() {
                return questions_typeid;
            }

            public void setQuestions_typeid(String questions_typeid) {
                this.questions_typeid = questions_typeid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }
        }
    }
}
