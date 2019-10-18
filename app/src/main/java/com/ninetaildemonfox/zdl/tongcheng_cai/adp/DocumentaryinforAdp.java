package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/4
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class DocumentaryinforAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {


    public DocumentaryinforAdp(int item_record_list_adp) {
        super(item_record_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.text_end_date, item.get("text_end_date") + item.get("end_hour_minute"))
                .setText(R.id.text_home_team_name, item.get("home_team_name"))
                .setText(R.id.text_guest_team_name, item.get("guest_team_name"))
                .setText(R.id.text_week, item.get("week"))
                .setText(R.id.text_dan, item.get("dan"));

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        Color1Adp colorAdp = new Color1Adp(R.layout.item_color_list_adp);
        recyclerView.setAdapter(colorAdp);
        ArrayList<Map<String, String>> member_item = JSONUtils.parseKeyAndValueToMapList(item.get("member_item"));
        colorAdp.setNewData(member_item);

    }
}