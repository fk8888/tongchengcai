package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.HomeViewBottomAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MapBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.HomePresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.HomeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/26 9:12
 * 功能描述： 店铺列表
 * 联系方式：1037438704@qq.com
 */
public class ShopListActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemClickListener, HomeContract {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.reyclerViewBottom)
    RecyclerView reyclerViewBottom;
    private HomeViewBottomAdp homeViewBottomAdp;
    private HomePresenter presenter;
    private String shop_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_shop_list;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        presenter = new HomePresenter(me, this);
        presenter.getIndexData(token);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("同城彩");
        reyclerViewBottom.setLayoutManager(new LinearLayoutManager(me));
        homeViewBottomAdp = new HomeViewBottomAdp(R.layout.item_home_view_bottom);
        reyclerViewBottom.setAdapter(homeViewBottomAdp);
        //店铺点击
        homeViewBottomAdp.setOnItemClickListener(this);

        //获取店铺id
        SharedPreferences shopidsp = getSharedPreferences("shopid", MODE_PRIVATE);
        SharedPreferences.Editor edit = shopidsp.edit();
        edit.putString("shopID",shop_id);
        //Log.e("shopid",shop_id);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Map<String, String> map = homeViewBottomAdp.getData().get(position);
        if (map == null) {
            return;
        }
        MapBean mapBean = new MapBean(map);
        bundle.putSerializable("map", mapBean);
        start(me, bundle, ShopDetailsActivity.class);
    }

    @Override
    public void getCommentsList(Map<String, String> data) {
        //店铺列表
        ArrayList<Map<String, String>> store_list = JSONUtils.parseKeyAndValueToMapList(data.get("store_list"));
        homeViewBottomAdp.setNewData(store_list);
    }
}