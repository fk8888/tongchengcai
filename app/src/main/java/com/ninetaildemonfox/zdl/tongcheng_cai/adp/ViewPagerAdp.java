package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import java.util.List;

public class ViewPagerAdp extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public ViewPagerAdp(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdp(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
