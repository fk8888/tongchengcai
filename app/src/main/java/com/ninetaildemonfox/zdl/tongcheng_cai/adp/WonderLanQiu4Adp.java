package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ScoreAllActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/9
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WonderLanQiu4Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private int wonderCount = 0;


    public void setWonderCount(int wonderCount) {
        this.wonderCount = wonderCount;
    }

    public WonderLanQiu4Adp(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public WonderLanQiu4Adp(int item_football_list_adp) {
        super(item_football_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView text_vs = helper.itemView.findViewById(R.id.text_vs);
        TextView text_full_depoyment = helper.itemView.findViewById(R.id.text_full_depoyment);
        text_vs.setVisibility(View.INVISIBLE);
        LinearLayout llGoneWhole = helper.itemView.findViewById(R.id.ll_gone_whole);
//        if (wonderCount == 5) {
//            llGoneWhole.setVisibility(View.VISIBLE);
//        } else {
//            llGoneWhole.setVisibility(View.GONE);
//        }

        RecyclerView recyclerViewWin = helper.itemView.findViewById(R.id.recyclerViewWin);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }
        recyclerViewWin.setLayoutManager(new GridLayoutManager(mContext, 2));

        ColorAdp colorAdp1 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewWin.setAdapter(colorAdp1);
//        colorAdp1.setNewData(list);

        colorAdp1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });


        RecyclerView recyclerViewFlat = helper.itemView.findViewById(R.id.recyclerViewFlat);
        recyclerViewFlat.setLayoutManager(new GridLayoutManager(mContext, 2));

        ColorAdp colorAdp2 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewFlat.setAdapter(colorAdp2);
//        colorAdp2.setNewData(list);

        colorAdp2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
        text_full_depoyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity me = (AppCompatActivity) mContext;
                Bundle bundle = new Bundle();
                bundle.putString("scoreview", "1");
                BaseActivity.start(me, bundle, ScoreAllActivity.class);
            }
        });

    }
}
