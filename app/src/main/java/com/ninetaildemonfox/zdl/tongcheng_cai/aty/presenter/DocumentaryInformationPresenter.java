package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SuccessPayActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DocumentaryInformationContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：跟单信息
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class DocumentaryInformationPresenter implements Constants {
    private AppCompatActivity me;
    private DocumentaryInformationContract contract;

    public DocumentaryInformationPresenter(AppCompatActivity me, DocumentaryInformationContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 查看发起跟单详情
     */
    public void getAgainOrderSendInfo(String token, String member_orders_send_id) {
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
                        contract.getAgainOrderSendInfo(data);
                    }
                });
    }
    /**
     * 发起跟单
     */
    public void createAgainOrderSend(String token, final String member_orders_send_id, String match_type, String again_times) {
        HttpRequest.POST(me, CREATEAGAINORDERSEND
                , new Parameter()
                        .add("token", token)
                        .add("order_id", member_orders_send_id)
                        .add("match_type", match_type)
                        .add("again_times", again_times)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
//                        ToastUtil.show(map.get("message"));
                        me.finish();
                        //2进跟单成功
                        Bundle bundle = new Bundle();
                        bundle.putInt("success", 3);
                        bundle.putString("order_id", member_orders_send_id);
                        BaseActivity.start(me, bundle, SuccessPayActivity.class);
                    }
                });
    }



    /**
     * 用户跟单跟单
     */
    public void createAgainOrder(String token, String member_orders_send_id, String again_times) {
        HttpRequest.POST(me, CREATEAGAINORDER
                , new Parameter()
                        .add("token", token)
                        .add("member_orders_send_id", member_orders_send_id)
                        .add("again_times", again_times)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
//                        ToastUtil.show(map.get("message"));
                        me.finish();
                        //2进跟单成功
                        Bundle bundle = new Bundle();
                        bundle.putInt("success", 3);
                        BaseActivity.start(me, bundle, SuccessPayActivity.class);
                    }
                });
    }

}