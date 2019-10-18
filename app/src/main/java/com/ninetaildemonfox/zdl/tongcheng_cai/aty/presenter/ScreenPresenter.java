package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ScreenContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：赛事类型筛选
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ScreenPresenter implements Constants {
    private AppCompatActivity me;
    private ScreenContract contract;


    public ScreenPresenter(AppCompatActivity me, ScreenContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 赛事类型筛选
     */
    public void getMatchTypeList(String type, String play_flag) {

        HttpRequest.POST(me, GETMATCHTYPELIST
                , new Parameter()
                        .add("type", type)
                        .add("play_flag", play_flag)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null){
                            return;
                        }
                        contract.getMatchTypeList(data);
                    }
                });
    }
}