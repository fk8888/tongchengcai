package com.ninetaildemonfox.zdl.tongcheng_cai.fgt.my;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.CommentAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ForumTextActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.CommentPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.CommentContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/18 11:46
 * 功能描述： 1我评论的 2评论我的
 * 联系方式：1037438704@qq.com
 */
public class CommentFragment extends BaseFragment
        implements BaseQuickAdapter.OnItemClickListener
        , CommentContract {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommentAdp commentAdp;
    private String forumItme;
    private CommentPresenter presenter;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int page = 1;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        presenter = new CommentPresenter(fgtContext, this);
        forumItme = this.getArguments().getString("forumItme");
        recyclerView.setLayoutManager(new LinearLayoutManager(fgtContext));
        commentAdp = new CommentAdp(R.layout.item_comment_list_adp);
        commentAdp.setEmptyView(view);
        commentAdp.setCount(forumItme);
        recyclerView.setAdapter(commentAdp);
        commentAdp.setOnItemClickListener(this);
        http();
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

    private void http() {
        presenter.getCommentsList(token, forumItme, page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = commentAdp.getData();
        if (data == null) {
            return;
        }
        String id = data.get(position).get("post_id");
        if (id == null) {
            return;
        }
        bundle.putString("forumItme", forumItme);
        bundle.putString("post_id", id);
        start(fgtContext, bundle, ForumTextActivity.class);
    }

    public static CommentFragment newInstance(String forumItme) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("forumItme", forumItme);
        commentFragment.setArguments(bundle);
        return commentFragment;
    }

    @Override
    public void getCommentsList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            commentAdp.setNewData(data);
        } else {
            if (data == null || data.size() == 0) {
                ToastUtil.show("没有更多数据了");
                return;
            }
            commentAdp.addData(data);
        }
    }
}