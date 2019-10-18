package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.RecommendItemAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.RecommendationDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ShoppingMallDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.RecommendItemPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.RecommendItemContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 10:39
 * 功能描述：推荐中的推荐界面
 * 联系方式：1037438704@qq.com
 */
public class RecommendItemFragment extends BaseFragment
        implements RecommendItemContract, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecommendItemAdp recommendItemAdp;
    private RecommendItemPresenter presenter;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int page = 1;


    public static RecommendItemFragment newInstance() {
        return new RecommendItemFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend_item;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        presenter = new RecommendItemPresenter(fgtContext, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(fgtContext));
        recommendItemAdp = new RecommendItemAdp(R.layout.item_recdommend_list_adp);
        recyclerView.setAdapter(recommendItemAdp);
        recommendItemAdp.setOnItemClickListener(this);
        http();
    }

    @Override
    public void pushOrdersList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            recommendItemAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                ToastUtil.show("没有更多数据了！");
                return;
            }
            recommendItemAdp.addData(data);
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

    private void http() {
        presenter.pushOrdersList(token, page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = recommendItemAdp.getData();
        if (data == null) {
            return;
        }
        String id = data.get(position).get("id");
        bundle.putString("match_push_id", id);
        start(fgtContext, bundle, RecommendationDetailsActivity.class);
    }
}