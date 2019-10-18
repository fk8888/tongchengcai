package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述： 论坛界面数据请求
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ForumPresenter implements Constants {
    private AppCompatActivity me;
    private ForumContract contract;

    public ForumPresenter(AppCompatActivity me, ForumContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 编辑收货地址
     */
    public void addReceiveAddress(String token, int page) {

        HttpRequest.POST(me, SELECTPOSTLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.selectPostCommentsList(data);
                    }
                });
    }

    /**
     * 点赞帖子
     */
    public void giveLike(String token, String status, String post_id, final int item) {
        HttpRequest.POST(me, GIVELIKE
                , new Parameter()
                        .add("token", token)
                        .add("status", status)
                        .add("post_id", post_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        contract.delPost(item);
                    }
                });
    }

}