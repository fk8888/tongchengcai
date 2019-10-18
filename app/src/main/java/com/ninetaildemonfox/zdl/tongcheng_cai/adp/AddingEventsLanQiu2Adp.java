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

public class AddingEventsLanQiu2Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private String addingCount = "";


    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEventsLanQiu2Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.text_gallbladder);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            list.add("");
        }
        RecyclerView recyclerViewUp = helper.itemView.findViewById(R.id.recyclerViewUp);
        recyclerViewUp.setLayoutManager(new GridLayoutManager(mContext,3));
        ColorAdp colorAdp1 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewUp.setAdapter(colorAdp1);
//        colorAdp1.setNewData(list);
        colorAdp1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
        RecyclerView recyclerViewDow = helper.itemView.findViewById(R.id.recyclerViewDow);
        recyclerViewDow.setLayoutManager(new GridLayoutManager(mContext,3));
        ColorAdp colorAdp2 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewDow.setAdapter(colorAdp2);
//        colorAdp2.setNewData(list);
        colorAdp2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
    }
}
