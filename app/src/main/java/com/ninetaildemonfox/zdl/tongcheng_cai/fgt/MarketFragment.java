package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.MarketAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ShoppingMallDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MarketPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MarketContract;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 10:52
 * 功能描述： 积分商场
 * 联系方式：1037438704@qq.com
 */
public class MarketFragment extends BaseFragment implements MarketContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_mall_ash)
    TextView textMallAsh;
    @BindView(R.id.text_convertibility)
    TextView textConvertibility;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private MarketAdp marketAdp;
    private String sort = "";
    private String sort_type = "";
    private Drawable drawable1, drawable2;
    private MarketPresenter marketPresenter;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_market;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {
        marketAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = marketAdp.getData().get(position).get("id");
                if (TextUtils.isEmpty(id)) {
                    return;
                }
                bundle.putString("integral_goods_id", id);
                start(fgtContext, bundle, ShoppingMallDetailsActivity.class);
            }
        });

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                sort = "";
                sort_type = "";
                drawable1 = ContextCompat.getDrawable(fgtContext, R.mipmap.icon_mall_ash);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textMallAsh.setCompoundDrawables(null, null, drawable1, null);
                textConvertibility.setCompoundDrawables(null, null, drawable1, null);
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
        marketPresenter.getIntegralGoodsList(sort, page, sort_type);
    }

    private void initView() {
        marketPresenter = new MarketPresenter(fgtContext, this);
        textCenter.setText("积分商场");
        recyclerView.setLayoutManager(new GridLayoutManager(fgtContext, 2));
        marketAdp = new MarketAdp(R.layout.item_market_adp);
        recyclerView.setAdapter(marketAdp);
        http();
    }

    @OnClick({R.id.text_mall_ash, R.id.text_convertibility})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_mall_ash:
                if (sort.equals("")) {
                    drawable1 = ContextCompat.getDrawable(fgtContext, R.mipmap.icon_mall_up);
                    sort = "1";
                } else if (sort.equals("1")) {
                    drawable1 = ContextCompat.getDrawable(fgtContext, R.mipmap.icon_mall_down);
                    sort = "2";
                } else if (sort.equals("2")) {
                    drawable1 = fgtContext.getResources().getDrawable(R.mipmap.icon_mall_ash);
                    sort = "";
                }
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textMallAsh.setCompoundDrawables(null, null, drawable1, null);
                page = 1;
                http();
                break;
            case R.id.text_convertibility:
                if (sort_type.equals("")) {
                    drawable2 = ContextCompat.getDrawable(fgtContext, R.mipmap.icon_mall_up);
                    sort_type = "1";
                } else if (sort_type.equals("1")) {
                    drawable2 = ContextCompat.getDrawable(fgtContext, R.mipmap.icon_mall_down);
                    sort_type = "2";
                } else if (sort_type.equals("2")) {
                    drawable2 = fgtContext.getResources().getDrawable(R.mipmap.icon_mall_ash);
                    sort_type = "";
                }
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textConvertibility.setCompoundDrawables(null, null, drawable2, null);
                page = 1;
                http();
                break;
            default:
        }
    }


    public static MarketFragment newInstance() {
        return new MarketFragment();
    }

    @Override
    public void Success(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            marketAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                return;
            }
            marketAdp.addData(data);
        }
    }
}