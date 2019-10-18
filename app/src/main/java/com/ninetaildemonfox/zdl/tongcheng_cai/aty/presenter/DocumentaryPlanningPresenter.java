package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DocumentaryPlanningContract;
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

public class DocumentaryPlanningPresenter implements Constants {
    private AppCompatActivity me;
    private DocumentaryPlanningContract contract;


    public DocumentaryPlanningPresenter(AppCompatActivity me, DocumentaryPlanningContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 跟单规则、注册协议、积分规则、签到规则
     */
    public void getNormalRuleInfo(int type) {
        HttpRequest.POST(me, GETNORMALRULEINFO
                , new Parameter()
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        contract.getNormalRuleInfo(map.get("data"));
                    }
                });
    }

}