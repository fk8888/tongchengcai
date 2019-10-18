package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.RecordAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.RecordPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.RecordContract;
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
 * @date 2019/9/4 17:36
 * 功能描述： 战绩
 * 联系方式：1037438704@qq.com
 */
public class RecordActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemClickListener,
        RecordContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private RecordAdp recordAdp;
    private RecordPresenter presenter;
    private int page = 1;
    private String member_orders_send_id;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_record;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
    }

    private void initView() {
        member_orders_send_id = getIntent().getExtras().getString("member_orders_send_id");
        presenter = new RecordPresenter(me, this);
        textCenter.setText("战绩");
        imageleftFinish.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        recordAdp = new RecordAdp(R.layout.item_record_list_adp);
        recordAdp.setEmptyView(viewError);
        recyclerView.setAdapter(recordAdp);
        recordAdp.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        http();
    }

    public void http() {
        presenter.getMemberAgainSendRecordList(token, member_orders_send_id, page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String id = recordAdp.getData().get(position).get("id");
        bundle.putInt("documentart", 2);
        bundle.putString("id", id);
        start(me, bundle, DocumentaryInformationActivity.class);
    }

    @Override
    public void getMemberAgainSendRecordList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            recordAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                ToastUtil.show("没有更多数据了！");
                return;
            }
            recordAdp.addData(data);
        }
    }

    private void initData() {
        http();
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
}