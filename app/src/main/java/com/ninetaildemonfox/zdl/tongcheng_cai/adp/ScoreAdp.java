package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.nozzie.AdpItemClick;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ScoreAllActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/9
 * 功能描述：店铺详情比分item
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ScoreAdp extends BaseQuickAdapter<WFootallBean.DataBeanXXX.ListBean, BaseViewHolder> {

    private int fooballCount;

    public ScoreAdp(int item_score_list_adp) {
        super(item_score_list_adp);
    }

    public void getFooballCount(int typeContext) {
        this.fooballCount = typeContext;
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

        final String zhudui = item.getHome_team_name();
        final String guest_team_name = item.getGuest_team_name();
        TextView text_expand_all = helper.itemView.findViewById(R.id.text_expand_all);
        text_expand_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //展开界面
                AppCompatActivity me = (AppCompatActivity) mContext;
                Bundle bundle = new Bundle();
                bundle.putString("scoreview", fooballCount+"");
                bundle.putString("zhudui",zhudui);
                bundle.putString("kedui",guest_team_name);
                BaseActivity.start(me, bundle, ScoreAllActivity.class);
            }
        });
        //胜

        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = item.getPlay_value();
        if (play_value == null){
            return;
        }
        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean> score_value = play_value.getScore_value();
        if(score_value == null || score_value.size() == 0){
            return;
        }

        for (int i = 0; i < score_value.size(); i++) {
            switch (i){
                case 0:
                    //胜
                    sheng(helper,score_value.get(i));
                    break;
                case 1:
                    //平
                    ping(helper,score_value.get(i));
                    break;
                case 2:
                    //负
                    fu(helper,score_value.get(i));
                    break;
                    default:
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
    }

    private void fu(BaseViewHolder helper, WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean scoreValueBean) {
        RecyclerView recyclerViewNegative = helper.itemView.findViewById(R.id.recyclerViewNegative);
        recyclerViewNegative.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp3 = new Color3Adp(R.layout.item_score_item);
        recyclerViewNegative.setAdapter(colorAdp3);

        if (scoreValueBean != null){
            if (scoreValueBean.getData() != null){
                helper.setText(R.id.text_flag_fu,scoreValueBean.getFlag());
                colorAdp3.setNewData( scoreValueBean.getData());
                colorAdp3.setOnItemClickListener(new OnItemClickListener() {

                    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data;

                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //ToastUtil.show("点击了");
                        data = colorAdp3.getData();
                        if (data.get(position).getTypeBoolean().equals("1")) {
                            data.get(position).setTypeBoolean("0");
                        } else {
                            data.get(position).setTypeBoolean("1");
                        }
                        colorAdp3.notifyDataSetChanged();
                        if (adpItemClick != null) {
                            adpItemClick.onClickItemClickApd(fooballCount,position);
                        }
                    }
                });
            }
        }
    }

    private void ping(BaseViewHolder helper, WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean scoreValueBean) {
        RecyclerView recyclerViewFlat = helper.itemView.findViewById(R.id.recyclerViewFlat);
        recyclerViewFlat.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp2 = new Color3Adp(R.layout.item_score_item);
        recyclerViewFlat.setAdapter(colorAdp2);

        if (scoreValueBean != null){
            if (scoreValueBean.getData() != null){
                helper.setText(R.id.text_flag_ping,scoreValueBean.getFlag());
                colorAdp2.setNewData( scoreValueBean.getData());
                colorAdp2.setOnItemClickListener(new OnItemClickListener() {

                    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data1;

                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //ToastUtil.show("点击了");
                        data1 = colorAdp2.getData();
                        if (data1.get(position).getTypeBoolean().equals("1")) {
                            data1.get(position).setTypeBoolean("0");
                        } else {
                            data1.get(position).setTypeBoolean("1");
                        }
                        colorAdp2.notifyDataSetChanged();
                        if (adpItemClick != null) {
                            adpItemClick.onClickItemClickApd(fooballCount,position);
                        }
                    }
                });
            }
        }
    }

    private void sheng(BaseViewHolder helper, WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean scoreValueBean) {

        RecyclerView recyclerViewWin = helper.itemView.findViewById(R.id.recyclerViewWin);
        recyclerViewWin.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color3Adp colorAdp = new Color3Adp(R.layout.item_score_item);
        recyclerViewWin.setAdapter(colorAdp);
        if (scoreValueBean != null){
            if (scoreValueBean.getData() != null){
                helper.setText(R.id.text_flag_win,scoreValueBean.getFlag());
                colorAdp.setNewData(scoreValueBean.getData());
                colorAdp.setOnItemClickListener(new OnItemClickListener() {

                    private List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data2;

                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //ToastUtil.show("点击了");
                        data2 = colorAdp.getData();
                        if (data2.get(position).getTypeBoolean().equals("1")) {
                            data2.get(position).setTypeBoolean("0");
                        } else {
                            data2.get(position).setTypeBoolean("1");
                        }
                        colorAdp.notifyDataSetChanged();
                        if (adpItemClick != null) {
                            adpItemClick.onClickItemClickApd(fooballCount,position);
                        }
                    }
                });
            }
        }
    }

    private AdpItemClick adpItemClick;

    public void setAdpItemClick(AdpItemClick adpItemClick) {
        this.adpItemClick = adpItemClick;
    }

}
