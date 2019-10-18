package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/8 11:22
 * 功能描述：暂停购买    4排列三 5 排列五   6 七星彩  7大乐透
 * 联系方式：1037438704@qq.com
 */

public class SuspensionPurchaseActivity extends BaseActivity {


    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;

    private int count;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_suspension_purchase;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);

        count = getIntent().getExtras().getInt("count");

        String titile = "";
        if (count == 4) {
            titile = "排列三";
        } else if (count == 5) {
            titile = "排列五";
        } else if (count == 6) {
            titile = "七星彩";
        } else if (count == 7) {
            titile = "大乐透";
        }
        textCenter.setText(titile);

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
}