package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ScreenAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/8 17:05
 * 功能描述： 过关方式
 * 联系方式：1037438704@qq.com
 */
public class ModeBottomActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.text_cancel)
    TextView text_cancel;
    @BindView(R.id.text_sure)
    TextView text_sure;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mode_bottom;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        text_cancel.setText("取消");
        recyclerView.setLayoutManager(new GridLayoutManager(me, 4));
        ScreenAdp screenAdp = new ScreenAdp(R.layout.item_screen_list_adp);
        recyclerView.setAdapter(screenAdp);
        List<String> screenList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            screenList.add("");
        }
//        screenAdp.setNewData(list);
        screenAdp.setOnItemClickListener(this);
    }

    @OnClick({R.id.text_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_sure:
                finish();
                break;
            default:
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        finish();
    }
}