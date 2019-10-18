package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class BiddingOrderAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder>{


    public String status;
    public String play_flag;

    public String getPlay_flag() {
        return play_flag;
    }

    public void setPlay_flag(String play_flag) {
        this.play_flag = play_flag;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public BiddingOrderAdp(int item_bidding_order_center_adp) {
        super(item_bidding_order_center_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        //victory——胜平负 let_victory——让球胜平负 score——比分
        // number——总进球 half——半全场 mix——混合过关 single——单关 lottery_c——任九 lottery_d——胜负彩
        if (play_flag.equals("lottery_c")||play_flag.equals("lottery_d")){
            helper.setText(R.id.list_week_two, item.get("match_number"))
                    .setText(R.id.list_team_two,item.get("match_info"));
        }else{
            helper.setText(R.id.list_week, item.get("week"))
                    .setText(R.id.list_month, item.get("end_date"))
                    .setText(R.id.list_time, item.get("end_hour_minute"))
                    .setText(R.id.list_dan,item.get("dan"))
                    .setText(R.id.list_team1,item.get("home_team_name"))
                    .setText(R.id.list_team2,item.get("guest_team_name"));
        }

        TextView list_touzhu = helper.itemView.findViewById(R.id.list_touzhu);
        TextView list_caiguo = helper.itemView.findViewById(R.id.list_caiguo);
        RecyclerView reyclerView_caiguo = helper.itemView.findViewById(R.id.list_caiguo_recyclerview);
        RecyclerView reyclerView_touzhu = helper.itemView.findViewById(R.id.list_touzhu_recyclerview);

        //状态： 0-全部 1-待出票 2-待开奖 3-已开奖 4-已中奖 5-未中奖 6-已取消 8-跟单中
        if (status.equals("1")||status.equals("2")||status.equals("6")||status.equals("8")){
            list_caiguo.setVisibility(View.VISIBLE);
            reyclerView_caiguo.setVisibility(View.GONE);
            list_caiguo.setText("未开出");
        }else{
            //彩果
            reyclerView_caiguo.setLayoutManager(new LinearLayoutManager(mContext));
            MyCaiGuoZhuAdp myCaiGuoZhuAdp = new MyCaiGuoZhuAdp(R.layout.item_touzhu);
            ArrayList<Map<String, String>> system_item = JSONUtils.parseKeyAndValueToMapList(item.get("system_item"));
            myCaiGuoZhuAdp.setNewData(system_item);
            myCaiGuoZhuAdp.setStatus(status);

            reyclerView_caiguo.setAdapter(myCaiGuoZhuAdp);
        }

        //投注项
        reyclerView_touzhu.setLayoutManager(new LinearLayoutManager(mContext));
        MyCaiTouZhuAdp myCaiTouZhuAdp = new MyCaiTouZhuAdp(R.layout.item_touzhu);
        ArrayList<Map<String, String>> member_item = JSONUtils.parseKeyAndValueToMapList(item.get("member_item"));

        myCaiTouZhuAdp.setNewData(member_item);
        myCaiTouZhuAdp.setStatus(status);
        reyclerView_touzhu.setAdapter(myCaiTouZhuAdp);
    }

}