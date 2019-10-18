package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/5
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AccountDetailsAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public AccountDetailsAdp(int item_account_list_adp) {
        super(item_account_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_title, item.get("integral_source"))
                .setText(R.id.text_time, item.get("create_time"));
        TextView text_money = helper.itemView.findViewById(R.id.text_money);
        TextView text_status = helper.itemView.findViewById(R.id.text_status);
        String integral_type = item.get("integral_type");
        //sub-支出  add-收入
        if (integral_type.equals("add")) {
            text_money.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            text_money.setText("+" + item.get("integral_number"));
        } else {
            text_money.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_color));
            text_money.setText("-" + item.get("integral_number"));
        }
//        提现状态—— 0-全部 1-待处理 2-已发放 3-已拒绝
        String status = item.get("status");
        if (!TextUtils.isEmpty(status)) {
            if (status.equals("1")){
                text_status.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_color));
                text_status.setText("待处理");
            }else if (status.equals("2")){
                text_status.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_color));
                text_status.setText("已发放");
            }else if (status.equals("3")){
                text_status.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                text_status.setText("已拒绝");
            }
            text_status.setVisibility(View.VISIBLE);
        } else {
            text_status.setVisibility(View.GONE);
        }
    }
}