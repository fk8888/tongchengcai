package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.my;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MyLotteryOrderAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.BiddingOrderActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.OrderDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SubmitExchangeActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.GetOrderInfoPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.GetOrderInfoContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyLotteryOrderPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/6 11:27
 * 功能描述：   "全部", "待出票", "待开奖", "已中奖", "未中奖"
 * 联系方式：1037438704@qq.com
 */
public class MyLotteryOrderFragment extends BaseFragment
        implements BaseQuickAdapter.OnItemClickListener,
        AccountDetailsContract,
        BaseQuickAdapter.OnItemChildClickListener , GetOrderInfoContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int page = 1;


    private MyLotteryOrderAdp myLotteryOrderAdp;
    private MyLotteryOrderPresenter presenter;
    private String count;
    private String url;
    private int status;
    private GetOrderInfoPresenter getOrderInfoPresenter;
    private String order_id;
    private String order_type;
    private String type;
    private String stype;
    private String order_name;
    private String status1;

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        initData();

    }

    private void initData() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                http();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                http();
            }
        });
    }

    private void initView() {
        presenter = new MyLotteryOrderPresenter(fgtContext, this);
        count = getArguments().getString("count");
        status = getArguments().getInt("status");

        recyclerView.setLayoutManager(new LinearLayoutManager(fgtContext));

        if (count.equals("1")) {
            //彩票订单
            myLotteryOrderAdp = new MyLotteryOrderAdp(R.layout.item_order_list_adp);
            url = GETORDERSLIST;
            http();
        } else if (count.equals("2")) {
            //跟单订单
            myLotteryOrderAdp = new MyLotteryOrderAdp(R.layout.item_order_list_adp);
            url = AGAINORDERSLIST;
            http();
           // presenter.oredergendanList(token,page, status, type);
        } else if (count.equals("3")) {
            //商场订单
            myLotteryOrderAdp = new MyLotteryOrderAdp(R.layout.item_order_shop_adp);
            url = GETEXCHANGEINTEGRALLOGLIST;
            http();
        }
        myLotteryOrderAdp.setEmptyView(view);
        myLotteryOrderAdp.setLotteryCount(Integer.valueOf(count));
        recyclerView.setAdapter(myLotteryOrderAdp);
        myLotteryOrderAdp.setOnItemClickListener(this);
        myLotteryOrderAdp.setOnItemChildClickListener(this);


    }

    private void http() {
        presenter.orederList(url, token, page, status, count);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_lottery_order;
    }

    //条目点击跳转详情页
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = myLotteryOrderAdp.getData();
        if (count.equals("1")) {  //彩票订单
            //获得订单id
           String stype = myLotteryOrderAdp.getData().get(position).get("id");
            order_id = stype;
            //获得订单类型
            order_type = myLotteryOrderAdp.getData().get(position).get("order_type");
            //订单状态
            String status = myLotteryOrderAdp.getData().get(position).get("status");
            bundle.putString("stype", stype);
            bundle.putString("order_type", order_type);
            bundle.putString("status",status);
            start(fgtContext, bundle, BiddingOrderActivity.class);

            getOrderInfoPresenter = new GetOrderInfoPresenter(fgtContext, this);

            myLotteryOrderAdp.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.text_button:
                            getOrderInfoPresenter.cancleList(token, order_id, order_type);
                            break;

                            default:
                    }
                }
            });
        } else if (count.equals("2")) {   //跟单订单
            //获得订单id
            stype = myLotteryOrderAdp.getData().get(position).get("member_orders_send_id");
            Log.e("sss",stype);
            //获得订单类型
            order_type = myLotteryOrderAdp.getData().get(position).get("order_type");
            order_name = myLotteryOrderAdp.getData().get(position).get("order_name");
            //订单状态
            status1 = myLotteryOrderAdp.getData().get(position).get("status");

            bundle.putString("stype", stype);
            bundle.putString("ordername",order_name);
            bundle.putString("order_type", order_type);
            bundle.putString("status", status1);
            start(fgtContext,bundle, OrderDetailsActivity.class);

        } else if (count.equals("3")) {
            bundle.putString("integral_goods_id", data.get(position).get("id"));
            bundle.putString("type", "2");
            start(fgtContext, bundle, SubmitExchangeActivity.class);
        }else {
            ToastUtil.show("数据出错！");
        }
    }

    @Override
    public void Success(List<Map<String, String>> data) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        if (page == 1) {
            myLotteryOrderAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                ToastUtil.show("没有更多数据了！");
                return;
            }
            myLotteryOrderAdp.addData(data);
        }
    }

    @Override
    public void confirmIntegralOrder(int position) {
        List<Map<String, String>> data = myLotteryOrderAdp.getData();
        if (status == 2) {
            myLotteryOrderAdp.remove(position);
        } else {
            data.get(position).put("status", "3");
            myLotteryOrderAdp.notifyItemChanged(position);
        }
    }

    public static MyLotteryOrderFragment newInstance(String count, int status) {
        MyLotteryOrderFragment myLotteryOrderFragment = new MyLotteryOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("count", count);
        bundle.putInt("status", status);
        myLotteryOrderFragment.setArguments(bundle);
        return myLotteryOrderFragment;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = myLotteryOrderAdp.getData();
        switch (view.getId()) {
            case R.id.text_button:
                presenter.confirmIntegralOrder(token, data.get(position).get("id"), position);
                break;
            default:
        }
    }

    @Override
    public void getOrder(Map<String, String> data) {

    }

    @Override
    public void cancleList(Map<String, String> data) {
        ToastUtil.show("撤单成功");
    }

}