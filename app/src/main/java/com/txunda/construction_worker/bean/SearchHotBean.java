package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/28 028 13:24:29.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SearchHotBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"sort":"1","name":"法律法规","subject_id":"1"},{"sort":"1","name":"市政实务","subject_id":"2"},{"sort":"0","name":"工程管理","subject_id":"3"},{"sort":"0","name":"建设工程的基本法律知识","subject_id":"4"},{"sort":"0","name":"施工许可法律制度","subject_id":"6"},{"sort":"0","name":"建筑工程施工管理","subject_id":"8"}]
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
         * sort : 1
         * name : 法律法规
         * subject_id : 1
         */

        private String sort;
        private String name;
        private String subject_id;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }
    }
}
