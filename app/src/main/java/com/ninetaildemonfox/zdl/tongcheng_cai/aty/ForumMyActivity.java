package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ForumMyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ForumMyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.recyclerview.RecyclerViewAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 11:32
 * 功能描述： 我的论坛
 * 联系方式：1037438704@qq.com
 */

public class ForumMyActivity extends BaseActivity
        implements RecyclerViewAdapter.onSlidingViewClickListener, ForumMyContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.fl_error)
    FrameLayout fl_error;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    private ForumMyPresenter presenter;
    private String member_id;
    private int page = 1;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forum_my;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initDatt();
    }

    private void initDatt() {
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
        member_id = getIntent().getExtras().getString("member_id");
        presenter = new ForumMyPresenter(me, this);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("我的论坛");
        textRight.setText("所有评论");
        textRight.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        rvAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(rvAdapter);
        //控制Item增删的动画，需要通过ItemAnimator  DefaultItemAnimator -- 实现自定义动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置滑动监听器 （侧滑）
        rvAdapter.setOnSlidListener(this);
        http();
    }

    private void http() {
        presenter.selectPostList(token, page, member_id);
    }

    @OnClick({R.id.image_left_finish, R.id.text_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_right:
                //所有评论
                start(me, AllCommentsActivity.class);
                break;
            default:
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        List<Map<String, String>> maps = rvAdapter.dataAll();
        if (maps == null) {
            return;
        }
        String id = maps.get(position).get("id");
        if (id == null) {
            return;
        }
        //item点击事件
        bundle.putString("forumItme", "1");
        bundle.putString("post_id", id);
        start(me, bundle, ForumTextActivity.class);
    }

    @Override
    public void onEditClick(View view, int position) {
        List<Map<String, String>> maps = rvAdapter.dataAll();
        if (maps == null) {
            return;
        }
        String id = maps.get(position).get("id");
        if (id == null) {
            return;
        }
        //编辑事件  修改论坛
        bundle.putInt("count", 2);
        bundle.putString("post_id", id);
        bundle.putInt("item", position);
        start(me, bundle, PublishContentActivity.class, 100);
    }

    //点击删除按钮时，根据传入的 position 调用 RecyclerAdapter 中的 removeData() 方法
    @Override
    public void onDeleteBtnCilck(View view, int position) {
        List<Map<String, String>> maps = rvAdapter.dataAll();
        if (maps == null) {
            return;
        }
        String id = maps.get(position).get("id");
        if (id == null) {
            return;
        }
        presenter.delPost(token, id, position);
    }

    @Override
    public void selectPostList(List<Map<String, String>> data) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        if (page == 1) {
            if (data == null) {
                fl_error.setVisibility(View.VISIBLE);
            } else {
                fl_error.setVisibility(View.GONE);
            }
            rvAdapter.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                ToastUtil.show("没有更多数据了");
                return;
            }
            rvAdapter.addData(data);
        }
    }

    @Override
    public void delPost(int item) {
        rvAdapter.removeData(item);
        if (rvAdapter.dataAll() == null) {
            fl_error.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            int item = bundle.getInt("item");
            String context = bundle.getString("context");
            if (context != null) {
                List<Map<String, String>> maps = rvAdapter.dataAll();
                if (maps == null) {
                    return;
                }
                Map<String, String> map = maps.get(item);
                map.put("post_content", context);
                rvAdapter.notifyItemChanged(item);
            }
        }
    }
}