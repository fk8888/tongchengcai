package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.view.View;
import android.widget.ImageView;
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
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class RecommendItemAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public RecommendItemAdp(int item_forum_list_adp) {
        super(item_forum_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.addOnClickListener(R.id.text_comment)
                .setText(R.id.text_comment, "命中率" + item.get("hit_percent"))
                .setText(R.id.text_name, item.get("nick_name"))
                .setText(R.id.text_time, item.get("create_time"))
                .setText(R.id.text_push_content, item.get("push_content"))
                .setText(R.id.text_week, item.get("week"))
                .setText(R.id.text_home_vs_guest, item.get("home_team_name") + "\t\tVS\t\t" + item.get("guest_team_name"))
                .setText(R.id.text_play_money, "￥" + item.get("pay_money"))
        ;

        ImageView image_head = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.circular(mContext, item.get("head_pic"), image_head);

        TextView text_strand = helper.itemView.findViewById(R.id.text_strand);
        text_strand.setVisibility(View.GONE);
    }
}