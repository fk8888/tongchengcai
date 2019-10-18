package com.ninetaildemonfox.zdl.tongcheng_cai.utlis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ninetaildemonfox.zdl.tongcheng_cai.adp.WonderfulFootballAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.AddingEventsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

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

public class ButtonView {
    /**
     * shopDetails  竞彩足球
     * play_flag   如 胜平负
     * data 数据
     * */
    public static int textSure(int shopDetails, String play_flag, WonderfulFootballAdp wonderfulFootballAdp) {

        boolean safafa = false;
        int count = 0;
        //足球
        if (shopDetails == 0) {
            if (play_flag.equals("victory")) {
//                胜平负
                List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.get(j).getPlay_value();
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean victory = play_value.getVictory();
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data1 = victory.getData();
                        for (int k = 0; k < data1.size(); k++) {
                            if (data1.get(k).getTypeBoolean().equals("1")){
                                safafa = true;
                            }
                        }
                        if (safafa == true){
                            safafa = false;
                            count++;
                        }
                    }
                }
                return count;
            } else if (play_flag.equals("let_victory")) {
//                让球胜平负
                List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.get(j).getPlay_value();
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean victory = play_value.getLet_victory();
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data1 = victory.getData();

                        for (int k = 0; k < data1.size(); k++) {
                            if (data1.get(k).getTypeBoolean().equals("1")) {
                                safafa = true;
                               // ToastUtil.show(data1.get(k).getKey());
                            }
                        }
                        if (safafa == true) {
                            safafa = false;
                            count++;
                        }
                    }
                }
                return count;
            } else if (play_flag.equals("score")) {
//                比分
                List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.get(j).getPlay_value();
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean> score_value = play_value.getScore_value();
                       for (int g = 0; g<score_value.size(); g++){
                           List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data1 = score_value.get(g).getData();

                           for (int k = 0; k < data1.size(); k++) {
                               if (data1.get(k).getTypeBoolean().equals("1")) {
                                   safafa = true;
                                   //ToastUtil.show(data1.get(k).getKey());
                               }
                           }
                           if (safafa == true) {
                               safafa = false;
                               count++;
                           }
                       }
                    }
                }
                return count;
            } else if (play_flag.equals("number")) {
//                总进球数
                List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.get(j).getPlay_value();
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> number_value = play_value.getNumber_value();
                        for (int g = 0; g<number_value.size(); g++){
                            if (number_value.get(g).getTypeBoolean().equals("1")) {
                                    safafa = true;
                                    //ToastUtil.show(number_value.get(g).getKey());
                            }

                            if (safafa == true) {
                                safafa = false;
                                count++;
                            }
                        }
                    }
                }
                return count;
            } else if (play_flag.equals("half")) {
//                半全场
                List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.get(j).getPlay_value();
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> number_value = play_value.getHalf();
                        for (int g = 0; g<number_value.size(); g++){
                            if (number_value.get(g).getTypeBoolean().equals("1")) {
                                safafa = true;
                                //ToastUtil.show(number_value.get(g).getKey());
                            }

                            if (safafa == true) {
                                safafa = false;
                                count++;
                            }
                        }
                    }
                }
                return count;
            } else if (play_flag.equals("mix")) {
//                混合过关
            } else if (play_flag.equals("single")) {
//                竟足单关
            }
        } else if (shopDetails == 1) {
            //篮球
            if (play_flag.equals("victory")) {
//                胜负
            } else if (play_flag.equals("let_victory")) {
//                让分胜负
            } else if (play_flag.equals("big")) {
//                大小分
            } else if (play_flag.equals("score")) {
//                分数差
            } else if (play_flag.equals("single")) {
//                竞蓝单关
            } else if (play_flag.equals("mix")) {
//                混合过关
            }
        } else if (shopDetails == 2) {
//            胜负彩
        } else if (shopDetails == 3) {
//            任选9
        }
        return 0;
    }


    //状态
    public static void setIsCheck(String play_flag,WonderfulFootballAdp wonderfulFootballAdp) {
        List<WFootallBean.DataBeanXXX> wonderData = wonderfulFootballAdp.getData();
        for (int i = 0; i < wonderData.size(); i++) {
            List<WFootallBean.DataBeanXXX.ListBean> list = wonderData.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (play_flag.equals("victory")){
                    //胜平负
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data = list.get(j).getPlay_value().getVictory().getData();
                    for (int k = 0; k < data.size(); k++) {
                        data.get(k).setTypeBoolean("0");
                    }
                }else if (play_flag.equals("let_victory")){
                    //让球胜平负
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data1 = list.get(j).getPlay_value().getLet_victory().getData();
                    for (int g = 0; g < data1.size(); g++) {
                        data1.get(g).setTypeBoolean("0");
                    }
                }else if (play_flag.equals("score")){
                    //比分
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean> data2 = list.get(j).getPlay_value().getScore_value();
                    for (int m = 0; m < data2.size(); m++) {
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data6 = data2.get(m).getData();
                        for (int kk = 0; kk <data6.size(); kk++){
                            data6.get(kk).setTypeBoolean("0");
                        }
                    }
                }else if (play_flag.equals("number")){
                    //总进球
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> data3 = list.get(j).getPlay_value().getNumber_value();
                    for (int n = 0; n < data3.size(); n++) {
                        data3.get(n).setTypeBoolean("0");
                    }
                }else if (play_flag.equals("half")){
                    //半全场
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> data4 = list.get(j).getPlay_value().getHalf();
                    for (int v = 0; v < data4.size(); v++) {
                        data4.get(v).setTypeBoolean("0");
                    }
                }
            }

        }
        wonderfulFootballAdp.notifyDataSetChanged();
    }

    public static void sureZuqiu(AppCompatActivity me, int shopDetails, String play_flag, WonderfulFootballAdp wonderfulFootballAdp) {
        int i = textSure(shopDetails, play_flag, wonderfulFootballAdp);
        if (i < 2){
            Toast.makeText(me, "请至少选择2场比赛", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("play_flag", play_flag);
            bundle.putInt("shopDetails", shopDetails);
            List<WFootallBean.DataBeanXXX> data = wonderfulFootballAdp.getData();
            bundle.putSerializable("wFootall2Bean", (Serializable) data);
            BaseActivity.start(me, bundle, AddingEventsActivity.class);
        }
    }
}