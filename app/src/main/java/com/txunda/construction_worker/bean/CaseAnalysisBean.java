package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/2/21 021 10:56:03.
 * 功能描述：专项-案例分析题实体类
 * 联系方式： win_hzy@163.com
 */
public class CaseAnalysisBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"questions_id":"4","title":"案例分析","sub_title":"\n\t单位通过投标承接了某建筑的设备安装工程，并与建设单位签订了工程总承包合同,安装内容包括给排水、通风空调、电气照明、电梯安装等，电梯由建设单位负责采购，合同指明在安装地点交货，A经建设单位同意将电梯安装分包给了B安装公司。\n\n\n\t1、在施工过程中，A单位需做哪些协调工作？\n\n\n\t2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用？\n\n\n\t\n","answer1":"\n\t1、在施工过程中，A单位需做哪些协调工作\n\n\n\t2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用\n\n\n\t\n","fenxi_pic":"http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","analysis":"法律法规题案例解析","key":"法律法规题案例解析题考点","is_fee":"2","collection":2,"pay":1}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * questions_id : 4
             * title : 案例分析
             * sub_title :
             单位通过投标承接了某建筑的设备安装工程，并与建设单位签订了工程总承包合同,安装内容包括给排水、通风空调、电气照明、电梯安装等，电梯由建设单位负责采购，合同指明在安装地点交货，A经建设单位同意将电梯安装分包给了B安装公司。


             1、在施工过程中，A单位需做哪些协调工作？


             2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用？




             * answer1 :
             1、在施工过程中，A单位需做哪些协调工作


             2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用




             * fenxi_pic : http://jiangongbang.cn/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
             * analysis : 法律法规题案例解析
             * key : 法律法规题案例解析题考点
             * is_fee : 2
             * collection : 2
             * pay : 1
             */

            private String questions_id;
            private String title;
            private String sub_title;
            private String answer1;
            private String fenxi_pic;
            private String analysis;
            private String key;
            private String is_fee;
            private int collection;
            private int pay;

            public String getQuestions_id() {
                return questions_id;
            }

            public void setQuestions_id(String questions_id) {
                this.questions_id = questions_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public String getAnswer1() {
                return answer1;
            }

            public void setAnswer1(String answer1) {
                this.answer1 = answer1;
            }

            public String getFenxi_pic() {
                return fenxi_pic;
            }

            public void setFenxi_pic(String fenxi_pic) {
                this.fenxi_pic = fenxi_pic;
            }

            public String getAnalysis() {
                return analysis;
            }

            public void setAnalysis(String analysis) {
                this.analysis = analysis;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getIs_fee() {
                return is_fee;
            }

            public void setIs_fee(String is_fee) {
                this.is_fee = is_fee;
            }

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }
        }
    }
}
