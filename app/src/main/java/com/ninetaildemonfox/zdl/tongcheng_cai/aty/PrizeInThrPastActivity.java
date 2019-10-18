package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.LotteryAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.PrizeInThrPastPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.PrizeInThrPastContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author NineTailDemonFox
 * @date 2019/9/20 11:54
 * 功能描述： 往期开奖
 * 联系方式：1037438704@qq.com
 */
public class PrizeInThrPastActivity extends BaseActivity implements PrizeInThrPastContract, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recycelrView)
    RecyclerView recycelrView;
    private String type;
    private PrizeInThrPastPresenter prizeInThrPastPresenter;
    private LotteryAdp lotteryAdp;
    private int page = 1;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_prize_in_thr_past;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        WaitDialog.show(me, "");
        http();
        initData();
    }

    private void initView() {
        imageleftFinish.setVisibility(View.VISIBLE);
        type = getIntent().getExtras().getString("type");
        if (type.equals("1")) {
            textCenter.setText("胜负彩");
        } else if (type.equals("2")) {
            textCenter.setText("进球彩");
        } else if (type.equals("3")) {
            textCenter.setText("半全场");
        }

        prizeInThrPastPresenter = new PrizeInThrPastPresenter(me, this);
        recycelrView.setLayoutManager(new LinearLayoutManager(me));
        lotteryAdp = new LotteryAdp(R.layout.item_immediate_list_adp);
        lotteryAdp.setCount(2);
        lotteryAdp.setOnItemClickListener(this);
        recycelrView.setAdapter(lotteryAdp);
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
    public void beforeFootballMatchRewardsList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            lotteryAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                return;
            }
            lotteryAdp.addData(data);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        start(me, bundle, DetailsAwardActivity.class);
    }

    private void http() {
        prizeInThrPastPresenter.beforeFootballMatchRewardsList(type, page);
    }
}
