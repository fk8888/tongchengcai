package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AddingEventsAdp extends BaseQuickAdapter<WFootallBean.DataBeanXXX, BaseViewHolder> {

    private String addingCount;
    private int count = 0;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data1;

    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEventsAdp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX item) {
        helper.addOnClickListener(R.id.text_gallbladder);
        TextView text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);
        TextView num = helper.itemView.findViewById(R.id.num);
        if (addingCount.equals("1")) {
            text_football_left_red.setVisibility(View.VISIBLE);
        } else {
            text_football_left_red.setVisibility(View.GONE);
        }

        if (addingCount.equals("let_victory")){
            for (int i = 0; i<item.getList().size();i++){
                data = item.getList().get(i).getPlay_value().getLet_victory().getData();
                helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                        .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
            }
            RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            Color4Adp colorAdp = new Color4Adp(R.layout.item_color_list_adp);
            recyclerView.setAdapter(colorAdp);
            colorAdp.setNewData(data);
        }else if (addingCount.equals("victory")){
            for (int i = 0; i<item.getList().size();i++){
                data1 = item.getList().get(i).getPlay_value().getVictory().getData();
                helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                        .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
            }

            RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            final Color2Adp colorAdp = new Color2Adp(R.layout.item_color_list_adp);
            recyclerView.setAdapter(colorAdp);
            colorAdp.setNewData(data1);
        }
    }
}
