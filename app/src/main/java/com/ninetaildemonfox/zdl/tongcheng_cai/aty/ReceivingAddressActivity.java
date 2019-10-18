package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ReceivingAddressAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ReceivingAddressPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ReceivingAddressContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author NineTailDemonFox
 * @date 2019/9/7 9:52
 * 功能描述： 收货地址管理
 * 联系方式：1037438704@qq.com
 */
public class ReceivingAddressActivity extends BaseActivity implements ReceivingAddressContract, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_add_address)
    TextView text_add_address;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ReceivingAddressAdp receivingAddressAdp;
    private ReceivingAddressPresenter presenter;
    private int pageSize = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_receiving_address;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initDate();
    }

    private void initDate() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                receivingAddressAdp.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                pageSize = 1;
                presenter.getReceiveAddressList(token, pageSize);
            }
        });
        receivingAddressAdp.setOnItemChildClickListener(this);
    }

    private void initView() {
        presenter = new ReceivingAddressPresenter(me, this);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("收货地址管理");
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        receivingAddressAdp = new ReceivingAddressAdp(R.layout.item_receiving_address_adp);
        recyclerView.setAdapter(receivingAddressAdp);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getReceiveAddressList(token, pageSize);
    }

    @OnClick({R.id.image_left_finish, R.id.text_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_add_address:
                //添加收货地址
                bundle.putString("count", "1");
                start(me, bundle, AddAddressActivity.class);
                break;
            default:
        }
    }

    @Override
    public void success(List<Map<String, String>> data) {
        if (pageSize == 1) {
            mSwipeRefreshLayout.setRefreshing(false);
            receivingAddressAdp.setEnableLoadMore(true);
            receivingAddressAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                ToastUtil.show("没有更多数据了！");
            }
            receivingAddressAdp.addData(data);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = receivingAddressAdp.getData();
        if (data == null) {
            return;
        }
        Map<String, String> map = data.get(position);
        if (map == null) {
            return;
        }
        if (view.getId() == R.id.text_flag) {
            //设置默认地址 1    删除2
            presenter.saveDefaultAddress(token, map.get("id"), position);
        } else if (view.getId() == R.id.text_edit) {
            //编辑
            bundle.putString("map", map.toString().trim());
            bundle.putString("count", "2");
            start(me, bundle, AddAddressActivity.class);
        } else if (view.getId() == R.id.text_delete) {
            presenter.delReceiveAddress(token, map.get("id"), position);
        }
    }

    @Override
    public void saveDefaultAddress(int position, int type) {
        List<Map<String, String>> data = receivingAddressAdp.getData();
        Map<String, String> map = data.get(position);
//        是否是默认地址: 1-不是 2-是
        if (type == 1) {
            if (map.get("flag").equals("2")) {
                map.put("flag", "1");
            } else {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("flag", "1");
                }
                map.put("flag", "2");
            }
            receivingAddressAdp.notifyDataSetChanged();
        } else {
            receivingAddressAdp.remove(position);
        }
    }
}