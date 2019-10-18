package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.LotteryAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.DetailsOfCompetitionActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.PrizeInThrPastActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.LotteryPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.LotteryContract;
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
 * @date 2019/9/2 10:53
 * 功能描述： 开奖界面
 * 联系方式：1037438704@qq.com
 */
public class LotteryFragment extends BaseFragment
        implements LotteryContract, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_mall_ash)
    TextView text_mall_ash;
    @BindView(R.id.recycelrView)
    RecyclerView recycelrView;
    @BindView(R.id.ll_gone)
    LinearLayout ll_gone;
    @BindView(R.id.text_convertibility)
    TextView text_convertibility;
    @BindView(R.id.text_finish)
    TextView text_finish;
    @BindView(R.id.text_immediate)
    TextView text_immediate;
    private LotteryAdp lotteryAdp;
    private int abloon = 1;
    private int kaijiang = 1;
    private Drawable drawable;
    private LotteryPresenter presenter;
    private String type = "1";
    private int page = 1;
    private Drawable drawable2 = null;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_lottery;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        xuanze();
        initData();
        textRight.setVisibility(View.VISIBLE);
        textRight.setVisibility(View.GONE);
    }

    private void initData() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (kaijiang == 2) {
                    refreshLayout.finishRefresh();
                    return;
                }
                page = 1;
                presenter.getFootballMatchRewardsList(type, page);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (kaijiang == 2) {
                    refreshLayout.finishLoadMore();
                    return;
                }
                page++;
                presenter.getFootballMatchRewardsList(type, page);
            }
        });
    }

    private void initView() {
        presenter = new LotteryPresenter(fgtContext, this);
        drawable2 = fgtContext.getResources().getDrawable(R.mipmap.icon_award_red_right);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        textCenter.setText("开奖");
        recycelrView.setLayoutManager(new LinearLayoutManager(fgtContext));
        lotteryAdp = new LotteryAdp(R.layout.item_lottery_list_adp);
        lotteryAdp.setCount(kaijiang);
        recycelrView.setAdapter(lotteryAdp);
        lotteryAdp.setOnItemClickListener(this);
        presenter.getFootballMatchRewardsList(type, page);
    }

    private void xuanze() {
        if (kaijiang == 1) {
            text_mall_ash.setTextColor(fgtContext.getResources().getColor(R.color.colorAccent));
            text_convertibility.setTextColor(fgtContext.getResources().getColor(R.color.text_gray_666666));
        } else {
            text_mall_ash.setTextColor(fgtContext.getResources().getColor(R.color.text_gray_666666));
            text_convertibility.setTextColor(fgtContext.getResources().getColor(R.color.colorAccent));
        }
    }

    @OnClick({R.id.text_mall_ash, R.id.text_convertibility, R.id.ll_gone, R.id.text_immediate, R.id.text_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_mall_ash:
                if (kaijiang == 1) {
                    if (abloon == 1) {
                        drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_award_up);
                        ll_gone.setVisibility(View.VISIBLE);
                        abloon = 2;
                    } else {
                        drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_award_down);
                        ll_gone.setVisibility(View.GONE);
                        abloon = 1;
                    }
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    text_mall_ash.setCompoundDrawables(null, null, drawable, null);
                }
                text_mall_ash.setTextColor(fgtContext.getResources().getColor(R.color.colorAccent));
                text_convertibility.setTextColor(fgtContext.getResources().getColor(R.color.text_gray_666666));
                kaijiang = 1;
                //这里添加数据
                lotteryAdp = new LotteryAdp(R.layout.item_lottery_list_adp);
                lotteryAdp.setCount(kaijiang);
                recycelrView.setAdapter(lotteryAdp);
                lotteryAdp.setOnItemClickListener(this);
                presenter.getFootballMatchRewardsList(type, page);
                break;
            case R.id.text_convertibility:
//                drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_award_down);
//                ll_gone.setVisibility(View.GONE);
//                abloon = 1;
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                text_mall_ash.setCompoundDrawables(null, null, drawable, null);
                //即时
//                ll_gone.setVisibility(View.GONE);
//                abloon = 1;
                if (abloon == 2) {
                    return;
                }
                text_mall_ash.setTextColor(fgtContext.getResources().getColor(R.color.text_gray_666666));
                text_convertibility.setTextColor(fgtContext.getResources().getColor(R.color.colorAccent));
                kaijiang = 2;
                //添加数据
                lotteryAdp = new LotteryAdp(R.layout.item_immediate_list_adp);
                lotteryAdp.setCount(kaijiang);
                recycelrView.setAdapter(lotteryAdp);
                lotteryAdp.setOnItemClickListener(this);
                presenter.openFootballMatchRewardsList();
                break;
            case R.id.text_finish:
                type = "2";
                presenter.getFootballMatchRewardsList(type, page);
                text_mall_ash.setText("完场");
                drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_award_down);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                text_mall_ash.setCompoundDrawables(null, null, drawable, null);
                ll_gone.setVisibility(View.GONE);
                abloon = 1;
                text_immediate.setCompoundDrawables(null, null, null, null);
                text_finish.setCompoundDrawables(null, null, drawable2, null);
                break;
            case R.id.text_immediate:
                type = "1";
                presenter.getFootballMatchRewardsList(type, page);
                text_immediate.setCompoundDrawables(null, null, drawable2, null);
                text_finish.setCompoundDrawables(null, null, null, null);
                text_mall_ash.setText("即时");
                drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_award_down);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                text_mall_ash.setCompoundDrawables(null, null, drawable, null);
                ll_gone.setVisibility(View.GONE);
                abloon = 1;
                break;
            case R.id.ll_gone:
                ll_gone.setVisibility(View.GONE);
                abloon = 1;
                break;
            default:
        }
    }

    public static LotteryFragment newInstance() {
        return new LotteryFragment();
    }


    @Override
    public void getFootballMatchRewardsList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            lotteryAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                ToastUtil.show("没有更多数据了");
                return;
            }
            lotteryAdp.addData(data);
        }
    }

    @Override
    public void openFootballMatchRewardsList(List<Map<String, String>> data) {
        lotteryAdp.setNewData(data);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = lotteryAdp.getData();
        if (data == null) {
            return;
        }
        String type = data.get(position).get("type");
        if (type == null) {
            return;
        }
        if (kaijiang == 1) {
            String id = data.get(position).get("id");
            if (id == null) {
                return;
            }
            bundle.putString("football_match_id", id);
            bundle.putString("type", type);
            start(fgtContext, bundle, DetailsOfCompetitionActivity.class);
        } else {
            //点击查看往期
            bundle.putString("type", type);
            start(fgtContext, bundle, PrizeInThrPastActivity.class);
        }
    }
}