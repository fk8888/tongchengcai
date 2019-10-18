package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.WFootallContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：足彩各个玩法列表数据
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WFootallPresenter implements Constants {
    private AppCompatActivity me;
    private WFootallContract wFootallContract;


    public WFootallPresenter(AppCompatActivity me, WFootallContract wFootallContract) {
        this.me = me;
        this.wFootallContract = wFootallContract;
    }

    /**
     * 足彩各个玩法列表数据
     */
    public void getFootballPlayList(String match_type_id, final String play_flag, String odds_id) {
        HttpRequest.POST(me, GETFOOTBALLPLAYLIST
                , new Parameter()
                        .add("match_type_id", match_type_id)
                        .add("play_flag", play_flag)
                        .add("odds_id", odds_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Gson gson = new Gson();
                        WFootallBean wFootallBean = gson.fromJson(response, WFootallBean.class);
                        Log.d("DataBeanXXX", "=============" + wFootallBean.toString().toString());

                        List<WFootallBean.DataBeanXXX> data = wFootallBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setTypeCount("0");
                        }
                        wFootallContract.getFootballPlayList(data);
                    }
                });
    }

    //下单
    public void getXiaDan(String play_flag, List<WFootallBean.DataBeanXXX> choose_info, String token) {
        HttpRequest.POST(me, CHOOSEFOOTBALLMATCH
                , new Parameter()
                        .add("play_flag", play_flag)
                        .add("choose_info", choose_info)
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        wFootallContract.getAgainOrderSendInfo(data);
                    }
                });
    }

    //确认下单
    public void surecast(String play_flag, List<WFootallBean.DataBeanXXX> choose_info, String textMode, String zhuBei, int editCount, String token, String shopID) {
        HttpRequest.POST(me, CONFIRMFOOTBALLORDER
                , new Parameter()
                        .add("play_flag", play_flag)
                        .add("choose_info", choose_info)
                        .add("lottery_info",textMode)
                        .add("lottery_note",zhuBei)
                        .add("lottery_times", editCount)
                        .add("token", token)
                        .add("shop_id" ,2)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        wFootallContract.getSureList(data);
                    }
                });

    }
}