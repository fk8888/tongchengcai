package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ViewPagerAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.GuidePagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author NineTailDemonFox
 * @date 2019/9/20 11:54
 * 功能描述： 引导界面
 * 联系方式：1037438704@qq.com
 */
public class GuidePageActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_indicator)
    LinearLayout llIndicator;
    private ViewPagerAdp adaper;
    private List<BaseFragment> fragments = new ArrayList<>();
    private int[] bgRes = {R.mipmap.icon_guid_pager_1, R.mipmap.icon_grud_pager_2, R.mipmap.icon_grud_pager_3};


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_guide_page;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        for (int i = 0; i < bgRes.length; i++) {
            fragments.add(GuidePagerFragment.newInstance(i));
        }
        adaper = new ViewPagerAdp(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adaper);
        initlndicator();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int index, float v, int i1) {
                for (int i = 0; i < fragments.size(); i++) {
                    llIndicator.getChildAt(i).setBackgroundResource(index == i ? R.drawable.dot_focus : R.drawable.dot_normal);
                }
                if (index == 2) {
                    llIndicator.setVisibility(View.GONE);
                } else {
                    llIndicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initlndicator() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.setMargins(10, 0, 10, 0);
        for (int i = 0; i < fragments.size(); i++) {
            View view = new View(this);
            view.setId(i);
            view.setBackgroundResource(i == 0 ? R.drawable.dot_focus : R.drawable.dot_normal);
            view.setLayoutParams(lp);
            llIndicator.addView(view, i);
            if (i == 2) {
                llIndicator.setVisibility(View.GONE);
            } else {
                llIndicator.setVisibility(View.VISIBLE);
            }
        }
    }
}