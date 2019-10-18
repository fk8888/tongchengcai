package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/12
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AccountDetailsPresenter implements Constants{

    private AppCompatActivity me;
    private AccountDetailsContract contract;

    public AccountDetailsPresenter(AppCompatActivity me, AccountDetailsContract contract) {
        this.me = me;
        this.contract = contract;
    }
    public void getIntegralLogList(String token,int page, String integral_type){
         HttpRequest.POST(me, GETINTEGRALLOGLIST
                         , new Parameter()
                                 .add("token", token)
                                 .add("page", page)
                                 .add("integral_type", integral_type)
                         , new ResponseListener() {
                     @Override
                     public void onResponse(String response, Exception error) {
                         Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                         ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                         contract.Success(data);
                     }
                 });
    }
    /**
     * 提现明细
     * */
    public void getWithdrawLogList(String token,int page, String integral_type){
//        integral_type	状态：0-全部 1-待处理 2-已发放
        HttpRequest.POST(me, GETWITHDRAWLOGLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                        .add("integral_type", integral_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.Success(data);
                    }
                });
    }

    /**
     * 账本明细
     * */
    public void getMoneyLogList(String token,int page, String integral_type){
//        integral_type	状态：0-全部 1-待处理 2-已发放
        HttpRequest.POST(me, GETMONEYLOGLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                        .add("integral_type", integral_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.Success(data);
                    }
                });
    }


}
