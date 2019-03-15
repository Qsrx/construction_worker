package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/8 008 17:23:55.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ChoiceProfessionBean {


    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":"1","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"一级建造师"},{"id":"2","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"二级建造师"},{"id":"3","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"一级消防工程师"},{"id":"4","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"造价工程师"},{"id":"5","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"BIM"},{"id":"6","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"八大员 "},{"id":"7","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"三类人员"},{"id":"8","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg","iname":"测绘师"},{"id":"9","general_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","industry_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg","iname":"房产估价师"}]
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
         * id : 1
         * general_pic : http://jiangongbang.cn/Uploads/Index/2019-01-17/5c3fdf0335b9d.jpg
         * industry_pic : http://jiangongbang.cn/Uploads/Index/2019-01-10/5c370448ecf6f.jpg
         * iname : 一级建造师
         */

        private String id;
        private String general_pic;
        private String industry_pic;
        private String iname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGeneral_pic() {
            return general_pic;
        }

        public void setGeneral_pic(String general_pic) {
            this.general_pic = general_pic;
        }

        public String getIndustry_pic() {
            return industry_pic;
        }

        public void setIndustry_pic(String industry_pic) {
            this.industry_pic = industry_pic;
        }

        public String getIname() {
            return iname;
        }

        public void setIname(String iname) {
            this.iname = iname;
        }
    }
}
