package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.LotteryContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/12
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */
public class LotteryPresenter implements Constants {

    private AppCompatActivity me;
    private LotteryContract contract;

    public LotteryPresenter(AppCompatActivity me, LotteryContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 即时全程
     */
    public void getFootballMatchRewardsList(String type, int page) {
        HttpRequest.POST(me, GETFOOTBALLMATCHREWARDSLIST
                , new Parameter()
                        .add("type", type)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.getFootballMatchRewardsList(data);
                    }
                });
    }


    /**
     * 开奖
     */
    public void openFootballMatchRewardsList() {
        HttpRequest.POST(me, OPENFOOTBALLMATCHREWARDSLIST
                , new Parameter()
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.openFootballMatchRewardsList(data);
                    }
                });
    }

}