package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
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

public class BankCardPresenter implements Constants {
    private AppCompatActivity me;

    public BankCardPresenter(AppCompatActivity me) {
        this.me = me;
    }

    /**
     * 绑定银行卡
     */
    public void bindBankCard(String token, String bank_name, String bank_type, String bank_card, String bank_phone) {
        if (TextUtils.isEmpty(bank_name)) {
            ToastUtil.show("名字不能为空！");
            return;
        }
        if (TextUtils.isEmpty(bank_type)) {
            ToastUtil.show("请选择银行！");
            return;
        }
        if (TextUtils.isEmpty(bank_card)) {
            ToastUtil.show("请输入银行卡号！");
            return;
        }
        if (TextUtils.isEmpty(bank_phone)) {
            ToastUtil.show("请输入预留手机号！");
            return;
        }
        HttpRequest.POST(me, BINDBANKCARD
                , new Parameter()
                        .add("token", token)
                        .add("bank_name", bank_name)
                        .add("bank_type", bank_type)
                        .add("bank_card", bank_card)
                        .add("bank_phone", bank_phone)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        me.finish();
                    }
                });
    }


}