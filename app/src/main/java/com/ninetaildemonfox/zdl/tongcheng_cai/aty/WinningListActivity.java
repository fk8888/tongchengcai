package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.WinningListAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.WinningListPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.WinningListContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author NineTailDemonFox
 * @date 2019/9/3 10:49
 * 功能描述： 中奖名单
 * 联系方式：1037438704@qq.com
 */
public class WinningListActivity extends BaseActivity implements WinningListContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private WinningListAdp winningListAdp;
    private WinningListPresenter presenter;
    private int page = 1;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_winning_list;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
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

    private void http() {
        presenter.getWinLogList(page);
    }

    private void initView() {
        presenter = new WinningListPresenter(me, this);
        textCenter.setText("中奖名单");
        imageleftFinish.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        winningListAdp = new WinningListAdp(R.layout.item_winning_list_adp);
        winningListAdp.setEmptyView(viewError);
        recyclerView.setAdapter(winningListAdp);
        http();
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
    public void success(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            winningListAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                ToastUtil.show("没有更多数据了");
                return;
            }
            winningListAdp.addData(data);
        }
    }
}