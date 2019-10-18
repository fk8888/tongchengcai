package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/5
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class CommentAdp extends BaseQuickAdapter<Map<String,String>, BaseViewHolder> {

    public String count;

    public void setCount(String count) {
        this.count = count;
    }

    public CommentAdp(int item_dinformation_list_adp) {
        super(item_dinformation_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        ImageView image_comment_head_pic = helper.itemView.findViewById(R.id.image_comment_head_pic);
        ImageView imageHead = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.circularGaoSi(mContext,item.get("comment_head_pic"),imageHead);
        TextView textComment = helper.itemView.findViewById(R.id.text_comment);
        LinearLayout ll_gone_view = helper.itemView.findViewById(R.id.ll_gone_view);
        textComment.setVisibility(View.GONE);

        helper.setText(R.id.text_name,item.get("comment_nick_name"))
                .setText(R.id.text_time,item.get("comment_time"))
                .setText(R.id.text_comment_context,item.get("comment_content"));

//        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
//        ImageViewAdp imageViewAdp = new ImageViewAdp(R.layout.item_image_apd);
//        recyclerView.setAdapter(imageViewAdp);
//        ArrayList<Map<String, String>> post_img_paths_android = JSONUtils.parseKeyAndValueToMapList(item.get("post_img_paths_android"));
//        if (post_img_paths_android != null) {
//            imageViewAdp.setNewData(post_img_paths_android);
//        }
//        评论内容——"comment_content": "我回复你了",
//                帖子ID——"post_id":"11"
//        评论时间—— "comment_time": "2019-08-22 18:58",
//                "is_admin": "2",
//                帖子标题—— "post_title": "篮球混合必中",
//                帖子内容—— "post_content": "篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中篮球混合必中",
//                帖子作者头像——  "post_head_pic": "http://www.tongchengcai.com/Public/logo/off2-logo.png",
//                帖子作者昵称——"post_nick_name": "同城彩官方",
//                评论者昵称——"comment_nick_name": "娃哈哈",
//                评论者头像—— "comment_head_pic": "http://www.tongchengcai.com/Uploads/Member/2019-08-24/5d609631243ad.png"
        if (count.equals("1")) {
            ll_gone_view.setVisibility(View.VISIBLE);
            //这个是我自己的
            helper.setText(R.id.text_comment_nick_name,item.get("post_nick_name"))
                    .setText(R.id.text_comment_content,item.get("post_content"));
            GlidUtils.circular(mContext,item.get("post_head_pic"),image_comment_head_pic);
        } else if (count.equals("2")) {
            ll_gone_view.setVisibility(View.GONE);
        }
    }
}
