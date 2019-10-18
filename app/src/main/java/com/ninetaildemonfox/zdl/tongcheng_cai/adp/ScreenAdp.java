package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
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

public class ScreenAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public ScreenAdp(int item_screen_list_adp) {
        super(item_screen_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
//        name   type
        TextView text_tag = helper.itemView.findViewById(R.id.text_tag);
        text_tag.setText(item.get("name"));
        if (item.get("type").equals("0")){
            text_tag.setBackgroundResource(R.drawable.shape_drawable_black_bg);
            text_tag.setTextColor(ContextCompat.getColor(mContext,R.color.blackColor));
        }else {
            text_tag.setBackgroundResource(R.drawable.shape_drawable_login);
            text_tag.setTextColor(ContextCompat.getColor(mContext,R.color.whileColor));
        }

    }
}