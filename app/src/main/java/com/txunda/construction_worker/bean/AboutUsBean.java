package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/14 014 16:37:37.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class AboutUsBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"cover":"http://jiangongbang.cn/Uploads/Index/2019-01-18/5c4120c6c7d1c.jpg","title":"建工邦","down":"建工邦有限公司","coins":"CopyRight2018 jiangongbang.com"}
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
         * cover : http://jiangongbang.cn/Uploads/Index/2019-01-18/5c4120c6c7d1c.jpg
         * title : 建工邦
         * down : 建工邦有限公司
         * coins : CopyRight2018 jiangongbang.com
         */

        private String cover;
        private String title;
        private String down;
        private String coins;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }
    }

//
//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"title":"关于我们","content":"建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我","coins":"Copyright © 建工邦 ALL Right Reserved","cover":"http://jgb.cn/Uploads/Member/default.png"}
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
//         * title : 关于我们
//         * content : 建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我们建工帮关于我
//         * coins : Copyright © 建工邦 ALL Right Reserved
//         * cover : http://jgb.cn/Uploads/Member/default.png
//         */
//
//        private String title;
//        private String content;
//        private String coins;
//        private String cover;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public String getCoins() {
//            return coins;
//        }
//
//        public void setCoins(String coins) {
//            this.coins = coins;
//        }
//
//        public String getCover() {
//            return cover;
//        }
//
//        public void setCover(String cover) {
//            this.cover = cover;
//        }
//    }
}
