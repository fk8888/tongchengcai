package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.WalletRulesAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.WalletRulesPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.WalletRulesContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MapBean;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author NineTailDemonFox
 * @date 2019/9/26 23:43
 * 功能描述： 钱包规则
 * 联系方式：1037438704@qq.com
 */
public class WalletRulesActivity extends BaseActivity
        implements WalletRulesContract, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private WalletRulesPresenter presenter;
    private WalletRulesAdp walletRulesAdp;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_wallet_rules;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        textCenter.setText("钱包规则");
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        walletRulesAdp = new WalletRulesAdp(R.layout.item_wallet_rules_adp);
        recyclerView.setAdapter(walletRulesAdp);
        presenter = new WalletRulesPresenter(me, this);
        presenter.getMoneyRuleList(token);
        walletRulesAdp.setOnItemClickListener(this);
    }

    @Override
    public void getMoneyRuleList(List<Map<String, String>> data) {
        if (data == null) {
            return;
        }
        walletRulesAdp.setNewData(data);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Map<String, String> map = walletRulesAdp.getData().get(position);
        if (map == null) {
            return;
        }
        bundle.putInt("type",4);
        MapBean mapBean = new MapBean(map);
        bundle.putSerializable("map",mapBean);
        start(me, bundle, DocumentaryPlanningActivity.class);

    }
}
