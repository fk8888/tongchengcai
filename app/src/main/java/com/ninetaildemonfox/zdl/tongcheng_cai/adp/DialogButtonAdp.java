package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * Created by dell-pc on 2019/1/4.
 */
public class DialogButtonAdp extends BaseQuickAdapter<String, BaseViewHolder> {

    public DialogButtonAdp(int item_kaihu_adp) {
        super(item_kaihu_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iamge_bank_pic = helper.itemView.findViewById(R.id.iamge_bank_pic);
//        GlideUtils.GlideDefault(mContext, item.get("bank_pic"), iamge_bank_pic);
//        if (item.get("card_number") != null || !item.get("card_number").equals("") || !item.get("card_number").equals("null")){
//            String card_number = item.get("card_number");
//            String substring = card_number.substring(card_number.length() - 4, card_number.length());
//            helper.setText(R.id.text_bank_name, item.get("bank_name") + "(" + substring + ")");
//        }
//        ImageView image_gone = helper.itemView.findViewById(R.id.image_gone);
//        if (item.get("boole").equals("1")) {
//            image_gone.setVisibility(View.GONE);
//        } else {
//            image_gone.setVisibility(View.VISIBLE);
//        }

    }
}
