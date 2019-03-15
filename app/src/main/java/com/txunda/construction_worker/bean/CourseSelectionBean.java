package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/18 018 17:32:10.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class CourseSelectionBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"shipin":[{"mulu":"1","video_pic":"http://jiangongbang.cn/Uploads/Index/2018-11-05/5bdfa923e902b.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"2","video_pic":"http://jiangongbang.cn/Uploads/Index/2018-11-05/5bdfa8eb7961e.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"}],"course_list":[{"course":"1","type":"1"},{"course":"2","type":"1"},{"course":"3","type":"1"},{"course":"4","type":"1"}],"directory":[{"title":"串讲","list":[{"mulu":"1","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"2","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"}]},{"title":"精讲","list":[{"mulu":"3","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"4","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"5分钟","type":"1"},{"mulu":"5","gold":"2","name":"法律法规，你知道吗？","time":"30分钟","type":"2"}]},{"title":"习题班","list":[{"mulu":"6","gold":"3","name":"法律法规，你知道吗？","time":"20分钟","type":"2"},{"mulu":"7","gold":"3","name":"法律法规，你知道吗？","time":"25分钟","type":"2"}]}],"comment":[{"nickname":"IGU9VH","head_pic":"http://jiangongbang.cn/Uploads/Member/2019-01-17/5c404ddde9d70.jpg","star":"5","create_time":"2019/01/18","content":"这个课程好啊，很满意的一次购物，好评没毛病"}],"ppt":[{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}],"notes":"这课程讲的好啊","is_pay":1,"price":"300.00","collection":2}
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
         * shipin : [{"mulu":"1","video_pic":"http://jiangongbang.cn/Uploads/Index/2018-11-05/5bdfa923e902b.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"2","video_pic":"http://jiangongbang.cn/Uploads/Index/2018-11-05/5bdfa8eb7961e.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"}]
         * course_list : [{"course":"1","type":"1"},{"course":"2","type":"1"},{"course":"3","type":"1"},{"course":"4","type":"1"}]
         * directory : [{"title":"串讲","list":[{"mulu":"1","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"2","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"}]},{"title":"精讲","list":[{"mulu":"3","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"4","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"5分钟","type":"1"},{"mulu":"5","gold":"2","name":"法律法规，你知道吗？","time":"30分钟","type":"2"}]},{"title":"习题班","list":[{"mulu":"6","gold":"3","name":"法律法规，你知道吗？","time":"20分钟","type":"2"},{"mulu":"7","gold":"3","name":"法律法规，你知道吗？","time":"25分钟","type":"2"}]}]
         * comment : [{"nickname":"IGU9VH","head_pic":"http://jiangongbang.cn/Uploads/Member/2019-01-17/5c404ddde9d70.jpg","star":"5","create_time":"2019/01/18","content":"这个课程好啊，很满意的一次购物，好评没毛病"}]
         * ppt : [{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}]
         * notes : 这课程讲的好啊
         * is_pay : 1
         * price : 300.00
         * collection : 2
         */

        private String notes;
        private int is_pay;
        private String price;
        private int collection;
        private List<ShipinBean> shipin;
        private List<CourseListBean> course_list;
        private List<DirectoryBean> directory;
        private List<CommentBean> comment;
        private List<PptBean> ppt;

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public List<ShipinBean> getShipin() {
            return shipin;
        }

        public void setShipin(List<ShipinBean> shipin) {
            this.shipin = shipin;
        }

        public List<CourseListBean> getCourse_list() {
            return course_list;
        }

        public void setCourse_list(List<CourseListBean> course_list) {
            this.course_list = course_list;
        }

        public List<DirectoryBean> getDirectory() {
            return directory;
        }

        public void setDirectory(List<DirectoryBean> directory) {
            this.directory = directory;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public List<PptBean> getPpt() {
            return ppt;
        }

        public void setPpt(List<PptBean> ppt) {
            this.ppt = ppt;
        }

        public static class ShipinBean {
            /**
             * mulu : 1
             * video_pic : http://jiangongbang.cn/Uploads/Index/2018-11-05/5bdfa923e902b.png
             * video_path : https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4
             */

            private String name;
            private String mulu;
            private String video_pic;
            private String video_path;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMulu() {
                return mulu;
            }

            public void setMulu(String mulu) {
                this.mulu = mulu;
            }

            public String getVideo_pic() {
                return video_pic;
            }

            public void setVideo_pic(String video_pic) {
                this.video_pic = video_pic;
            }

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }
        }

        public static class CourseListBean {
            /**
             * course : 1
             * type : 1
             */

            private String course;
            private String type;

            public String getCourse() {
                return course;
            }

            public void setCourse(String course) {
                this.course = course;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class DirectoryBean {
            /**
             * title : 串讲
             * list : [{"mulu":"1","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"2","gold":"1","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"}]
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
                 * mulu : 1
                 * gold : 1
                 * name : 法律法规的前言串讲，你知道吗？
                 * time : 10分钟
                 * type : 1
                 */

                private String mulu;
                private String gold;
                private String name;
                private String time;
                private String type;

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
            }
        }

        public static class CommentBean {
            /**
             * nickname : IGU9VH
             * head_pic : http://jiangongbang.cn/Uploads/Member/2019-01-17/5c404ddde9d70.jpg
             * star : 5
             * create_time : 2019/01/18
             * content : 这个课程好啊，很满意的一次购物，好评没毛病
             */

            private String nickname;
            private String head_pic;
            private String star;
            private String create_time;
            private String content;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class PptBean {
            /**
             * path : http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg
             */

            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"shipin":[{"mulu":"1","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa923e902b.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"2","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa8eb7961e.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"3","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa5ca33997.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"4","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa5e3611df.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"5","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-09/5be4e45a9cfa1.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"6","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfae8a53db7.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"7","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-10/5be6a5229de9f.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"8","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-12/5be9482749a00.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"9","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-13/5bea5ed52f916.gif","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"}],"course_list":[{"course":"1","type":"1"},{"course":"2","type":"1"},{"course":"3","type":"1"},{"course":"4","type":"1"},{"course":"5","type":"2"},{"course":"6","type":"2"},{"course":"7","type":"2"},{"course":"8","type":"2"},{"course":"9","type":"2"}],"directory":{"chuan":[{"mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"2","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"3","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"4","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"5分钟","type":"1"}],"jing":[{"mulu":"5","gold":"1","name":"法律法规，你知道吗？","time":"30分钟","type":"2"},{"mulu":"6","gold":"1","name":"法律法规，你知道吗？","time":"20分钟","type":"2"},{"mulu":"7","gold":"1","name":"法律法规，你知道吗？","time":"25分钟","type":"2"},{"mulu":"8","gold":"1","name":"法律法规，你知道吗？","time":"15分钟","type":"2"},{"mulu":"9","gold":"1","name":"法律法规，你知道吗？","time":"35分钟","type":"2"}]},"comment":[{"nickname":"IGU9VH","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","star":"5","create_time":"2019/01/18","content":"这个课程好啊，很满意的一次购物，好评没毛病"}],"ppt":[{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}],"notes":"Android开发","is_pay":2,"price":"300.00","collection":1}
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
//         * shipin : [{"mulu":"1","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa923e902b.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"2","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa8eb7961e.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"3","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa5ca33997.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"4","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa5e3611df.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"5","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-09/5be4e45a9cfa1.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"6","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfae8a53db7.png","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"7","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-10/5be6a5229de9f.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4"},{"mulu":"8","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-12/5be9482749a00.jpg","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"},{"mulu":"9","video_pic":"http://jgb.txunda.com/Uploads/Index/2018-11-13/5bea5ed52f916.gif","video_path":"https://electroniclife.oss-cn-beijing.aliyuncs.com/"}]
//         * course_list : [{"course":"1","type":"1"},{"course":"2","type":"1"},{"course":"3","type":"1"},{"course":"4","type":"1"},{"course":"5","type":"2"},{"course":"6","type":"2"},{"course":"7","type":"2"},{"course":"8","type":"2"},{"course":"9","type":"2"}]
//         * directory : {"chuan":[{"mulu":"1","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"2","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"3","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"10分钟","type":"1"},{"mulu":"4","gold":"2","name":"法律法规的前言串讲，你知道吗？","time":"5分钟","type":"1"}],"jing":[{"mulu":"5","gold":"1","name":"法律法规，你知道吗？","time":"30分钟","type":"2"},{"mulu":"6","gold":"1","name":"法律法规，你知道吗？","time":"20分钟","type":"2"},{"mulu":"7","gold":"1","name":"法律法规，你知道吗？","time":"25分钟","type":"2"},{"mulu":"8","gold":"1","name":"法律法规，你知道吗？","time":"15分钟","type":"2"},{"mulu":"9","gold":"1","name":"法律法规，你知道吗？","time":"35分钟","type":"2"}]}
//         * comment : [{"nickname":"IGU9VH","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","star":"5","create_time":"2019/01/18","content":"这个课程好啊，很满意的一次购物，好评没毛病"}]
//         * ppt : [{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},{"path":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}]
//         * notes : Android开发
//         * is_pay : 2
//         * price : 300.00
//         * collection : 1
//         */
//
//        private DirectoryBean directory;
//        private String notes;
//        private int is_pay;
//        private String price;
//        private int collection;
//        private List<ShipinBean> shipin;
//        private List<CourseListBean> course_list;
//        private List<CommentBean> comment;
//        private List<PptBean> ppt;
//
//        public DirectoryBean getDirectory() {
//            return directory;
//        }
//
//        public void setDirectory(DirectoryBean directory) {
//            this.directory = directory;
//        }
//
//        public String getNotes() {
//            return notes;
//        }
//
//        public void setNotes(String notes) {
//            this.notes = notes;
//        }
//
//        public int getIs_pay() {
//            return is_pay;
//        }
//
//        public void setIs_pay(int is_pay) {
//            this.is_pay = is_pay;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public int getCollection() {
//            return collection;
//        }
//
//        public void setCollection(int collection) {
//            this.collection = collection;
//        }
//
//        public List<ShipinBean> getShipin() {
//            return shipin;
//        }
//
//        public void setShipin(List<ShipinBean> shipin) {
//            this.shipin = shipin;
//        }
//
//        public List<CourseListBean> getCourse_list() {
//            return course_list;
//        }
//
//        public void setCourse_list(List<CourseListBean> course_list) {
//            this.course_list = course_list;
//        }
//
//        public List<CommentBean> getComment() {
//            return comment;
//        }
//
//        public void setComment(List<CommentBean> comment) {
//            this.comment = comment;
//        }
//
//        public List<PptBean> getPpt() {
//            return ppt;
//        }
//
//        public void setPpt(List<PptBean> ppt) {
//            this.ppt = ppt;
//        }
//
//        public static class DirectoryBean {
//            private List<ChuanBean> chuan;
//            private List<JingBean> jing;
//
//            public List<ChuanBean> getChuan() {
//                return chuan;
//            }
//
//            public void setChuan(List<ChuanBean> chuan) {
//                this.chuan = chuan;
//            }
//
//            public List<JingBean> getJing() {
//                return jing;
//            }
//
//            public void setJing(List<JingBean> jing) {
//                this.jing = jing;
//            }
//
//            public static class ChuanBean {
//                /**
//                 * mulu : 1
//                 * gold : 2
//                 * name : 法律法规的前言串讲，你知道吗？
//                 * time : 10分钟
//                 * type : 1
//                 */
//
//                private String mulu;
//                private String gold;
//                private String name;
//                private String time;
//                private String type;
//
//                public String getMulu() {
//                    return mulu;
//                }
//
//                public void setMulu(String mulu) {
//                    this.mulu = mulu;
//                }
//
//                public String getGold() {
//                    return gold;
//                }
//
//                public void setGold(String gold) {
//                    this.gold = gold;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getTime() {
//                    return time;
//                }
//
//                public void setTime(String time) {
//                    this.time = time;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//            }
//
//            public static class JingBean {
//                /**
//                 * mulu : 5
//                 * gold : 1
//                 * name : 法律法规，你知道吗？
//                 * time : 30分钟
//                 * type : 2
//                 */
//
//                private String mulu;
//                private String gold;
//                private String name;
//                private String time;
//                private String type;
//
//                public String getMulu() {
//                    return mulu;
//                }
//
//                public void setMulu(String mulu) {
//                    this.mulu = mulu;
//                }
//
//                public String getGold() {
//                    return gold;
//                }
//
//                public void setGold(String gold) {
//                    this.gold = gold;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getTime() {
//                    return time;
//                }
//
//                public void setTime(String time) {
//                    this.time = time;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//            }
//        }
//
//        public static class ShipinBean {
//            /**
//             * mulu : 1
//             * video_pic : http://jgb.txunda.com/Uploads/Index/2018-11-05/5bdfa923e902b.png
//             * video_path : https://electroniclife.oss-cn-beijing.aliyuncs.com/https://electroniclife.oss-cn-beijing.aliyuncs.com/user-dir/sZzn4MCi7W.mp4
//             */
//
//            private String mulu;
//            private String video_pic;
//            private String video_path;
//
//            public String getMulu() {
//                return mulu;
//            }
//
//            public void setMulu(String mulu) {
//                this.mulu = mulu;
//            }
//
//            public String getVideo_pic() {
//                return video_pic;
//            }
//
//            public void setVideo_pic(String video_pic) {
//                this.video_pic = video_pic;
//            }
//
//            public String getVideo_path() {
//                return video_path;
//            }
//
//            public void setVideo_path(String video_path) {
//                this.video_path = video_path;
//            }
//        }
//
//        public static class CourseListBean {
//            /**
//             * course : 1
//             * type : 1
//             */
//
//            private String course;
//            private String type;
//
//            public String getCourse() {
//                return course;
//            }
//
//            public void setCourse(String course) {
//                this.course = course;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//        }
//
//        public static class CommentBean {
//            /**
//             * nickname : IGU9VH
//             * head_pic : http://jgb.txunda.com/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
//             * star : 5
//             * create_time : 2019/01/18
//             * content : 这个课程好啊，很满意的一次购物，好评没毛病
//             */
//
//            private String nickname;
//            private String head_pic;
//            private String star;
//            private String create_time;
//            private String content;
//
//            public String getNickname() {
//                return nickname;
//            }
//
//            public void setNickname(String nickname) {
//                this.nickname = nickname;
//            }
//
//            public String getHead_pic() {
//                return head_pic;
//            }
//
//            public void setHead_pic(String head_pic) {
//                this.head_pic = head_pic;
//            }
//
//            public String getStar() {
//                return star;
//            }
//
//            public void setStar(String star) {
//                this.star = star;
//            }
//
//            public String getCreate_time() {
//                return create_time;
//            }
//
//            public void setCreate_time(String create_time) {
//                this.create_time = create_time;
//            }
//
//            public String getContent() {
//                return content;
//            }
//
//            public void setContent(String content) {
//                this.content = content;
//            }
//        }
//
//        public static class PptBean {
//            /**
//             * path : http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg
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
//    }

}