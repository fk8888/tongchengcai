package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.LoginPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 9:43
 * 功能描述：登录界面
 * 联系方式：1037438704@qq.com
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_psd)
    EditText editPsd;
    @BindView(R.id.text_quick)
    TextView textQuick;
    @BindView(R.id.image_wx)
    ImageView imageWx;
    @BindView(R.id.image_qq)
    ImageView imageQq;
    @BindView(R.id.iamge_eye)
    ImageView iamgeEye;
    private LoginPresenter loginPresenter;

    private boolean aBoolean = false;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        whitchBar();
        initView();
    }

    private void initView() {
        loginPresenter = new LoginPresenter(me);
    }

    @OnClick({R.id.image_left_finish, R.id.text_login, R.id.text_register, R.id.text_forget
            , R.id.text_quick, R.id.image_wx, R.id.image_qq, R.id.iamge_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_login:
                //登录
                loginPresenter.login(editPhone.getText().toString().trim(), editPsd.getText().toString().trim());
                break;
            case R.id.text_forget:
                //忘记密码  1
                bundle.putString("count", "1");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.text_register:
                //新用户注册  2
                bundle.putString("count", "2");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.text_quick:
                //快捷登录  3
//                bundle.putString("count", "3");
//                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.image_wx:
                //微信登录  4
                /*bundle.putString("count", "4");
                start(me, bundle, RegisterActivity.class);*/
            /*    text_WeChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        open_type = "2";
                        if (CheckAppExist.getInstancei().isAppAvilible(me, "com.tencent.mm")) {
                            Platform wx = ShareSDK.getPlatform(Wechat.NAME);
                            wx.removeAccount(true);
                            authorize(wx);
                        } else {
                            toast("请安装微信");
                        }
                    }
                });*/

                break;
            case R.id.image_qq:
                //qq登录  5
                bundle.putString("count", "5");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.iamge_eye:
                if (aBoolean == false) {
                    aBoolean = true;
                    iamgeEye.setSelected(aBoolean);
                    //设置EditText的密码为可见的
                    editPsd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    aBoolean = false;
                    iamgeEye.setSelected(aBoolean);
                    //设置密码为隐藏的
                    editPsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                editPsd.setSelection(editPsd.getText().length());
                break;
            default:
        }
    }

}