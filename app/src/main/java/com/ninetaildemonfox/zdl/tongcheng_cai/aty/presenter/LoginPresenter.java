package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.MainActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LoginActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.Preferences;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：   最佳拍档   建工邦  顺心拼车  顺心车主  电商人生   必得Pro
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class LoginPresenter implements Constants {
    public AppCompatActivity me;

    public LoginPresenter(AppCompatActivity me) {
        this.me = me;
    }

    /**
     * 登录账号
     */
    public void login(String account, String password) {
        if (TextUtils.isEmpty(account)) {
            ToastUtil.show("账号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("密码不能为空！");
            return;
        }
        WaitDialog.show(me, "数据请求中...");
        HttpRequest.POST(me, LOGIN
                , new Parameter()
                        .add("account", account)
                        .add("password", password)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Preferences.getInstance().commit(me, "user", "token", data.get("token"));
                        //这里记录账号密码进行界面跳转
                        AppManager.getInstance().killAllActivity();
                        BaseActivity.start(me, MainActivity.class);
                    }
                });
//        账号—— "account": "18222905308",
//                积分——"integral": "2",
//                "token": "530d8f2f8bd578b7d08e53611c775e56",
//                "expired_time": "1567126660",
//                邀请码—— "invite_code": "823916",
//                "p_id": "4",
//                真实姓名——"real_name": "",
//                昵称——"nick_name": "娃哈哈",
//                "sex": "女",
//                头像——"head_pic": "http://www.tongchengcai.com/Uploads/Member/2019-08-24/5d609631243ad.png",
//                身份证号—— "id_card": "123456789012345678",
//                身份证正面照—— "id_card_up": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5de8af.png",
//                身份证反面照——"ic_card_down": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5df467.png",
//                账户余额——"recharge_money": "0.00",
//                可提现余额——"withdraw_money": "0.00",
//                "status": "1",
//                实名认证——"is_cert": "2",    1——未认证   2——已认证
//        "create_time": "1566436932",
//                "update_time": "1566641381"

    }

    /**
     * 新用户注册
     */
    public void register(String invite_code, String account, String verify, String password) {
        if (TextUtils.isEmpty(invite_code)) {
            ToastUtil.show("邀请码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            ToastUtil.show("手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            ToastUtil.show("邀请码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("密码不能为空！");
            return;
        }
        WaitDialog.show(me, "数据请求中...");
        HttpRequest.POST(me, REGISTER
                , new Parameter()
                        .add("invite_code", invite_code)
                        .add("account", account)
                        .add("verify", verify)
                        .add("password", password)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Preferences.getInstance().commit(me, "user", "token", data.get("token"));
                        BaseActivity.start(me, MainActivity.class);
                        AppManager.getInstance().killActivity(LoginActivity.class);
                        me.finish();
//                        "id": "5", 用户ID
//                        "account": "18222905307", 手机号
//                        "integral": "0",  用户积分
//                        "token": "94e48c14a0ed53083abeebd8d1ac8bc2",
//                                "expired_time": "1567040512", token过期时间
//                        "invite_code": "640918",  邀请码
//                        "p_id": "4", 推荐人ID
//                        "nick_name": "", 呢称
//                        "sex": "男",  性别（注册时默认是难）
//                        "head_pic": "",  头像
//                        "id_card": ""   身份证
                    }
                });
    }

    /**
     * 找回密码
     */
    public void resetPassword(String account, String verify, String password) {
        if (TextUtils.isEmpty(account)) {
            ToastUtil.show("手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            ToastUtil.show("邀请码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("密码不能为空！");
            return;
        }
        HttpRequest.POST(me, RESETPASSWORD
                , new Parameter()
                        .add("account", account)
                        .add("verify", verify)
                        .add("password", password)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        ToastUtil.show(map.get("message"));
                        me.finish();
//                         {
//                             "code": "1",
//                                 "message": "找回密码成功",
//                                 "data": []
//                         }
                    }
                });

    }

    /**
     * 发送短信验证码
     * login(登录)，register（注册）,retrieve（找回密码）
     * ，mod_bind（修改绑定账号）
     * ， re_bind（绑定新账号，三方登录绑定手机号）
     */
    public void sendVerify(String account, String send_type) {
        if (TextUtils.isEmpty(account)) {
            ToastUtil.show("手机号不能为空！");
            return;
        }
        HttpRequest.POST(me, SENDVERIFY
                , new Parameter()
                        .add("account", account)
                        .add("send_type", send_type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                    }
                });

    }


}