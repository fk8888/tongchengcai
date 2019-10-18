package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

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

public class TheOutcomeRenJiuAdp extends BaseQuickAdapter<String,BaseViewHolder>{

    public TheOutcomeRenJiuAdp(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public TheOutcomeRenJiuAdp(int item_the_outcome_of_list_adp) {
        super(item_the_outcome_of_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        ColorAdp colorAdp = new ColorAdp(R.layout.item_color_list_adp);
        recyclerView.setAdapter(colorAdp);
//        colorAdp.setNewData(list);

    }
}
