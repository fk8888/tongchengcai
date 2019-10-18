package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.nozzie.AdpItemClick;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.nozzie.AdpItemClickUp;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：  这个适配器用来显示哪个item
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */
public class WonderfulFootballAdp extends BaseQuickAdapter<WFootallBean.DataBeanXXX, BaseViewHolder> {

    private int typeContext;
    private int shopDetails;
    /*private List<WFootallBean.DataBeanXXX.ListBean> holderList;
    private WonderfullFooballCenter2Adp wonderfullFooballCenterAdp;
    private  text_football_left_red;*/

    public void setShopDetails(int shopDetails) {
        this.shopDetails = shopDetails;
    }

    public void getTypeContext(int typeContext) {
        this.typeContext = typeContext;
    }


    public WonderfulFootballAdp(int item_football_list_adp) {
        super(item_football_list_adp);
    }

    @Override
    protected void convert(BaseViewHolder helper, WFootallBean.DataBeanXXX item) {

        TextView text_football_left_red = helper.itemView.findViewById(R.id.text_football_left_red);

        //一级列表的显示和隐藏
        helper.addOnClickListener(R.id.text_title_bag).setText(R.id.text_title_bag, item.getSum());
        TextView text_title_bag = helper.itemView.findViewById(R.id.text_title_bag);
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //一级列表的显示和隐藏
        helper.addOnClickListener(R.id.text_title_bag);
        Drawable drawable;
        if (item.getTypeCount().equals("0")) {
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_analysis_down_ash);
            recyclerView.setVisibility(View.GONE);
        } else {
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_analysis_up_ash);
            recyclerView.setVisibility(View.VISIBLE);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        text_title_bag.setCompoundDrawables(null, null, drawable, null);

        if (shopDetails == 0) {
            zuqiu(recyclerView, item);
        } else if (shopDetails == 1) {
//            lanqiu(recyclerView, item);
        } else if (shopDetails == 2) {
//            shengfucai(recyclerView, item);
        } else if (shopDetails == 3) {
//            renjiu(recyclerView, item);
        }
    }


    /**
     * 任九
     */
    private void renjiu(RecyclerView recyclerView, Map<String, String> item) {
        //胜负彩
        TheOutcomeOfColorAdp theOutcomeOfColorAdp = new TheOutcomeOfColorAdp(R.layout.item_outcome_of_list_adp);
        recyclerView.setAdapter(theOutcomeOfColorAdp);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        theOutcomeOfColorAdp.setNewData(list);
    }

    /**
     * 胜负彩
     */
    private void shengfucai(RecyclerView recyclerView, Map<String, String> item) {
        //胜负彩
        TheOutcomeOfColorAdp theOutcomeOfColorAdp = new TheOutcomeOfColorAdp(R.layout.item_outcome_of_list_adp);
        recyclerView.setAdapter(theOutcomeOfColorAdp);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        theOutcomeOfColorAdp.setNewData(list);

    }

    /**
     * 篮球
     */
    private void lanqiu(RecyclerView recyclerView, Map<String, String> item) {
        if (typeContext == 0) {
            //胜负
            WonderLanQiu1Adp wonderLanQiu1Adp = new WonderLanQiu1Adp(R.layout.item_lanqiu_list_adp);
            recyclerView.setAdapter(wonderLanQiu1Adp);
            wonderLanQiu1Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu1Adp.setNewData(list);
        } else if (typeContext == 1) {
            //让分胜负
            WonderLanQiu1Adp wonderLanQiu1Adp = new WonderLanQiu1Adp(R.layout.item_lanqiu_list_adp);
            recyclerView.setAdapter(wonderLanQiu1Adp);
            wonderLanQiu1Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu1Adp.setNewData(list);
        } else if (typeContext == 2) {
            //分数差
            WonderLanQiu2Adp wonderLanQiu2Adp = new WonderLanQiu2Adp(R.layout.item_lanqiu_list_adp_2);
            recyclerView.setAdapter(wonderLanQiu2Adp);
            wonderLanQiu2Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu2Adp.setNewData(list);

        } else if (typeContext == 3) {
            //大小分
            WonderLanQiu3Adp wonderLanQiu3Adp = new WonderLanQiu3Adp(R.layout.item_lanqiu_list_adp_3);
            recyclerView.setAdapter(wonderLanQiu3Adp);
            wonderLanQiu3Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu3Adp.setNewData(list);

        } else if (typeContext == 4) {
            //分数差
            WonderLanQiu4Adp wonderLanQiu4Adp = new WonderLanQiu4Adp(R.layout.item_mixed_customs_clearance);
            recyclerView.setAdapter(wonderLanQiu4Adp);
            wonderLanQiu4Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu4Adp.setNewData(list);


        } else if (typeContext == 5) {
            //竞蓝单关
            WonderLanQiu4Adp wonderLanQiu4Adp = new WonderLanQiu4Adp(R.layout.item_mixed_customs_clearance);
            recyclerView.setAdapter(wonderLanQiu4Adp);
            wonderLanQiu4Adp.setWonderCount(typeContext);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            wonderLanQiu4Adp.setNewData(list);
        }
    }

    /**
     * 足球
     */
    private void zuqiu(RecyclerView recyclerView, WFootallBean.DataBeanXXX item) {
        Log.d("zdasfasfasfa","===================="+item.toString());
        List<WFootallBean.DataBeanXXX.ListBean> holderList = item.getList();
        if (typeContext == 0) {
            //胜平负
            WonderfullFooballCenter2Adp  wonderfullFooballCenterAdp = new WonderfullFooballCenter2Adp(R.layout.item_football_list_adp);
            recyclerView.setAdapter(wonderfullFooballCenterAdp);
            wonderfullFooballCenterAdp.getFooballCount(typeContext);
            wonderfullFooballCenterAdp.setNewData(holderList);
            wonderfullFooballCenterAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });

        } else if (typeContext == 1) {
            //让球胜平负
            WonderfullFooballCenter2Adp  wonderfullFooballCenterAdp = new WonderfullFooballCenter2Adp(R.layout.item_football_list_adp);
            recyclerView.setAdapter(wonderfullFooballCenterAdp);
            wonderfullFooballCenterAdp.getFooballCount(typeContext);
            wonderfullFooballCenterAdp.setNewData(holderList);
            wonderfullFooballCenterAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });
        } else if (typeContext == 2) {
            //比分
            ScoreAdp scoreAdp = new ScoreAdp(R.layout.item_score_list_adp);
            recyclerView.setAdapter(scoreAdp);
            scoreAdp.getFooballCount(typeContext);
            scoreAdp.setNewData(holderList);

            scoreAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });
        } else if (typeContext == 3) {
            //总进球数
            TotalGoalsAdp totalGoalsAdp = new TotalGoalsAdp(R.layout.item_total_goals);
            totalGoalsAdp.setTotalCount(typeContext);
            recyclerView.setAdapter(totalGoalsAdp);
            totalGoalsAdp.setNewData(holderList);
            totalGoalsAdp.getFooballCount(typeContext);

            totalGoalsAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });
        } else if (typeContext == 4) {
            //半全场
            //总进球数
            TotalGoalsAdp totalGoalsAdp = new TotalGoalsAdp(R.layout.item_total_goals);
            totalGoalsAdp.setTotalCount(typeContext);
            recyclerView.setAdapter(totalGoalsAdp);
            totalGoalsAdp.setNewData(holderList);
            totalGoalsAdp.getFooballCount(typeContext);

            totalGoalsAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });

        } else if (typeContext == 5) {
            //混合过关
            MixedCustomsAdp mixedCustomsAdp = new MixedCustomsAdp(R.layout.item_mixed_customs_clearance);
            mixedCustomsAdp.setMixedContext(typeContext);
            recyclerView.setAdapter(mixedCustomsAdp);
            mixedCustomsAdp.setNewData(holderList);
            mixedCustomsAdp.getFooballCount(typeContext);

            mixedCustomsAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });

        } else if (typeContext == 6) {
            //竞足单关
            MixedCustomsAdp mixedCustomsAdp = new MixedCustomsAdp(R.layout.item_mixed_customs_clearance);
            mixedCustomsAdp.setMixedContext(typeContext);
            recyclerView.setAdapter(mixedCustomsAdp);
            mixedCustomsAdp.setNewData(holderList);
            mixedCustomsAdp.getFooballCount(typeContext);

            mixedCustomsAdp.setAdpItemClick(new AdpItemClick() {
                @Override
                public void onClickItemClickApd(int shopDetails,int position) {
                    if (adpItemClick != null) {
                        adpItemClick.onClickItemClickUpApd(shopDetails,position);
                    }
                }
            });
        }
    }



    private AdpItemClickUp adpItemClick;

    public void setAdpItemClick(AdpItemClickUp adpItemClick) {
        this.adpItemClick = adpItemClick;
    }

}