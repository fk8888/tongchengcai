package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 14:22
 * 功能描述：余额清账
 * 联系方式：1037438704@qq.com
 */
public class BalanceClearActivity extends BaseActivity implements MyContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.edit_money)
    EditText edit_money;
    @BindView(R.id.text_sure)
    TextView text_sure;
    @BindView(R.id.text_bank_card)
    TextView text_bank_card;
    @BindView(R.id.text_withdrawable)
    TextView text_withdrawable;
    @BindView(R.id.text_all_withdrawals)
    TextView text_all_withdrawals;
    @BindView(R.id.image_question)
    ImageView imageQuestion;
    private String cardId;
    private String sumMoney;
    private MyPresenter myPresenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_balance_clear;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("余额清账");
        imageQuestion.setVisibility(View.VISIBLE);
        imageQuestion.setImageResource(R.mipmap.icon_award_help);
        myPresenter = new MyPresenter(me, this);
        myPresenter.memberCenter(token);
    }

    @OnClick({R.id.image_left_finish, R.id.text_sure, R.id.text_bank_card, R.id.text_all_withdrawals, R.id.image_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_sure:
                myPresenter.addWithdrawLog(token, cardId, edit_money.getText().toString().trim());
                break;
            case R.id.text_bank_card:
                //请选择提现方式
                BaseActivity.start(me, DialogButtonActivity.class, 103);
                break;
            case R.id.text_all_withdrawals:
                if (sumMoney == null) {
                    return;
                }
                edit_money.setText(sumMoney);
                break;
            case R.id.image_question:
                start(me, WalletRulesActivity.class);
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            cardId = bundle.getString("id");
            String name = bundle.getString("name");
            if (TextUtils.isEmpty(name)) {
                return;
            }
            text_bank_card.setText(name);
        }
    }

    @Override
    public void success(Map<String, String> data) {
        //        可提现余额
        text_withdrawable.setText("可提现余额:" + data.get("withdraw_money"));
        sumMoney = data.get("withdraw_money");
    }
}