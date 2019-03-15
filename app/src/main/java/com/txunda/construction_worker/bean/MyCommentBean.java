package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/21 021 15:35:00.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class MyCommentBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"m_id":"150","b_id":"1","replay_content":"这个是回复回复回复","create_time":"1546943306","head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"dcawsBi","other_pic":"http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg","other_nickname":"王天乐","other_content":"论坛问题问题问题222","content_pic":[{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}]},{"m_id":"150","b_id":"9","replay_content":"这个是回复回复回复","create_time":"1546943392","head_pic":"http://jgb.cn/Uploads/Member/default.png","nickname":"dcawsBi","other_pic":"http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg","other_nickname":"dcawsBi","other_content":"提问问题问题问","content_pic":[]}]
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
         * m_id : 150
         * b_id : 1
         * replay_content : 这个是回复回复回复
         * create_time : 1546943306
         * head_pic : http://jgb.cn/Uploads/Member/default.png
         * nickname : dcawsBi
         * other_pic : http://jgb.cn/Uploads/Member/2019-01-04/5c2efd6301090.jpg
         * other_nickname : 王天乐
         * other_content : 论坛问题问题问题222
         * content_pic : [{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"},{"path":"http://jgb.cn/Uploads/Index/2019-01-07/5c330d6067db8.jpg"}]
         */

        private String m_id;
        private String b_id;
        private String replay_content;
        private String create_time;
        private String head_pic;
        private String nickname;
        private String other_pic;
        private String other_nickname;
        private String other_content;
        private List<ContentPicBean> content_pic;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getB_id() {
            return b_id;
        }

        public void setB_id(String b_id) {
            this.b_id = b_id;
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

        public String getOther_pic() {
            return other_pic;
        }

        public void setOther_pic(String other_pic) {
            this.other_pic = other_pic;
        }

        public String getOther_nickname() {
            return other_nickname;
        }

        public void setOther_nickname(String other_nickname) {
            this.other_nickname = other_nickname;
        }

        public String getOther_content() {
            return other_content;
        }

        public void setOther_content(String other_content) {
            this.other_content = other_content;
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
}
