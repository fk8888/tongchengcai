package com.ninetaildemonfox.zdl.tongcheng_cai.utlis;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class GlidUtils {
    public static void circular(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().circleCrop().error(R.mipmap.icon_my_headmoren))
                .into(imageView);
    }

    public static void circularGaoSi(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().circleCrop().error(R.mipmap.icon_my_headmoren))
                .into(imageView);
    }

    public static void defaultGlid(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().error(R.mipmap.icon_my_headmoren))
                .into(imageView);
    }

}
