package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
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

public class ColorAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public ColorAdp(int item_color_list_adp) {
        super(item_color_list_adp);
    }


    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        Log.d("convert", "=======================" + item.get("key"));
        TextView textContext = helper.itemView.findViewById(R.id.text_context);
        TextView text_is_push = helper.itemView.findViewById(R.id.text_is_push);
        textContext.setText(item.get("key") + item.get("value"));
        if (item.get("is_push").equals("true")) {
            text_is_push.setVisibility(View.VISIBLE);
            textContext.setBackgroundResource(R.drawable.shape_red_bg);
            textContext.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
        } else {
            text_is_push.setVisibility(View.GONE);
            textContext.setBackgroundResource(R.drawable.shape_gray_text_bg);
            textContext.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_666666));
        }
    }
}