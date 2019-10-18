package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/3
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WinningListAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
    public WinningListAdp(int item_winning_list_adp) {
        super(item_winning_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_nick_name, item.get("nick_name"))
                .setText(R.id.text_lottery_flag, item.get("lottery_flag"))
                .setText(R.id.text_win_money, item.get("win_money"))
                .setText(R.id.text_create_time, item.get("create_time"));
    }

}