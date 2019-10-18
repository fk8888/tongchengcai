package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SuccessPayActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
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

public class MyPresenter implements Constants {
    private AppCompatActivity me;
    private MyContract myContract;

    public MyPresenter(AppCompatActivity me, MyContract myContract) {
        this.me = me;
        this.myContract = myContract;
    }

    public MyPresenter(AppCompatActivity me) {
        this.me = me;
    }

    public void memberCenter(String token) {
        WaitDialog.show(me, "");
        HttpRequest.POST(me, MEMBERCENTER
                , new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            myContract.success(data);
                        } else {
                            ToastUtil.show(error.getLocalizedMessage());
                        }
                    }
                });
    }

    /**
     * 余额清账(申请提现)
     */
    public void addWithdrawLog(String token, String bankcard_id, String withdraw_money) {
        if (TextUtils.isEmpty(bankcard_id)) {
            ToastUtil.show("请选择银行卡！");
            return;
        }
        if (TextUtils.isEmpty(withdraw_money)) {
            ToastUtil.show("提现金额不能为空！");
            return;
        }
        HttpRequest.POST(me, ADDWITHDRAWLOG
                , new Parameter()
                        .add("token", token)
                        .add("bankcard_id", bankcard_id)
                        .add("withdraw_money", withdraw_money)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        //确定提现  跳转到清账成功  4清账成功
                        Bundle bundle = new Bundle();
                        bundle.putInt("success", 4);
                        BaseActivity.start(me, bundle, SuccessPayActivity.class);
                        me.finish();
                    }
                });
    }
}