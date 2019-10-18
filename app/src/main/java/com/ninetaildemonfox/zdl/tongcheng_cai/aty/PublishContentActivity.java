package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.PublishContentPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.PublishContentContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 14:23
 * 功能描述： 1发布内容  2修改论坛
 * 联系方式：1037438704@qq.com
 */
public class PublishContentActivity extends BaseActivity implements PublishContentContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_release)
    TextView textRelease;
    @BindView(R.id.fl_view_jianpan)
    FrameLayout flViewJianpan;
    @BindView(R.id.edit_context)
    EditText editContext;
    private String url;
    private PublishContentPresenter publishContentPresenter;
    private String post_id;
    private int item = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_publish_content;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        publishContentPresenter = new PublishContentPresenter(me, this);
        int count = getIntent().getExtras().getInt("count");
        if (count == 1) {
            textCenter.setText("发布内容");
            url = ADDPOST;
        } else {
            post_id = getIntent().getExtras().getString("post_id");
            item = getIntent().getExtras().getInt("item");
            textCenter.setText("修改论坛");
            textRelease.setText("确定");
            url = EDITPOST;
        }
        imageleftFinish.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.image_left_finish, R.id.text_release, R.id.fl_view_jianpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_release:
                publishContentPresenter.addPost(url, token, post_id, editContext.getText().toString().trim(), item);
                break;
            case R.id.fl_view_jianpan:
                editContext.setFocusable(true);
                editContext.setFocusableInTouchMode(true);
                editContext.requestFocus();
                editContext.findFocus();
                InputMethodManager inputManager = (InputMethodManager) editContext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editContext, 0);
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {

    }
}