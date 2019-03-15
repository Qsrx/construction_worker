package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/18 018 14:34:52.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ClassMlBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"title":"串讲","list":[{"course_id":"1","mulu":"1","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","is_cache":"1"},{"mulu":"2","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"}]},{"title":"精讲","list":[{"mulu":"3","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"4","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"5分钟","type":"1"},{"mulu":"5","gold":"2","name":"法律法规，你知道吗？","time":"30分钟","type":"2"}]},{"title":"习题班","list":[{"mulu":"6","gold":"3","name":"法律法规，你知道吗？","time":"20分钟","type":"2"},{"mulu":"7","gold":"3","name":"法律法规，你知道吗？","time":"25分钟","type":"2"}]},{"title":"基础班","list":[{"mulu":"8","gold":"4","name":"法律法规，你知道吗？","time":"15分钟","type":"2"}]},{"title":"案例分析班","list":[{"mulu":"9","gold":"5","name":"法律法规，你知道吗？","time":"35分钟","type":"2"}]}]
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
         * title : 串讲
         * list : [{"course_id":"1","mulu":"1","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1","is_cache":"1"},{"mulu":"2","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"}]
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * course_id : 1
             * mulu : 1
             * gold : 1
             * name : 法律法规的前言串讲，你知道吗？
             * time : 10分钟
             * type : 1
             * is_cache : 1
             */

            private String course_id;
            private String mulu;
            private String gold;
            private String name;
            private String time;
            private String type;
            private String is_cache;
            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public String getMulu() {
                return mulu;
            }

            public void setMulu(String mulu) {
                this.mulu = mulu;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIs_cache() {
                return is_cache;
            }

            public void setIs_cache(String is_cache) {
                this.is_cache = is_cache;
            }
        }
    }
}
