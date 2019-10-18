package com.ninetaildemonfox.zdl.tongcheng_cai.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.Preferences;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ninetaildemonfox.zdl.tongcheng_cai.R.layout.item_null_activity;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/8/23
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public abstract class BaseFragment extends Fragment implements Constants {
    public List<String> list;
    /**
     * 是否可见
     */
    protected boolean isViable = false;
    public String token;
    protected boolean isPrepared = false;
    protected View rootView;
    public Bundle bundle;

    protected abstract int getLayoutResource();

    protected abstract void onInitView(Bundle savedInstanceState);

    public AppCompatActivity fgtContext;
    public Unbinder unbinder;
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != 0) {
            rootView = inflater.inflate(getLayoutResource(), null);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        view = getLayoutInflater().inflate(item_null_activity, container, false);
        token = Preferences.getInstance().getString(getContext(), "user", "token");
        unbinder = ButterKnife.bind(this, rootView);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        bundle = new Bundle();
        onInitView(savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        token = Preferences.getInstance().getString(getContext(), "user", "token");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("name (%s.java:0)", getClass().getSimpleName());
        fgtContext = (AppCompatActivity) getActivity();
        token = Preferences.getInstance().getString(getContext(), "user", "token");
    }

    public void start(Context me, Bundle bundle, Class activity) {
        Intent intent = new Intent(me, activity);
        intent.putExtras(bundle);
        me.startActivity(intent);
    }

    public void start(Context me, Class activity) {
        me.startActivity(new Intent(me, activity));
    }
}
