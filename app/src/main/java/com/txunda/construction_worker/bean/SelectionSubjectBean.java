package com.txunda.construction_worker.bean;

import java.util.List;

/**
 * 开发者： Hzy
 * 创建时间： 2019/1/17 017 17:35:48.
 * 功能描述：
 * 联系方式： win_hzy@163.com
 */
public class SelectionSubjectBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"list":[{"taocan_id":"1","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班-法律法规","price":"300.00"},{"taocan_id":"2","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】市政实务高级班-市政实务","price":"300.00"},{"taocan_id":"3","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程管理高级班-工程管理","price":"300.00"}],"miaoshu":{"main_title":"工程管理","desc":"市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书","price_count":"1288.00","live_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"},"is_pay":1}
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
         * list : [{"taocan_id":"1","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】法律法规高级班-法律法规","price":"300.00"},{"taocan_id":"2","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】市政实务高级班-市政实务","price":"300.00"},{"taocan_id":"3","zu_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程管理高级班-工程管理","price":"300.00"}]
         * miaoshu : {"main_title":"工程管理","desc":"市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书","price_count":"1288.00","live_pic":"http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}
         * is_pay : 1
         */

        private MiaoshuBean miaoshu;
        private int is_pay;
        private List<ListBean> list;

        public MiaoshuBean getMiaoshu() {
            return miaoshu;
        }

        public void setMiaoshu(MiaoshuBean miaoshu) {
            this.miaoshu = miaoshu;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MiaoshuBean {
            /**
             * main_title : 工程管理
             * desc : 市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书
             * price_count : 1288.00
             * live_pic : http://jgb.txunda.com/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg
             */

            private String main_title;
            private String desc;
            private String price_count;
            private String live_pic;

            public String getMain_title() {
                return main_title;
            }

            public void setMain_title(String main_title) {
                this.main_title = main_title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPrice_count() {
                return price_count;
            }

            public void setPrice_count(String price_count) {
                this.price_count = price_count;
            }

            public String getLive_pic() {
                return live_pic;
            }

            public void setLive_pic(String live_pic) {
                this.live_pic = live_pic;
            }
        }

        public static class ListBean {
            /**
             * taocan_id : 1
             * zu_pic : http://jgb.txunda.com/Uploads/Index/2019-01-15/5c3d837278165.jpg
             * title : 【全科】法律法规高级班-法律法规
             * price : 300.00
             */

            private String taocan_id;
            private String zu_pic;
            private String title;
            private String price;

            public String getTaocan_id() {
                return taocan_id;
            }

            public void setTaocan_id(String taocan_id) {
                this.taocan_id = taocan_id;
            }

            public String getZu_pic() {
                return zu_pic;
            }

            public void setZu_pic(String zu_pic) {
                this.zu_pic = zu_pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }

//    /**
//     * code : 1
//     * message : 请求成功
//     * data : {"list":[{"taocan_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-法律法规","price":"300.00"},{"taocan_id":"2","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-市政实务","price":"300.00"},{"taocan_id":"3","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-工程管理","price":"300.00"}],"miaoshu":{"main_title":"工程项目管理","desc":"市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书","price_count":"1288.00","live_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}}
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
//         * list : [{"taocan_id":"1","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-法律法规","price":"300.00"},{"taocan_id":"2","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-市政实务","price":"300.00"},{"taocan_id":"3","zu_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg","title":"【全科】工程项目管理高级班-工程管理","price":"300.00"}]
//         * miaoshu : {"main_title":"工程项目管理","desc":"市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书","price_count":"1288.00","live_pic":"http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg"}
//         */
//
//        private MiaoshuBean miaoshu;
//        private List<ListBean> list;
//
//        public MiaoshuBean getMiaoshu() {
//            return miaoshu;
//        }
//
//        public void setMiaoshu(MiaoshuBean miaoshu) {
//            this.miaoshu = miaoshu;
//        }
//
//        public List<ListBean> getList() {
//            return list;
//        }
//
//        public void setList(List<ListBean> list) {
//            this.list = list;
//        }
//
//        public static class MiaoshuBean {
//            /**
//             * main_title : 工程项目管理
//             * desc : 市政专业高级班包含三门科目，我们会详细讲解，助力各位学员成功考取证书
//             * price_count : 1288.00
//             * live_pic : http://jiangongbang.cn/Uploads/Index/2019-01-16/5c3ee29c1e8c8.jpg
//             */
//
//            private String main_title;
//            private String desc;
//            private String price_count;
//            private String live_pic;
//
//            public String getMain_title() {
//                return main_title;
//            }
//
//            public void setMain_title(String main_title) {
//                this.main_title = main_title;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getPrice_count() {
//                return price_count;
//            }
//
//            public void setPrice_count(String price_count) {
//                this.price_count = price_count;
//            }
//
//            public String getLive_pic() {
//                return live_pic;
//            }
//
//            public void setLive_pic(String live_pic) {
//                this.live_pic = live_pic;
//            }
//        }
//
//        public static class ListBean {
//            /**
//             * taocan_id : 1
//             * zu_pic : http://jiangongbang.cn/Uploads/Index/2019-01-15/5c3d837278165.jpg
//             * title : 【全科】工程项目管理高级班-法律法规
//             * price : 300.00
//             */
//
//            private String taocan_id;
//            private String zu_pic;
//            private String title;
//            private String price;
//
//            public String getTaocan_id() {
//                return taocan_id;
//            }
//
//            public void setTaocan_id(String taocan_id) {
//                this.taocan_id = taocan_id;
//            }
//
//            public String getZu_pic() {
//                return zu_pic;
//            }
//
//            public void setZu_pic(String zu_pic) {
//                this.zu_pic = zu_pic;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getPrice() {
//                return price;
//            }
//
//            public void setPrice(String price) {
//                this.price = price;
//            }
//        }
//    }
}
