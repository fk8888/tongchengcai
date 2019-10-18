package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 11:26
 * 功能描述：支付成功界面   1 支付成功  2储值成功  3跟单成功   4清账成功  5购买成功
 * 联系方式：1037438704@qq.com
 */

public class SuccessPayActivity extends BaseActivity {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_question)
    ImageView imageQuestion;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_context)
    TextView text_context;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_success)
    TextView textSuccess;
    @BindView(R.id.text_title_success)
    TextView text_title_success;
    private int success;
    private String shopDetails;
    private String order_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_success_pay;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        success = getIntent().getExtras().getInt("success");
        shopDetails = getIntent().getExtras().getString("shopDetails");
        order_id = getIntent().getExtras().getString("order_id");

//        1 支付成功  2储值成功
        if (success == 1) {
            textCenter.setText("支付成功");
        } else if (success == 2) {
            textCenter.setText("储值成功");
            text_context.setText("现在可以用余额跟单啦~");
            textSuccess.setText("去跟单");
        } else if (success == 3) {
            textCenter.setText("跟单成功");
            text_context.setText("在个人中心跟单订单里查看详情");
            textSuccess.setText("订单详情");
        } else if (success == 4) {
            textCenter.setText("清账成功");
            text_context.setText("我们将在三个工作日内处理您的申请");
            textSuccess.setText("完成");
        } else if (success == 5) {
            textCenter.setText("购买成功");
            text_context.setText("等待出票，请耐心等待");
            textSuccess.setText("查看订单");
            text_title_success.setText("购买成功");
        }

        imageleftFinish.setVisibility(View.VISIBLE);
        imageQuestion.setVisibility(View.VISIBLE);
        imageQuestion.setImageResource(R.mipmap.icon_award_help);
    }

    @OnClick({R.id.image_left_finish, R.id.text_success, R.id.image_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.image_question:
                ToastUtil.show("未开通");
                break;
            case R.id.text_success:
                switch (success) {
                    case 1:
                        AppManager.getInstance().killActivity(RecommendationDetailsActivity.class);
                        start(me, RecommendationDetailsActivity.class);
                        finish();
                        break;
                    case 2:
                        finish();
                        break;
                    case 3:
                        //订单详情
//                        bundle.putString("order_id",order_id);
//                        start(me, bundle,OrderDetailsActivity.class);
                        finish();
                        break;
                    case 4:
                        //清账成功
                        finish();
                        break;
                    case 5:
                        //查看订单
                        if (shopDetails != null) {
                            if (shopDetails.equals("2") || shopDetails.equals("3")) {
                                bundle.putString("stype", "4");
                                start(me, bundle, BiddingOrderActivity.class);
                                return;
                            }
                        }
                        bundle.putString("stype", "0");
                        start(me, bundle, BiddingOrderActivity.class);
                        break;
                    default:
                }
                break;
            default:
        }
    }
}
