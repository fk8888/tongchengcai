package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.RecordContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：查看战绩
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class RecordPresenter implements Constants {
    private AppCompatActivity me;
    private RecordContract contract;

    public RecordPresenter(AppCompatActivity me, RecordContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 查看战绩
     */
    public void getMemberAgainSendRecordList(String token, String send_member_id, int page) {
        HttpRequest.POST(me, GETMEMBERAGAINSENDRECORDLIST
                , new Parameter()
                        .add("token", token)
                        .add("send_member_id", send_member_id)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.getMemberAgainSendRecordList(data);
                    }
                });
    }
}