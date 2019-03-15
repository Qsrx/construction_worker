package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/21 021 13:37:56.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class CommentMySelfBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"m_id":"150","b_id":"9","replay_content":"这个是回复回复回复","create_time":"2019/01/08 18:29:52","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"dcawsBi"},{"m_id":"150","b_id":"1","replay_content":"这个是回复回复回复","create_time":"2019/01/08 18:28:26","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"dcawsBi"},{"m_id":"155","b_id":"9","replay_content":"评论评论评论评论评论评论评论评论评论评论","create_time":"2019/01/08 18:17:06","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"苟亚康"},{"m_id":"159","b_id":"1","replay_content":"奥术大师大所大大所多","create_time":"2019/01/05 14:10:57","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"王根基"},{"m_id":"140","b_id":"1","replay_content":"奥术大师大所大大所多","create_time":"2019/01/05 14:10:57","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"khbnYO"},{"m_id":"157","b_id":"1","replay_content":"奥术大师大所大大所多","create_time":"2019/01/05 14:10:57","head_pic":"http://jgb.cn/Upload/Member/default.png","nickname":"刘东升"}]
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
         * b_id : 9
         * replay_content : 这个是回复回复回复
         * create_time : 2019/01/08 18:29:52
         * head_pic : http://jgb.cn/Upload/Member/default.png
         * nickname : dcawsBi
         */

        private String m_id;
        private String b_id;
        private String replay_content;
        private String create_time;
        private String head_pic;
        private String nickname;

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
    }
}
