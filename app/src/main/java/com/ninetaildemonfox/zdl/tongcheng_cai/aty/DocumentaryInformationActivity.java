package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.DocumentaryinforAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.DocumentaryInformationPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DocumentaryInformationContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ArithmeticUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 11:02
 * 功能描述： 跟单信息
 * 联系方式：1037438704@qq.com
 */
public class DocumentaryInformationActivity extends BaseActivity
        implements DocumentaryInformationContract, MyContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_comment)
    TextView textComment;
    @BindView(R.id.text_pay)
    TextView textPay;
    @BindView(R.id.btnDecrease)
    Button btnDecrease;
    @BindView(R.id.btnIncrease)
    Button btnIncrease;
    @BindView(R.id.edit_count)
    EditText edit_count;
    @BindView(R.id.text_money)
    TextView text_money;
    @BindView(R.id.text_time)
    TextView text_time;
    @BindView(R.id.text_unpaid)
    TextView text_unpaid;
    @BindView(R.id.text_yue)
    TextView text_yue;
    @BindView(R.id.recyclerVIew)
    RecyclerView recyclerVIew;
    @BindView(R.id.ll_gone_one)
    LinearLayout ll_gone_one;
    @BindView(R.id.ll_gone_two)
    LinearLayout ll_gone_two;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_hit_percent)
    TextView textHitPercent;
    @BindView(R.id.text_avg_percent)
    TextView text_avg_percent;
    @BindView(R.id.text_people_number)
    TextView text_people_number;
    @BindView(R.id.text_lottery_info)
    TextView text_lottery_info;
    @BindView(R.id.text_last_date)
    TextView text_last_date;
    @BindView(R.id.text_up_money)
    TextView text_up_money;
    @BindView(R.id.text_self_buy_money)
    TextView text_self_buy_money;
    @BindView(R.id.text_start_buy_money)
    TextView text_start_buy_money;
    @BindView(R.id.text_pay_money)
    TextView text_pay_money;
    private int amount = 1;
    private String money;
    private int documentart;
    private DocumentaryinforAdp documentaryinforAdp;
    private DocumentaryInformationPresenter presenter;
    private String member_orders_send_id;
    private MyPresenter myPresenter;
    private String pay_money;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_documentary_information;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        myPresenter = new MyPresenter(me, this);
        presenter = new DocumentaryInformationPresenter(me, this);
        textCenter.setText("跟单信息");
        //1  首页推荐 - 》跟单界面   购买跟单界面
//        documentart = getIntent().getExtras().getInt("documentart");
        member_orders_send_id = getIntent().getExtras().getString("member_orders_send_id");
        presenter.getAgainOrderSendInfo(token, member_orders_send_id);

        textComment.setTextColor(me.getResources().getColor(R.color.colorAccent));
        textComment.setTypeface(Typeface.DEFAULT_BOLD);
        textComment.setBackgroundResource(R.color.whileColor);
        textComment.setText("跟单中");
        imageleftFinish.setVisibility(View.VISIBLE);
        textRight.setVisibility(View.VISIBLE);
        textRight.setText("跟单规则");
        text_time.setText("查看战绩 >");
        recyclerVIew.setLayoutManager(new LinearLayoutManager(me));
        documentaryinforAdp = new DocumentaryinforAdp(R.layout.item_dinformation_list_adp);
        recyclerVIew.setAdapter(documentaryinforAdp);

    }

    @Override
    protected void onResume() {
        super.onResume();
        WaitDialog.show(me, "");
        myPresenter.memberCenter(token);
    }

    @OnClick({R.id.image_left_finish, R.id.text_right, R.id.btnDecrease, R.id.btnIncrease, R.id.text_pay, R.id.text_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_right:
                bundle.putInt("type", 0);
                start(me, bundle, DocumentaryPlanningActivity.class);
                break;
            case R.id.btnDecrease:
                //减号
                if (amount == 1) {
                    return;
                }
                amount--;
                edit_count.setText(amount + "");
                BigDecimal mul = ArithmeticUtils.mul(pay_money, String.valueOf(amount));
                text_pay_money.setText("￥" + mul);
                break;
            case R.id.btnIncrease:
                //加号
                amount++;
                edit_count.setText(amount + "");
                BigDecimal mul1 = ArithmeticUtils.mul(pay_money, String.valueOf(amount));
                text_pay_money.setText("￥" + mul1);
                break;
            case R.id.text_pay:
                //1 进储值
                if (money == null || money.equals("0")) {
                    start(me, StoredValueActivity.class, 1);
                    return;
                }
                //跟单接口
                presenter.createAgainOrderSend(token, member_orders_send_id, order_type, String.valueOf(amount));
                //用户跟单
//                presenter.createAgainOrder(token, member_orders_send_id, String.valueOf(amount));
                break;
            case R.id.text_time:
                bundle.putString("member_orders_send_id", member_orders_send_id);
                start(me, bundle, RecordActivity.class);
                break;
            default:
        }
    }

    private String order_type;

    @Override
    public void getAgainOrderSendInfo(Map<String, String> data) {
        GlidUtils.circular(me, data.get("head_pic"), imageHead);
//        ——发起订单类型：football——足球  basketball——篮球
        order_type = data.get("order_type");
        text_name.setText(data.get("nick_name"));
//        text_time.setText(data.get("update_time"));
        textHitPercent.setText(data.get("hit_percent"));
        text_avg_percent.setText(data.get("avg_percent"));
        text_people_number.setText(data.get("people_number"));
        text_lottery_info.setText(data.get("lottery_info"));
        text_last_date.setText("截止时间：" + data.get("last_date"));
        text_up_money.setText(data.get("up_money") + "%");
        text_self_buy_money.setText("￥" + data.get("self_buy_money"));
        //起跟金额
        text_start_buy_money.setText("￥" + data.get("pay_money"));
        pay_money = data.get("pay_money");
        text_pay_money.setText("￥" + pay_money);
        // true ——是否是自己发起的，true——是  false——否
        data.get("is_myself");
        // true   ——当前用户是否跟单  1——已跟单  0——未跟单
        String is_again = data.get("is_again");
        if (is_again.equals("0")) {
            ll_gone_one.setVisibility(View.VISIBLE);
            ll_gone_two.setVisibility(View.GONE);
        } else {
            ll_gone_one.setVisibility(View.GONE);
            ll_gone_two.setVisibility(View.VISIBLE);
            ArrayList<Map<String, String>> choose_items = JSONUtils.parseKeyAndValueToMapList(data.get("choose_items"));
            if (choose_items != null || choose_items.size() != 0) {
                documentaryinforAdp.setNewData(choose_items);
            }
        }
    }

    @Override
    public void success(Map<String, String> data) {
        //这里获取余额
        text_money.setText(data.get("recharge_money"));
        money = data.get("recharge_money");
    }
}