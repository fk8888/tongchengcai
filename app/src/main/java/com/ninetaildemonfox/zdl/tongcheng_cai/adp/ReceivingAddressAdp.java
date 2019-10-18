package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/7
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ReceivingAddressAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public ReceivingAddressAdp(int item_order_shop_adp) {
        super(item_order_shop_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        String pcar = item.get("receive_province") + "\t" + item.get("receive_city") + "\t" + item.get("receive_area") + "\t" + item.get("receive_address");
        String receive_phone = item.get("receive_phone");
        if (receive_phone.length() == 11) {
            receive_phone = receive_phone.substring(0, 3) + "****" + receive_phone.substring(7, 11);
        }
        helper.setText(R.id.text_name, item.get("receive_name"))
                .setText(R.id.text_phone, receive_phone)
                .setText(R.id.text_address, pcar)
                .addOnClickListener(R.id.text_flag)
                .addOnClickListener(R.id.text_delete)
                .addOnClickListener(R.id.text_edit);

        TextView text_flag = helper.itemView.findViewById(R.id.text_flag);
        Drawable drawable;
        if (item.get("flag").equals("2")) {
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_award_right);
        } else {
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.ic_circular_false);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        text_flag.setCompoundDrawables(drawable, null, null, null);
    }
}