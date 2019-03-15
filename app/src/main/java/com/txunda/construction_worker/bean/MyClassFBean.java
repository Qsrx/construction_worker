package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/19 019 15:52:44.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MyClassFBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"advert":[{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-19/5c427b912cc38.jpg","url":"http://www.jiangongbang.com/"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-19/5c427b912cc38.jpg","url":"http://www.jiangongbang.com/"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-02-26/5c7508d9b8650.jpg","url":"http://www.jiangongbang.com/"}],"myCourse":[{"subject_id":null,"zu_pic":"http://jgb.txunda.com","title":"【全科】"},{"subject_id":null,"zu_pic":"http://jgb.txunda.com","title":"【全科】"},{"subject_id":"1","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班"},{"subject_id":null,"zu_pic":"http://jgb.txunda.com","title":"【全科】"},{"subject_id":null,"zu_pic":"http://jgb.txunda.com","title":"【全科】"},{"subject_id":"2","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规中级班"},{"subject_id":"1","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班"}]}
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
        private List<AdvertBean> advert;
        private List<MyCourseBean> myCourse;

        public List<AdvertBean> getAdvert() {
            return advert;
        }

        public void setAdvert(List<AdvertBean> advert) {
            this.advert = advert;
        }

        public List<MyCourseBean> getMyCourse() {
            return myCourse;
        }

        public void setMyCourse(List<MyCourseBean> myCourse) {
            this.myCourse = myCourse;
        }

        public static class AdvertBean {
            /**
             * path : http://jgb.txunda.com/Uploads/Index/2019-01-19/5c427b912cc38.jpg
             * url : http://www.jiangongbang.com/
             */

            private String path;
            private String url;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class MyCourseBean {
            /**
             * subject_id : null
             * zu_pic : http://jgb.txunda.com
             * title : 【全科】
             */

            private Object subject_id;
            private String zu_pic;
            private String title;

            public Object getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(Object subject_id) {
                this.subject_id = subject_id;
            }

            public String getZu_pic() {
                return zu_pic;
            }

            public void setZu_pic(String zu_pic) {
                this.zu_pic = zu_pic;
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
//     * data : {"advert":[{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-19/5c427b912cc38.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-19/5c427b912cc38.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-19/5c427bbbd7c52.jpg"}],"myCourse":[{"subject_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班"},{"subject_id":"4","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【单科】法律法规"}]}
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
//        private List<AdvertBean> advert;
//        private List<MyCourseBean> myCourse;
//
//        public List<AdvertBean> getAdvert() {
//            return advert;
//        }
//
//        public void setAdvert(List<AdvertBean> advert) {
//            this.advert = advert;
//        }
//
//        public List<MyCourseBean> getMyCourse() {
//            return myCourse;
//        }
//
//        public void setMyCourse(List<MyCourseBean> myCourse) {
//            this.myCourse = myCourse;
//        }
//
//        public static class AdvertBean {
//            /**
//             * path : http://jiangongbang.cn/Uploads/Index/2019-01-19/5c427b912cc38.jpg
//             */
//
//            private String path;
//
//            public String getPath() {
//                return path;
//            }
//
//            public void setPath(String path) {
//                this.path = path;
//            }
//        }
//
//        public static class MyCourseBean {
//            /**
//             * subject_id : 1
//             * zu_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
//             * title : 【全科】工程项目管理高级班
//             */
//
//            private String subject_id;
//            private String zu_pic;
//            private String title;
//
//            public String getSubject_id() {
//                return subject_id;
//            }
//
//            public void setSubject_id(String subject_id) {
//                this.subject_id = subject_id;
//            }
//
//            public String getZu_pic() {
//                return zu_pic;
//            }
//
//            public void setZu_pic(String zu_pic) {
//                this.zu_pic = zu_pic;
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
