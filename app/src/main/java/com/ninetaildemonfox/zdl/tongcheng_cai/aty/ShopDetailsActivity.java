package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ShopDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MapBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.ShopDetailsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/8 10:19
 * 功能描述：店铺详情
 * 联系方式：1037438704@qq.com
 */

public class ShopDetailsActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.text_store_name)
    TextView text_store_name;
    @BindView(R.id.text_store_hours)
    TextView text_store_hours;
    @BindView(R.id.text_store_address)
    TextView text_store_address;
    private ShopDetailsAdp shopDetailsAdp;
    private List<ShopDetailsBean> list;
    private Map<String, String> map;
    private String shop_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_shop_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        textCenter.setText("店铺详情");
        imageleftFinish.setVisibility(View.VISIBLE);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(me, 4));
        shopDetailsAdp = new ShopDetailsAdp(R.layout.item_shop_details_adp);
        recyclerView.setAdapter(shopDetailsAdp);
        list.add(new ShopDetailsBean("竞彩足球", R.mipmap.icon_football));
        list.add(new ShopDetailsBean("竞彩篮球", R.mipmap.icon_basketball));
        list.add(new ShopDetailsBean("胜负彩", R.mipmap.icon_shengfucia));
        list.add(new ShopDetailsBean("任选九", R.mipmap.icon_renxuanjiu));
        list.add(new ShopDetailsBean("排列三", R.mipmap.icon_pailiesna));
        list.add(new ShopDetailsBean("排列五", R.mipmap.icon_pailiewu));
        list.add(new ShopDetailsBean("七星彩", R.mipmap.icon_qixingcai));
        list.add(new ShopDetailsBean("大乐透", R.mipmap.icon_daletou));
        shopDetailsAdp.setNewData(list);
        shopDetailsAdp.setOnItemClickListener(this);

        MapBean mapBean = (MapBean) getIntent().getExtras().getSerializable("map");
        map = mapBean.getMap();
        text_store_name.setText(map.get("store_name"));
        text_store_hours.setText("营业时间：" + map.get("store_hours"));
        text_store_address.setText("店铺地址：" + map.get("store_address"));
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
        String id = map.get("id");
        bundle.putInt("shopDetails", position);
        bundle.putString("id", id);
        switch (position) {
            case 0:
                //足球
                start(me, bundle, WonderfulFootballActivity.class);
                break;
            case 1:
                //篮球
                start(me, bundle, WonderfulFootballActivity.class);
                break;
            case 2:
                //胜负彩
                start(me, bundle, WonderfulFootballActivity.class);
                break;
            case 3:
//                任九
                start(me, bundle, WonderfulFootballActivity.class);
                break;
            case 4:
                start(me, bundle, SuspensionPurchaseActivity.class);
                break;
            case 5:
                start(me, bundle, SuspensionPurchaseActivity.class);
                break;
            case 6:
                start(me, bundle, SuspensionPurchaseActivity.class);
                break;
            case 7:
                start(me, bundle, SuspensionPurchaseActivity.class);
                break;
            default:

        }
    }
}
