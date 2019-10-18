package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.PublishContentContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class PublishContentPresenter implements Constants {
    private AppCompatActivity me;
    private PublishContentContract contentContract;


    public PublishContentPresenter(AppCompatActivity me, PublishContentContract contentContract) {
        this.me = me;
        this.contentContract = contentContract;
    }

    /**
     * 用户发帖
     */
    public void addPost(String url, final String token, String post_id, final String post_content, final int item) {
        if (TextUtils.isEmpty(post_content)) {
            ToastUtil.show("内容不能为空！");
            return;
        }
        if (post_id == null) {
            HttpRequest.POST(me, url
                    , new Parameter()
                            .add("token", token)
                            .add("post_content", post_content)
                    , new ResponseListener() {
                        @Override
                        public void onResponse(String response, Exception error) {
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            ToastUtil.show(map.get("message"));
                            Bundle bundle = new Bundle();
                            Intent i = new Intent();
                            bundle.putString("token", token);
                            i.putExtras(bundle);
                            me.setResult(RESULT_OK, i);
                            me.finish();
                        }
                    });
        } else {
            HttpRequest.POST(me, url
                    , new Parameter()
                            .add("token", token)
                            .add("post_id", post_id)
                            .add("post_content", post_content)
                    , new ResponseListener() {
                        @Override
                        public void onResponse(String response, Exception error) {
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            ToastUtil.show(map.get("message"));
                            Bundle bundle = new Bundle();
                            Intent i = new Intent();
                            bundle.putInt("item", item);
                            bundle.putString("context", post_content);
                            i.putExtras(bundle);
                            me.setResult(RESULT_OK, i);
                            me.finish();
                        }
                    });
        }
    }
}