package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/16 016 16:45:03.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ClassBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"recommend":{"taocan_id":"1","type":"高级班","price":"1288.00","taocan_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班","count":"1人已付费"},"subject":[{"subject_id":"1","cname":"法律法规"},{"subject_id":"2","cname":"市政实务"},{"subject_id":"3","cname":"工程管理"}],"system":[{"taocan_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"1288.00","title":"【全科】法律法规高级班","num":"1人已付费"},{"taocan_id":"2","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"1088.00","title":"【全科】法律法规中级班","num":"0人已付费"},{"taocan_id":"3","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"888.00","title":"【全科】法律法规普通班","num":"0人已付费"},{"taocan_id":"4","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】法律法规","num":"0人已付费"},{"taocan_id":"5","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】市政实务","num":"0人已付费"},{"taocan_id":"6","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】工程管理","num":"0人已付费"}]}
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
         * recommend : {"taocan_id":"1","type":"高级班","price":"1288.00","taocan_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班","count":"1人已付费"}
         * subject : [{"subject_id":"1","cname":"法律法规"},{"subject_id":"2","cname":"市政实务"},{"subject_id":"3","cname":"工程管理"}]
         * system : [{"taocan_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"1288.00","title":"【全科】法律法规高级班","num":"1人已付费"},{"taocan_id":"2","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"1088.00","title":"【全科】法律法规中级班","num":"0人已付费"},{"taocan_id":"3","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"888.00","title":"【全科】法律法规普通班","num":"0人已付费"},{"taocan_id":"4","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】法律法规","num":"0人已付费"},{"taocan_id":"5","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】市政实务","num":"0人已付费"},{"taocan_id":"6","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","price":"300.00","title":"【单科】工程管理","num":"0人已付费"}]
         */

        private RecommendBean recommend;
        private List<SubjectBean> subject;
        private List<SystemBean> system;

        public RecommendBean getRecommend() {
            return recommend;
        }

        public void setRecommend(RecommendBean recommend) {
            this.recommend = recommend;
        }

        public List<SubjectBean> getSubject() {
            return subject;
        }

        public void setSubject(List<SubjectBean> subject) {
            this.subject = subject;
        }

        public List<SystemBean> getSystem() {
            return system;
        }

        public void setSystem(List<SystemBean> system) {
            this.system = system;
        }

        public static class RecommendBean {
            /**
             * taocan_id : 1
             * type : 高级班
             * price : 1288.00
             * taocan_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
             * title : 【全科】法律法规高级班
             * count : 1人已付费
             */

            private String taocan_id;
            private String type;
            private String price;
            private String taocan_pic;
            private String title;
            private String count;

            public String getTaocan_id() {
                return taocan_id;
            }

            public void setTaocan_id(String taocan_id) {
                this.taocan_id = taocan_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTaocan_pic() {
                return taocan_pic;
            }

            public void setTaocan_pic(String taocan_pic) {
                this.taocan_pic = taocan_pic;
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
        }

        public static class SubjectBean {
            /**
             * subject_id : 1
             * cname : 法律法规
             */

            private String subject_id;
            private String cname;

            public String getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(String subject_id) {
                this.subject_id = subject_id;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }
        }

        public static class SystemBean {
            /**
             * taocan_id : 1
             * zu_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
             * price : 1288.00
             * title : 【全科】法律法规高级班
             * num : 1人已付费
             */

            private String taocan_id;
            private String zu_pic;
            private String price;
            private String title;
            private String num;

            public String getTaocan_id() {
                return taocan_id;
            }

            public void setTaocan_id(String taocan_id) {
                this.taocan_id = taocan_id;
            }

            public String getZu_pic() {
                return zu_pic;
            }

            public void setZu_pic(String zu_pic) {
                this.zu_pic = zu_pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
