package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.ninetaildemonfox.zdl.tongcheng_cai.MainActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.Preferences;
import com.orhanobut.logger.Logger;

/**
 * @author NineTailDemonFox
 * @date 2019/9/18 8:37
 * 功能描述： 启动界面
 * 联系方式：1037438704@qq.com
 */
public class SplashActivity extends AppCompatActivity {

    private ConstraintLayout cl_layout;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cl_layout = findViewById(R.id.cl_layout);
        count = Preferences.getInstance().getInt(this, "page", "count");
        jumpToMain();
    }

    private void jumpToMain() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        cl_layout.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (count == 0){
                    Preferences.getInstance().commit(SplashActivity.this, "page", "count", count + 1);
                    startActivity(new Intent(SplashActivity.this, GuidePageActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 防止用户返回键退出APP
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_HOME
                || super.onKeyDown(keyCode, event);
    }

}
