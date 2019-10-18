package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.takephoto.TakePhotoUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.PersonalPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.PersonalContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.io.File;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 8:52
 * 功能描述： 个人信息
 * 联系方式：1037438704@qq.com
 */
public class PersonalActivity extends BaseActivity implements PersonalContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_sex)
    TextView textSex;
    @BindView(R.id.text_is_cert)
    TextView textIsCert;
    @BindView(R.id.text_change_password2)
    TextView text_change_password2;
    @BindView(R.id.text_change_password)
    TextView text_change_password;
    @BindView(R.id.image_head_portrait)
    ImageView imageHeadPortrait;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_phone)
    TextView textPhone;
    private File file;
    private PersonalPresenter personalPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_personal;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
    }

    private void initView() {
        imageleftFinish.setVisibility(View.VISIBLE);
        textRight.setVisibility(View.VISIBLE);
        textRight.setText("保存");
        personalPresenter = new PersonalPresenter(me, this);
        personalPresenter.getMemberBaseData(token);
    }

    private void initData() {
        TakePhotoUtil.getInstance(this).setReturnPhoto(new TakePhotoUtil.ReturnPhoto() {
            @Override
            public void onGetPhotos(String[] selectImagePaths) {
                file = new File(selectImagePaths[0]);
                Glide.with(me).load(selectImagePaths[0]).apply(new RequestOptions().circleCrop()).into(imageHeadPortrait);

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @OnClick({R.id.image_left_finish, R.id.image_head_portrait
            , R.id.text_sex, R.id.text_change_password, R.id.text_right
            , R.id.text_change_password2, R.id.ll_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.image_head_portrait:
                BottomMenu.show(this, new String[]{"拍照", "从相册里选择"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (index) {
                            case 0:
                                //使用相机拍摄
                                TakePhotoUtil.getInstance(me).doOpenCamera();
                                break;
                            case 1:
                                TakePhotoUtil.getInstance(me).doOpenGallery();
                                break;
                            default:
                        }
                    }
                });
                break;
            case R.id.text_sex:
                BottomMenu.show(this, new String[]{"男", "女"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (index) {
                            case 0:
                                textSex.setText("男");
                                break;
                            case 1:
                                textSex.setText("女");
                                break;
                            default:
                        }
                    }
                });
                break;
            case R.id.text_change_password:
                //修改密码
                bundle.putString("count", "7");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.text_change_password2:
                //设置密码
                bundle.putString("count", "6");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.ll_phone:
                //验证身份
                bundle.putString("count", "8");
                start(me, bundle, RegisterActivity.class);
                break;
            case R.id.text_right:
                //保存
                personalPresenter.editMemberBaseData(token
                        , file, textSex.getText().toString().trim()
                        , textName.getText().toString().trim(), head_pic);
                break;
            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakePhotoUtil.getInstance(me).onActivityResult(requestCode, resultCode, data);
    }

    private String head_pic;

    @Override
    public void Success(Map<String, String> map) {
//        "id": "7",
//                "account": "18222905309",  账号
//        "head_pic": "http://www.tongchengcai.com/Uploads/Member/2019-08-24/5d609631243ad.png",
//                "nick_name": "娃哈哈",   昵称
//        "is_cert": "1",    是否认证：1-未认证 2-已认证
//        "is_password": 2    是否设置密码：1-未设置 2-已设置
        head_pic = map.get("head_pic");
        GlidUtils.circular(me, head_pic, imageHeadPortrait);
        textName.setText(map.get("nick_name"));
        textSex.setText(map.get("sex"));
        String is_cert = map.get("is_cert");
        if (is_cert.equals("1")) {
            textIsCert.setText("未认证");
            textIsCert.setTextColor(ContextCompat.getColor(me, R.color.text_gray_color));
        } else {
            textIsCert.setTextColor(ContextCompat.getColor(me, R.color.colorAccent));
            textIsCert.setText("已认证");
        }
        textPhone.setText(map.get("account"));
        String is_password = map.get("is_password");

        if (is_password.equals("1")) {
            text_change_password2.setVisibility(View.VISIBLE);
            text_change_password.setVisibility(View.GONE);
        } else {
            text_change_password2.setVisibility(View.GONE);
            text_change_password.setVisibility(View.VISIBLE);
        }
    }
}