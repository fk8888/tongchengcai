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
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述： 标题下面的item中的item
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class WonderfullFooballCenterAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    private int fooballCount = 1;

    public void getFooballCount(int fooballCount) {
        this.fooballCount = fooballCount;
    }


    public WonderfullFooballCenterAdp(int item_football_list_adp) {
        super(item_football_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Map<String, String> item) {



        TextView text_analysis_onclick = helper.itemView.findViewById(R.id.text_analysis_onclick);



        text_analysis_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.get("fenxi").equals("0")) {
                    item.put("fenxi", "1");
                } else {
                    item.put("fenxi", "0");
                }
                notifyDataSetChanged();
            }
        });

        Map<String, String> play_value = JSONUtils.parseKeyAndValueToMap(item.get("play_value"));
        Map<String, String> victory = JSONUtils.parseKeyAndValueToMap(play_value.get("victory"));
        String flag = victory.get("flag");


        //需要判断隐藏显示的
        TextView text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);
        View view_football_gone = helper.itemView.findViewById(R.id.view_football_gone);
        if (fooballCount == 0) {
            text_football_left_red.setVisibility(View.GONE);
            view_football_gone.setVisibility(View.GONE);
        } else {
            text_football_left_red.setText(flag);
            text_football_left_red.setVisibility(View.VISIBLE);
            view_football_gone.setVisibility(View.VISIBLE);
        }
        final LinearLayout ll_analysis_gone = helper.itemView.findViewById(R.id.ll_analysis_gone);

        //分析
        if (item.get("fenxi").equals("0")) {
            ll_analysis_gone.setVisibility(View.GONE);
        } else {
            ll_analysis_gone.setVisibility(View.VISIBLE);
        }

        //历史交锋
//        胜历史交锋——"history_victory_number": "1",
//                平历史交锋——"history_flat_number": "0",
//                负历史交锋——"history_lose_number": "0"
        String victoryFlatLose = "胜" + item.get("history_victory_number") + "平" + item.get("history_flat_number") + "负" + item.get("history_lose_number");

        //历史战绩主队
        String hVictoryFlatLose = "胜" + item.get("home_victory_number") + "平" + item.get("home_flat_number") + "负" + item.get("home_lose_number");
        //历史战绩客队
        String gVictoryFlatLose = "胜" + item.get("guest_victory_number") + "平" + item.get("guest_flat_number") + "负" + item.get("guest_lose_number");
        //平均赔率
//        胜平均赔率 ——"avg_victory_odds": 1,
//                平平均赔率 ——"avg_flat_odds": 1,
//                负平均赔率 ——"avg_lose_odds": 1,
        String avgVictoryFlatLose = "平均赔率      " + item.get("avg_victory_odds") + "\t\t\t" + item.get("avg_flat_odds") + "\t\t\t" + item.get("avg_lose_odds");

        //截止时间
        helper.setText(R.id.text_end_date, item.get("match_type_name") + "\n" + item.get("match_number") + "\n" + item.get("end_hour_minute") + "截止")
                //主队
                .setText(R.id.text_home_team_name, item.get("home_team_name"))
                //客队
                .setText(R.id.text_guest_team_name, item.get("guest_team_name"))
                //历史交锋
                .setText(R.id.text_victory_flat_lose, victoryFlatLose)
                //历史战绩 主
                .setText(R.id.texthVictoryFlatLose, hVictoryFlatLose)
                //历史战绩 客
                .setText(R.id.textgVictoryFlatLose, gVictoryFlatLose)
                //平均赔率
                .setText(R.id.textAvgVictoryFlatLose, avgVictoryFlatLose)
        ;

        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        final Color2Adp colorAdp = new Color2Adp(R.layout.item_color_list_adp);
        recyclerView.setAdapter(colorAdp);
        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(victory.get("data"));
        for (int i = 0; i < data.size(); i++) {
            data.get(i).put("colorCount", "0");
        }

    }

    private AdpItemClick adpItemClick;

    public void setAdpItemClick(AdpItemClick adpItemClick) {
        this.adpItemClick = adpItemClick;
    }
}