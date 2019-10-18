package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.BankCardManagementPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.BankCardManagementContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 15:12
 * 功能描述： 银行卡管理
 * 联系方式：1037438704@qq.com
 */

public class BankCardManagementActivity extends BaseActivity
        implements BankCardManagementContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_bank_type_name)
    TextView text_bank_type_name;
    @BindView(R.id.text_bank_card)
    TextView text_bank_card;
    private BankCardManagementPresenter presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bank_card_management;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("银行卡管理");
        textRight.setText("更换银行卡");
        textRight.setVisibility(View.VISIBLE);
        presenter = new BankCardManagementPresenter(me, this);
        presenter.addReceiveAddress(token);
    }

    @OnClick({R.id.image_left_finish, R.id.text_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_right:
                //更换绑定银行卡
                start(me, BankCardActivity.class);
                break;
            default:
        }
    }


    @Override
    public void findBankCard(Map<String, String> data) {
        text_bank_type_name.setText(data.get("bank_type_name"));
        text_bank_card.setText(data.get("format_bank_card"));
//        "id": "2",
//                "member_id": "7",
//                持卡人姓名—— "bank_name": "王大发",
//                开户行—— "bank_type": "2",     1-建设银行 2-中国银行 3-农业银行
//        "bank_type_name": "中国银行"  ——银行卡名称
//        银行卡号——"bank_card": "6228851061503701",
//                银行预留手机号——"bank_phone": "18222905301",
//                "create_time": "1566788830",
//                "update_time": "1566789124",
//                格式化的银行卡号——"format_bank_card": "6228**** ****3701",
//                格式化的手机号——"format_bank_phone": "182****5301",
//                格式化银行卡的后4位——"format_four_number": "3701",
//                可提现余额——"withdraw_money": "0.00"
//        "bank_type_path": "http://www.tongchengcai.com/Uploads/Index/2019-09-16/5d7eddf16e356.jpg"  ——银行卡图标
    }
}