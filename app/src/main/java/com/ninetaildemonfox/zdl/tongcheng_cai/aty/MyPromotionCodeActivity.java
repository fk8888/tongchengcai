package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.MyPromotionCodePresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.MyPromotionCodeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 8:55
 * 功能描述： 我的推广码
 * 联系方式：1037438704@qq.com
 */
public class MyPromotionCodeActivity extends BaseActivity implements MyPromotionCodeContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_nick_name)
    TextView textNickName;
    @BindView(R.id.text_invite_code)
    TextView textInviteCode;
    @BindView(R.id.image_invite_path)
    ImageView imageInvitePath;

    private MyPromotionCodePresenter promotionCodePresenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_promotion_code;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        TransparentBar();
        promotionCodePresenter = new MyPromotionCodePresenter(me, this);
        promotionCodePresenter.getMemberPromoteInfo(token);
    }

    @OnClick({R.id.image_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {
        GlidUtils.circularGaoSi(me,data.get("head_pic"),imageHead);
        GlidUtils.defaultGlid(me,data.get("invite_img_path"),imageInvitePath);
        textNickName.setText(data.get("nick_name"));
        textInviteCode.setText(data.get("invite_code"));
    }
}