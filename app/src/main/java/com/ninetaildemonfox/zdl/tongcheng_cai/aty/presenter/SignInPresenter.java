package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.SignInContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class SignInPresenter implements Constants {
    private AppCompatActivity me;
    private SignInContract contract;

    public SignInPresenter(AppCompatActivity me, SignInContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 用户签到列表
     */
    public void getMemberSignList(String token) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        HttpRequest.POST(me, GETMEMBERSIGNLIST
                , new Parameter()
                        .add("token", token)
                        .add("sign_month", dateFormat.format(date))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.success(data);
                    }
                });
    }

    /**
     * 用户签到
     */
    public void addMemberSign(final String token) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
        HttpRequest.POST(me, ADDMEMBERSIGN
                , new Parameter()
                        .add("token", token)
                        .add("sign_date", dateFormat.format(date))
                        .add("sign_month", dateFormat2.format(date))
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        getMemberSignList(token);
                    }
                });
    }

}