package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.PayAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.StoredValuePresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.PayNamePayBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 15:59
 * 功能描述： 储值
 * 联系方式：1037438704@qq.com
 */
public class StoredValueActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemClickListener, MyContract {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_question)
    ImageView image_question;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_recharge)
    TextView textRecharge;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edit_money)
    EditText edit_money;
    @BindView(R.id.text_recharge_money)
    TextView text_recharge_money;
    private List<PayNamePayBean> list;
    private PayAdapter payAdapter;
    private StoredValuePresenter presenter;
    private int pay_type;
    private MyPresenter myPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_stored_value;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        image_question.setVisibility(View.VISIBLE);
        image_question.setImageResource(R.mipmap.icon_award_help);
        myPresenter = new MyPresenter(me, this);
        WaitDialog.show(me, "");
        myPresenter.memberCenter(token);
        presenter = new StoredValuePresenter(me);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("账户储值");
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        payAdapter = new PayAdapter(R.layout.item_pay_list_adp);
        list = new ArrayList<>();
        recyclerView.setAdapter(payAdapter);
        list.add(new PayNamePayBean("微信支付", false, R.mipmap.icon_pay_weixin));
        list.add(new PayNamePayBean("支付宝支付", false, R.mipmap.icon_pay_zhifubao));
        payAdapter.setNewData(list);
        payAdapter.setOnItemClickListener(this);
    }

    @OnClick({R.id.image_left_finish, R.id.text_recharge, R.id.image_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_recharge:
                if (edit_money.getText().toString().trim() == null) {
                    ToastUtil.show("充值金额不能为空");
                    return;
                }
                presenter.addRechargeLog(token, edit_money.getText().toString().trim(), pay_type);
                break;

            case R.id.image_question:
                start(me, WalletRulesActivity.class);
                break;
            default:
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<PayNamePayBean> data = payAdapter.getData();
        PayNamePayBean payNamePayBean = data.get(position);
        if (payNamePayBean.getChoice() == true) {
            payNamePayBean.setChoice(false);
        } else {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setChoice(false);
            }
            payNamePayBean.setChoice(true);
        }
        pay_type = position + 1;
        payAdapter.notifyDataSetChanged();
    }

    @Override
    public void success(Map<String, String> data) {
        text_recharge_money.setText("账户余额：" + data.get("recharge_money"));
    }
}
