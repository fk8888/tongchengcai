package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class Screen2Adp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public Screen2Adp(int item_screen_list_adp) {
        super(item_screen_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        TextView text_tag = helper.itemView.findViewById(R.id.text_tag);
        String stringName = "";

        if (!TextUtils.isEmpty(item.get("odds_value"))) {
            stringName = item.get("odds_value");
        }
        if (!TextUtils.isEmpty(item.get("type_name"))) {
            stringName = item.get("type_name");
        }



        text_tag.setText(stringName);


        if (item.get("type").equals("0")) {
            text_tag.setBackgroundResource(R.drawable.shape_drawable_black_bg);
            text_tag.setTextColor(ContextCompat.getColor(mContext, R.color.blackColor));
        } else {
            text_tag.setBackgroundResource(R.drawable.shape_drawable_login);
            text_tag.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
        }

    }
}