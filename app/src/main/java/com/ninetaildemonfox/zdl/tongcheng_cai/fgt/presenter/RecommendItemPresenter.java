package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.RecommendItemContract;
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

public class RecommendItemPresenter implements Constants {

    private AppCompatActivity me;
    private RecommendItemContract contract;

    public RecommendItemPresenter(AppCompatActivity me, RecommendItemContract contract) {
        this.me = me;
        this.contract = contract;
    }

    public void pushOrdersList(String token,int page) {
        HttpRequest.POST(me, PUSHORDERSLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        contract.pushOrdersList(data);
                    }
                });
    }

}
