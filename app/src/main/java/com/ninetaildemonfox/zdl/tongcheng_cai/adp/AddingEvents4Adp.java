package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AddingEvents4Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private String addingCount = "";


    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEvents4Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
