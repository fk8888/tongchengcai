package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
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
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ForumAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ForumContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ForumPresenter;
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
 * @date 2019/9/3 11:54
 * 功能描述： 论坛中心
 * 联系方式：1037438704@qq.com
 */
public class ForumActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemClickListener,
        ForumContract, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.image_forum)
    ImageView image_forum;
    private ForumAdp forumAdp;
    private ForumPresenter presenter;
    private int page = 1;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forum;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        WaitDialog.show(me, "");
        http();
        initData();
    }

    private void initView() {
        presenter = new ForumPresenter(me, this);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("论坛中心");
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        forumAdp = new ForumAdp(R.layout.item_forum_list_adp);
        forumAdp.setEmptyView(viewError);
        TextView text_null = viewError.findViewById(R.id.text_null);
        text_null.setText("当前暂无评论");
        forumAdp.setForumcount(1);
        recyclerView.setAdapter(forumAdp);
        forumAdp.setOnItemClickListener(this);
        forumAdp.setOnItemChildClickListener(this);

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
        presenter.addReceiveAddress(token, page);
    }

    @OnClick({R.id.image_left_finish, R.id.image_forum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.image_forum:
                //发布论坛
                bundle.putInt("count", 1);
                start(me, bundle, PublishContentActivity.class,101);
                break;
            default:
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Map<String, String> map = forumAdp.getData().get(position);
        if (map == null) {
            return;
        }
        String id = map.get("id");
        bundle.putString("forumItme", "1");
        bundle.putString("post_id", id);
        start(me, bundle, ForumTextActivity.class);
    }

    @Override
    public void selectPostCommentsList(List<Map<String, String>> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            forumAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                return;
            }
            forumAdp.addData(data);
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Map<String, String> map = forumAdp.getData().get(position);
        if (map == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.text_snap:
                //点赞
                String id = map.get("id");
                String is_click = map.get("is_click");
                if (is_click.equals("true")) {
                    is_click = "2";
                } else {
                    is_click = "1";
                }
                presenter.giveLike(token, is_click, id, position);
                break;
            case R.id.text_comment:
                //评论
                break;
            default:
        }
    }

    /**
     * 点赞
     */
    @Override
    public void delPost(int item) {
        Map<String, String> map = forumAdp.getData().get(item);
        String is_click = map.get("is_click");
        String click_number = map.get("click_number");
        if (TextUtils.isEmpty(click_number)) {
            return;
        }
        int count = Integer.valueOf(click_number);
        if (is_click.equals("true")) {
            map.put("is_click", "false");
            count--;
            map.put("click_number", String.valueOf(count));
        } else {
            count++;
            map.put("is_click", "true");
            map.put("click_number", String.valueOf(count));
        }
        forumAdp.notifyItemChanged(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String string = bundle.getString(token);
            ToastUtil.show(string);
            page = 1;
            http();
        }
    }
}