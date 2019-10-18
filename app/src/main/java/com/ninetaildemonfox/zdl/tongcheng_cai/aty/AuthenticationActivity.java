package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.takephoto.TakePhotoUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.AuthenticationPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 16:49
 * 功能描述： 实名认证
 * 联系方式：1037438704@qq.com
 */
public class AuthenticationActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.imageViewPositive)
    ImageView imageViewPositive;
    @BindView(R.id.imageViewBack)
    ImageView imageViewBack;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.edit_name)
    TextView editName;
    @BindView(R.id.edit_idcard)
    TextView editIdcard;
    @BindView(R.id.text_authentication)
    TextView text_authentication;
    private File file1, file2;
    private int count = 1;


    private AuthenticationPresenter presenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initData() {
        TakePhotoUtil.getInstance(this).setReturnPhoto(new TakePhotoUtil.ReturnPhoto() {
            @Override
            public void onGetPhotos(String[] selectImagePaths) {
                if (count == 1) {
                    file1 = new File(selectImagePaths[0]);
                    Glide.with(me).load(selectImagePaths[0]).into(imageViewPositive);
                } else {
                    file2 = new File(selectImagePaths[0]);
                    Glide.with(me).load(selectImagePaths[0]).into(imageViewBack);
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initView() {
        textCenter.setText("实名认证");
        imageleftFinish.setVisibility(View.VISIBLE);
        presenter = new AuthenticationPresenter(me);
    }

    @OnClick({R.id.image_left_finish, R.id.text_authentication, R.id.imageViewPositive, R.id.imageViewBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                AppManager.getInstance().killActivity(WalletActivity.class);
                finish();
                break;
            case R.id.text_authentication:
                presenter.saveReceiveAddress(token, file1, file2,
                        editName.getText().toString().trim(),
                        editIdcard.getText().toString());
                break;
            case R.id.imageViewPositive:
                count = 1;
                photo();
                break;
            case R.id.imageViewBack:
                count = 2;
                photo();
                break;
            default:
        }
    }

    private void photo() {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakePhotoUtil.getInstance(me).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        AppManager.getInstance().killActivity(WalletActivity.class);
        super.onBackPressed();
    }
}