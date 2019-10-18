package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.recommend;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.DocumentaryAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.DocumentaryInformationActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.DocumentaryPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.DocumentaryContract;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 首页推荐 - 》跟单界面
 *
 * @author NineTailDemonFox
 */
public class DocumentaryFragment extends BaseFragment
        implements DocumentaryContract, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DocumentaryAdp documentaryAdp;
    private int again_type, power_type;
    private int page = 1;
    private DocumentaryPresenter presenter;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_documentary;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        http();
        initData();
    }

    private void initView() {
        presenter = new DocumentaryPresenter(fgtContext, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(fgtContext));
        documentaryAdp = new DocumentaryAdp(R.layout.item_documentary_list_adp);
        recyclerView.setAdapter(documentaryAdp);
        documentaryAdp.setOnItemClickListener(this);
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
        presenter.getAgainOrdersSendList(token, power_type, again_type, page);
    }

    public static DocumentaryFragment newInstance() {
        return new DocumentaryFragment();
    }


    /**
     * I
     * 跟单类型查询 1——用户跟单 2——我的跟单 0——全部跟单
     * 筛选条件 1——实力 2——盈利 3——人气 0——全部
     **/
    public void setAgain_type(int again_type, int power_type) {
        this.again_type = again_type;
        this.power_type = power_type;
        page = 1;
        http();
    }

    @Override
    public void getAgainOrdersSendList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            documentaryAdp.setNewData(data);
        } else {
            documentaryAdp.addData(data);
        }
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = documentaryAdp.getData();
        String id = data.get(position).get("id");
        bundle.putInt("documentart", 1);
        bundle.putString("member_orders_send_id",id);
        start(fgtContext, bundle, DocumentaryInformationActivity.class);
    }
}