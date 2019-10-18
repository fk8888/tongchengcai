package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.MainActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.LoginPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.VerificationTimer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 9:47
 * 功能描述： 注册       1忘记密码      2新用户注册       3快捷登录      4 微信登录     5qq登录
 * 6设置密码   * 7修改密码     8  验证身份
 * 联系方式：1037438704@qq.com
 */
public class RegisterActivity extends BaseActivity {
    private String count;

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_title)
    TextView textTitle;
    //邀请码
    @BindView(R.id.edit_invitation)
    EditText editInvitation;

    @BindView(R.id.edit_phone)
    EditText editPhone;
    //验证框
    @BindView(R.id.fl_verification)
    FrameLayout flVerification;

    //密码框
    @BindView(R.id.fl_psd)
    FrameLayout flPsd;
    @BindView(R.id.text_gone)
    TextView textGone;
    @BindView(R.id.ll_gone)
    LinearLayout llGone;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.edit_psd)
    EditText editPsd;
    @BindView(R.id.iamge_eye)
    ImageView iamgeEye;
    @BindView(R.id.text_verification)
    TextView textVerification;
    @BindView(R.id.text_register)
    TextView text_register;
    private LoginPresenter loginPresenter;
    private VerificationTimer verificationTimer;
    private String send_type = "";
    private boolean aBoolean = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        verificationTimer = new VerificationTimer(60000, 1000, textVerification, R.color.white, R.color.colorAccent, R.color.blackColor);
        whitchBar();
        loginPresenter = new LoginPresenter(me);
        count = getIntent().getExtras().getString("count");
//       1  忘记密码          2新用户注册           3  快捷登录      4 微信登录    5qq登录
//            6修改密码   7修改密码     8  验证身份   9修改绑定手机号
        if (count.equals("1")) {
            send_type = "retrieve";
            textTitle.setText("找回密码");
            editInvitation.setVisibility(View.GONE);
            textGone.setVisibility(View.GONE);
            llGone.setVisibility(View.GONE);
            textLogin.setText("确定");
        } else if (count.equals("2")) {
            send_type = "register";
        } else if (count.equals("3")) {
            send_type = "login";
            textTitle.setText("快捷登录");
            editInvitation.setVisibility(View.GONE);
            flPsd.setVisibility(View.GONE);
            textLogin.setText("登录");
        } else if (count.equals("4")) {
            textTitle.setText("绑定手机号");
            editInvitation.setVisibility(View.GONE);
            flPsd.setVisibility(View.GONE);
            textLogin.setText("立即绑定");
            send_type = "re_bind";
        } else if (count.equals("5")) {
            send_type = "re_bind";
            textTitle.setText("绑定手机号");
            editInvitation.setVisibility(View.GONE);
            flPsd.setVisibility(View.GONE);
            textLogin.setText("立即绑定");
        } else if (count.equals("6")) {
            textTitle.setText("设置密码");
            editInvitation.setVisibility(View.GONE);
            textGone.setVisibility(View.GONE);
            llGone.setVisibility(View.GONE);
            textLogin.setText("确定");
        } else if (count.equals("7")) {
            textTitle.setText("修改密码");
            editInvitation.setVisibility(View.GONE);
            textGone.setVisibility(View.GONE);
            llGone.setVisibility(View.GONE);
            textLogin.setText("确定");
        } else if (count.equals("8")) {
            textTitle.setText("验证身份");
            editInvitation.setVisibility(View.GONE);
            textGone.setVisibility(View.GONE);
            llGone.setVisibility(View.GONE);
            textLogin.setText("下一步");
        } else if (count.equals("9")) {
            send_type = "mod_bind";
            textTitle.setText("修改绑定手机号");
            editInvitation.setVisibility(View.GONE);
            textGone.setVisibility(View.GONE);
            llGone.setVisibility(View.GONE);
            textLogin.setText("立即绑定");
        }
    }

    @OnClick({R.id.image_left_finish, R.id.text_login, R.id.text_verification, R.id.iamge_eye, R.id.text_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_login:
                setEvent();
                break;
            case R.id.text_verification:
                //获取邀请码
                verificationTimer.start();
                verification();
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
            case R.id.text_register:
                bundle.putInt("type", 1);
                start(me, bundle, DocumentaryPlanningActivity.class);
                break;
            default:
        }
    }

    private void verification() {
        //       1  忘记密码          2新用户注册           3  快捷登录      4 微信登录    5qq登录
//            6修改密码       7  验证身份   8修改绑定手机号
        if (count.equals("1")) {
//            忘记密码
        } else if (count.equals("2")) {
            //新用户注册
            loginPresenter.sendVerify(editPhone.getText().toString().trim(), send_type);
        } else if (count.equals("3")) {
//            快捷登录
        } else if (count.equals("4")) {
//            4 微信登录
        } else if (count.equals("5")) {
//            5qq登录
        } else if (count.equals("6")) {
//            6修改密码
        } else if (count.equals("7")) {
//            7  验证身份
        } else if (count.equals("8")) {
//            8修改绑定手机号
        }
    }

    private void setEvent() {
        //       1  忘记密码          2新用户注册           3  快捷登录      4 微信登录    5qq登录
//            6修改密码   7修改密码     8  验证身份   9修改绑定手机号
        if (count.equals("1")) {
            loginPresenter.resetPassword(editPhone.getText().toString().trim(), editCode.getText().toString().trim(), editPsd.getText().toString().trim());
        } else if (count.equals("2")) {
            //新用户注册
            loginPresenter.register(editInvitation.getText().toString().trim()
                    , editPhone.getText().toString().trim(), editCode.getText().toString().trim()
                    , editPsd.getText().toString().trim());
        } else if (count.equals("3")) {
            start(me, MainActivity.class);
            finish();
        } else if (count.equals("4")) {
            start(me, MainActivity.class);
            finish();
        } else if (count.equals("5")) {
            start(me, MainActivity.class);
            finish();
        } else if (count.equals("6")) {
            finish();
        } else if (count.equals("7")) {
            finish();
        } else if (count.equals("8")) {
            start(me, bundle, RegisterActivity.class);
            finish();
        } else if (count.equals("9")) {
            finish();
        }
    }

}