package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/7
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class SelectAddressAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public SelectAddressAdp(int item_order_shop_adp) {
        super(item_order_shop_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_city, item.get("region_name"));
    }

}