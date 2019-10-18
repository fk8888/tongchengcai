package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class Color3Adp extends BaseQuickAdapter<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX, BaseViewHolder> {

    public Color3Adp(int item_color_list_adp) {
        super(item_color_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX item) {
        helper.setText(R.id.text_context_key,item.getKey())
                .setText(R.id.text_context_value,item.getValue());

        LinearLayout textContext = helper.itemView.findViewById(R.id.text_contextscore);
        TextView text_value = helper.itemView.findViewById(R.id.text_context_value);
        TextView text_key = helper.itemView.findViewById(R.id.text_context_key);
        if (item.getTypeBoolean().equals("1")) {
            textContext.setBackgroundResource(R.drawable.shape_red_bg);
            text_key.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
            text_value.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
        } else {
            textContext.setBackgroundResource(R.drawable.shape_gray_text_bg);
            text_key.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_666666));
            text_value.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_666666));
        }

    }

}