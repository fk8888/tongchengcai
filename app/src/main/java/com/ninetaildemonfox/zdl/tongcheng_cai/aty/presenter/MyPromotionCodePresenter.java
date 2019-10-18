package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.MyPromotionCodeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：我的推广码
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MyPromotionCodePresenter implements Constants {
    private AppCompatActivity me;
    private MyPromotionCodeContract codeContract;


    public MyPromotionCodePresenter(AppCompatActivity me, MyPromotionCodeContract codeContract) {
        this.me = me;
        this.codeContract = codeContract;
    }

    /**
     * 编辑收货地址
     */
    public void getMemberPromoteInfo(String token) {
        HttpRequest.POST(me, GETMEMBERPROMOTEINFO
                , new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        codeContract.success(data);
                    }
                });
    }

}