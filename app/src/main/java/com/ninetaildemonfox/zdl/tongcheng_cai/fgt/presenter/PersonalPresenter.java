package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.PersonalContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.io.File;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class PersonalPresenter implements Constants {
    public AppCompatActivity me;
    public PersonalContract personalContract;

    public PersonalPresenter(AppCompatActivity me, PersonalContract personalContract) {
        this.me = me;
        this.personalContract = personalContract;
    }

    public void getMemberBaseData(String token) {
        HttpRequest.POST(me, GETMEMBERBASEDATA
                , new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        personalContract.Success(data);
                    }
                });
    }

    /**
     * 保存
     */
    public void editMemberBaseData(String token, File file, String sex, String nick_name, String head_pic) {
        if (TextUtils.isEmpty(head_pic)) {
            ToastUtil.show("数据出错");
            return;
        }
        WaitDialog.show(me, "数据上传中...");
        HttpRequest.POST(me, EDITMEMBERBASEDATA
                , new Parameter()
                        .add("token", token)
                        .add("file", file)
                        .add("sex", sex)
                        .add("nick_name", nick_name)
                        .add("head_pic", head_pic)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        me.finish();
                    }
                });
    }

}
