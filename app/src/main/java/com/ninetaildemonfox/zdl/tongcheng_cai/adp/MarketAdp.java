package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/2
 * 功能描述： 商场适配器
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MarketAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public MarketAdp(int item_view_name_list) {
        super(item_view_name_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        AppCompatActivity me = (AppCompatActivity) mContext;
        ImageView image_head = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.defaultGlid(me, item.get("integral_img_path"), image_head);
        helper.setText(R.id.text_context, item.get("integral_goods_name"))
                .setText(R.id.text_integral_number, item.get("integral_number"))
                .setText(R.id.text_exchange_number, "兑换量:" + item.get("exchange_number"));
    }
}
