package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/21 021 8:42:14.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ItemBankBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"subject_id":"1","subject":"法律法规","count":"11","accuracy":18},{"subject_id":"2","subject":"市政实务","count":0,"accuracy":0},{"subject_id":"3","subject":"工程管理","count":0,"accuracy":0}],"days":"7"}
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
         * list : [{"subject_id":"1","subject":"法律法规","count":"11","accuracy":18},{"subject_id":"2","subject":"市政实务","count":0,"accuracy":0},{"subject_id":"3","subject":"工程管理","count":0,"accuracy":0}]
         * days : 7
         */

        private String days;
        private List<ListBean> list;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * subject_id : 1
             * subject : 法律法规
             * count : 11
             * accuracy : 18
             */

            private String subject_id;
            private String subject;
            private String count;
            private int accuracy;

            public String getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(String subject_id) {
                this.subject_id = subject_id;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public int getAccuracy() {
                return accuracy;
            }

            public void setAccuracy(int accuracy) {
                this.accuracy = accuracy;
            }
        }
    }

//
//    /**
//     * code : 1
//     * message : 请求成功
//     * data : [{"subject_id":"1","subject":"法律法规","count":"2","accuracy":"50%"},{"subject_id":"2","subject":"市政实务","count":"1","accuracy":"0%"},{"subject_id":"3","subject":"工程管理","count":0,"accuracy":"0%"}]
//     */
//
//    private String code;
//    private String message;
//    private List<DataBean> data;
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
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * subject_id : 1
//         * subject : 法律法规
//         * count : 2
//         * accuracy : 50%
//         */
//
//        private String subject_id;
//        private String subject;
//        private String count;
//        private String accuracy;
//
//        public String getSubject_id() {
//            return subject_id;
//        }
//
//        public void setSubject_id(String subject_id) {
//            this.subject_id = subject_id;
//        }
//
//        public String getSubject() {
//            return subject;
//        }
//
//        public void setSubject(String subject) {
//            this.subject = subject;
//        }
//
//        public String getCount() {
//            return count;
//        }
//
//        public void setCount(String count) {
//            this.count = count;
//        }
//
//        public String getAccuracy() {
//            return accuracy;
//        }
//
//        public void setAccuracy(String accuracy) {
//            this.accuracy = accuracy;
//        }
//    }
}
