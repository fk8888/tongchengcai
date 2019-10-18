package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ForumAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ImageViewAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ForumTextPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ForumTextContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 13:09
 * 功能描述： 1论坛正文  2我评论的  3 评论我的
 * 联系方式：1037438704@qq.com
 */
public class ForumTextActivity extends BaseActivity implements ForumTextContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_head)
    ImageView image_head;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_time)
    TextView text_time;
    @BindView(R.id.text_post_content)
    TextView text_post_content;
    @BindView(R.id.textSubmission)
    TextView textSubmission;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerViewImageView)
    RecyclerView recyclerViewImageView;
    @BindView(R.id.edit_context)
    EditText editContext;
    private ForumAdp forumAdp;
    private String forumItme, post_id;
    private ForumTextPresenter presenter;
    private ImageViewAdp imageViewAdp;


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forum_text;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
        http();
    }

    private void initView() {
        recyclerViewImageView.setLayoutManager(new GridLayoutManager(me, 3));
        imageViewAdp = new ImageViewAdp(R.layout.item_image_apd);
        recyclerViewImageView.setAdapter(imageViewAdp);
        forumItme = getIntent().getExtras().getString("forumItme");
//        查看帖子详情
        post_id = getIntent().getExtras().getString("post_id");
        presenter = new ForumTextPresenter(me, this);
        presenter.saveReceiveAddress(token, post_id);
        if (forumItme.equals("1")) {
            textCenter.setText("论坛正文");
        } else if (forumItme.equals("2")) {
            textCenter.setText("我评论的");
        } else if (forumItme.equals("3")) {
            textCenter.setText("评论我的");
        } else {
            return;
        }
        imageleftFinish.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        forumAdp = new ForumAdp(R.layout.item_forum_list_adp);
        forumAdp.setForumcount(2);
        forumAdp.setEmptyView(viewError);
        TextView text_null = viewError.findViewById(R.id.text_null);
        text_null.setText("当前暂无评论");
        recyclerView.setAdapter(forumAdp);
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
        presenter.selectPostCommentsList(post_id, page);
    }

    @OnClick({R.id.image_left_finish, R.id.textSubmission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.textSubmission:
//                token	token	否	文本	14446c40e00f76a53f96240275c103a9
//                post_id	帖子ID	否	文本	11
//                member_id	作者ID, 如果是后台管理员发的帖子就是 0	否	文本	0
//                comment_content	评论内容
                presenter.addPostComments(token, post_id, member_id, editContext.getText().toString().trim());
                editContext.setText("");
                break;
            default:
        }
    }

    private String member_id;

    @Override
    public void findPostInfo(Map<String, String> data) {
        GlidUtils.circular(me, data.get("head_pic"), image_head);
        member_id = data.get("member_id");
        text_name.setText(data.get("nick_name"));
        text_time.setText(data.get("create_time"));
        text_post_content.setText(data.get("post_content"));

        ArrayList<Map<String, String>> post_img_paths_android = JSONUtils.parseKeyAndValueToMapList(data.get("post_img_paths_android"));
        if (post_img_paths_android != null) {
            imageViewAdp.setNewData(post_img_paths_android);
        }
    }

    @Override
    public void selectPostCommentsList(List<Map<String, String>> data) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        if (page == 1) {
            forumAdp.setNewData(data);
        } else {
            if (data.size() == 0 || data == null) {
                return;
            }
            forumAdp.addData(data);
        }
    }
}