package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.IDCard;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.io.File;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AuthenticationPresenter implements Constants {
    private AppCompatActivity me;

    public AuthenticationPresenter(AppCompatActivity me) {
        this.me = me;
    }

    /**
     * 上传认证
     */
    public void saveReceiveAddress(String token, File id_card_up, File ic_card_down, String real_name
            , String id_card) {

        if (id_card_up == null) {
            ToastUtil.show("身份证正面不能为空！");
            return;
        }

        if (ic_card_down == null) {
            ToastUtil.show("身份证背面不能为空！");
            return;
        }
        if (TextUtils.isEmpty(real_name)) {
            ToastUtil.show("姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(id_card)) {
            ToastUtil.show("身份证不能为空！");
            return;
        }
        if (IDCard.IDCardValidate(id_card) == false) {
            ToastUtil.show("身份证错误！");
            return;
        }
        HttpRequest.POST(me, REALNAMECERTIFICATION
                , new Parameter()
                        .add("token", token)
                        .add("id_card_up", id_card_up)
                        .add("ic_card_down", ic_card_down)
                        .add("real_name", real_name)
                        .add("id_card", id_card)
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