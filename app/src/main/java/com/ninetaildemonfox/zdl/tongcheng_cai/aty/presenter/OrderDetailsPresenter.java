package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.OrderDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：查看发起跟单详情
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class OrderDetailsPresenter implements Constants {
    private AppCompatActivity me;
    private OrderDetailsContract orderDetailsContract;

    public OrderDetailsPresenter(AppCompatActivity me, OrderDetailsContract orderDetailsContract) {
        this.me = me;
        this.orderDetailsContract = orderDetailsContract;
    }

    /**
     * 跟单的订单详情
     */
    public void getAgainOrderSendInfo(String token, String member_orders_send_id) {
        WaitDialog.show(me, "");
        HttpRequest.POST(me, GETAGAINORDERSENDINFO
                , new Parameter()
                        .add("token", token)
                        .add("member_orders_send_id", member_orders_send_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        orderDetailsContract.getAgainOrderSendInfo(data);
                    }
                });
    }
}