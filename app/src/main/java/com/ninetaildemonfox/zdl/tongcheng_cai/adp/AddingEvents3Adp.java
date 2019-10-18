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

public class AddingEvents3Adp extends BaseQuickAdapter<WFootallBean.DataBeanXXX, BaseViewHolder> {

    private String addingCount = "";
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> data1;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> data2;

    public void setAddingCount(String addingCount) {
        this.addingCount = addingCount;
    }

    public AddingEvents3Adp(int item_adding_events_list_adp) {
        super(item_adding_events_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX item) {
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        if (addingCount .equals("3")) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            final NumValueAdp colorAdp = new NumValueAdp(R.layout.item_score_item);
            recyclerView.setAdapter(colorAdp);
            for (int i = 0; i<item.getList().size();i++){
                data1 = item.getList().get(i).getPlay_value().getNumber_value();
                helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                        .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
            }

            colorAdp.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show("点击了");
                }
            });
            recyclerView.setAdapter(colorAdp);
            colorAdp.setNewData(data1);

            colorAdp.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show("点击了");
                }
            });
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            final HalfNumAdp colorAdp = new HalfNumAdp(R.layout.item_score_item);
            recyclerView.setAdapter(colorAdp);
            for (int i = 0; i<item.getList().size();i++){
                data2 = item.getList().get(i).getPlay_value().getHalf();
                helper.setText(R.id.text_guest_team_name,item.getList().get(i).getGuest_team_name())
                        .setText(R.id.text_home_team_name,item.getList().get(i).getHome_team_name());
            }

            colorAdp.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show("点击了");
                }
            });
            recyclerView.setAdapter(colorAdp);
            colorAdp.setNewData(data2);

            colorAdp.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show("点击了");
                }
            });
        }
    }
}
