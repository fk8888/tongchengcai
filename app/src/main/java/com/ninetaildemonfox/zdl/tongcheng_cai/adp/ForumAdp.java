package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/3
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ForumAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public int forumcount;


    public void setForumcount(int forumcount) {
        this.forumcount = forumcount;
    }

    public ForumAdp(int item_forum_list_adp) {
        super(item_forum_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        ImageView image_head = helper.itemView.findViewById(R.id.image_head);
        TextView text_snap = helper.itemView.findViewById(R.id.text_snap);
        TextView text_comment = helper.itemView.findViewById(R.id.text_comment);

        GlidUtils.circular(mContext, item.get("head_pic"), image_head);
        helper.setText(R.id.text_name, item.get("nick_name"))
                .setText(R.id.text_time, item.get("create_time"))
                .addOnClickListener(R.id.text_snap)
                .addOnClickListener(R.id.text_comment);

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        ImageViewAdp imageViewAdp = new ImageViewAdp(R.layout.item_image_apd);
        recyclerView.setAdapter(imageViewAdp);
        ArrayList<Map<String, String>> post_img_paths_android = JSONUtils.parseKeyAndValueToMapList(item.get("post_img_paths_android"));
        if (post_img_paths_android != null) {
            imageViewAdp.setNewData(post_img_paths_android);
        }

        //1不隐藏 2 隐藏
        if (forumcount == 1) {
            text_snap.setVisibility(View.VISIBLE);
            text_comment.setVisibility(View.VISIBLE);

            text_snap.setText(item.get("click_number"));
            text_comment.setText(item.get("comment_number"));
            String is_click = item.get("is_click");
//            当前登陆者是否点赞  true-已点赞  false-未点赞,
            Drawable drawable = null;
            if (is_click.equals("false")) {
                drawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_home_snap_false);
            } else {
                drawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_home_snap_true);
            }
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                text_snap.setCompoundDrawables(drawable, null, null, null);
            }
            helper.setText(R.id.text_post_content, item.get("post_content"));
        } else {
            helper.setText(R.id.text_post_content, item.get("comment_content"));
//            recyclerView
            text_snap.setVisibility(View.GONE);
            text_comment.setVisibility(View.GONE);
        }
    }
}