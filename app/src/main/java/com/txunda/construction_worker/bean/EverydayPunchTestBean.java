package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/23 023 10:26:48.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class EverydayPunchTestBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"content":{"poetry":"不积跬步，无以至千里;不积小流，无以成江河。","author":"荀子","date":"2019/01/22"},"list":[{"questions_id":"16","title":"2019/01/20单选题"},{"questions_id":"16","title":"2019/01/20单选题"}],"sign":1}
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
         * content : {"poetry":"不积跬步，无以至千里;不积小流，无以成江河。","author":"荀子","date":"2019/01/22"}
         * list : [{"questions_id":"16","title":"2019/01/20单选题"},{"questions_id":"16","title":"2019/01/20单选题"}]
         * sign : 1
         */

        private ContentBean content;
        private int sign;
        private List<ListBean> list;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ContentBean {
            /**
             * poetry : 不积跬步，无以至千里;不积小流，无以成江河。
             * author : 荀子
             * date : 2019/01/22
             */

            private String poetry;
            private String author;
            private String date;

            public String getPoetry() {
                return poetry;
            }

            public void setPoetry(String poetry) {
                this.poetry = poetry;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ListBean {
            /**
             * questions_id : 16
             * title : 2019/01/20单选题
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

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"content":{"poetry":"不积跬步，无以至千里;不积小流，无以成江河。","author":"荀子","date":"2019/01/22"},"list":[{"questions_id":"16","title":"2019/01/20单选题"},{"questions_id":"16","title":"2019/01/20单选题"}]}
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
//         * content : {"poetry":"不积跬步，无以至千里;不积小流，无以成江河。","author":"荀子","date":"2019/01/22"}
//         * list : [{"questions_id":"16","title":"2019/01/20单选题"},{"questions_id":"16","title":"2019/01/20单选题"}]
//         */
//
//        private ContentBean content;
//        private List<ListBean> list;
//
//        public ContentBean getContent() {
//            return content;
//        }
//
//        public void setContent(ContentBean content) {
//            this.content = content;
//        }
//
//        public List<ListBean> getList() {
//            return list;
//        }
//
//        public void setList(List<ListBean> list) {
//            this.list = list;
//        }
//
//        public static class ContentBean {
//            /**
//             * poetry : 不积跬步，无以至千里;不积小流，无以成江河。
//             * author : 荀子
//             * date : 2019/01/22
//             */
//
//            private String poetry;
//            private String author;
//            private String date;
//
//            public String getPoetry() {
//                return poetry;
//            }
//
//            public void setPoetry(String poetry) {
//                this.poetry = poetry;
//            }
//
//            public String getAuthor() {
//                return author;
//            }
//
//            public void setAuthor(String author) {
//                this.author = author;
//            }
//
//            public String getDate() {
//                return date;
//            }
//
//            public void setDate(String date) {
//                this.date = date;
//            }
//        }
//
//        public static class ListBean {
//            /**
//             * questions_id : 16
//             * title : 2019/01/20单选题
//             */
//
//            private String questions_id;
//            private String title;
//
//            public String getQuestions_id() {
//                return questions_id;
//            }
//
//            public void setQuestions_id(String questions_id) {
//                this.questions_id = questions_id;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//        }
//    }
}
