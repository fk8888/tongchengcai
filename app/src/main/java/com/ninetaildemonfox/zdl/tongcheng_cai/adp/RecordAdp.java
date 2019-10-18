package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class RecordAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public RecordAdp(int item_record_list_adp) {
        super(item_record_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        String is_again = item.get("is_again");
        if (is_again.equals("0")) {
            is_again = "未跟单";
        } else if (is_again.equals("1")) {
            is_again = "已跟单";
        }
        helper.setText(R.id.text_lottery_info, item.get("lottery_info"))
                .setText(R.id.text_is_again, is_again)
                .setText(R.id.text_last_date, "截止时间：" + item.get("last_date"))
                .setText(R.id.text_self_buy_money,item.get("self_buy_money"))
        .setText(R.id.text_again_all_money,item.get("again_all_money"))
        .setText(R.id.text_people_number,item.get("people_number"))
        .setText(R.id.text_max_sp,item.get("max_sp"))
        ;
    }
}