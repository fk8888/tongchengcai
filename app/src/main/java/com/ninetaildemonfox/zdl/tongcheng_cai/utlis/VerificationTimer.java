package com.ninetaildemonfox.zdl.tongcheng_cai.utlis;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by dell-pc on 2018/9/19.
 */

public class VerificationTimer extends CountDownTimer {

    private TextView textView;

    private int one, tow, there;
    private String string;

    /**
     * @author millisInFuture 一共的时间
     * @author countDownInterval 几秒
     * @author textView 控件可以更换你所需要的控件
     * @author one onTick方法中的背景颜色        这两个都是用于控制控件背景的
     * @author tow  onFinish 方法中的背景颜色
     */

    public VerificationTimer(long millisInFuture, long countDownInterval, TextView textView, String string) {

        super(millisInFuture, countDownInterval);

        this.textView = textView;
        this.string = string;

    }

    public VerificationTimer(long millisInFuture, long countDownInterval, TextView textView, int one, int tow) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.one = one;
        this.tow = tow;

    }

    public VerificationTimer(long millisInFuture, long countDownInterval, TextView textView, int one, int tow, int there) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.one = one;
        this.tow = tow;
        this.there = there;

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onTick(long millisUntilFinished) {
        //如果为空就不换背景颜色
        if (one != 0) {
            textView.setTextColor(Color.GRAY);
            textView.setBackgroundResource(one);
        }

        textView.setText(millisUntilFinished / 1000 + "s");
        textView.setEnabled(false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onFinish() {
//如果为空就不换背景颜色
        if (tow != 0) {
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(tow);
        }
        textView.setEnabled(true);
        if (string != null) {
            textView.setText(string);
        } else {
            textView.setText("验证码");
        }


    }
}