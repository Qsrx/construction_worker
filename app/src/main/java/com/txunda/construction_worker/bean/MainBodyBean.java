package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/10 010 15:03:10.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MainBodyBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"details":{"id":"1","m_id":"145","content":"论坛问题问题问题","content_pic":[{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}],"support":"10","create_time":"2019/01/08 18:01:11","head_pic":"http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg","nickname":"王天乐","is_like":1},"comment":[{"m_id":"159","replay_content":"奥术大师大所大大所多","create_time":"2019/01/08 18:01:11","is_like":1,"head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"王根基"},{"m_id":"157","replay_content":"奥术大师大所大大所多","create_time":"2019/01/08 18:01:11","is_like":1,"head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"刘东升"}]}
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
         * details : {"id":"1","m_id":"145","content":"论坛问题问题问题","content_pic":[{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}],"support":"10","create_time":"2019/01/08 18:01:11","head_pic":"http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg","nickname":"王天乐","is_like":1}
         * comment : [{"m_id":"159","replay_content":"奥术大师大所大大所多","create_time":"2019/01/08 18:01:11","is_like":1,"head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"王根基"},{"m_id":"157","replay_content":"奥术大师大所大大所多","create_time":"2019/01/08 18:01:11","is_like":1,"head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"刘东升"}]
         */

        private DetailsBean details;
        private List<CommentBean> comment;

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class DetailsBean {
            /**
             * id : 1
             * m_id : 145
             * content : 论坛问题问题问题
             * content_pic : [{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}]
             * support : 10
             * create_time : 2019/01/08 18:01:11
             * head_pic : http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg
             * nickname : 王天乐
             * is_like : 1
             */

            private String id;
            private String m_id;
            private String content;
            private String support;
            private String create_time;
            private String head_pic;
            private String nickname;
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

            public String getSupport() {
                return support;
            }

            public void setSupport(String support) {
                this.support = support;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
                 * path : http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg
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

        public static class CommentBean {
            /**
             * m_id : 159
             * replay_content : 奥术大师大所大大所多
             * create_time : 2019/01/08 18:01:11
             * is_like : 1
             * head_pic : http://jgb.cn/Uploads/Member/default.png
             * nickname : 王根基
             */

            private String m_id;
            private String replay_content;
            private String create_time;
            private int is_like;
            private String head_pic;
            private String nickname;

            public String getM_id() {
                return m_id;
            }

            public void setM_id(String m_id) {
                this.m_id = m_id;
            }

            public String getReplay_content() {
                return replay_content;
            }

            public void setReplay_content(String replay_content) {
                this.replay_content = replay_content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
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
        }
    }
}