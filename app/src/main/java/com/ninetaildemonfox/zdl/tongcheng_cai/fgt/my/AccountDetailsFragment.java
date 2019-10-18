package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.my;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AccountDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.AccountDetailsPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.AccountDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 10:48
 * 功能描述：1账本明细  2 提现明细 3积分明细
 * 联系方式：1037438704@qq.com
 */
public class AccountDetailsFragment extends BaseFragment implements AccountDetailsContract {

    private AccountDetailsAdp accountDetailsAdp;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AccountDetailsPresenter presenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String count;
    private String type;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account_details;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        count = getArguments().getString("count");
        type = getArguments().getString("type");
        presenter = new AccountDetailsPresenter(fgtContext, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(fgtContext));
        accountDetailsAdp = new AccountDetailsAdp(R.layout.item_account_list_adp);
        accountDetailsAdp.setEmptyView(view);
        recyclerView.setAdapter(accountDetailsAdp);

        http();
        initData();
    }

    private void http() {
        if (count.equals("1")) {
            presenter.getMoneyLogList(token, page, type);
        } else if (count.equals("2")) {
            presenter.getWithdrawLogList(token, page, type);
        } else if (count.equals("3")) {
            presenter.getIntegralLogList(token, page, type);
        }
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


    public static AccountDetailsFragment newInstance(String count, String type) {
        AccountDetailsFragment accountDetailsFragment = new AccountDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("count", count);
        bundle.putString("type", type);
        accountDetailsFragment.setArguments(bundle);
        return accountDetailsFragment;
    }

    @Override
    public void Success(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            accountDetailsAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                ToastUtil.show("没有更多数据了");
                return;
            }
            accountDetailsAdp.addData(data);
        }
    }

    @Override
    public void confirmIntegralOrder(int position) {

    }
}