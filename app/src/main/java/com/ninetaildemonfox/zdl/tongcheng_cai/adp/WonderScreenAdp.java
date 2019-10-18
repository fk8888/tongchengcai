package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WonderScreenAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {


    public WonderScreenAdp(int item_screen_list_adp) {
        super(item_screen_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        TextView text_tag = helper.itemView.findViewById(R.id.text_tag);
        text_tag.setText(item.get("name"));
        if (item.get("count").equals("1")) {
            text_tag.setBackgroundResource(R.drawable.shape_drawable_black_bg);
            text_tag.setTextColor(ContextCompat.getColor(mContext, R.color.blackColor));
        } else {
            text_tag.setBackgroundResource(R.drawable.button_red);
            text_tag.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
        }
    }
}