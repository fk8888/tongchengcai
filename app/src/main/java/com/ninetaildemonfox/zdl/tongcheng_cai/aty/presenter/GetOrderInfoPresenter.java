package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.GetOrderInfoContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;


/**
 * @author dell
 */
public class GetOrderInfoPresenter implements Constants {

    private AppCompatActivity me;
    private GetOrderInfoContract contract;

    public GetOrderInfoPresenter(AppCompatActivity me, GetOrderInfoContract contract) {
        this.me = me;
        this.contract = contract;
    }

    //彩票订单详情
    public void  getOrderInfo(final String token, final String order_id, final String order_type ){
        HttpRequest.POST(me, GETORDERINFO
                , new Parameter()
                        .add("token", token)
                        .add("order_id", order_id)
                        .add("order_type", order_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null){
                            return;
                        }
                        contract.getOrder(data);
                    }
                });
    }

    //彩票订单---撤单
    public void cancleList(String token, String order_id, String order_type) {
        HttpRequest.POST(me, CANCELORDER
                , new Parameter()
                        .add("token", token)
                        .add("order_id", order_id)
                        .add("order_type", order_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.cancleList(data);
                    }
                });
    }
}