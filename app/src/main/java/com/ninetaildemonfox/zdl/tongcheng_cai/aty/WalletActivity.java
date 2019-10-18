package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 10:06
 * 功能描述： 钱包
 * 联系方式：1037438704@qq.com
 */

public class WalletActivity extends BaseActivity implements MyContract {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_stored_value)
    TextView textStoredValue;
    @BindView(R.id.image_question)
    ImageView image_question;
    @BindView(R.id.text_ledger_details)
    TextView text_ledger_details;
    @BindView(R.id.text_details_of_withdrawal)
    TextView text_details_of_withdrawal;
    @BindView(R.id.text_clear_value)
    TextView text_clear_value;
    @BindView(R.id.text_authentication)
    TextView text_authentication;
    @BindView(R.id.text_renzheng)
    TextView text_renzheng;
    @BindView(R.id.textRechargeMoney)
    TextView textRechargeMoney;
    @BindView(R.id.textWithdrawMoney)
    TextView textWithdrawMoney;
    private MyPresenter myPresenter;
    private String is_cert;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("钱包");
        image_question.setVisibility(View.VISIBLE);
        image_question.setImageResource(R.mipmap.icon_award_help);
        myPresenter = new MyPresenter(me, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.memberCenter(token);
    }

    @OnClick({R.id.image_left_finish, R.id.image_question, R.id.text_stored_value
            , R.id.text_ledger_details, R.id.text_details_of_withdrawal, R.id.text_clear_value
            , R.id.text_renzheng, R.id.text_authentication})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_stored_value:
                //储值
                start(me, StoredValueActivity.class);
                break;
            case R.id.image_question:
                //说明
                start(me, WalletRulesActivity.class);
                break;
            case R.id.text_ledger_details:
                //账本明细
                bundle.putString("count", "1");
                start(me, bundle, DetailedActivity.class);
                break;
            case R.id.text_details_of_withdrawal:
                //提现明细
                bundle.putString("count", "2");
                start(me, bundle, DetailedActivity.class);
                break;
            case R.id.text_clear_value:
                //清空余额
                start(me, bundle, BalanceClearActivity.class);
                break;
            case R.id.text_authentication:
                //银行卡管理
                start(me, bundle, BankCardManagementActivity.class);
                break;
            case R.id.text_renzheng:
                //认证界面
                start(me, AuthenticationActivity.class);
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {
        //—是否实名认证， 1——未认证   2——已认证
        is_cert = data.get("is_cert");
        if (is_cert.equals("1")) {
            start(me, AuthenticationActivity.class);
            return;
        }
//        账户余额
        textRechargeMoney.setText("￥" + data.get("recharge_money"));
//        可提现余额
        textWithdrawMoney.setText("￥" + data.get("withdraw_money"));
//                账号—— "account": "18222905308",
//                积分——"integral": "2",
//                "expired_time": "1568622801",
//                邀请码—— "invite_code": "823916",
//                "p_id": "4",
//                真是姓名—— "real_name": "隔壁老樊",
//                昵称——"nick_name": "娃哈哈",
//                "sex": "女",
//                头像——"head_pic": "http://www.tongchengcai.com/Uploads/Member/2019-08-24/5d609631243ad.png",
//                "id_card": "123456789012345678",
//                "id_card_up": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5de8af.png",
//                "ic_card_down": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5df467.png",
//                账户余额——"recharge_money": "954.00",
//                可提现余额—— "withdraw_money": "50.00",
//                "status": "1",
//                "is_cert": "2",
//                "create_time": "1566436932",
//                "update_time": "1568018001",
//                点赞总数——"click_total_number": 1,
//                论坛发帖总数——"post_total_number": 1,
//                是否这是密码—— "is_password": 2  1——未设置  2——已设置

    }
}
