package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/6
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */
public class MyLotteryOrderAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    private int lotteryCount;

    public void setLotteryCount(int lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    public MyLotteryOrderAdp(int item_order_list_adp) {
        super(item_order_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        if (lotteryCount == 1) {
            caipiaolist(helper, item);
        } else if (lotteryCount == 2) {
            caipiao(helper, item);
        } else {
            IntegralLogList(helper, item);
        }
    }

    //商城订单
    private void IntegralLogList(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_title, item.get("integral_goods_name"))
                .setText(R.id.text_loaction, item.get("exchange_number"))
                .setText(R.id.text_right_type, "兑换商品")
                .addOnClickListener(R.id.text_button);

        AppCompatActivity me = (AppCompatActivity) mContext;
        //状态: 1-待发货 2-待收货 3-已确认
        String status = item.get("status");
        TextView textStype = helper.itemView.findViewById(R.id.text_stype);
        TextView textButton = helper.itemView.findViewById(R.id.text_button);
        ImageView imageHead = helper.itemView.findViewById(R.id.image_head);
        GlidUtils.defaultGlid(me, item.get("integral_img_path"), imageHead);
        if (status.equals("1")) {
            textStype.setText("待发货");
            textButton.setVisibility(View.GONE);
        } else if (status.equals("2")) {
            textStype.setText("待收货");
            textButton.setVisibility(View.VISIBLE);
        } else if (status.equals("3")) {
            textStype.setText("已确认");
            textButton.setVisibility(View.GONE);
        }

    }

    //跟单订单
    private void caipiao(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_win_money, "中奖金额：¥"+item.get("win_money"))
                .setText(R.id.text_buy_type, "购买方式：" + item.get("buy_type"))
                .setText(R.id.text_pay_money, "投注金额：¥" + item.get("pay_money"))
                .setText(R.id.text_lottery_info, "投注信息：" + item.get("lottery_info"))
                .setText(R.id.text_create_date, "支付时间：" + item.get("create_date"))
                .setText(R.id.text_right_type,item.get("order_name"))
        ;
        TextView text_button = helper.itemView.findViewById(R.id.text_button);
        TextView text_stype = helper.itemView.findViewById(R.id.text_stype);
      // 状态：0——全部 1-待开奖 2——已开奖 3——未中奖
        String status = item.get("status");

        if (status.equals("1")) {
            //待开奖
            text_stype.setText("待开奖");
            text_button.setVisibility(View.GONE);
        } else if (status.equals("2")) {
            //已中奖
            text_stype.setText("已中奖");
            text_button.setVisibility(View.GONE);
        } else if (status.equals("3")) {
            //未中奖
            text_stype.setText("未中奖");
            text_button.setVisibility(View.GONE);
        }
    }

    //彩票订单
    private void caipiaolist(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_win_money, "中奖金额：¥"+item.get("win_money"))
                .setText(R.id.text_buy_type, "购买方式：" + item.get("buy_type"))
                .setText(R.id.text_pay_money, "投注金额：¥" + item.get("order_money"))
                .setText(R.id.text_lottery_info, "投注信息：" + item.get("lottery_info"))
                .setText(R.id.text_create_date, "支付时间：" + item.get("create_time"))
                .setText(R.id.text_right_type,item.get("order_name"))
                .addOnClickListener(R.id.text_button)
        ;
        TextView text_button = helper.itemView.findViewById(R.id.text_button);
        TextView text_stype = helper.itemView.findViewById(R.id.text_stype);

//        状态： 0-全部 1-待出票 2-待开奖 3-已开奖 4-已中奖 5-未中奖 6-已取消 8-跟单中

        String status = item.get("status");
        if (status.equals("1")) {
            //待开奖
            text_stype.setText("待出票");
            text_stype.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
            text_button.setText("撤单");
            text_button.setVisibility(View.VISIBLE);
            text_button.setBackgroundResource(R.drawable.shape_red_text_bg);
            text_button.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));

            final String order_id = item.get("id");
            final String order_type = item.get("order_type");
            //条目撤单
            text_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        } else if (status.equals("2")) {
            text_button.setVisibility(View.GONE);
            //待开奖
            text_stype.setText("待开奖");
            text_stype.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
            text_button.setText("发起跟单");
            text_button.setVisibility(View.VISIBLE);
            text_button.setBackgroundResource(R.drawable.shape_red_bg);
            text_button.setTextColor(mContext.getResources().getColor(R.color.whileColor));
        } else if (status.equals("3")) {
            //以开奖
            text_stype.setText("已开奖");
            text_button.setVisibility(View.GONE);
        }else if (status.equals("4")){
            //已中奖
            text_stype.setText("已中奖");
            text_button.setVisibility(View.GONE);
        }else if  (status.equals("5")){
            //未中奖
            text_stype.setText("未中奖");
            text_button.setVisibility(View.GONE);
        }else if  (status.equals("6")){
            //已取消
            text_stype.setText("已取消");
            text_button.setVisibility(View.GONE);
            text_stype.setTextColor(mContext.getResources().getColor(R.color.text_gray_color));
        }else if (status.equals("8")){
            //跟单中
            text_stype.setText("跟单中");
            text_button.setVisibility(View.GONE);
        }
    }
}