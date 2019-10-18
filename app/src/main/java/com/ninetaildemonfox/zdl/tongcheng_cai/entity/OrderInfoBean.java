package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

import java.util.List;

public class OrderInfoBean {


    /**
     * code : 1
     * data : {"choose_items":[{"dan":0,"end_date":"09/03","end_hour_minute":"20:00","guest_team_name":"洛杉矶火花","home_team_name":"达拉斯飞马","match_id":2,"match_info":"冈山绿熊vs广岛三箭","match_number":1,"member_item":[{"is_true":false,"key":"主胜","value":"1"},{"is_true":false,"key":"主胜","value":"1"}],"system_item":[],"week":"周二"},{"dan":0,"end_date":"09/03","end_hour_minute":"20:00","guest_team_name":"达拉斯小牛","home_team_name":"华盛顿神秘人","match_id":3,"member_item":[{"is_true":true,"key":"主胜(1-5)","value":"1"},{"is_true":true,"key":"大","value":"1"}],"system_item":[{"is_true":true,"key":"主胜(1-5)"},{"is_true":true,"key":"大"}],"week":"周二"}],"choose_match_ids":"2,3","create_date":"2019-09-04 17:58:20","create_time":"1567591100","id":"7","last_match_id":"3","lottery_details":[{"items":[{"match":"周三001","result":"胜"},{"match":"周三002","result":"1"}],"lottery_info":"2串1 1注1倍","order_money":2,"win_money":"0.00"}],"lottery_info":"2串1","lottery_note":"4","lottery_times":"2","member_id":"7","order_money":"16.00","order_name":"竞彩足球混合过关","order_no":"FM156759110021876594","order_type":"basketball","play_flag":"mix","send_money":"10","status":"1","update_time":"1567591100","win_money":"0.00"}
     * message : 查询成功
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * choose_items : [{"dan":0,"end_date":"09/03","end_hour_minute":"20:00","guest_team_name":"洛杉矶火花","home_team_name":"达拉斯飞马","match_id":2,"match_info":"冈山绿熊vs广岛三箭","match_number":1,"member_item":[{"is_true":false,"key":"主胜","value":"1"},{"is_true":false,"key":"主胜","value":"1"}],"system_item":[],"week":"周二"},{"dan":0,"end_date":"09/03","end_hour_minute":"20:00","guest_team_name":"达拉斯小牛","home_team_name":"华盛顿神秘人","match_id":3,"member_item":[{"is_true":true,"key":"主胜(1-5)","value":"1"},{"is_true":true,"key":"大","value":"1"}],"system_item":[{"is_true":true,"key":"主胜(1-5)"},{"is_true":true,"key":"大"}],"week":"周二"}]
         * choose_match_ids : 2,3
         * create_date : 2019-09-04 17:58:20
         * create_time : 1567591100
         * id : 7
         * last_match_id : 3
         * lottery_details : [{"items":[{"match":"周三001","result":"胜"},{"match":"周三002","result":"1"}],"lottery_info":"2串1 1注1倍","order_money":2,"win_money":"0.00"}]
         * lottery_info : 2串1
         * lottery_note : 4
         * lottery_times : 2
         * member_id : 7
         * order_money : 16.00
         * order_name : 竞彩足球混合过关
         * order_no : FM156759110021876594
         * order_type : basketball
         * play_flag : mix
         * send_money : 10
         * status : 1
         * update_time : 1567591100
         * win_money : 0.00
         */

        private String choose_match_ids;
        private String create_date;
        private String create_time;
        private String id;
        private String last_match_id;
        private String lottery_info;
        private String lottery_note;
        private String lottery_times;
        private String member_id;
        private String order_money;
        private String order_name;
        private String order_no;
        private String order_type;
        private String play_flag;
        private String send_money;
        private String status;
        private String update_time;
        private String win_money;
        private List<ChooseItemsBean> choose_items;
        private List<LotteryDetailsBean> lottery_details;

        public String getChoose_match_ids() {
            return choose_match_ids;
        }

        public void setChoose_match_ids(String choose_match_ids) {
            this.choose_match_ids = choose_match_ids;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLast_match_id() {
            return last_match_id;
        }

        public void setLast_match_id(String last_match_id) {
            this.last_match_id = last_match_id;
        }

        public String getLottery_info() {
            return lottery_info;
        }

        public void setLottery_info(String lottery_info) {
            this.lottery_info = lottery_info;
        }

        public String getLottery_note() {
            return lottery_note;
        }

        public void setLottery_note(String lottery_note) {
            this.lottery_note = lottery_note;
        }

        public String getLottery_times() {
            return lottery_times;
        }

        public void setLottery_times(String lottery_times) {
            this.lottery_times = lottery_times;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getOrder_money() {
            return order_money;
        }

        public void setOrder_money(String order_money) {
            this.order_money = order_money;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getPlay_flag() {
            return play_flag;
        }

        public void setPlay_flag(String play_flag) {
            this.play_flag = play_flag;
        }

        public String getSend_money() {
            return send_money;
        }

        public void setSend_money(String send_money) {
            this.send_money = send_money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getWin_money() {
            return win_money;
        }

        public void setWin_money(String win_money) {
            this.win_money = win_money;
        }

        public List<ChooseItemsBean> getChoose_items() {
            return choose_items;
        }

        public void setChoose_items(List<ChooseItemsBean> choose_items) {
            this.choose_items = choose_items;
        }

        public List<LotteryDetailsBean> getLottery_details() {
            return lottery_details;
        }

        public void setLottery_details(List<LotteryDetailsBean> lottery_details) {
            this.lottery_details = lottery_details;
        }

        public static class ChooseItemsBean {
            /**
             * dan : 0
             * end_date : 09/03
             * end_hour_minute : 20:00
             * guest_team_name : 洛杉矶火花
             * home_team_name : 达拉斯飞马
             * match_id : 2
             * match_info : 冈山绿熊vs广岛三箭
             * match_number : 1
             * member_item : [{"is_true":false,"key":"主胜","value":"1"},{"is_true":false,"key":"主胜","value":"1"}]
             * system_item : []
             * week : 周二
             */

            private int dan;
            private String end_date;
            private String end_hour_minute;
            private String guest_team_name;
            private String home_team_name;
            private int match_id;
            private String match_info;
            private int match_number;
            private String week;
            private List<MemberItemBean> member_item;
            private List<?> system_item;

            public int getDan() {
                return dan;
            }

            public void setDan(int dan) {
                this.dan = dan;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getEnd_hour_minute() {
                return end_hour_minute;
            }

            public void setEnd_hour_minute(String end_hour_minute) {
                this.end_hour_minute = end_hour_minute;
            }

            public String getGuest_team_name() {
                return guest_team_name;
            }

            public void setGuest_team_name(String guest_team_name) {
                this.guest_team_name = guest_team_name;
            }

            public String getHome_team_name() {
                return home_team_name;
            }

            public void setHome_team_name(String home_team_name) {
                this.home_team_name = home_team_name;
            }

            public int getMatch_id() {
                return match_id;
            }

            public void setMatch_id(int match_id) {
                this.match_id = match_id;
            }

            public String getMatch_info() {
                return match_info;
            }

            public void setMatch_info(String match_info) {
                this.match_info = match_info;
            }

            public int getMatch_number() {
                return match_number;
            }

            public void setMatch_number(int match_number) {
                this.match_number = match_number;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public List<MemberItemBean> getMember_item() {
                return member_item;
            }

            public void setMember_item(List<MemberItemBean> member_item) {
                this.member_item = member_item;
            }

            public List<?> getSystem_item() {
                return system_item;
            }

            public void setSystem_item(List<?> system_item) {
                this.system_item = system_item;
            }

            public static class MemberItemBean {
                /**
                 * is_true : false
                 * key : 主胜
                 * value : 1
                 */

                private boolean is_true;
                private String key;
                private String value;

                public boolean isIs_true() {
                    return is_true;
                }

                public void setIs_true(boolean is_true) {
                    this.is_true = is_true;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class LotteryDetailsBean {
            /**
             * items : [{"match":"周三001","result":"胜"},{"match":"周三002","result":"1"}]
             * lottery_info : 2串1 1注1倍
             * order_money : 2
             * win_money : 0.00
             */

            private String lottery_info;
            private int order_money;
            private String win_money;
            private List<ItemsBean> items;

            public String getLottery_info() {
                return lottery_info;
            }

            public void setLottery_info(String lottery_info) {
                this.lottery_info = lottery_info;
            }

            public int getOrder_money() {
                return order_money;
            }

            public void setOrder_money(int order_money) {
                this.order_money = order_money;
            }

            public String getWin_money() {
                return win_money;
            }

            public void setWin_money(String win_money) {
                this.win_money = win_money;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * match : 周三001
                 * result : 胜
                 */

                private String match;
                private String result;

                public String getMatch() {
                    return match;
                }

                public void setMatch(String match) {
                    this.match = match;
                }

                public String getResult() {
                    return result;
                }

                public void setResult(String result) {
                    this.result = result;
                }
            }
        }
    }
}
