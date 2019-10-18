package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SuccessPayActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class StoredValuePresenter implements Constants {
    private AppCompatActivity me;

    public StoredValuePresenter(AppCompatActivity me) {
        this.me = me;
    }

    /**
     * 账户储值
     */
    public void addRechargeLog(String token, String recharge_money, int pay_type) {
        if (pay_type == 0) {
            ToastUtil.show("请选择储值方式！");
            return;
        }
        if (TextUtils.isEmpty(recharge_money)){
            ToastUtil.show("金额不能为空！");
            return;
        }
        HttpRequest.POST(me, ADDRECHARGELOG
                , new Parameter()
                        .add("token", token)
                        .add("receive_name", recharge_money)
                        .add("pay_type", pay_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        //充值
                        Bundle bundle = new Bundle();
                        bundle.putInt("success", 2);
                        BaseActivity.start(me, bundle, SuccessPayActivity.class);
                        me.finish();
                    }
                });
    }

}