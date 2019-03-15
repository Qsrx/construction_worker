package com.txunda.construction_worker.bean;

/**
 * 开发者： Hzy
 * 创建时间： 2019/3/14 014 13:34:59.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class AnliDataBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"专项练习题","sub_title":"<p>\n\t单位通过投标承接了某建筑的设备安装工程，并与建设单位签订了工程总承包合同,安装内容包括给排水、通风空调、电气照明、电梯安装等，电梯由建设单位负责采购，合同指明在安装地点交货，A经建设单位同意将电梯安装分包给了B安装公司。\n<\/p>\n<p>\n\t<br />\n<\/p>\n<p>\n\t<br />\n<\/p>","questions_id":"58","pic":"http://jgb.txunda.com/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg","answer":"<p>\n\t1、在施工过程中，A单位需做哪些协调工作\n<\/p>\n<p>\n\t2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用\n<\/p>\n<p>\n\t<br />\n<\/p>","is_analysis":1,"analysis":"法律法规题案例解析","key":"法律法规题案例解析题考点","collection":2}
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
         * title : 专项练习题
         * sub_title : <p>
         单位通过投标承接了某建筑的设备安装工程，并与建设单位签订了工程总承包合同,安装内容包括给排水、通风空调、电气照明、电梯安装等，电梯由建设单位负责采购，合同指明在安装地点交货，A经建设单位同意将电梯安装分包给了B安装公司。
         </p>
         <p>
         <br />
         </p>
         <p>
         <br />
         </p>
         * questions_id : 58
         * pic : http://jgb.txunda.com/Uploads/Member/2019-01-19/5c42d4f4aae56.jpg
         * answer : <p>
         1、在施工过程中，A单位需做哪些协调工作
         </p>
         <p>
         2、电梯制造商按期完成了设备制造，但由于季节性原因导致全国运力紧张，使电梯实际现场交货延迟了10天，问中间可能会涉及哪些主体之间的索赔？其中B可索赔哪些费用
         </p>
         <p>
         <br />
         </p>
         * is_analysis : 1
         * analysis : 法律法规题案例解析
         * key : 法律法规题案例解析题考点
         * collection : 2
         */

        private String title;
        private String sub_title;
        private String questions_id;
        private String pic;
        private String answer;
        private int is_analysis;
        private String analysis;
        private String key;
        private int collection;

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

        public String getQuestions_id() {
            return questions_id;
        }

        public void setQuestions_id(String questions_id) {
            this.questions_id = questions_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getIs_analysis() {
            return is_analysis;
        }

        public void setIs_analysis(int is_analysis) {
            this.is_analysis = is_analysis;
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

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }
    }
}
