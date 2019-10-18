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

public class AddAddressPresenter implements Constants {
    private AppCompatActivity me;

    public AddAddressPresenter(AppCompatActivity me) {
        this.me = me;
    }

    /**
     * 编辑收货地址
     */
    public void addReceiveAddress(String token, String receive_name, String receive_phone
            , String receive_province, String receive_city, String receive_area, String receive_address) {
        if (TextUtils.isEmpty(receive_name)) {
            ToastUtil.show("名字不能为空！");
            return;
        }
        if (TextUtils.isEmpty(receive_phone)) {
            ToastUtil.show("手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(receive_province) && TextUtils.isEmpty(receive_city) && TextUtils.isEmpty(receive_area)) {
            ToastUtil.show("请选择城市！");
            return;
        }
        if (TextUtils.isEmpty(receive_address)) {
            ToastUtil.show("请输入详细地址！");
            return;
        }
        if (receive_phone.length() < 11) {
            ToastUtil.show("请输入正确的手机号！");
            return;
        }

        HttpRequest.POST(me, ADDRECEIVEADDRESS
                , new Parameter()
                        .add("token", token)
                        .add("receive_name", receive_name)
                        .add("receive_phone", receive_phone)
                        .add("receive_province", receive_province)
                        .add("receive_city", receive_city)
                        .add("receive_area", receive_area)
                        .add("receive_address", receive_address)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        me.finish();
                    }
                });
    }

    /**
     * 修改地址
     */
    public void saveReceiveAddress(String token,String address_id, String receive_name, String receive_phone
            , String receive_province, String receive_city, String receive_area, String receive_address) {

        if (TextUtils.isEmpty(receive_name)) {
            ToastUtil.show("名字不能为空！");
            return;
        }
        if (TextUtils.isEmpty(receive_phone)) {
            ToastUtil.show("手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(receive_province) && TextUtils.isEmpty(receive_city) && TextUtils.isEmpty(receive_area)) {
            ToastUtil.show("请选择城市！");
            return;
        }
        if (TextUtils.isEmpty(receive_address)) {
            ToastUtil.show("请输入详细地址！");
            return;
        }
        if (receive_phone.length() < 11) {
            ToastUtil.show("请输入正确的手机号！");
            return;
        }
        HttpRequest.POST(me, SAVERECEIVEADDRESS
                , new Parameter()
                        .add("token", token)
                        .add("address_id", address_id)
                        .add("receive_name", receive_name)
                        .add("receive_phone", receive_phone)
                        .add("receive_province", receive_province)
                        .add("receive_city", receive_city)
                        .add("receive_area", receive_area)
                        .add("receive_address", receive_address)
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