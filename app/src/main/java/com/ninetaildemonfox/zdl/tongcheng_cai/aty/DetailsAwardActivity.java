package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.DetailsAwardadp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 17:38
 * 功能描述： 开奖  彩票详情
 * 联系方式：1037438704@qq.com
 */

public class DetailsAwardActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_analysis)
    TextView textAnalysis;
    @BindView(R.id.recyclerViewLottery)
    RecyclerView recyclerViewLottery;
    @BindView(R.id.recyclerViewInjection)
    RecyclerView recyclerViewInjection;
    private DetailsAwardadp detailsAwardadp;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_details_award;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        textCenter.setText("胜负彩详情");
        imageleftFinish.setVisibility(View.VISIBLE);
        textAnalysis.setVisibility(View.GONE);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(me));
        recyclerViewInjection.setLayoutManager(new LinearLayoutManager(me));
        detailsAwardadp = new DetailsAwardadp(R.layout.item_details_list_adp);

        recyclerViewLottery.setAdapter(detailsAwardadp);
        recyclerViewInjection.setAdapter(detailsAwardadp);
        detailsAwardadp.setNewData(list);

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

}