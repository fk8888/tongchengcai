package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ShoppingMallDetailsPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.SubmitExchangePresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ShoppingMallDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.SubmitExchangeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 15:11
 * 功能描述： 提交兑换
 * 联系方式：1037438704@qq.com
 */

public class SubmitExchangeActivity extends BaseActivity implements SubmitExchangeContract, ShoppingMallDetailsContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_add_laction)
    TextView text_add_laction;
    @BindView(R.id.text_name_phone)
    TextView text_name_phone;
    @BindView(R.id.text_receive_address)
    TextView text_receive_address;
    @BindView(R.id.text_title)
    TextView text_title;
    @BindView(R.id.text_sure)
    TextView text_sure;
    @BindView(R.id.text_loaction)
    TextView text_loaction;
    @BindView(R.id.text_order_no)
    TextView text_order_no;
    @BindView(R.id.text_create_time)
    TextView text_create_time;
    @BindView(R.id.text_redeem_now)
    TextView text_redeem_now;
    @BindView(R.id.text_jifen)
    TextView text_jifen;
    @BindView(R.id.text_delivery_time)
    TextView text_delivery_time;
    @BindView(R.id.text_confirm_time)
    TextView text_confirm_time;
    @BindView(R.id.rl_address_view)
    RelativeLayout rl_address_view;
    @BindView(R.id.rl_sure_gone)
    RelativeLayout rl_sure_gone;
    @BindView(R.id.ll_gone_information)
    LinearLayout ll_gone_information;
    @BindView(R.id.ll_buttom_gone)
    LinearLayout ll_buttom_gone;
    private String type;


    private String receive_name, receive_phone, receive_province, receive_city, receive_area, receive_address;
    private ShoppingMallDetailsPresenter shoppingMallDetailsPresenter;
    private SubmitExchangePresenter presenter;
    private String integral_goods_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_submit_exchange;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        integral_goods_id = getIntent().getExtras().getString("integral_goods_id");
        type = getIntent().getExtras().getString("type");
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("提交兑换");
        presenter = new SubmitExchangePresenter(me, this);
        shoppingMallDetailsPresenter = new ShoppingMallDetailsPresenter(me, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type.equals("1")) {
            ll_gone_information.setVisibility(View.GONE);
            ll_buttom_gone.setVisibility(View.VISIBLE);
            shoppingMallDetailsPresenter.getIntegralGoodsInfo(integral_goods_id);
            presenter.findDefaultAddress(token);
            rl_sure_gone.setVisibility(View.GONE);
        } else {
            ll_gone_information.setVisibility(View.VISIBLE);
            ll_buttom_gone.setVisibility(View.GONE);
            presenter.getExchangeIntegralLogInfo(token, integral_goods_id);
        }
    }

    @OnClick({R.id.image_left_finish, R.id.text_redeem_now, R.id.text_add_laction, R.id.text_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_redeem_now:
                presenter.exchangeIntegralGoods(token, integral_goods_id
                        , text_loaction.getText().toString().trim(), receive_name,
                        receive_phone, receive_province,
                        receive_city, receive_area, receive_address);
                break;
            case R.id.text_add_laction:
                start(me, ReceivingAddressActivity.class);
                break;
            case R.id.text_sure:
                presenter.confirmIntegralOrder(token, integral_goods_id);
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {
        if (data == null) {
            text_add_laction.setVisibility(View.VISIBLE);
            rl_address_view.setVisibility(View.GONE);
        } else {
            rl_address_view.setVisibility(View.VISIBLE);
            text_add_laction.setVisibility(View.GONE);
            receive_name = data.get("receive_name");
            receive_phone = data.get("receive_phone");
            receive_province = data.get("receive_province");
            receive_city = data.get("receive_city");
            receive_area = data.get("receive_area");
            receive_address = data.get("receive_address");

            text_name_phone.setText(receive_name + "\t" + receive_phone);
            String address = data.get("receive_province") + data.get("receive_city") + data.get("receive_area") + data.get("receive_address");
            text_receive_address.setText(address);
        }
    }

    @Override
    public void getExchangeIntegralLogInfo(Map<String, String> data) {
        GlidUtils.defaultGlid(me, data.get("integral_img_path"), imageHead);
        text_title.setText(data.get("integral_goods_name"));
        text_loaction.setText(data.get("integral_number"));
        text_order_no.setText("订单编号：" + data.get("order_no"));
        text_create_time.setText("兑换时间：" + data.get("create_time"));
//        "status": "1",   状态: 1-待发货 2-待收货 3-已确认
        String status = data.get("status");
        if (status.equals("1")) {
            rl_sure_gone.setVisibility(View.GONE);
        } else if (status.equals("2")) {
            rl_sure_gone.setVisibility(View.VISIBLE);
        } else if (status.equals("3")) {
            rl_sure_gone.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(data.get("delivery_time"))) {
            text_delivery_time.setVisibility(View.GONE);
        } else {
            text_delivery_time.setText("发货时间：" + data.get("delivery_time"));
            text_delivery_time.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(data.get("confirm_time"))) {
            text_confirm_time.setVisibility(View.GONE);
        } else {
            text_confirm_time.setText("成交时间：" + data.get("confirm_time"));
            text_confirm_time.setVisibility(View.VISIBLE);
        }

        receive_name = data.get("receive_name");
        receive_phone = data.get("receive_phone");
        receive_province = data.get("receive_province");
        receive_city = data.get("receive_city");
        receive_area = data.get("receive_area");
        receive_address = data.get("receive_address");

        text_name_phone.setText(receive_name + "\t" + receive_phone);
        String address = data.get("receive_province") + data.get("receive_city") + data.get("receive_area") + data.get("receive_address");
        text_receive_address.setText(address);

    }

    @Override
    public void shopSuccess(Map<String, String> data) {
        GlidUtils.defaultGlid(me, data.get("integral_img_path"), imageHead);
        text_title.setText(data.get("integral_goods_name"));
        text_loaction.setText(data.get("integral_number"));
        text_jifen.setText(data.get("integral_number"));
    }
}