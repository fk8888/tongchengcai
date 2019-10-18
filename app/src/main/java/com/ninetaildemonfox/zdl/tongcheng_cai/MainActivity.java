package com.ninetaildemonfox.zdl.tongcheng_cai;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kongzue.baseokhttp.listener.ParameterInterceptListener;
import com.kongzue.baseokhttp.listener.ResponseInterceptListener;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MainFragmentPageAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LoginActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MessageEvent;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.HomeFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.LotteryFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.MarketFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.MyFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.RecommendFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/8/23 9:04
 * 功能描述： 主界面
 * 联系方式：1037438704@qq.com
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_rgp)
    RadioGroup mainRgp;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private long exitTime = 0;
    private List<Fragment> fragemnts;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        EventBus.getDefault().register(this);
        requestPemissions();
        initView();
        initData();
        setListener();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        RadioButton rb = (RadioButton) mainRgp.getChildAt(event.potation);
        rb.setChecked(true);
    }

    private void setListener() {
        //viewPager的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //获取当前位置的RadioButton
                RadioButton rb = (RadioButton) mainRgp.getChildAt(position);
                //设置为true
                rb.setChecked(true);
            }
        });
        //RadioGroup的事件监听
        mainRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < mainRgp.getChildCount(); index++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(index);
                    if (rb.isChecked()) {
                        viewPager.setCurrentItem(index, false);
                        break;
                    }
                }
            }
        });
    }

    private void initData() {
        fragemnts = new ArrayList<>();
        fragemnts.add(HomeFragment.newInstance());
        fragemnts.add(LotteryFragment.newInstance());
        fragemnts.add(MarketFragment.newInstance());
        fragemnts.add(RecommendFragment.newInstance());
        fragemnts.add(MyFragment.newInstance());
        //实例化适配器
        MainFragmentPageAdapter adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragemnts);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        //设置适配器
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        //设置默认第一个为选中状态
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
    }

    /**
     * 按两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().killAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void requestPemissions() {
        requestPermission(new OnPermissionResponseListener() {
                              @Override
                              public void onSuccess(String[] permissions) {

                              }

                              @Override
                              public void onFail() {
                                  startAppSettings();
                              }
                          }, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        );
    }
}