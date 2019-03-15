package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/28 028 9:14:58.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class TrueQuestionsBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"type_id":"6","title":"真题考试题（2018）","is_fee":2},{"type_id":"7","title":"真题考试题（2017）","is_fee":2},{"type_id":"8","title":"真题考试题（2016）","is_fee":2}],"pay":1}
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
         * list : [{"type_id":"6","title":"真题考试题（2018）","is_fee":2},{"type_id":"7","title":"真题考试题（2017）","is_fee":2},{"type_id":"8","title":"真题考试题（2016）","is_fee":2}]
         * pay : 1
         */

        private int pay;
        private List<ListBean> list;

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type_id : 6
             * title : 真题考试题（2018）
             * is_fee : 2
             */

            private String type_id;
            private String title;
            private int is_fee;

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIs_fee() {
                return is_fee;
            }

            public void setIs_fee(int is_fee) {
                this.is_fee = is_fee;
            }
        }
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"list":[{"title":"模拟测试（一）","is_fee":"2"},{"title":"模拟测试（二）","is_fee":"2"}],"pay":1}
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
//         * list : [{"title":"模拟测试（一）","is_fee":"2"},{"title":"模拟测试（二）","is_fee":"2"}]
//         * pay : 1
//         */
//
//        private int pay;
//        private List<ListBean> list;
//
//        public int getPay() {
//            return pay;
//        }
//
//        public void setPay(int pay) {
//            this.pay = pay;
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
//        public static class ListBean {
//            /**
//             * title : 模拟测试（一）
//             * is_fee : 2
//             */
//
//            private String title;
//            private String is_fee;
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getIs_fee() {
//                return is_fee;
//            }
//
//            public void setIs_fee(String is_fee) {
//                this.is_fee = is_fee;
//            }
//        }
//    }
}
