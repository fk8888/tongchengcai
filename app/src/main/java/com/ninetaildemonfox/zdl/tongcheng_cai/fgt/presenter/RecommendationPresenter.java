package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SuccessPayActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.RecommendationContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/12
 * 功能描述： 推荐详情
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class RecommendationPresenter implements Constants {

    private AppCompatActivity me;
    private RecommendationContract contract;

    public RecommendationPresenter(AppCompatActivity me, RecommendationContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 详情
     */
    public void pushOrdersInfo(String token, String match_push_id) {
        HttpRequest.POST(me, PUSHORDERSINFO
                , new Parameter()
                        .add("token", token)
                        .add("match_push_id", match_push_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.pushOrdersInfo(data);
                    }
                });
    }

    /**
     * 支付
     */
    public void payPushOrder(String token, String match_push_id) {
        HttpRequest.POST(me, PAYPUSHORDER
                , new Parameter()
                        .add("token", token)
                        .add("match_push_id", match_push_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Bundle bundle = new Bundle();
                        bundle.putInt("success", 1);
                        BaseActivity.start(me, bundle, SuccessPayActivity.class);
                    }
                });
    }
}