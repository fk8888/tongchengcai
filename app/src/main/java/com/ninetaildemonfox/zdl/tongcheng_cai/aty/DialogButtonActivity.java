package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.DialogButtonAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.BankCardManagementPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.BankCardManagementContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author NineTailDemonFox
 * @date 2019/9/5 14:30
 * 功能描述： 银行卡弹框
 * 联系方式：1037438704@qq.com
 */
public class DialogButtonActivity extends BaseActivity
        implements BankCardManagementContract {

    @BindView(R.id.iamge_bank_pic)
    ImageView iamge_bank_pic;
    @BindView(R.id.image_gone)
    ImageView image_gone;
    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    @BindView(R.id.textViewNew)
    TextView textViewNew;
    @BindView(R.id.text_bank_name)
    TextView text_bank_name;
    //    DialogButtonAdp dialogButtonAdp;
    private BankCardManagementPresenter presenter;
    private String id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_dialog_button;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(me));
//        dialogButtonAdp = new DialogButtonAdp(R.layout.item_open_account_adp);
//        recyclerView.setAdapter(dialogButtonAdp);
//        dialogButtonAdp.setNewData(list);
//        dialogButtonAdp.setOnItemClickListener(this);
        presenter = new BankCardManagementPresenter(me, this);
        presenter.addReceiveAddress(token);
    }


    @OnClick({R.id.textViewNew, R.id.text_bank_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textViewNew:
                //使用新卡提现
                start(me, BankCardActivity.class);
                break;
            case R.id.text_bank_name:
                if (id == null) {
                    return;
                }
                image_gone.setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                Intent i = new Intent();
                bundle.putString("id", id);
                bundle.putString("name", text_bank_name.getText().toString().trim());
                i.putExtras(bundle);
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
        }
    }

    @Override
    public void findBankCard(Map<String, String> data) {
        text_bank_name.setText(data.get("bank_type_name"));
        GlidUtils.defaultGlid(me, data.get("bank_type_path"), iamge_bank_pic);
        id = data.get("id");
//        text_bank_card.setText(data.get("format_bank_card"));
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
