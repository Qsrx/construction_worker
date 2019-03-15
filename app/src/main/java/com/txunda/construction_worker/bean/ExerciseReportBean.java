package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/25 025 17:52:59.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class ExerciseReportBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"count":"1","list":[{"sort":"1","is_right":"2"},{"sort":"2","is_right":"2"},{"sort":"3","is_right":"2"},{"sort":"4","is_right":"3"},{"sort":"5","is_right":"1"}]}
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
         * count : 1
         * list : [{"sort":"1","is_right":"2"},{"sort":"2","is_right":"2"},{"sort":"3","is_right":"2"},{"sort":"4","is_right":"3"},{"sort":"5","is_right":"1"}]
         */

        private String count;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * sort : 1
             * is_right : 2
             */

            private String sort;
            private String is_right;

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getIs_right() {
                return is_right;
            }

            public void setIs_right(String is_right) {
                this.is_right = is_right;
            }
        }
    }
}
