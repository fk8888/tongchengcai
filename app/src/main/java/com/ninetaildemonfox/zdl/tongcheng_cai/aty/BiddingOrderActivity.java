package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.BiddingOrderAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.TicketDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.GetOrderInfoPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.GetOrderInfoContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/6 15:08
 * 功能描述： 竞彩的订单详情
 * 联系方式：1037438704@qq.com
 */

public class BiddingOrderActivity extends BaseActivity implements GetOrderInfoContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerViewTicketDetails)
    RecyclerView recyclerViewTicketDetails;
    @BindView(R.id.text_ticket_details)
    TextView text_ticket_details;
    @BindView(R.id.text_no_time)
    TextView text_no_time;
    @BindView(R.id.text_after_is_issued)
    TextView text_after_is_issued;
    @BindView(R.id.fl_button_bottom)
    FrameLayout fl_button_bottom;
    @BindView(R.id.text_button)
    TextView text_button;
    @BindView(R.id.ll_bottom_documentary)
    LinearLayout ll_bottom_documentary;
    @BindView(R.id.ll_fangan_one)
    LinearLayout ll_fangan_one;
    @BindView(R.id.ll_fangan_tow)
    LinearLayout ll_fangan_tow;
    private BiddingOrderAdp biddingOrderAdp;
    private TicketDetailsAdp ticketDetailsAdp;
    private String stype;

    private GetOrderInfoPresenter presenter;
    private String order_type;
    private String status;
    private TextView orderinfoName;
    @BindView(R.id.orderinfo_status)
    TextView orderinfoStatus;
    private String order_id;
    private TextView orderinfoListnum;
    private TextView orderinfoTmoney;
    private TextView orderinfoGetmoney;
    private TextView orderinfoMessage;
    private TextView orderinfoTime;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bidding_order;
    }

    @Override
    protected void onInitialization(Bundle bundle) {

        initView();

        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        textCenter.setText("订单详情");
        imageleftFinish.setVisibility(View.VISIBLE);

        //出票详情列表
        ticketDetailsAdp = new TicketDetailsAdp(R.layout.item_ticket_details_list_adp );
        recyclerViewTicketDetails.setAdapter(ticketDetailsAdp);

        //点击事件示例
        /*ticketDetailsAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        ticketDetailsAdp.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.wait_message:

                        break;
                    default:
                }
            }
        });*/

    }

    private void initView() {
        //订单类型
        order_type = getIntent().getExtras().getString("order_type");
        //订单id
        stype = getIntent().getExtras().getString("stype");
        //订单状态
        status = getIntent().getExtras().getString("status");
        order_id = stype;

        orderinfoName = (TextView) findViewById(R.id.orderinfo_name);
        orderinfoListnum = (TextView) findViewById(R.id.orderinfo_listnum);
        orderinfoTmoney = (TextView) findViewById(R.id.orderinfo_tmoney);
        orderinfoGetmoney = (TextView) findViewById(R.id.orderinfo_getmoney);
        orderinfoMessage = (TextView) findViewById(R.id.orderinfo_message);
        orderinfoTime = (TextView) findViewById(R.id.orderinfo_time);

        presenter = new GetOrderInfoPresenter(me, this);
        presenter.getOrderInfo(token, order_id, order_type);

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

    /**
     * 彩票订单详情
     */
    @Override
    public void getOrder(Map<String, String> data) {
        //订单名称
        orderinfoName.setText(data.get("order_name"));
        //订单号
        orderinfoListnum.setText(data.get("order_no"));
        //投注金额
        orderinfoTmoney.setText("¥ "+data.get("order_money"));
        //获奖金额
        orderinfoGetmoney.setText("¥ "+data.get("win_money"));
        //投注信息
        orderinfoMessage.setText(data.get("lottery_info") + " " + data.get("lottery_note") + "注" + data.get("lottery_times") + "倍");
        //+"  lottery_note"+"lottery_times"
        // 创建时间
        orderinfoTime.setText(data.get("create_date"));


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

        //状态： 0-全部 1-待出票 2-待开奖 3-已开奖 4-已中奖 5-未中奖 6-已取消 8-跟单中
        if (this.status.equals("1")) {
            orderinfoStatus.setText("待出票");
            ll_bottom_documentary.setVisibility(View.GONE);
            //待出票撤单
            text_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.cancleList(token,order_id,order_type);
                }
            });
        } else if (this.status.equals("2")) {
            orderinfoStatus.setText("待开奖");
            ll_bottom_documentary.setVisibility(View.GONE);
            text_button.setText("发起跟单");
            text_button.setBackgroundResource(R.drawable.button_red);
            text_button.setTextColor(me.getResources().getColor(R.color.whileColor));
            text_no_time.setVisibility(View.GONE);
            text_after_is_issued.setVisibility(View.GONE);
            recyclerViewTicketDetails.setVisibility(View.VISIBLE);
            recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
            //出票详情的列表
            ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
            ticketDetailsAdp.setNewData(lottery_details);

        } else if (this.status.equals("3")) {
            orderinfoStatus.setText("已开奖");
        } else if (this.status.equals("4")) {
            orderinfoStatus.setText("已中奖");
            orderinfoStatus.setTextColor(me.getResources().getColor(R.color.colorAccent));
            ll_bottom_documentary.setVisibility(View.GONE);
            text_button.setVisibility(View.GONE);
            text_no_time.setVisibility(View.GONE);
            text_after_is_issued.setVisibility(View.GONE);
            recyclerViewTicketDetails.setVisibility(View.VISIBLE);
            orderinfoGetmoney.setTextColor(me.getResources().getColor(R.color.colorAccent));
            //出票详情的列表
            recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
            ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
            ticketDetailsAdp.setNewData(lottery_details);

        } else if (this.status.equals("5")) {
            orderinfoStatus.setText("未中奖");
            text_no_time.setVisibility(View.GONE);
            text_after_is_issued.setVisibility(View.GONE);
            ll_bottom_documentary.setVisibility(View.GONE);
            text_button.setVisibility(View.GONE);
            recyclerViewTicketDetails.setVisibility(View.VISIBLE);
            orderinfoGetmoney.setTextColor(me.getResources().getColor(R.color.colorAccent));
            //出票详情的列表
            recyclerViewTicketDetails.setLayoutManager(new LinearLayoutManager(me));
            ArrayList<Map<String, String>> lottery_details = JSONUtils.parseKeyAndValueToMapList(data.get("lottery_details"));
            ticketDetailsAdp.setNewData(lottery_details);

        } else if (this.status.equals("6")) {
            orderinfoStatus.setText("已取消");
            orderinfoStatus.setTextColor(me.getResources().getColor(R.color.text_gray_color));
            ll_bottom_documentary.setVisibility(View.GONE);
            text_button.setVisibility(View.GONE);
        } else if (this.status.equals("8")) {
            orderinfoStatus.setText("跟单中");
        }
    }

    //撤单
    @Override
    public void cancleList(Map<String, String> data) {
        ToastUtil.show(data.get("status"));
        presenter.getOrderInfo(token, order_id, order_type);
        ticketDetailsAdp.notifyDataSetChanged();
        ToastUtil.show("撤单成功");
    }
}