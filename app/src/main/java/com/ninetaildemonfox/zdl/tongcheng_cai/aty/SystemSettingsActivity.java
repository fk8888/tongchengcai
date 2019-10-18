package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MessageEvent;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlideCacheUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 9:09
 * 功能描述： 系统设置
 * 联系方式：1037438704@qq.com
 */
public class SystemSettingsActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_getCacheSize)
    TextView textGetCacheSize;
    @BindView(R.id.text_about_us)
    TextView text_about_us;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text_out_login)
    TextView text_out_login;

    private GlideCacheUtil instance;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_system_settings;
    }

    @Subscribe
    @Override
    public void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("系统设置");
        instance = GlideCacheUtil.getInstance();
        textGetCacheSize.setText(instance.getCacheSize(me));
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.image_left_finish, R.id.text_getCacheSize, R.id.text_about_us, R.id.button, R.id.text_out_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_getCacheSize:
                MessageDialog.show(me, "确定要清除缓存吗？", "", "是", "否")
                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                instance = GlideCacheUtil.getInstance();
                                instance.clearImageAllCache(me);
                                ShowToast(me, "清除成功");
                                textGetCacheSize.setText(instance.getCacheSize(me));
                                return false;
                            }
                        });
                break;
            case R.id.text_about_us:
                start(me, AboutUsActivity.class);
                break;
            case R.id.button:
                EventBus.getDefault().post(new MessageEvent("发送的消息"));
                break;
            case R.id.text_out_login:
                start(me, LoginActivity.class);
                break;
            default:
        }
    }

}
