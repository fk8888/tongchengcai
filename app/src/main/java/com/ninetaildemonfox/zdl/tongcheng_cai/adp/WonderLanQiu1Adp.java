package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
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
 * 创建时间： 2019/9/9
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WonderLanQiu1Adp extends BaseQuickAdapter<String, BaseViewHolder> {

    private int wonderCount = 0;


    public void setWonderCount(int wonderCount) {
        this.wonderCount = wonderCount;
    }

    public WonderLanQiu1Adp(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public WonderLanQiu1Adp(int item_football_list_adp) {
        super(item_football_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView text_vs = helper.itemView.findViewById(R.id.text_vs);
        text_vs.setVisibility(View.INVISIBLE);

        View view_football_gone = helper.itemView.findViewById(R.id.view_football_gone);
        TextView text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);
        if (wonderCount == 0) {
            view_football_gone.setVisibility(View.GONE);
            text_football_left_red.setVisibility(View.GONE);
        } else {
            view_football_gone.setVisibility(View.VISIBLE);
            text_football_left_red.setVisibility(View.VISIBLE);
        }


        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        ColorAdp colorAdp2 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerView.setAdapter(colorAdp2);
//        colorAdp2.setNewData(list);
        colorAdp2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
    }
}
