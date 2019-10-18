package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MainFragmentPageAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.my.MyLotteryOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsTwoActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] string;
    private List<Fragment> mFragmentList;
    private MainFragmentPageAdapter adapter;
    private String count = "4";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_lottery_order;
    }

    @Override
    protected void onInitialization(Bundle bundle) {


        initView();
        initData();
    }

    private void initData() {
        imageleftFinish.setVisibility(View.VISIBLE);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(mFragmentList.size());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        textCenter.setText("我的跟单");
        string = new String[]{"全部", "待开奖", "已中奖", "未中奖"};

        mFragmentList = new ArrayList<>();
        for (int i = 0; i < string.length; i++) {
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,i));
        }
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
