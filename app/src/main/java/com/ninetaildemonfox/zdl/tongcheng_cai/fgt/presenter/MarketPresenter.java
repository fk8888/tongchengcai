package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MarketContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/11
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MarketPresenter implements Constants {
    private AppCompatActivity me;
    private MarketContract contract;

    public MarketPresenter(AppCompatActivity me, MarketContract contract) {
        this.me = me;
        this.contract = contract;
    }

    public void getIntegralGoodsList(String sort, int page, String sort_type) {
        HttpRequest.POST(me, GETINTEGRALGOODSLIST
                , new Parameter()
                        .add("sort", sort)
                        .add("page", page)
                        .add("sort_type", sort_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        if (map == null) {
                            return;
                        }
                        ArrayList<Map<String, String>> list = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.Success(list);
                    }
                });


    }

}
