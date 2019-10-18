package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

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

public class AddingEventsLanQiu1Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private String addingCount = "";


    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEventsLanQiu1Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView textVS = helper.itemView.findViewById(R.id.text_vs);
        textVS.setVisibility(View.INVISIBLE);

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        helper.addOnClickListener(R.id.text_gallbladder);
        TextView text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);
        if (addingCount.equals("0")) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            text_football_left_red.setVisibility(View.GONE);
        } else if (addingCount.equals("1")) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            text_football_left_red.setVisibility(View.VISIBLE);
        } else if (addingCount.equals("3")) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            text_football_left_red.setVisibility(View.GONE);
        }


        ColorAdp colorAdp = new ColorAdp(R.layout.item_color_list_adp);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        recyclerView.setAdapter(colorAdp);
//        colorAdp.setNewData(list);
    }
}
