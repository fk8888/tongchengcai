package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.SelectAddressAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ReceivingAddressPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ReceivingAddressContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 11:08
 * 功能描述： 城市选择器
 * 联系方式：1037438704@qq.com
 */

public class SelectAddressActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, ReceivingAddressContract {
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.fl_context)
    FrameLayout fl_context;


    //================省================
    @BindView(R.id.text_province)
    TextView text_province;
    @BindView(R.id.view_province)
    View view_province;
    @BindView(R.id.rl_gone_one)
    RelativeLayout rl_gone_one;
    //================市==================
    @BindView(R.id.text_city)
    TextView text_city;
    @BindView(R.id.view_city)
    View view_city;
    @BindView(R.id.rl_gone_two)
    RelativeLayout rl_gone_two;
    //================区================
    @BindView(R.id.text_area)
    TextView text_area;
    @BindView(R.id.view_area)
    View view_area;
    @BindView(R.id.rl_gone_three)
    RelativeLayout rl_gone_three;
    int viewCount = 0;

    private SelectAddressAdp selectAddressAdp;
    private ReceivingAddressPresenter presenter;
    private String cityId, areaId;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        presenter = new ReceivingAddressPresenter(me, this);
        presenter.getRegionList("1");
        recycerView.setLayoutManager(new LinearLayoutManager(me));
        selectAddressAdp = new SelectAddressAdp(R.layout.item_select_address_list_adp);
        recycerView.setAdapter(selectAddressAdp);
        selectAddressAdp.setOnItemClickListener(this);
    }

    @OnClick({R.id.rl_gone_one, R.id.rl_gone_two, R.id.rl_gone_three, R.id.fl_context})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_gone_one:
                viewCount = 0;
                rtv();
                break;
            case R.id.rl_gone_two:
                viewCount = 1;
                presenter.getRegionList(cityId);
                rtv();
                break;
            case R.id.rl_gone_three:
                viewCount = 2;
                presenter.getRegionList(areaId);
                rtv();
                break;
            case R.id.fl_context:
                finish();
                break;
            default:
        }
    }

    private void rtv() {
        if (viewCount == 0) {
            presenter.getRegionList("1");
            //省
            text_province.setTextColor(me.getResources().getColor(R.color.colorAccent));
            view_province.setVisibility(View.VISIBLE);
            //市
            text_city.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_city.setVisibility(View.GONE);
            //区
            text_area.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_area.setVisibility(View.GONE);
        } else if (viewCount == 1) {
            //省
            text_province.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_province.setVisibility(View.GONE);
            //市
            text_city.setTextColor(me.getResources().getColor(R.color.colorAccent));
            view_city.setVisibility(View.VISIBLE);
            //区
            text_area.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_area.setVisibility(View.GONE);
        } else if (viewCount == 2) {
            //省
            text_province.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_province.setVisibility(View.GONE);
            //市
            text_city.setTextColor(me.getResources().getColor(R.color.blackColor));
            view_city.setVisibility(View.GONE);
            //区
            text_area.setTextColor(me.getResources().getColor(R.color.colorAccent));
            view_area.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String region_name = selectAddressAdp.getData().get(position).get("region_name");
        if (viewCount == 0) {
            cityId = selectAddressAdp.getData().get(position).get("id");
            presenter.getRegionList(cityId);
            text_province.setText(region_name);
            view_province.setVisibility(View.GONE);
            text_province.setTextColor(me.getResources().getColor(R.color.blackColor));
            viewCount = 1;
            //显示市的
            rl_gone_two.setVisibility(View.VISIBLE);
            text_city.setText("请选择");
            text_city.setTextColor(me.getResources().getColor(R.color.colorAccent));
            view_city.setVisibility(View.VISIBLE);
        } else if (viewCount == 1) {
            areaId = selectAddressAdp.getData().get(position).get("id");
            presenter.getRegionList(areaId);
            text_city.setText(region_name);
            view_city.setVisibility(View.GONE);
            text_city.setTextColor(me.getResources().getColor(R.color.blackColor));
            viewCount = 2;
            //显示区的
            rl_gone_three.setVisibility(View.VISIBLE);
            text_area.setText("请选择");
            view_area.setVisibility(View.VISIBLE);
            text_area.setTextColor(me.getResources().getColor(R.color.colorAccent));
        } else if (viewCount == 2) {
            text_province.setText(region_name);
            Intent intent = new Intent();
            bundle.putString("province", text_province.getText().toString().trim());
            bundle.putString("city", text_city.getText().toString().trim());
            bundle.putString("area", region_name);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void success(List<Map<String, String>> data) {
        selectAddressAdp.setNewData(data);
    }

    @Override
    public void saveDefaultAddress(int position, int type) {

    }
}