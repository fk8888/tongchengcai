package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.BankCardPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 14:41
 * 功能描述： 绑定银行卡界面
 * 联系方式：1037438704@qq.com
 */
public class BankCardActivity extends BaseActivity {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_login)
    TextView text_login;
    @BindView(R.id.text_open_account)
    TextView text_open_account;
    @BindView(R.id.edit_name)
    EditText edit_name;
    @BindView(R.id.edit_card_number)
    EditText edit_card_number;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    private BankCardPresenter presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("绑定银行卡");
        presenter = new BankCardPresenter(me);
    }

    @OnClick({R.id.image_left_finish, R.id.text_login, R.id.text_open_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_login:
                presenter.bindBankCard(token, edit_name.getText().toString().trim()
                        , bank_type
                        , edit_card_number.getText().toString().trim()
                        , edit_phone.getText().toString().trim());
                break;
            case R.id.text_open_account:
                //开户行
                start(me, OpenAccountActivity.class, 102);
                break;
            default:
        }
    }

    private String bank_type, bank_name;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            bank_type = bundle.getString("bank_type");
            bank_name = bundle.getString("bank_name");
            text_open_account.setText(bank_name);
        }
    }
}