package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/5
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class OpenAccountAdp extends BaseQuickAdapter<Map<String,String>, BaseViewHolder> {

    public OpenAccountAdp(int item_open_account_adp) {
        super(item_open_account_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        ImageView iamge_bank_pic = helper.itemView.findViewById(R.id.iamge_bank_pic);
        GlidUtils.circular(mContext,item.get("bank_img_path"),iamge_bank_pic);
        helper.setText(R.id.text_bank_name,item.get("bank_name"));

        ImageView image_gone = helper.itemView.findViewById(R.id.image_gone);

        if (item.get("count").equals("1")){
            image_gone.setImageResource(R.mipmap.icon_award_right);
        }else {
            image_gone.setImageResource(R.mipmap.ic_circular_false);
        }
    }
}
