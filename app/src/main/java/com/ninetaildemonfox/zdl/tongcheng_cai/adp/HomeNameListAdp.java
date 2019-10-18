package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/2
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class HomeNameListAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
    public HomeNameListAdp(int item_view_name_list) {
        super(item_view_name_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_nick_name,item.get("nick_name"))
                .setText(R.id.text_lottery_flag,item.get("lottery_flag"))
                .setText(R.id.text_win_money,item.get("win_money"));
    }
}