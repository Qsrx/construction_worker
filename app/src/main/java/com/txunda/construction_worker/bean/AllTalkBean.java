package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/31 031 11:14:12.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class AllTalkBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3ffcd1ecb2b.jpg","nickname":"李吉吉","star":"10","create_time":"2019/01/18","content":"这个课程好啊，很满意的一次购物，好评没毛病"},{"pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","nickname":"IGU9VH","star":"10","create_time":"2019/01/15","content":"好好好好好啊"}]
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
         * pic : http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3ffcd1ecb2b.jpg
         * nickname : 李吉吉
         * star : 10
         * create_time : 2019/01/18
         * content : 这个课程好啊，很满意的一次购物，好评没毛病
         */

        private String pic;
        private String nickname;
        private String star;
        private String create_time;
        private String content;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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
}
