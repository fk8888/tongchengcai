package com.ninetaildemonfox.zdl.tongcheng_cai.entity;


import java.io.Serializable;
import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/27
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WFootallBean implements Serializable {

    /**
     * code : 1
     * message : 查询成功
     * data : [{"week":"星期天","count":"2场可投注","date":"2019-09-29","list":[{"id":"46","match_number":"001","home_team_id":"2","guest_team_id":"13","end_time":"1569686400","start_time":"1569772800","start_date":"2019-09-30","end_date":"2019-09-29","home_team_name":"广岛三箭","guest_team_name":"切尔西","match_type_name":"法甲","home_victory_number":"6","home_flat_number":"1","home_lose_number":"1","guest_victory_number":"0","guest_flat_number":"0","guest_lose_number":"0","end_hour_minute":"00:00","start_hour_minute":"00:00","play_flag":"mix","play_value":{"victory":{"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"let_victory":{"flag":"-3","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"half":[{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}],"number_value":[{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}],"score_value":[{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]},"avg_victory_odds":"17","avg_flat_odds":"1.5","avg_lose_odds":"2","history_victory_number":"0","history_flat_number":"0","history_lose_number":"0"},{"id":"47","match_number":"002","home_team_id":"4","guest_team_id":"15","end_time":"1569686400","start_time":"1569772800","start_date":"2019-09-30","end_date":"2019-09-29","home_team_name":"大宫松鼠","guest_team_name":"国际米兰","match_type_name":"法甲","home_victory_number":"0","home_flat_number":"0","home_lose_number":"0","guest_victory_number":"0","guest_flat_number":"0","guest_lose_number":"0","end_hour_minute":"00:00","start_hour_minute":"00:00","play_flag":"mix","play_value":{"victory":{"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"let_victory":{"flag":"+1","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"half":[{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}],"number_value":[{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}],"score_value":[{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]},"avg_victory_odds":"1","avg_flat_odds":"1","avg_lose_odds":"1","history_victory_number":"0","history_flat_number":"0","history_lose_number":"0"}]}]
     */

    private int code;
    private String message;
    private List<DataBeanXXX> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public WFootallBean(List<DataBeanXXX> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBeanXXX> getData() {
        return data;
    }

    public void setData(List<DataBeanXXX> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WFootallBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanXXX implements Serializable{
        /**
         * week : 星期天
         * count : 2场可投注
         * date : 2019-09-29
         * list : [{"id":"46","match_number":"001","home_team_id":"2","guest_team_id":"13","end_time":"1569686400","start_time":"1569772800","start_date":"2019-09-30","end_date":"2019-09-29","home_team_name":"广岛三箭","guest_team_name":"切尔西","match_type_name":"法甲","home_victory_number":"6","home_flat_number":"1","home_lose_number":"1","guest_victory_number":"0","guest_flat_number":"0","guest_lose_number":"0","end_hour_minute":"00:00","start_hour_minute":"00:00","play_flag":"mix","play_value":{"victory":{"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"let_victory":{"flag":"-3","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"half":[{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}],"number_value":[{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}],"score_value":[{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]},"avg_victory_odds":"17","avg_flat_odds":"1.5","avg_lose_odds":"2","history_victory_number":"0","history_flat_number":"0","history_lose_number":"0"},{"id":"47","match_number":"002","home_team_id":"4","guest_team_id":"15","end_time":"1569686400","start_time":"1569772800","start_date":"2019-09-30","end_date":"2019-09-29","home_team_name":"大宫松鼠","guest_team_name":"国际米兰","match_type_name":"法甲","home_victory_number":"0","home_flat_number":"0","home_lose_number":"0","guest_victory_number":"0","guest_flat_number":"0","guest_lose_number":"0","end_hour_minute":"00:00","start_hour_minute":"00:00","play_flag":"mix","play_value":{"victory":{"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"let_victory":{"flag":"+1","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"half":[{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}],"number_value":[{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}],"score_value":[{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]},"avg_victory_odds":"1","avg_flat_odds":"1","avg_lose_odds":"1","history_victory_number":"0","history_flat_number":"0","history_lose_number":"0"}]
         */

        private String week;
        private String count;
        private String date;
        private String typeCount;
        private List<ListBean> list;

        public String getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(String typeCount) {
            this.typeCount = typeCount;
        }


        public String getSum(){
            return getDate()+ getWeek() + getCount();
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }


        @Override
        public String toString() {
            return "DataBeanXXX{" +
                    "week='" + week + '\'' +
                    ", count='" + count + '\'' +
                    ", date='" + date + '\'' +
                    ", typeCount='" + typeCount + '\'' +
                    ", list=" + list +
                    '}';
        }

        public static class ListBean implements Serializable{
            /**
             * id : 46
             * match_number : 001
             * home_team_id : 2
             * guest_team_id : 13
             * end_time : 1569686400
             * start_time : 1569772800
             * start_date : 2019-09-30
             * end_date : 2019-09-29
             * home_team_name : 广岛三箭
             * guest_team_name : 切尔西
             * match_type_name : 法甲
             * home_victory_number : 6
             * home_flat_number : 1
             * home_lose_number : 1
             * guest_victory_number : 0
             * guest_flat_number : 0
             * guest_lose_number : 0
             * end_hour_minute : 00:00
             * start_hour_minute : 00:00
             * play_flag : mix
             * play_value : {"victory":{"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"let_victory":{"flag":"-3","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]},"half":[{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}],"number_value":[{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}],"score_value":[{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]}
             * avg_victory_odds : 17
             * avg_flat_odds : 1.5
             * avg_lose_odds : 2
             * history_victory_number : 0
             * history_flat_number : 0
             * history_lose_number : 0
             */

            private String id;
            private String match_number;
            private String home_team_id;
            private String guest_team_id;
            private String end_time;
            private String start_time;
            private String start_date;
            private String end_date;
            private String home_team_name;
            private String guest_team_name;
            private String match_type_name;
            private String home_victory_number;
            private String home_flat_number;
            private String home_lose_number;
            private String guest_victory_number;
            private String guest_flat_number;
            private String guest_lose_number;
            private String end_hour_minute;
            private String start_hour_minute;
            private String play_flag;
            private PlayValueBean play_value;
            private String avg_victory_odds;
            private String avg_flat_odds;
            private String avg_lose_odds;
            private String history_victory_number;
            private String history_flat_number;
            private String history_lose_number;
            private String fenXi;

            private boolean ischecked = false;

            public boolean isIschecked() {
                return ischecked;
            }

            public void setIschecked(boolean ischecked) {
                this.ischecked = ischecked;
            }

            public String getMatchNumBerHour(){
                return getMatch_type_name()+"\n"+getMatch_number()+"\n"+getEnd_hour_minute()+"截止";
            }

            public String getFenXi() {
                return fenXi;
            }

            public void setFenXi(String fenXi) {
                this.fenXi = fenXi;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMatch_number() {
                return match_number;
            }

            public void setMatch_number(String match_number) {
                this.match_number = match_number;
            }

            public String getHome_team_id() {
                return home_team_id;
            }

            public void setHome_team_id(String home_team_id) {
                this.home_team_id = home_team_id;
            }

            public String getGuest_team_id() {
                return guest_team_id;
            }

            public void setGuest_team_id(String guest_team_id) {
                this.guest_team_id = guest_team_id;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getHome_team_name() {
                return home_team_name;
            }

            public void setHome_team_name(String home_team_name) {
                this.home_team_name = home_team_name;
            }

            public String getGuest_team_name() {
                return guest_team_name;
            }

            public void setGuest_team_name(String guest_team_name) {
                this.guest_team_name = guest_team_name;
            }

            public String getMatch_type_name() {
                return match_type_name;
            }

            public void setMatch_type_name(String match_type_name) {
                this.match_type_name = match_type_name;
            }

            public String getHome_victory_number() {
                return home_victory_number;
            }

            public void setHome_victory_number(String home_victory_number) {
                this.home_victory_number = home_victory_number;
            }

            public String getHome_flat_number() {
                return home_flat_number;
            }

            public void setHome_flat_number(String home_flat_number) {
                this.home_flat_number = home_flat_number;
            }

            public String getHome_lose_number() {
                return home_lose_number;
            }

            public void setHome_lose_number(String home_lose_number) {
                this.home_lose_number = home_lose_number;
            }

            public String getGuest_victory_number() {
                return guest_victory_number;
            }

            public void setGuest_victory_number(String guest_victory_number) {
                this.guest_victory_number = guest_victory_number;
            }

            public String getGuest_flat_number() {
                return guest_flat_number;
            }

            public void setGuest_flat_number(String guest_flat_number) {
                this.guest_flat_number = guest_flat_number;
            }

            public String getGuest_lose_number() {
                return guest_lose_number;
            }

            public void setGuest_lose_number(String guest_lose_number) {
                this.guest_lose_number = guest_lose_number;
            }

            public String getEnd_hour_minute() {
                return end_hour_minute;
            }

            public void setEnd_hour_minute(String end_hour_minute) {
                this.end_hour_minute = end_hour_minute;
            }

            public String getStart_hour_minute() {
                return start_hour_minute;
            }

            public void setStart_hour_minute(String start_hour_minute) {
                this.start_hour_minute = start_hour_minute;
            }

            public String getPlay_flag() {
                return play_flag;
            }

            public void setPlay_flag(String play_flag) {
                this.play_flag = play_flag;
            }

            public PlayValueBean getPlay_value() {
                return play_value;
            }

            public void setPlay_value(PlayValueBean play_value) {
                this.play_value = play_value;
            }

            public String getAvg_victory_odds() {
                return avg_victory_odds;
            }

            public void setAvg_victory_odds(String avg_victory_odds) {
                this.avg_victory_odds = avg_victory_odds;
            }

            public String getAvg_flat_odds() {
                return avg_flat_odds;
            }

            public void setAvg_flat_odds(String avg_flat_odds) {
                this.avg_flat_odds = avg_flat_odds;
            }

            public String getAvg_lose_odds() {
                return avg_lose_odds;
            }

            public void setAvg_lose_odds(String avg_lose_odds) {
                this.avg_lose_odds = avg_lose_odds;
            }

            public String getHistory_victory_number() {
                return history_victory_number;
            }

            public void setHistory_victory_number(String history_victory_number) {
                this.history_victory_number = history_victory_number;
            }

            public String getHistory_flat_number() {
                return history_flat_number;
            }

            public void setHistory_flat_number(String history_flat_number) {
                this.history_flat_number = history_flat_number;
            }

            public String getHistory_lose_number() {
                return history_lose_number;
            }

            public void setHistory_lose_number(String history_lose_number) {
                this.history_lose_number = history_lose_number;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", match_number='" + match_number + '\'' +
                        ", home_team_id='" + home_team_id + '\'' +
                        ", guest_team_id='" + guest_team_id + '\'' +
                        ", end_time='" + end_time + '\'' +
                        ", start_time='" + start_time + '\'' +
                        ", start_date='" + start_date + '\'' +
                        ", end_date='" + end_date + '\'' +
                        ", home_team_name='" + home_team_name + '\'' +
                        ", guest_team_name='" + guest_team_name + '\'' +
                        ", match_type_name='" + match_type_name + '\'' +
                        ", home_victory_number='" + home_victory_number + '\'' +
                        ", home_flat_number='" + home_flat_number + '\'' +
                        ", home_lose_number='" + home_lose_number + '\'' +
                        ", guest_victory_number='" + guest_victory_number + '\'' +
                        ", guest_flat_number='" + guest_flat_number + '\'' +
                        ", guest_lose_number='" + guest_lose_number + '\'' +
                        ", end_hour_minute='" + end_hour_minute + '\'' +
                        ", start_hour_minute='" + start_hour_minute + '\'' +
                        ", play_flag='" + play_flag + '\'' +
                        ", play_value=" + play_value +
                        ", avg_victory_odds='" + avg_victory_odds + '\'' +
                        ", avg_flat_odds='" + avg_flat_odds + '\'' +
                        ", avg_lose_odds='" + avg_lose_odds + '\'' +
                        ", history_victory_number='" + history_victory_number + '\'' +
                        ", history_flat_number='" + history_flat_number + '\'' +
                        ", history_lose_number='" + history_lose_number + '\'' +
                        '}';
            }

            public static class PlayValueBean implements Serializable{
                /**
                 * victory : {"flag":"0","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]}
                 * let_victory : {"flag":"-3","data":[{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]}
                 * half : [{"key":"胜胜","value":"1"},{"key":"胜平","value":"1"},{"key":"胜负","value":"1"},{"key":"平胜","value":"1"},{"key":"平平","value":"1"},{"key":"平负","value":"1"},{"key":"负胜","value":"1"},{"key":"负平","value":"1"},{"key":"负负","value":"1"}]
                 * number_value : [{"key":"1球","value":"1"},{"key":"2球","value":"1"},{"key":"3球","value":"1"},{"key":"4球","value":"1"},{"key":"5球","value":"1"},{"key":"6球","value":"1"},{"key":"7+球","value":"1"},{"key":"0球","value":"1"}]
                 * score_value : [{"flag":"胜","data":[{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]},{"flag":"平","data":[{"key":"平其他","value":"1"},{"key":"0:0","value":"1"},{"key":"1:1","value":"1"},{"key":"2:2","value":"1"},{"key":"3:3","value":"1"}]},{"flag":"负","data":[{"key":"负其他","value":"1"},{"key":"0:1","value":"1"},{"key":"0:2","value":"1"},{"key":"0:3","value":"1"},{"key":"1:2","value":"1"},{"key":"1:3","value":"1"}]}]
                 */

                private VictoryBean victory;
                private LetVictoryBean let_victory;
                private List<HalfBean> half;
                private List<NumberValueBean> number_value;
                private List<ScoreValueBean> score_value;

                private boolean isChildCheck = false;

                public boolean isChildCheck() {
                    return isChildCheck;
                }

                public void setChildCheck(boolean childCheck) {
                    isChildCheck = childCheck;
                }

                public VictoryBean getVictory() {
                    return victory;
                }

                public void setVictory(VictoryBean victory) {
                    this.victory = victory;
                }

                public LetVictoryBean getLet_victory() {
                    return let_victory;
                }

                public void setLet_victory(LetVictoryBean let_victory) {
                    this.let_victory = let_victory;
                }

                public List<HalfBean> getHalf() {
                    return half;
                }

                public void setHalf(List<HalfBean> half) {
                    this.half = half;
                }

                public List<NumberValueBean> getNumber_value() {
                    return number_value;
                }

                public void setNumber_value(List<NumberValueBean> number_value) {
                    this.number_value = number_value;
                }

                public List<ScoreValueBean> getScore_value() {
                    return score_value;
                }

                public void setScore_value(List<ScoreValueBean> score_value) {
                    this.score_value = score_value;
                }

                @Override
                public String toString() {
                    return "PlayValueBean{" +
                            "victory=" + victory +
                            ", let_victory=" + let_victory +
                            ", half=" + half +
                            ", number_value=" + number_value +
                            ", score_value=" + score_value +
                            '}';
                }

                /**
                 *      混合过关全都有
                 *
                 * */

                /**
                 * 胜平负   竟足单关
                 */
                public static class VictoryBean implements Serializable{
                    /**
                     * flag : 0
                     * data : [{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]
                     */

                    private String flag;
                    private List<DataBean> data;

                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }

                    public List<DataBean> getData() {
                        return data;
                    }

                    public void setData(List<DataBean> data) {
                        this.data = data;
                    }

                    @Override
                    public String toString() {
                        return "VictoryBean{" +
                                "flag='" + flag + '\'' +
                                ", data=" + data +
                                '}';
                    }

                    public static class DataBean implements Serializable{
                        /**
                         * key : 胜
                         * value : 1
                         */

                        private String key;
                        private String value;
                        private String typeBoolean;

                        public String getTypeBoolean() {
                            return typeBoolean;
                        }

                        public void setTypeBoolean(String typeBoolean) {
                            this.typeBoolean = typeBoolean;
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

                        @Override
                        public String toString() {
                            return "DataBean{" +
                                    "key='" + key + '\'' +
                                    ", value='" + value + '\'' +
                                    '}';
                        }
                    }

                }

                /**
                 * 让球胜平负      竟足单关
                 */
                public static class LetVictoryBean implements Serializable{
                    /**
                     * flag : -3
                     * data : [{"key":"胜","value":"1"},{"key":"平","value":"1"},{"key":"负","value":"1"}]
                     */

                    private String flag;
                    private List<DataBeanX> data;

                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }

                    public List<DataBeanX> getData() {
                        return data;
                    }

                    public void setData(List<DataBeanX> data) {
                        this.data = data;
                    }

                    @Override
                    public String toString() {
                        return "LetVictoryBean{" +
                                "flag='" + flag + '\'' +
                                ", data=" + data +
                                '}';
                    }

                    public static class DataBeanX implements Serializable{
                        /**
                         * key : 胜
                         * value : 1
                         */

                        private String key;
                        private String value;

                        private String typeBoolean;

                        public String getTypeBoolean() {
                            return typeBoolean;
                        }

                        public void setTypeBoolean(String typeBoolean) {
                            this.typeBoolean = typeBoolean;
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

                        @Override
                        public String toString() {
                            return "DataBeanX{" +
                                    "key='" + key + '\'' +
                                    ", value='" + value + '\'' +
                                    '}';
                        }
                    }
                }

                /**
                 * 半全场
                 */
                public static class HalfBean implements Serializable{
                    /**
                     * key : 胜胜
                     * value : 1
                     */

                    private String key;
                    private String value;

                    private String typeBoolean;

                    public String getTypeBoolean() {
                        return typeBoolean;
                    }

                    public void setTypeBoolean(String typeBoolean) {
                        this.typeBoolean = typeBoolean;
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

                    @Override
                    public String toString() {
                        return "HalfBean{" +
                                "key='" + key + '\'' +
                                ", value='" + value + '\'' +
                                '}';
                    }
                }

                /**
                 * 总进球
                 */
                public static class NumberValueBean implements Serializable{
                    /**
                     * key : 1球
                     * value : 1
                     */

                    private String key;
                    private String value;
                    private String typeBoolean;

                    public String getTypeBoolean() {
                        return typeBoolean;
                    }

                    public void setTypeBoolean(String typeBoolean) {
                        this.typeBoolean = typeBoolean;
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

                    @Override
                    public String toString() {
                        return "NumberValueBean{" +
                                "key='" + key + '\'' +
                                ", value='" + value + '\'' +
                                '}';
                    }
                }

                /**
                 * 比分
                 */
                public static class ScoreValueBean implements Serializable{
                    /**
                     * flag : 胜
                     * data : [{"key":"1:0","value":"1"},{"key":"2:1","value":"1"},{"key":"4:1","value":"1"},{"key":"5:1","value":"1"},{"key":"胜其他","value":"1"},{"key":"2:0","value":"1"},{"key":"3:0","value":"1"},{"key":"2:3","value":"1"}]
                     */

                    private String flag;
                    private List<DataBeanXX> data;


                    public String getFlag() {
                        return flag;
                    }

                    public void setFlag(String flag) {
                        this.flag = flag;
                    }

                    public List<DataBeanXX> getData() {
                        return data;
                    }

                    public void setData(List<DataBeanXX> data) {
                        this.data = data;
                    }

                    @Override
                    public String toString() {
                        return "ScoreValueBean{" +
                                "flag='" + flag + '\'' +
                                ", data=" + data +
                                '}';
                    }

                    public static class DataBeanXX implements Serializable{
                        /**
                         * key : 1:0
                         * value : 1
                         */

                        private String key;
                        private String value;
                        private String typeBoolean;

                        public String getTypeBoolean() {
                            return typeBoolean;
                        }

                        public void setTypeBoolean(String typeBoolean) {
                            this.typeBoolean = typeBoolean;
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

                        @Override
                        public String toString() {
                            return "DataBeanXX{" +
                                    "key='" + key + '\'' +
                                    ", value='" + value + '\'' +
                                    '}';
                        }
                    }
                }
            }
        }
    }
}