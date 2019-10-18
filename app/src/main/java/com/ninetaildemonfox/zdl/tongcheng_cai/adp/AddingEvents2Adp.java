package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class AddingEvents2Adp extends BaseQuickAdapter<WFootallBean.DataBeanXXX, BaseViewHolder> {

    private String addingCount = "";
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data1;

    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEvents2Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX item) {
        //TextView dan = helper.itemView.findViewById(R.id.dan);
       // dan.setVisibility(View.GONE);
        //胜
        RecyclerView recyclerViewWin = helper.itemView.findViewById(R.id.recyclerViewWin);
        recyclerViewWin.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp3 = new Color3Adp(R.layout.item_score_item);
        recyclerViewWin.setAdapter(colorAdp3);
        for (int i = 0; i<item.getList().size();i++){
            data1 = item.getList().get(i).getPlay_value().getScore_value().get(i).getData();
            helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                    .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
        }

        colorAdp3.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
        recyclerViewWin.setAdapter(colorAdp3);
        colorAdp3.setNewData(data1);


        //平
        RecyclerView recyclerViewFlat = helper.itemView.findViewById(R.id.recyclerViewFlat);
        recyclerViewFlat.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp2 = new Color3Adp(R.layout.item_score_item);
        recyclerViewFlat.setAdapter(colorAdp2);
        for (int i = 0; i<item.getList().size();i++){
            data1 = item.getList().get(i).getPlay_value().getScore_value().get(i).getData();
            helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                    .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
        }

        colorAdp2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
        recyclerViewFlat.setAdapter(colorAdp2);
        colorAdp2.setNewData(data1);

        //负
        RecyclerView recyclerViewNegative = helper.itemView.findViewById(R.id.recyclerViewNegative);
        recyclerViewNegative.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp1 = new Color3Adp(R.layout.item_score_item);
        recyclerViewNegative.setAdapter(colorAdp1);
        for (int i = 0; i<item.getList().size();i++){
            data1 = item.getList().get(i).getPlay_value().getScore_value().get(i).getData();
            helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                    .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
        }

        recyclerViewNegative.setAdapter(colorAdp1);
        colorAdp1.setNewData(data1);
        colorAdp1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
      ;
    }
}
