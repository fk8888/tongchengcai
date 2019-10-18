package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ShoppingMallDetailsContract;
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

public class ShoppingMallDetailsPresenter implements Constants {
    private AppCompatActivity me;
    private ShoppingMallDetailsContract contract;


    public ShoppingMallDetailsPresenter(AppCompatActivity me, ShoppingMallDetailsContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 积分商品详情
     */
    public void getIntegralGoodsInfo(String integral_goods_id) {
        WaitDialog.show(me,"数据加载中");
        HttpRequest.POST(me, GETINTEGRALGOODSINFO
                , new Parameter()
                        .add("integral_goods_id", integral_goods_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        contract.shopSuccess(data);
                    }
                });
    }
}