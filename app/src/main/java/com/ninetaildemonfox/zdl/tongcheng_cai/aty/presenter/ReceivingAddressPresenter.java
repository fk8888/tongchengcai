package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ReceivingAddressContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ReceivingAddressPresenter implements Constants {
    private AppCompatActivity me;
    private ReceivingAddressContract contract;

    public ReceivingAddressPresenter(AppCompatActivity me, ReceivingAddressContract contract) {
        this.me = me;
        this.contract = contract;
    }

    public void getReceiveAddressList(String token, int page) {
        HttpRequest.POST(me, GETRECEIVEADDRESSLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> list = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.success(list);
                    }
                });
    }

    public void getRegionList(String parent_id) {
        HttpRequest.POST(me, GETREGIONLIST
                , new Parameter()
                        .add("parent_id", parent_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> list = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.success(list);
                    }
                });
    }


    /**
     * 设置默认收货地址
     */
    public void saveDefaultAddress(String token, String address_id, final int position) {
        HttpRequest.POST(me, SAVEDEFAULTADDRESS
                , new Parameter()
                        .add("token", token)
                        .add("address_id", address_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        contract.saveDefaultAddress(position,1);
                    }
                });
    }

    /**
     * 删除用户收货地址
     */
    public void delReceiveAddress(String token, String address_id, final int position) {
        HttpRequest.POST(me, DELRECEIVEADDRESS
                , new Parameter()
                        .add("token", token)
                        .add("address_id", address_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        contract.saveDefaultAddress(position,2);
                    }
                });

    }
}