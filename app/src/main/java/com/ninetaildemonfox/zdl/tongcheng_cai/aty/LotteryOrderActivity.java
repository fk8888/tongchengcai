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

/**
 * @author NineTailDemonFox
 * @date 2019/9/6 10:28
 * 功能描述：1彩票订单 2 跟单订单 3商场订单
 * 联系方式：1037438704@qq.com
 */
public class LotteryOrderActivity extends BaseActivity {

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
    private String count;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_lottery_order;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        count = getIntent().getExtras().getString("count");
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

        if (count.equals("1")) {
            textCenter.setText("彩票订单");
            string = new String[]{"全部", "待出票", "待开奖", "已中奖", "未中奖"};

            mFragmentList = new ArrayList<>();
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,0));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,1));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,2));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,4));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,5));

        } else if (count.equals("2")) {
            textCenter.setText("跟单订单");
            textRight.setVisibility(View.VISIBLE);
            textRight.setText("我的跟单");
            string = new String[]{"全部", "待开奖", "已中奖", "未中奖"};

            mFragmentList = new ArrayList<>();
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,0));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,1));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,2));
            mFragmentList.add(MyLotteryOrderFragment.newInstance(count,3));

        } else if (count.equals("3")) {
            textCenter.setText("商场订单");
            string = new String[]{"全部", "待发货", "待收货", "已完成"};

            mFragmentList = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
              mFragmentList.add(MyLotteryOrderFragment.newInstance(count,i));
            }
        }
        //实例化适配器
        adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), mFragmentList, string);

        //我的跟单
        textRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCenter.setText("我的跟单");
                textRight.setVisibility(View.GONE);

            }
        });
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