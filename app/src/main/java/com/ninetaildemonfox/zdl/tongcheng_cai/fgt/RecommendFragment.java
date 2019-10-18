package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MainFragmentPageAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ScreenAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.DataBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.recommend.DocumentaryFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.recommend.RecommendItemFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 10:44
 * 功能描述：推荐界面
 * 联系方式：1037438704@qq.com
 */
public class RecommendFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.article_viewpager)
    ViewPager viewPager;
    @BindView(R.id.text_screen)
    TextView textScreen;
    @BindView(R.id.text_reset)
    TextView textReset;
    @BindView(R.id.text_sure)
    TextView textSure;
    @BindView(R.id.ll_gone_one)
    LinearLayout llGoneOne;
    @BindView(R.id.recyclerVIewCondition)
    RecyclerView recyclerVIewCondition;
    @BindView(R.id.recyclerVIewTyep)
    RecyclerView recyclerVIewTyep;
    private List<Fragment> list;
    private String[] TITLE = {"跟单", "推荐"};
    private int screen = 1;
    private ScreenAdp screenAdp1, screenAdp2;
    private DocumentaryFragment documentaryFragment;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                screen = 1;
                llGoneOne.setVisibility(View.GONE);
                if (i == 0) {
                    textScreen.setText("筛选");
                    textScreen.setVisibility(View.VISIBLE);
                } else {
                    textScreen.setText("我的推荐");
                    textScreen.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        screenAdp1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Map<String, String>> data = screenAdp1.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("type", "0");
                }
                data.get(position).put("type", "1");
                screenAdp1.notifyDataSetChanged();
            }
        });

        screenAdp2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Map<String, String>> data = screenAdp2.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("type", "0");
                }
                data.get(position).put("type", "1");
                screenAdp2.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        recyclerVIewTyep.setLayoutManager(new GridLayoutManager(fgtContext, 4));
        recyclerVIewCondition.setLayoutManager(new GridLayoutManager(fgtContext, 4));
        screenAdp1 = new ScreenAdp(R.layout.item_screen_list_adp);
        screenAdp2 = new ScreenAdp(R.layout.item_screen_list_adp);
        recyclerVIewCondition.setAdapter(screenAdp1);
        recyclerVIewTyep.setAdapter(screenAdp2);
        screenAdp1.setNewData(DataBean.documentaryScreening());
        screenAdp2.setNewData(DataBean.documentaryType());
        list = new ArrayList<>();
        documentaryFragment = DocumentaryFragment.newInstance();
        list.add(documentaryFragment);
        list.add(RecommendItemFragment.newInstance());
        MainFragmentPageAdapter pagerFragmentAdapter = new MainFragmentPageAdapter(getChildFragmentManager(), list, TITLE);
        viewPager.setAdapter(pagerFragmentAdapter);
        viewPager.setOffscreenPageLimit(list.size());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.text_screen, R.id.text_reset, R.id.text_sure})
    public void onViewClicked(View view) {
        List<Map<String, String>> data1 = screenAdp1.getData();
        List<Map<String, String>> data2 = screenAdp2.getData();
        switch (view.getId()) {
            case R.id.text_screen:
                if (textScreen.getText().toString().equals("筛选")) {
                    if (screen == 1) {
                        screen = 2;
                        llGoneOne.setVisibility(View.VISIBLE);
                    } else {
                        screen = 1;
                        llGoneOne.setVisibility(View.GONE);
                    }
                } else {
                    if (screen == 1) {
                        screen = 2;
                        llGoneOne.setVisibility(View.VISIBLE);
                    } else {
                        screen = 1;
                        llGoneOne.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.text_reset:
                for (int i = 0; i < data1.size(); i++) {
                    data1.get(i).put("type", "0");
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.get(i).put("type", "0");
                }
                screenAdp1.notifyDataSetChanged();
                screenAdp2.notifyDataSetChanged();
                break;
            case R.id.text_sure:
                if (screen == 1) {
                    screen = 2;
                    llGoneOne.setVisibility(View.VISIBLE);
                } else {
                    screen = 1;
                    llGoneOne.setVisibility(View.GONE);
                }
                int a1 = 0, a2 = 0;
                //筛选条件
                for (int i = 0; i < data1.size(); i++) {
                    if (data1.get(i).get("type").equals("1")) {
                        a1 = i;
                    }
                }
                // 跟单类型查询
                for (int i = 0; i < data2.size(); i++) {
                    if (data2.get(i).get("type").equals("1")) {
                        a2 = i;
                    }
                }
                documentaryFragment.setAgain_type(a1, a2);
                break;
            default:
        }
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

}
