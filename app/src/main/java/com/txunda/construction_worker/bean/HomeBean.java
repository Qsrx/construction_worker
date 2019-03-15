package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/9 009 10:08:53.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class HomeBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"details":{"major":"一级建造师","advert":[{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"}],"exam":"考试已经开始，祝您取得好成绩","days":""},"article":[{"id":"1","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"},{"id":"6","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"},{"id":"7","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"}],"bbs":[{"id":"77","m_id":"160","content":"emmm","content_pic":[{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271d785b.jpg"},{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271def93.jpg"}],"create_time":"2019/02/28 11:16:01","support":"1","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c75fc746239d.jpg","nickname":"芳华y","count":"0","is_like":2},{"id":"76","m_id":"171","content":"痛到了天咯谢谢破红褐色破破破more 给你二二二二二二二二色色二二二二二二二二热热热热热热热热热人是热热热热热二热热热热热热热二二二二二二的人和爱都给我一个好朋友好姐妹装了什么东西是可以改变很多人说什么我要不能吃","content_pic":[],"create_time":"2019/02/28 09:57:31","support":"0","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c762375e74c5.png","nickname":"TDFJ6N","count":"2","is_like":2},{"id":"74","m_id":"160","content":"emmm","content_pic":[],"create_time":"2019/02/27 14:50:06","support":"1","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c75fc746239d.jpg","nickname":"芳华y","count":"3","is_like":2}]}
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
         * details : {"major":"一级建造师","advert":[{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"}],"exam":"考试已经开始，祝您取得好成绩","days":""}
         * article : [{"id":"1","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"},{"id":"6","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"},{"id":"7","title":"一级建造师报考须知","cover":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"1"}]
         * bbs : [{"id":"77","m_id":"160","content":"emmm","content_pic":[{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271d785b.jpg"},{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271def93.jpg"}],"create_time":"2019/02/28 11:16:01","support":"1","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c75fc746239d.jpg","nickname":"芳华y","count":"0","is_like":2},{"id":"76","m_id":"171","content":"痛到了天咯谢谢破红褐色破破破more 给你二二二二二二二二色色二二二二二二二二热热热热热热热热热人是热热热热热二热热热热热热热二二二二二二的人和爱都给我一个好朋友好姐妹装了什么东西是可以改变很多人说什么我要不能吃","content_pic":[],"create_time":"2019/02/28 09:57:31","support":"0","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c762375e74c5.png","nickname":"TDFJ6N","count":"2","is_like":2},{"id":"74","m_id":"160","content":"emmm","content_pic":[],"create_time":"2019/02/27 14:50:06","support":"1","head_pic":"http://jgb.txunda.com/Uploads/Member/2019-02-27/5c75fc746239d.jpg","nickname":"芳华y","count":"3","is_like":2}]
         */

        private DetailsBean details;
        private List<ArticleBean> article;
        private List<BbsBean> bbs;

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public List<BbsBean> getBbs() {
            return bbs;
        }

        public void setBbs(List<BbsBean> bbs) {
            this.bbs = bbs;
        }

        public static class DetailsBean {
            /**
             * major : 一级建造师
             * advert : [{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg","url":"http://www.jiangongbang.com/"}]
             * exam : 考试已经开始，祝您取得好成绩
             * days :
             */

            private String major;
            private String exam;
            private String days;
            private List<AdvertBean> advert;

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getExam() {
                return exam;
            }

            public void setExam(String exam) {
                this.exam = exam;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public List<AdvertBean> getAdvert() {
                return advert;
            }

            public void setAdvert(List<AdvertBean> advert) {
                this.advert = advert;
            }

            public static class AdvertBean {
                /**
                 * file_id : http://jgb.txunda.com/Uploads/Index/2019-01-07/5c32f184e9857.jpg
                 * url : http://www.jiangongbang.com/
                 */

                private String file_id;
                private String url;

                public String getFile_id() {
                    return file_id;
                }

                public void setFile_id(String file_id) {
                    this.file_id = file_id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class ArticleBean {
            /**
             * id : 1
             * title : 一级建造师报考须知
             * cover : http://jgb.txunda.com/Uploads/Index/2019-01-07/5c330d6067db8.jpg
             * create_time : 建工邦 2018/11/07
             * type : 1
             */

            private String id;
            private String title;
            private String cover;
            private String create_time;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class BbsBean {
            /**
             * id : 77
             * m_id : 160
             * content : emmm
             * content_pic : [{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271d785b.jpg"},{"path":"http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271def93.jpg"}]
             * create_time : 2019/02/28 11:16:01
             * support : 1
             * head_pic : http://jgb.txunda.com/Uploads/Member/2019-02-27/5c75fc746239d.jpg
             * nickname : 芳华y
             * count : 0
             * is_like : 2
             */

            private String id;
            private String m_id;
            private String content;
            private String create_time;
            private String support;
            private String head_pic;
            private String nickname;
            private String count;
            private int is_like;
            private List<ContentPicBean> content_pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getM_id() {
                return m_id;
            }

            public void setM_id(String m_id) {
                this.m_id = m_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getSupport() {
                return support;
            }

            public void setSupport(String support) {
                this.support = support;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public List<ContentPicBean> getContent_pic() {
                return content_pic;
            }

            public void setContent_pic(List<ContentPicBean> content_pic) {
                this.content_pic = content_pic;
            }

            public static class ContentPicBean {
                /**
                 * path : http://jgb.txunda.com/Uploads/Bbs/2019-02-28/5c775271d785b.jpg
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
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"details":{"major":"一级建造师","advert":[{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e2d4982d.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e411ba52.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e4e3343f.jpg","url":"http://www.jiangongbang.com/"}],"exam":"距离一级建造师考试还有","days":100},"article":[{"id":"11","title":"报考须知","cover":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"3"}],"bbs":[{"id":"3","m_id":"145","content":"论坛问题问题问题论坛问题问题问题 ","content_pic":[{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}],"create_time":"2019-01-07 17:12:45","support":"5","head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"","count":"0","is_like":2}]}
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
//         * details : {"major":"一级建造师","advert":[{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e2d4982d.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e411ba52.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e4e3343f.jpg","url":"http://www.jiangongbang.com/"}],"exam":"距离一级建造师考试还有","days":100}
//         * article : [{"id":"11","title":"报考须知","cover":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg","create_time":"建工邦 2018/11/07","type":"3"}]
//         * bbs : [{"id":"3","m_id":"145","content":"论坛问题问题问题论坛问题问题问题 ","content_pic":[{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}],"create_time":"2019-01-07 17:12:45","support":"5","head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"","count":"0","is_like":2}]
//         */
//
//        private DetailsBean details;
//        private List<ArticleBean> article;
//        private List<BbsBean> bbs;
//
//        public DetailsBean getDetails() {
//            return details;
//        }
//
//        public void setDetails(DetailsBean details) {
//            this.details = details;
//        }
//
//        public List<ArticleBean> getArticle() {
//            return article;
//        }
//
//        public void setArticle(List<ArticleBean> article) {
//            this.article = article;
//        }
//
//        public List<BbsBean> getBbs() {
//            return bbs;
//        }
//
//        public void setBbs(List<BbsBean> bbs) {
//            this.bbs = bbs;
//        }
//
//        public static class DetailsBean {
//            /**
//             * major : 一级建造师
//             * advert : [{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e2d4982d.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e411ba52.jpg","url":"http://www.jiangongbang.com/"},{"file_id":"http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e4e3343f.jpg","url":"http://www.jiangongbang.com/"}]
//             * exam : 距离一级建造师考试还有
//             * days : 100
//             */
//
//            private String major;
//            private String exam;
//            private int days;
//            private List<AdvertBean> advert;
//
//            public String getMajor() {
//                return major;
//            }
//
//            public void setMajor(String major) {
//                this.major = major;
//            }
//
//            public String getExam() {
//                return exam;
//            }
//
//            public void setExam(String exam) {
//                this.exam = exam;
//            }
//
//            public int getDays() {
//                return days;
//            }
//
//            public void setDays(int days) {
//                this.days = days;
//            }
//
//            public List<AdvertBean> getAdvert() {
//                return advert;
//            }
//
//            public void setAdvert(List<AdvertBean> advert) {
//                this.advert = advert;
//            }
//
//            public static class AdvertBean {
//                /**
//                 * file_id : http://jiangongbang.cn/Uploads/Article/2018-08-14/5b729e2d4982d.jpg
//                 * url : http://www.jiangongbang.com/
//                 */
//
//                private String file_id;
//                private String url;
//
//                public String getFile_id() {
//                    return file_id;
//                }
//
//                public void setFile_id(String file_id) {
//                    this.file_id = file_id;
//                }
//
//                public String getUrl() {
//                    return url;
//                }
//
//                public void setUrl(String url) {
//                    this.url = url;
//                }
//            }
//        }
//
//        public static class ArticleBean {
//            /**
//             * id : 11
//             * title : 报考须知
//             * cover : http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg
//             * create_time : 建工邦 2018/11/07
//             * type : 3
//             */
//
//            private String id;
//            private String title;
//            private String cover;
//            private String create_time;
//            private String type;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getCover() {
//                return cover;
//            }
//
//            public void setCover(String cover) {
//                this.cover = cover;
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
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//        }
//
//        public static class BbsBean {
//            /**
//             * id : 3
//             * m_id : 145
//             * content : 论坛问题问题问题论坛问题问题问题
//             * content_pic : [{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}]
//             * create_time : 2019-01-07 17:12:45
//             * support : 5
//             * head_pic : http://jgb.cn/Uploads/Member/default.png
//             * nickname :
//             * count : 0
//             * is_like : 2
//             */
//
//            private String id;
//            private String m_id;
//            private String content;
//            private String create_time;
//            private String support;
//            private String head_pic;
//            private String nickname;
//            private String count;
//            private int is_like;
//            private List<ContentPicBean> content_pic;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getM_id() {
//                return m_id;
//            }
//
//            public void setM_id(String m_id) {
//                this.m_id = m_id;
//            }
//
//            public String getContent() {
//                return content;
//            }
//
//            public void setContent(String content) {
//                this.content = content;
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
//            public String getSupport() {
//                return support;
//            }
//
//            public void setSupport(String support) {
//                this.support = support;
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
//            public String getNickname() {
//                return nickname;
//            }
//
//            public void setNickname(String nickname) {
//                this.nickname = nickname;
//            }
//
//            public String getCount() {
//                return count;
//            }
//
//            public void setCount(String count) {
//                this.count = count;
//            }
//
//            public int getIs_like() {
//                return is_like;
//            }
//
//            public void setIs_like(int is_like) {
//                this.is_like = is_like;
//            }
//
//            public List<ContentPicBean> getContent_pic() {
//                return content_pic;
//            }
//
//            public void setContent_pic(List<ContentPicBean> content_pic) {
//                this.content_pic = content_pic;
//            }
//
//            public static class ContentPicBean {
//                /**
//                 * path : http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg
//                 */
//
//                private String path;
//
//                public String getPath() {
//                    return path;
//                }
//
//                public void setPath(String path) {
//                    this.path = path;
//                }
//            }
//        }
//    }
//
}