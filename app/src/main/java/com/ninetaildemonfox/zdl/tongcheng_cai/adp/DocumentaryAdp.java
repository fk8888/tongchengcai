package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class DocumentaryAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public DocumentaryAdp(int item_documentary_list_adp) {
        super(item_documentary_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.addOnClickListener(R.id.text_comment)
                .setText(R.id.text_name, item.get("nick_name"))
                .setText(R.id.text_time, item.get("create_time"))
                .setText(R.id.text_hit_percent, item.get("hit_percent"))
                .setText(R.id.text_avg_percent, item.get("avg_percent"))
                .setText(R.id.text_people_number, item.get("people_number"))
                .setText(R.id.text_max_sp, item.get("max_sp"));
        TextView textComment = helper.itemView.findViewById(R.id.text_comment);
        String is_again = item.get("is_again");
        if (is_again.equals("0")) {
            textComment.setVisibility(View.VISIBLE);
        } else {
            textComment.setVisibility(View.GONE);
        }
        ImageView image_head = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.defaultGlid(mContext, item.get("head_pic"), image_head);
    }
}