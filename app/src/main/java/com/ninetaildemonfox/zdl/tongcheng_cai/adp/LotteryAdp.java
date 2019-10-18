package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/3
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class LotteryAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
    private int count = 1;

    public void setCount(int count) {
        this.count = count;
    }

    public LotteryAdp(int item_lottery_list_adp) {
        super(item_lottery_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.addOnClickListener(R.id.text_analysis);
        if (count == 1) {
            Immediate(helper, item);
        } else {
            lottery(helper, item);
        }
    }

    private void Immediate(BaseViewHolder helper, Map<String, String> item) {

        helper
                .setText(R.id.text_guest_team_img_path, item.get("guest_team_name"))
                .setText(R.id.text_home_team_img_path, item.get("home_team_name"))
                .setText(R.id.text_match_info, item.get("match_info"));
        TextView text_start_status = helper.itemView.findViewById(R.id.text_start_status);
        TextView text_shangbanchang = helper.itemView.findViewById(R.id.text_shangbanchang);
        TextView text_vs = helper.itemView.findViewById(R.id.text_vs);
        ImageView image_zhu = helper.itemView.findViewById(R.id.image_zhu);
        ImageView image_ke = helper.itemView.findViewById(R.id.image_ke);
        GlidUtils.defaultGlid(mContext, item.get("home_team_img_path"), image_zhu);
        GlidUtils.defaultGlid(mContext, item.get("guest_team_img_path"), image_ke);
//        类型 1——即时  2——完场  （移动传的数据我只是加到列表里了）
        String type = item.get("type");
        if (type.equals("1")) {
            text_start_status.setText(item.get("start_status"));
            text_start_status.setVisibility(View.VISIBLE);
            text_shangbanchang.setVisibility(View.GONE);
            text_vs.setText("VS");
            text_vs.setTextColor(ContextCompat.getColor(mContext, R.color.text_gray_color));
        } else {
            text_shangbanchang.setVisibility(View.VISIBLE);
            text_start_status.setVisibility(View.GONE);
            Map<String, String> complete_info = JSONUtils.parseKeyAndValueToMap(item.get("complete_info"));
            if (complete_info == null) {
                text_vs.setText("0:0");
                text_vs.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                text_shangbanchang.setText("上半场0:0");
            } else {
                text_vs.setText(complete_info.get("score_all_one") + ":" + complete_info.get("score_all_two"));
                text_vs.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                text_shangbanchang.setText("上半场" + complete_info.get("score_half_one") + ":" + complete_info.get("score_half_two"));
            }
        }
    }

    private void lottery(BaseViewHolder helper, Map<String, String> item) {
        String type = item.get("type");
        if (type.equals("1")) {
            type = "胜负彩";
        } else if (type.equals("2")) {
            type = "进球彩";
        } else if (type.equals("3")) {
            type = "半全场";
        }

        helper.setText(R.id.text_type, type)
                .setText(R.id.text_start_date, item.get("start_date") + "第" + item.get("match_period") + "期")
                .setText(R.id.text_msg, item.get("msg"));
    }
}