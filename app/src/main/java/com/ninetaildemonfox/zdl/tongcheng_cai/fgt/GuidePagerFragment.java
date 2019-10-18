package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ninetaildemonfox.zdl.tongcheng_cai.MainActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.GuidePageActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LoginActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author NineTailDemonFox
 * @date 2019/9/18 9:07
 * 功能描述：引导界面
 * 联系方式：1037438704@qq.com
 */
public class GuidePagerFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.imageButton)
    ImageView imageButton;
    @BindView(R.id.fl_bg)
    FrameLayout fl_bg;
    private int index;

    private int[] bgRes = {R.mipmap.icon_guid_pager_1, R.mipmap.icon_grud_pager_2, R.mipmap.icon_grud_pager_3};

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_guide_pager;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        index = this.getArguments().getInt("count");
        fl_bg.setBackgroundResource(bgRes[index]);
        if (index == 2) {
            imageButton.setVisibility(View.VISIBLE);
        } else {
            imageButton.setVisibility(View.GONE);
        }
        imageButton.setOnClickListener(this);
    }

    public static GuidePagerFragment newInstance(int count) {
        GuidePagerFragment dHomeFragment = new GuidePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        dHomeFragment.setArguments(bundle);
        return dHomeFragment;
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), MainActivity.class));
        AppManager.getInstance().killActivity(GuidePageActivity.class);
    }

}