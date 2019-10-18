package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AddingEventsLanQiu3Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private String addingCount = "";


    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEventsLanQiu3Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView text_negative = helper.itemView.findViewById(R.id.text_negative);
        text_negative.setText("其他");
//胜

        RecyclerView recyclerViewWin = helper.itemView.findViewById(R.id.recyclerViewWin);
        recyclerViewWin.setLayoutManager(new GridLayoutManager(mContext, 3));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        ColorAdp colorAdp = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewWin.setAdapter(colorAdp);
//        colorAdp.setNewData(list);
        colorAdp.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //平
        RecyclerView recyclerViewFlat = helper.itemView.findViewById(R.id.recyclerViewFlat);
        recyclerViewFlat.setLayoutManager(new GridLayoutManager(mContext, 3));
        ColorAdp colorAdp2 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewFlat.setAdapter(colorAdp2);
//        colorAdp2.setNewData(list);
        colorAdp2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //负
        RecyclerView recyclerViewNegative = helper.itemView.findViewById(R.id.recyclerViewNegative);
        recyclerViewNegative.setLayoutManager(new GridLayoutManager(mContext, 3));
        ColorAdp colorAdp3 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewNegative.setAdapter(colorAdp3);
//        colorAdp3.setNewData(list);
        colorAdp3.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

    }
}
