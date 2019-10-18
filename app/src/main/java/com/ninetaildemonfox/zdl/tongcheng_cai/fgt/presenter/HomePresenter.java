package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.CommentContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.HomeContract;
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

public class HomePresenter implements Constants {

    private AppCompatActivity me;
    private HomeContract contract;

    public HomePresenter(AppCompatActivity me, HomeContract contract) {
        this.me = me;
        this.contract = contract;
    }

    public void getIndexData(String token) {
        WaitDialog.show(me, "");
        HttpRequest.POST(me, GETINDEXDATA
                , new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.getCommentsList(data);
                    }
                });
    }

}
