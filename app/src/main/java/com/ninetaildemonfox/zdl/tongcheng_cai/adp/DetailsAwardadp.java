package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/3
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class DetailsAwardadp extends BaseQuickAdapter<String,BaseViewHolder>{
    public DetailsAwardadp(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public DetailsAwardadp(int item_details_list_adp) {
        super(item_details_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
