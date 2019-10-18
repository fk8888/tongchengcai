package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.ShopDetailsBean;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ShopDetailsAdp extends BaseQuickAdapter<ShopDetailsBean, BaseViewHolder> {

    public ShopDetailsAdp(int item_select_address_list_adp) {
        super(item_select_address_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopDetailsBean item) {
        TextView text_details = helper.itemView.findViewById(R.id.text_details);

        text_details.setText(item.getName());

        Drawable drawable = ContextCompat.getDrawable(mContext, item.getIcon());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        text_details.setCompoundDrawables(null, drawable, null, null);

    }


}