package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.nozzie.AdpItemClick;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/9
 * 功能描述：  3总进球数 item   4半全场 item
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class TotalGoalsAdp extends BaseQuickAdapter<WFootallBean.DataBeanXXX.ListBean, BaseViewHolder> {

    private int totalCount;
    private int fooballCount;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void getFooballCount(int typeContext) {
        this.fooballCount = typeContext;
    }

    public TotalGoalsAdp(int item_total_goals) {
        super(item_total_goals);
    }



    @Override
    protected void convert(BaseViewHolder helper, final WFootallBean.DataBeanXXX.ListBean item) {

        //分析
        TextView text_analysis_onclick = helper.itemView.findViewById(R.id.text_analysis_onclick);
        text_analysis_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getFenXi().equals("0")) {
                    item.setFenXi("1");
                } else {
                    item.setFenXi("0");
                }
                notifyDataSetChanged();
            }
        });

        final LinearLayout ll_analysis_gone = helper.itemView.findViewById(R.id.ll_analysis_gone);
        //分析
        if (item.getFenXi().equals("0")) {
            ll_analysis_gone.setVisibility(View.GONE);
        } else {
            ll_analysis_gone.setVisibility(View.VISIBLE);
        }


        //历史交锋
//        胜历史交锋——"history_victory_number": "1",
//        平历史交锋——"history_flat_number": "0",
//        负历史交锋——"history_lose_number": "0"
        String victoryFlatLose = "胜" + item.getHistory_victory_number() + "平" + item.getHistory_flat_number() + "负" + item.getHistory_lose_number();

        //历史战绩主队
        String hVictoryFlatLose = "胜" + item.getHome_victory_number() + "平" + item.getHome_flat_number() + "负" + item.getHome_lose_number();
        //历史战绩客队
        String gVictoryFlatLose = "胜" + item.getGuest_victory_number() + "平" + item.getGuest_flat_number() + "负" + item.getGuest_lose_number();
        //平均赔率
//        胜平均赔率 ——"avg_victory_odds": 1,
//        平平均赔率 ——"avg_flat_odds": 1,
//        负平均赔率 ——"avg_lose_odds": 1,
        String avgVictoryFlatLose = "平均赔率      " + item.getAvg_victory_odds() + "\t\t\t" + item.getAvg_flat_odds() + "\t\t\t" + item.getAvg_lose_odds();
        //截止时间
        helper.setText(R.id.text_end_date, item.getMatchNumBerHour())
                //主队
                .setText(R.id.text_home_team_name, item.getHome_team_name())
                //客队
                .setText(R.id.text_guest_team_name, item.getGuest_team_name())
                //历史交锋
                .setText(R.id.text_victory_flat_lose, victoryFlatLose)
                //历史战绩 主
                .setText(R.id.texthVictoryFlatLose, hVictoryFlatLose)
                //历史战绩 客
                .setText(R.id.textgVictoryFlatLose, gVictoryFlatLose)
                //平均赔率
                .setText(R.id.textAvgVictoryFlatLose, avgVictoryFlatLose);

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);

        if (totalCount == 3) {   //总进球数
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            final NumValueAdp colorAdp = new NumValueAdp(R.layout.item_score_item);
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = item.getPlay_value();
            if (play_value == null){
                return ;
            }
            List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> number_value = play_value.getNumber_value();
            if (number_value == null || number_value.size() == 0){
                return;
            }
            recyclerView.setAdapter(colorAdp);
            for (int i = 0;i<number_value.size();i++){
                colorAdp.setNewData(number_value);
            }

            colorAdp.setOnItemClickListener(new OnItemClickListener() {

                private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> data1;

                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    data1 = colorAdp.getData();
                    if (data1.get(position).getTypeBoolean().equals("1")) {
                        data1.get(position).setTypeBoolean("0");
                    } else {
                        data1.get(position).setTypeBoolean("1");
                    }
                    colorAdp.notifyDataSetChanged();
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickApd(fooballCount,position);
                    }
                }
            });

        } else if(totalCount == 4){   //半全场
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            final HalfNumAdp colorAdp = new HalfNumAdp(R.layout.item_score_item);
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = item.getPlay_value();
            if (play_value == null){
                return ;
            }
            List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> number_value = play_value.getHalf();
            if (number_value == null){
                return;
            }
            recyclerView.setAdapter(colorAdp);
            for (int i = 0;i<number_value.size();i++){
                colorAdp.setNewData(number_value);
            }

            colorAdp.setOnItemClickListener(new OnItemClickListener() {

                private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> data1;

                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    data1 = colorAdp.getData();
                    if (data1.get(position).getTypeBoolean().equals("1")) {
                        data1.get(position).setTypeBoolean("0");
                    } else {
                        data1.get(position).setTypeBoolean("1");
                    }
                    colorAdp.notifyDataSetChanged();
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickApd(fooballCount,position);
                    }
                }
            });
        }
    }

    private AdpItemClick adpItemClick;

    public void setAdpItemClick(AdpItemClick adpItemClick) {
        this.adpItemClick = adpItemClick;
    }


}
