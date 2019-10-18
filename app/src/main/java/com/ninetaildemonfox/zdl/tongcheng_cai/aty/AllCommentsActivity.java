package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MainFragmentPageAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.my.CommentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 16:29
 * 功能描述：所有评论
 * 联系方式：1037438704@qq.com
 */
public class AllCommentsActivity extends BaseActivity {

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
    private String[] string = {"我评论的", "评论我的"};
    private List<Fragment> mFragmentList;
    private MainFragmentPageAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_all_comments;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
    }

    private void initData() {
        textCenter.setText("所有评论");
        imageleftFinish.setVisibility(View.VISIBLE);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(mFragmentList.size());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(CommentFragment.newInstance("1"));
        mFragmentList.add(CommentFragment.newInstance("2"));
        //实例化适配器
        adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), mFragmentList, string);
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
