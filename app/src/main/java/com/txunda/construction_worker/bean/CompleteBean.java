package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/6 006 11:23:24.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class CompleteBean {
    /**
     * code : 1
     * message : 请求成功
     * data : [{"subject_id":"1","pic":"http:jgb.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg","title":"一级建造师-法律法规","count":"8","cache_count":"3","type":"1"},{"pic":"http:n/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","title":"一级建造师-工程管理","count":"1","cache_count":"1","type":"1"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * subject_id : 1
         * pic : http:jgb.cn/Uploads/Index/2019-02-19/5c6bbaba224d8.jpg
         * title : 一级建造师-法律法规
         * count : 8
         * cache_count : 3
         * type : 1
         */

        private String subject_id;
        private String pic;
        private String title;
        private String count;
        private String cache_count;
        private String type;

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCache_count() {
            return cache_count;
        }

        public void setCache_count(String cache_count) {
            this.cache_count = cache_count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : [{"title":"基础精讲班","list":[{"course_id":"30","mulu":"1","gold":"1","name":"环境心理学课程介绍","time":"63","type":"1","path":"https://open.163.com/movie/2012/9/V/J/M8HEO0A8T_M8HEV1QVJ.html","is_cache":"0"},{"course_id":"31","mulu":"2","gold":"1","name":"应用环境心理学原理分析并解释社区问题","time":"60","type":"1","path":"https://open.163.com/movie/2012/9/C/5/M8HEO0A8T_M8HEU3IC5.html","is_cache":"0"},{"course_id":"32","mulu":"3","gold":"1","name":"生态范式的来源与主要假设","time":"56","type":"1","path":"https://open.163.com/movie/2012/9/C/P/M8HEO0A8T_M8HEUN5CP.html","is_cache":"0"},{"course_id":"33","mulu":"4","gold":"1","name":"系统理论原则，生理与心理压力","time":"68","type":"2","path":"https://open.163.com/movie/2012/9/1/R/M8HEO0A8T_M8HEVAL1R.html","is_cache":"0"},{"course_id":"35","mulu":"5","gold":"1","name":"环境认知","time":"30","type":"2","path":"https://open.163.com/movie/2012/9/C/N/M8HEO0A8T_M8HEVKDCN.html","is_cache":"0"},{"course_id":"36","mulu":"6","gold":"1","name":"环境与人格","time":"60","type":"2","path":"https://open.163.com/movie/2012/9/N/0/M8HEO0A8T_M8HF04SN0.html","is_cache":"0"}]},{"title":"备考导学班","list":[{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"},{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"},{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"},{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"},{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"},{"course_id":"1","mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","path":"https://gslb.miaopai.com/stream/qr1Zo0TAtI7ae0B~Zz7BdXD4bUAsDVtwfTweYQ__.mp4","is_cache":"3"}]},{"title":"习题解析班","list":[]}]
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
//         * title : 基础精讲班
//         * list : [{"course_id":"30","mulu":"1","gold":"1","name":"环境心理学课程介绍","time":"63","type":"1","path":"https://open.163.com/movie/2012/9/V/J/M8HEO0A8T_M8HEV1QVJ.html","is_cache":"0"},{"course_id":"31","mulu":"2","gold":"1","name":"应用环境心理学原理分析并解释社区问题","time":"60","type":"1","path":"https://open.163.com/movie/2012/9/C/5/M8HEO0A8T_M8HEU3IC5.html","is_cache":"0"},{"course_id":"32","mulu":"3","gold":"1","name":"生态范式的来源与主要假设","time":"56","type":"1","path":"https://open.163.com/movie/2012/9/C/P/M8HEO0A8T_M8HEUN5CP.html","is_cache":"0"},{"course_id":"33","mulu":"4","gold":"1","name":"系统理论原则，生理与心理压力","time":"68","type":"2","path":"https://open.163.com/movie/2012/9/1/R/M8HEO0A8T_M8HEVAL1R.html","is_cache":"0"},{"course_id":"35","mulu":"5","gold":"1","name":"环境认知","time":"30","type":"2","path":"https://open.163.com/movie/2012/9/C/N/M8HEO0A8T_M8HEVKDCN.html","is_cache":"0"},{"course_id":"36","mulu":"6","gold":"1","name":"环境与人格","time":"60","type":"2","path":"https://open.163.com/movie/2012/9/N/0/M8HEO0A8T_M8HF04SN0.html","is_cache":"0"}]
//         */
//
//        private String title;
//        private List<ListBean> list;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
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
//             * course_id : 30
//             * mulu : 1
//             * gold : 1
//             * name : 环境心理学课程介绍
//             * time : 63
//             * type : 1
//             * path : https://open.163.com/movie/2012/9/V/J/M8HEO0A8T_M8HEV1QVJ.html
//             * is_cache : 0
//             */
//
//            private String course_id;
//            private String mulu;
//            private String gold;
//            private String name;
//            private String time;
//            private String type;
//            private String path;
//            private String is_cache;
//
//            public String getCourse_id() {
//                return course_id;
//            }
//
//            public void setCourse_id(String course_id) {
//                this.course_id = course_id;
//            }
//
//            public String getMulu() {
//                return mulu;
//            }
//
//            public void setMulu(String mulu) {
//                this.mulu = mulu;
//            }
//
//            public String getGold() {
//                return gold;
//            }
//
//            public void setGold(String gold) {
//                this.gold = gold;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getTime() {
//                return time;
//            }
//
//            public void setTime(String time) {
//                this.time = time;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getPath() {
//                return path;
//            }
//
//            public void setPath(String path) {
//                this.path = path;
//            }
//
//            public String getIs_cache() {
//                return is_cache;
//            }
//
//            public void setIs_cache(String is_cache) {
//                this.is_cache = is_cache;
//            }
//        }
//    }
}
