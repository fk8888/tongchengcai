package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.List;
import java.util.Map;

public class TicketDetailsItmeAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {


    public TicketDetailsItmeAdp(int layoutResId, @Nullable List<Map<String, String>> data) {
        super(layoutResId, data);
    }

    public TicketDetailsItmeAdp(int item_ticket_adp_top) {
        super(item_ticket_adp_top);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.wait_changci1, new StringBuffer("场次：").append(item.get("match")))
                .setText(R.id.wait_caiguo1, new StringBuffer("彩果：").append(item.get("result")));
    }
}
