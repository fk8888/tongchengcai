package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/12
 * 功能描述：积分商场
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MyLotteryOrderPresenter implements Constants {

    private AppCompatActivity me;
    private AccountDetailsContract contract;

    public MyLotteryOrderPresenter(AppCompatActivity me, AccountDetailsContract contract) {
        this.me = me;
        this.contract = contract;
    }

    //彩票订单列表
    public void orederList(String url,String token, int page, int status,String count) {
        HttpRequest.POST(me, url
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                        .add("status", status)
                        .add("type",1)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.Success(data);
                    }
                });
    }

    //跟单订单列表
    public void oredergendanList(String token, int page, int status,String type) {
        HttpRequest.POST(me, AGAINORDERSLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                        .add("status", status)
                        .add("type",type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.Success(data);
                    }
                });
    }
    /**
     * 积分订单确认收货
     */
    public void confirmIntegralOrder(final String token, final String integral_exchange_log_id, final int position) {
        if (TextUtils.isEmpty(integral_exchange_log_id)) {
            return;
        }
        HttpRequest.POST(me, CONFIRMINTEGRALORDER
                , new Parameter()
                        .add("token", token)
                        .add("integral_exchange_log_id", integral_exchange_log_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        contract.confirmIntegralOrder(position);
                    }
                });
    }


}
