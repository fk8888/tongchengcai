package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
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

public class Color4Adp extends BaseQuickAdapter<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX, BaseViewHolder> {

    public Color4Adp(int item_color_list_adp) {
        super(item_color_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX item) {
        TextView textContext = helper.itemView.findViewById(R.id.text_context);
        textContext.setText(item.getKey() + item.getValue());
        if (item.getTypeBoolean().equals("1")) {
            textContext.setBackgroundResource(R.drawable.shape_red_bg);
            textContext.setTextColor(ContextCompat.getColor(mContext, R.color.whileColor));
        } else {
            textContext.setBackgroundResource(R.drawable.shape_gray_text_bg);
            textContext.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_666666));
        }
    }

}