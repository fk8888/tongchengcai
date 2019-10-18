package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.PayNamePayBean;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/7/19
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class PayAdapter extends BaseQuickAdapter<PayNamePayBean, BaseViewHolder> {

    public PayAdapter(int list_dialog_pay) {
        super(list_dialog_pay);
    }

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayNamePayBean item) {
        TextView textView = helper.itemView.findViewById(R.id.text_pay);
        textView.setText(item.name);
        Drawable drawable2 = mContext.getResources().getDrawable(item.icon);
        if (count == 1) {
            textView.setTextColor(mContext.getResources().getColor(R.color.blackColor));
        }
        Drawable drawable = null;
        if (item.getChoice() == false) {
            drawable = mContext.getResources().getDrawable(R.mipmap.ic_circular_false);
        } else {
            drawable = mContext.getResources().getDrawable(R.mipmap.icon_award_right);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        textView.setCompoundDrawables(drawable2, null, drawable, null);
    }
}
