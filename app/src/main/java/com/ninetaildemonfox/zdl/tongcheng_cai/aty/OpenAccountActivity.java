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
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.OpenAccountAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.OpenAccountPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.OpenAccountContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/5 14:50
 * 功能描述：开户行
 * 联系方式：1037438704@qq.com
 */
public class OpenAccountActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemClickListener, OpenAccountContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private OpenAccountAdp openAccountAdp;
    private OpenAccountPresenter presenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_open_account;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        presenter = new OpenAccountPresenter(me, this);
        presenter.getBankList();
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("开户行");
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        openAccountAdp = new OpenAccountAdp(R.layout.item_open_account_adp);
        recyclerView.setAdapter(openAccountAdp);
        openAccountAdp.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<Map<String, String>> data = openAccountAdp.getData();
        Map<String, String> map = data.get(position);
        map.put("count", "1");
        openAccountAdp.notifyItemChanged(position);
//        "id": "2",
//                "bank_name": "农业银行",
//                "bank_img": "58",
//                "bank_img_path": "http://www.tongchengcai.com/Uploads/Index/2019-09-16/5d7eddf16e356.jpg"
        Bundle bundle = new Bundle();
        Intent i = new Intent();
        bundle.putString("bank_type", map.get("id"));
        bundle.putString("bank_name", map.get("bank_name"));
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick({R.id.image_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            default:
        }
    }

    @Override
    public void getBankList(List<Map<String, String>> data) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).put("count", "0");
        }
        openAccountAdp.setNewData(data);
    }


}
