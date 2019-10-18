package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/6
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */
public class TicketDetailsAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public TicketDetailsAdp(int item_ticket_details_list_adp) {
        super(item_ticket_details_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {


        helper.setText(R.id.wait_message,"投注信息：" + item.get("lottery_info"))
                .setText(R.id.wait_tmoney,"投注金额：¥" +item.get("order_money"))
                .setText(R.id.wait_getmoney,"中奖金额：¥" + item.get("win_money"))
                .setText(R.id.wait_num,"1")
        .addOnClickListener(R.id.wait_message);

        TextView viewById = helper.itemView.findViewById(R.id.wait_getmoney);
        if (item.get("win_money").equals("0.00")){
            viewById.setTextColor(mContext.getResources().getColor(R.color.color_text_02));
        }else{
            viewById.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        RecyclerView reyclerView = helper.itemView.findViewById(R.id.reyclerView);



        reyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        TicketDetailsItmeAdp ticketDetailsItmeAdp = new TicketDetailsItmeAdp(R.layout.item_ticket_adp_top);
        ArrayList<Map<String, String>> items = JSONUtils.parseKeyAndValueToMapList(item.get("items"));
        ticketDetailsItmeAdp.setNewData(items);
        reyclerView.setAdapter(ticketDetailsItmeAdp);
    }
}