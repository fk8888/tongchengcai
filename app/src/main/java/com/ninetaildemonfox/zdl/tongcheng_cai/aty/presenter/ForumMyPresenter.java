package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ForumMyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ForumMyPresenter implements Constants {
    private AppCompatActivity me;
    private ForumMyContract contract;

    public ForumMyPresenter(AppCompatActivity me, ForumMyContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 帖子列表
     */
    public void selectPostList(String token, int page, String member_id) {
        HttpRequest.POST(me, SELECTPOSTLIST
                , new Parameter()
                        .add("token", token)
                        .add("page", page)
                        .add("member_id", member_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.selectPostList(data);
                    }
                });
    }

    /**
     * 删除帖子
     */
    public void delPost(String token, String post_id, final int item) {
        HttpRequest.POST(me, DELPOST
                , new Parameter()
                        .add("token", token)
                        .add("post_id", post_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        contract.delPost(item);
                    }
                });
    }


}