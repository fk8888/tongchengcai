package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 9:29
 * 功能描述： 关于我们
 * 联系方式：1037438704@qq.com
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("关于我们");
    }

}