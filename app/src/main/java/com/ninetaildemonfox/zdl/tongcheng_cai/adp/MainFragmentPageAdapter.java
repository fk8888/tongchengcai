package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/7/15
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MainFragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] titleList;

    public MainFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MainFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments, String[] titleList) {
        super(fm);
        this.fragments = fragments;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList == null ? "" : titleList[position % titleList.length];
    }
}
