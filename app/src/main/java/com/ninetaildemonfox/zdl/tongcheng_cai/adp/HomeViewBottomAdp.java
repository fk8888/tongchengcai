package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/2
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class HomeViewBottomAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public HomeViewBottomAdp(int item_home_view_bottom) {
        super(item_home_view_bottom);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        ImageView imageHead = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.defaultGlid(mContext,item.get("store_img_path"),imageHead);
        helper.setText(R.id.text_title,item.get("store_name"))
                .setText(R.id.text_time,"营业时间："+item.get("store_hours"))
                .setText(R.id.text_loaction,"店铺地址："+item.get("store_address"));
    }
}