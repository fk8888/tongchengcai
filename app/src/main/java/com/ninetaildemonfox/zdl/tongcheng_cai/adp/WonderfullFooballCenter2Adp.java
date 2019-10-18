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
 * 创建时间： 2019/9/8
 * 功能描述： 标题下面的item中的item
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WonderfullFooballCenter2Adp extends BaseQuickAdapter<WFootallBean.DataBeanXXX.ListBean, BaseViewHolder> {

    private int fooballCount;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data1;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data;
    private TextView text_football_left_red;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data2;
    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data5;

    public void getFooballCount(int fooballCount) {
        this.fooballCount = fooballCount;
    }


    public WonderfullFooballCenter2Adp(int item_football_list_adp) {
        super(item_football_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, final WFootallBean.DataBeanXXX.ListBean item) {
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

        //需要判断隐藏显示的
        text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);
        View view_football_gone = helper.itemView.findViewById(R.id.view_football_gone);
        if (fooballCount == 0) {
            text_football_left_red.setVisibility(View.GONE);
            view_football_gone.setVisibility(View.GONE);
        } else {
            text_football_left_red.setText(item.getPlay_value().getLet_victory().getFlag());
            if (item.getPlay_value().getLet_victory().getFlag() != null){
                Integer flag = Integer.parseInt(item.getPlay_value().getLet_victory().getFlag());
                if(flag>0){
                    text_football_left_red.setBackgroundResource(R.drawable.shape_text_bg_red);
                }else{
                    text_football_left_red.setBackgroundResource(R.drawable.shape_text_bg_green);
                }
                text_football_left_red.setVisibility(View.VISIBLE);
                view_football_gone.setVisibility(View.VISIBLE);
            }

        }
        final LinearLayout ll_analysis_gone = helper.itemView.findViewById(R.id.ll_analysis_gone);
        //分析
        if (item.getFenXi().equals("0")) {
            ll_analysis_gone.setVisibility(View.GONE);
        } else {
            ll_analysis_gone.setVisibility(View.VISIBLE);
        }


        //历史交锋
//        胜历史交锋——"history_victory_number": "1",
//                平历史交锋——"history_flat_number": "0",
//                负历史交锋——"history_lose_number": "0"
        String victoryFlatLose = "胜" + item.getHistory_victory_number() + "平" + item.getHistory_flat_number() + "负" + item.getHistory_lose_number();

        //历史战绩主队
        String hVictoryFlatLose = "胜" + item.getHome_victory_number() + "平" + item.getHome_flat_number() + "负" + item.getHome_lose_number();
        //历史战绩客队
        String gVictoryFlatLose = "胜" + item.getGuest_victory_number() + "平" + item.getGuest_flat_number() + "负" + item.getGuest_lose_number();
        //平均赔率
//        胜平均赔率 ——"avg_victory_odds": 1,
//                平平均赔率 ——"avg_flat_odds": 1,
//                负平均赔率 ——"avg_lose_odds": 1,
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


        if (fooballCount == 0){
            RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            final Color2Adp colorAdp = new Color2Adp(R.layout.item_color_list_adp);
            recyclerView.setAdapter(colorAdp);
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = item.getPlay_value();
            if (play_value == null) {
                return;
            }
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean victory = play_value.getVictory();
            if (victory == null) {
                return;
            }
            data = victory.getData();
            colorAdp.setNewData(data);

            colorAdp.setOnItemClickListener(new OnItemClickListener() {
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

        }else if (fooballCount == 1){
            RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            final Color4Adp colorAdp = new Color4Adp(R.layout.item_color_list_adp);
            recyclerView.setAdapter(colorAdp);
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = item.getPlay_value();
            if (play_value == null) {
                return;
            }
            WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean let_victory = play_value.getLet_victory();
            if (let_victory == null) {
                return;
            }
            data2 = let_victory.getData();
            colorAdp.setNewData(data2);
            colorAdp.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    data5 = colorAdp.getData();
                    if (data5.get(position).getTypeBoolean().equals("1")) {
                        data5.get(position).setTypeBoolean("0");
                    } else {
                        data5.get(position).setTypeBoolean("1");
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