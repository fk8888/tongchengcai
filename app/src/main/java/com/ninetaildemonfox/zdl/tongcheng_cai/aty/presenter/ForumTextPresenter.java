package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ForumTextContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.IDCard;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述： 查看帖子详情
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ForumTextPresenter implements Constants {
    private AppCompatActivity me;

    private ForumTextContract contract;


    public ForumTextPresenter(AppCompatActivity me, ForumTextContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 查看帖子详情
     */
    public void saveReceiveAddress(String token, String post_id) {
        HttpRequest.POST(me, FINDPOSTINFO
                , new Parameter()
                        .add("token", token)
                        .add("post_id", post_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.findPostInfo(data);
                    }
                });
    }

    /**
     * 帖子列表
     */
    public void selectPostCommentsList(String post_id, int page) {
        HttpRequest.POST(me, SELECTPOSTCOMMENTSLIST
                , new Parameter()
                        .add("post_id", post_id)
                        .add("page", page)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        List<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        contract.selectPostCommentsList(data);
                    }
                });
    }

    /**
     * 查看帖子详情
     */
    public void addPostComments(String token, final String post_id, String member_id, String comment_content) {
        if (TextUtils.isEmpty(comment_content)) {
            ToastUtil.show("帖子内容不能为空！");
            return;
        }
        HttpRequest.POST(me, ADDPOSTCOMMENTS
                , new Parameter()
                        .add("token", token)
                        .add("post_id", post_id)
                        .add("member_id", member_id)
                        .add("comment_content", comment_content)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        selectPostCommentsList(post_id, 1);
                    }
                });
    }
}