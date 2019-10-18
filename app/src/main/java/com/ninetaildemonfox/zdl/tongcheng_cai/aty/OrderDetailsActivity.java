package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.BiddingOrderAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.TicketDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.OrderDetailsPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.OrderDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 17:09
 * 功能描述：  跟单的订单详情
 * 联系方式：1037438704@qq.com
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_order_type)
    TextView text_order_type;
    @BindView(R.id.text_status)
    TextView text_status;
    @BindView(R.id.text_nick_name)
    TextView text_nick_name;
    @BindView(R.id.text_order_id)
    TextView text_order_id;   //订单号
    @BindView(R.id.text_ordedr_tmoney)
    TextView text_ordedr_tmoney;   //投注金额
    @BindView(R.id.text_order_winmoney)
    TextView text_order_winmoney;   //获奖金额
    @BindView(R.id.text_order_yongjinmoney)
    TextView text_order_yongjinmoney;   //支付佣金
    @BindView(R.id.text_order_message)
    TextView text_order_message;   //订单信息
    @BindView(R.id.text_order_time)
    TextView text_order_time;   //订单创建时间
    @BindView(R.id.ll_fangan_one)
    LinearLayout ll_fangan_one;
    @BindView(R.id.ll_fangan_tow)
    LinearLayout ll_fangan_tow;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerViewTicketDetails)
    RecyclerView recyclerViewTicketDetails;
    private String member_orders_send_id;
    private OrderDetailsPresenter presenter;
    private String ordername;
    private BiddingOrderAdp biddingOrderAdp;
    private TicketDetailsAdp ticketDetailsAdp;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        textCenter.setText("订单详情");
        imageleftFinish.setVisibility(View.VISIBLE);
        member_orders_send_id = getIntent().getExtras().getString("stype");
        ordername = getIntent().getExtras().getString("ordername");
        Log.e("sss",member_orders_send_id);
        presenter = new OrderDetailsPresenter(me, this);
        presenter.getAgainOrderSendInfo(token, member_orders_send_id);

        //出票详情列表
        ticketDetailsAdp = new TicketDetailsAdp(R.layout.item_ticket_details_list_adp );
        recyclerViewTicketDetails.setAdapter(ticketDetailsAdp);
    }

    @OnClick({R.id.image_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            default:
        }
    }

    @Override
    public void getAgainOrderSendInfo(Map<String, String> data) {

        //公共部分信息
        text_nick_name.setText(data.get("nick_name"));
        //订单号
        text_order_id.setText(data.get("order_no"));
        //投注金额
        text_ordedr_tmoney.setText("¥ "+data.get("pay_money"));
        //获奖金额
        text_order_winmoney.setText("¥ "+data.get("win_money"));
        //支付yongjin
        text_order_yongjinmoney.setText("¥ "+data.get("commission_money"));
        //投注信息
        text_order_message.setText(data.get("lottery_info"));
        // 创建时间
        text_order_time.setText(data.get("create_time"));

       text_order_type.setText(ordername);
       String status = data.get("status");

        String play_flag = data.get("play_flag");

        if (play_flag == null){
            return;
        }
        //判断玩法
        if (play_flag.equals("lottery_c")||play_flag.equals("lottery_d")){
            ll_fangan_one.setVisibility(View.GONE);
            ll_fangan_tow.setVisibility(View.VISIBLE);
            biddingOrderAdp = new BiddingOrderAdp(R.layout.item_bidding_order_center_adp_two);

            recyclerView.setAdapter(biddingOrderAdp);
            //方案
            ArrayList<Map<String, String>> choose_items = JSONUtils.parseKeyAndValueToMapList(data.get("choose_items"));

            biddingOrderAdp.setNewData(choose_items);

            biddingOrderAdp.setStatus(status);
            biddingOrderAdp.setPlay_flag(play_flag);
        }else {
            ll_fangan_one.setVisibility(View.VISIBLE);
            ll_fangan_tow.setVisibility(View.GONE);
            //订单方案列表
            biddingOrderAdp = new BiddingOrderAdp(R.layout.item_bidding_order_center_adp);
            recyclerView.setAdapter(biddingOrderAdp);
            //方案
            ArrayList<Map<String, String>> choose_items = JSONUtils.parseKeyAndValueToMapList(data.get("choose_items"));
            biddingOrderAdp.setNewData(choose_items);
            biddingOrderAdp.setStatus(status);
            biddingOrderAdp.setPlay_flag(play_flag);
        }
        //——状态：1-待开奖 2-已中奖 3-未中奖
       if (status.equals("1")) {
           status = "待开奖";
            ll_fangan_one.setVisibility(View.GONE);
            ll_fangan_tow.setVisibility(View.GONE);
           recyclerViewTicketDetails.setVisibility(View.VISIBLE);
           recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
           //出票详情的列表
           ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
           ticketDetailsAdp.setNewData(lottery_details);
      } else if (status.equals("2")) {
           status = "已中奖";

           recyclerViewTicketDetails.setVisibility(View.VISIBLE);
           //出票详情的列表
           recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
           ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
           ticketDetailsAdp.setNewData(lottery_details);

       } else if (status.equals("3")) {
           status = "未中奖";

           //出票详情的列表
           recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
           ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
           ticketDetailsAdp.setNewData(lottery_details);
       }

      text_status.setText(status);
    }
}
