package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.WonderScreenAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.WonderfulFootballAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.nozzie.AdpItemClickUp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.WFootallPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.WFootallContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ButtonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/8 11:47
 *  * 功能描述： 0竞彩足球  1竟彩篮球  2 胜负彩  3 任选九
 *  * 联系方式：1037438704@qq.com
 */
public class WonderfulFootballActivity extends BaseActivity
        implements BaseQuickAdapter.OnItemChildClickListener, WFootallContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recycerViewTop)
    RecyclerView recycerViewTop;
    @BindView(R.id.fl_gone)
    FrameLayout flGone;
    @BindView(R.id.games_numbers)
    TextView gamesnumber;

    private WonderfulFootballAdp wonderfulFootballAdp;
    private WonderScreenAdp screenAdp;
    private List<Map<String, String>> list;
    private int screen = 1;
    private String addTag = "0";
    //判断进的哪个界面  0竞彩足球  1竟彩篮球  2 胜负彩  3 任选九
    private int shopDetails = 0;
    //这个参数用来传给筛选
    private String play_flag = "";
    private WFootallPresenter presenter;
    private String match_type_id = "", odds_id = "";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_wonderful_football;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        wonderfulFootballAdp.setOnItemChildClickListener(this);
    }

    private void initData() {
     // play_flag
        http();
    }

    private void http() {
        WaitDialog.show(me,"");
        if (shopDetails == 0) {  //竞彩足球
            presenter.getFootballPlayList(match_type_id, play_flag, odds_id);
        } else if (shopDetails == 1) {   //竞彩篮球
            presenter.getFootballPlayList(match_type_id, play_flag, odds_id);
        } else {   //任九 和 胜负彩
            presenter.getFootballPlayList(match_type_id, play_flag, odds_id);
        }
    }

    private Drawable drawable;

    private void initView() {

        presenter = new WFootallPresenter(me, this);
        shopDetails = getIntent().getExtras().getInt("shopDetails");
        drawable = me.getResources().getDrawable(R.mipmap.icon_choose_down_white);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textCenter.setCompoundDrawables(null, null, drawable, null);

        textRight.setText("筛选");
        imageleftFinish.setVisibility(View.VISIBLE);
        textRight.setVisibility(View.VISIBLE);

        recycerViewTop.setLayoutManager(new GridLayoutManager(me, 4));
        screenAdp = new WonderScreenAdp(R.layout.item_screen_list_adp);
        recycerViewTop.setAdapter(screenAdp);
        //界面顶部的列表   screenAdp
        wonderfulFootballAdp = new WonderfulFootballAdp(R.layout.item_football_top_adp);
        wonderfulFootballAdp.setShopDetails(shopDetails);
        wonderfulFootballAdp.getTypeContext(0);
        recyclerView.setAdapter(wonderfulFootballAdp);


        screen();

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(me));

        apdClickShaiXuan();

        Map<String, String> listMap1 = new ArrayMap<>();
        listMap1.put("count", "1");
        list.add(listMap1);

        Map<String, String> listMap2 = new ArrayMap<>();
        listMap2.put("count", "1");
        list.add(listMap2);


    }

    private void apdClickShaiXuan() {
        wonderfulFootballAdp.setAdpItemClick(new AdpItemClickUp() {
            @Override
            public void onClickItemClickUpApd(int ashopDetails, int poasition) {
                //添加/编辑赛事  这里要传一个 tag 来判断到底显示哪个界面
                int s =  ButtonView.textSure(shopDetails, play_flag, wonderfulFootballAdp);
                gamesnumber.setText("已选择"+s+"场赛事");
            }
        });
    }

    /**
     * 数据源
     */
    private void screen() {
        final List<Map<String, String>> screenList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        if (shopDetails == 0) {
            play_flag = "victory";
            textCenter.setText("胜平负");
            map.put("name", "胜平负");
            map.put("play_flag", "victory");
            map.put("count", "2");
            screenList.add(map);
            Map<String, String> map2 = new HashMap<>();
            map2.put("name", "让球胜平负");
            map2.put("count", "1");
            map2.put("play_flag", "let_victory");
            screenList.add(map2);
            Map<String, String> map3 = new HashMap<>();
            map3.put("name", "比分");
            map3.put("count", "1");
            map3.put("play_flag", "score");
            screenList.add(map3);
            Map<String, String> map4 = new HashMap<>();
            map4.put("name", "总进球数");
            map4.put("count", "1");
            map4.put("play_flag", "number");
            screenList.add(map4);
            Map<String, String> map5 = new HashMap<>();
            map5.put("name", "半全场");
            map5.put("count", "1");
            map5.put("play_flag", "half");
            screenList.add(map5);
            Map<String, String> map6 = new HashMap<>();
            map6.put("name", "混合过关");
            map6.put("count", "1");
            map6.put("play_flag", "mix");
            screenList.add(map6);
            Map<String, String> map7 = new HashMap<>();
            map7.put("name", "竟足单关");
            map7.put("count", "1");
            map7.put("play_flag", "single");
            screenList.add(map7);
        } else if (shopDetails == 1) {
            play_flag = "victory";
            textCenter.setText("胜平");
            map.put("name", "胜平");
            map.put("count", "2");
            map.put("play_flag", "victory");
            screenList.add(map);
            Map<String, String> map2 = new HashMap<>();
            map2.put("name", "让分胜负");
            map2.put("count", "1");
            map2.put("play_flag", "let_victory");
            screenList.add(map2);
            Map<String, String> map3 = new HashMap<>();
            map3.put("name", "分数差");
            map3.put("count", "1");
            map3.put("play_flag", "score");
            screenList.add(map3);
            Map<String, String> map4 = new HashMap<>();
            map4.put("name", "大小分");
            map4.put("count", "1");
            map4.put("play_flag", "number");
            screenList.add(map4);
            Map<String, String> map5 = new HashMap<>();
            map5.put("name", "混合过关");
            map5.put("count", "1");
            map5.put("play_flag", "mix");
            screenList.add(map5);
            Map<String, String> map6 = new HashMap<>();
            map6.put("name", "竟蓝单关");
            map6.put("count", "1");
            map6.put("play_flag", "single");
            screenList.add(map6);
        } else if (shopDetails == 2) {
            play_flag = "lottery_d";
            textCenter.setText("胜负彩");
        } else if (shopDetails == 3) {
            play_flag = "lottery_c";
            textCenter.setText("任选9");
        }

        screenAdp.setNewData(screenList);
        screenAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Map<String, String>> data = screenAdp.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("count", "1");
                }
                data.get(position).put("count", "2");
                play_flag = data.get(position).get("play_flag");
                textCenter.setText(data.get(position).get("name"));
                screenAdp.notifyDataSetChanged();
                drawable = ContextCompat.getDrawable(me, R.mipmap.icon_choose_down_white);
                screen = 1;
                flGone.setVisibility(View.GONE);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textCenter.setCompoundDrawables(null, null, drawable, null);
                //添加编辑赛事判断item 显示哪个界面
                addTag = position + "";
                wonderfulFootballAdp.getTypeContext(position);
                http();
                //判断显示哪个item

                // wonderfulFootballAdp.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.image_left_finish, R.id.text_center, R.id.text_right, R.id.text_sure, R.id.buttonclean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_center:
                if (screen == 1) {
                    screen = 2;
                    flGone.setVisibility(View.VISIBLE);
                    drawable = me.getResources().getDrawable(R.mipmap.icon_choose_up_white);
                } else {
                    drawable = me.getResources().getDrawable(R.mipmap.icon_choose_down_white);
                    screen = 1;
                    flGone.setVisibility(View.GONE);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textCenter.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.text_right:
                //筛选
                bundle.putInt("shopDetails", shopDetails);
                bundle.putString("play_flag", play_flag);
                start(me, bundle, ScreenActivity.class, 106);
                break;
            case R.id.text_sure:
                ButtonView.sureZuqiu(me,shopDetails,play_flag,wonderfulFootballAdp);
                break;
            case R.id.buttonclean:
                //清空选择状态
                ButtonView.setIsCheck(play_flag,wonderfulFootballAdp);
                gamesnumber.setText("至少选择2场赛事");
                break;
            default:
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.text_title_bag:
                WFootallBean.DataBeanXXX dataBeanXXX = wonderfulFootballAdp.getData().get(position);
                if (dataBeanXXX.getTypeCount().equals("0")) {
                    dataBeanXXX.setTypeCount("1");
                } else {
                    dataBeanXXX.setTypeCount("0");
                }
                wonderfulFootballAdp.notifyItemChanged(position);
                break;
            default:
        }
    }

    /**
     * 回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 106 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            odds_id = bundle.getString("odds_id");
            match_type_id = bundle.getString("match_type_id");
            http();
        }
    }

    @Override
    public void getFootballPlayList(List<WFootallBean.DataBeanXXX> data) {
        if (data == null) {
            return;
        }
        Log.d("getFootballPlayList","===================================="+data.size());
        for (int i = 0; i < data.size(); i++) {
            List<WFootallBean.DataBeanXXX.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                WFootallBean.DataBeanXXX.ListBean listBean = list.get(j);
                if (listBean != null) {
                    listBean.setFenXi("0");

                    //比分
                    WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = listBean.getPlay_value();
                    if (play_value != null) {
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean> score_value = play_value.getScore_value();
                        if (score_value != null) {
                            for (int k = 0; k < score_value.size(); k++) {
                                List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data1 = score_value.get(k).getData();
                                if (data1 != null|| data1.size() != 0){
                                    for (int l = 0; l < data1.size(); l++) {
                                        data1.get(l).setTypeBoolean("0");
                                    }
                                }
                            }
                        }

                        //总进球数
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.NumberValueBean> number_value = play_value.getNumber_value();
                        if(number_value != null){
                            for (int f = 0; f <number_value.size(); f++){
                                number_value.get(f).setTypeBoolean("0");
                            }
                        }

                        //半全场
                        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.HalfBean> half = play_value.getHalf();
                        if (half != null){
                            for (int h = 0; h <half.size(); h++){
                                half.get(h).setTypeBoolean("0");
                            }
                        }

                        //胜平负
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean victory = play_value.getVictory();
                        if (victory != null) {
                            List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.VictoryBean.DataBean> data1 = victory.getData();
                            if (data1 != null) {
                                for (int k = 0; k < data1.size(); k++) {
                                    data1.get(k).setTypeBoolean("0");
                                }
                            }
                        }

                        //让球胜平负
                        WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean let_victory = play_value.getLet_victory();
                        if (let_victory != null) {
                            List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.LetVictoryBean.DataBeanX> data1 = let_victory.getData();
                            if (data1 != null) {
                                for (int k = 0; k < data1.size(); k++) {
                                    data1.get(k).setTypeBoolean("0");
                                }
                            }
                        }
                    }
                }
            }
        }
        wonderfulFootballAdp.setNewData(data);
    }

    @Override
    public void getAgainOrderSendInfo(Map<String, String> data) {

    }

    @Override
    public void getSureList(Map<String, String> data) {

    }
}