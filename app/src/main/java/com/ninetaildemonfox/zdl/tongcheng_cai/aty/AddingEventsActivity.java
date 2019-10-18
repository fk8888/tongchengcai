package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.dialog.v3.CustomDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEvents2Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEvents3Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEvents4Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEventsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEventsLanQiu1Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEventsLanQiu2Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.AddingEventsLanQiu3Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.TheOutcomeRenJiuAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.WFootallPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.WFootallContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ArithmeticUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/8 16:22
 * 功能描述： 编辑/添加赛事
 * 联系方式：1037438704@qq.com
 */

public class AddingEventsActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener , WFootallContract {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.text_mode)
    TextView text_mode;
    @BindView(R.id.text_pay)
    TextView text_pay;

    //判断是哪个item
    private String play_flag;
    //判断是哪个界面
    private int shopDetails = 0;

    private WFootallPresenter presenter;
    private int amount = 1;
    private String pay_money = "2";

    private TextView textMoney;   //余额
    private TextView textMode;   //过关方式
    private android.widget.Button btnDecrease;  //倍数加
    private android.widget.EditText editCount;  //增减数
    private android.widget.Button btnIncrease;  //倍数减
    private TextView shifuMoney;  //实付金额
    private TextView zhuBei;  //几注几倍
    private List<WFootallBean.DataBeanXXX> choose_info;
    private String shopID;
    private String message;
    private String lottery_note;
    private String lottery_info;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_adding_events;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        if (shopDetails == 0) {
            //足球
            adpitemView();
        } else if (shopDetails == 1) {
            //篮球
            adpitemView2();
        } else if (shopDetails == 2) {
            //胜负彩
            adpitemView3();
        } else if (shopDetails == 3) {
            //任九
            adpitemView3();
        }

    }

    private void initView() {
        Bundle extras = getIntent().getExtras();
        shopDetails = extras.getInt("shopDetails");
        play_flag = extras.getString("play_flag");
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("添加/编辑赛事");
        presenter = new WFootallPresenter(me, this);

        choose_info = (List<WFootallBean.DataBeanXXX>) getIntent().getSerializableExtra("wFootall2Bean");
      /*  //组装字符串
        for (int i = 0;i<choose_info.size();i++){
           *//* "
                    "match_id": 12,
		            "start_time": "1567609200",
		            "play_flag": "victory",
		            "match_number": "001",
		            "let_number": "-1",
		            "key": "胜",
		            "value": "1",
		            "home_team_name": "创奇前锋",
		            "guest_team_name": "阿根廷",
		            "week": "周三",
		            "end_date": "2019-09-04",
		            "end_hour_minute": "21:00",
		            "dan": 0*//*
            String match_id = choose_info.get(i).getList().get(i).getId();
            String start_time = choose_info.get(i).getList().get(i).getStart_time();
            String play_flag = choose_info.get(i).getList().get(i).getPlay_flag();
            String match_number = choose_info.get(i).getList().get(i).getMatch_number();
            String home_team_name = choose_info.get(i).getList().get(i).getHome_team_name();
            String guest_team_name = choose_info.get(i).getList().get(i).getGuest_team_name();
            String week = choose_info.get(i).getWeek();
            String end_date = choose_info.get(i).getList().get(i).getEnd_date();
            String end_hour_minute = choose_info.get(i).getList().get(i).getEnd_hour_minute();
        }*/
        presenter.getXiaDan(play_flag, choose_info,token);

        SharedPreferences shopid = getSharedPreferences("shopid", Context.MODE_PRIVATE);
        shopID = shopid.getString("shopID", "");


        textMoney = (TextView) findViewById(R.id.text_money);
        textMode = (TextView) findViewById(R.id.text_mode);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        editCount = (EditText) findViewById(R.id.edit_count);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        shifuMoney = (TextView) findViewById(R.id.shifu_money);
        zhuBei = (TextView) findViewById(R.id.zhu_bei);
    }

    private void adpitemView3() {
        TheOutcomeRenJiuAdp theOutcomeRenJiuAdp = new TheOutcomeRenJiuAdp(R.layout.item_outcome_of_list_adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(me));
        recyclerView.setAdapter(theOutcomeRenJiuAdp);
        theOutcomeRenJiuAdp.setNewData(list);
        theOutcomeRenJiuAdp.setOnItemClickListener(this);
        theOutcomeRenJiuAdp.setOnItemChildClickListener(this);
    }

    private void adpitemView2() {
        //  victory——胜平负
        // let_victory——让球胜平负
        // score——比分
        // number——总进球
        // half——半全场
        // mix——混合过关
        // single——单关
        // lottery_c——任九
        // lottery_d——胜负彩

        if (play_flag.equals("victory")) {
            //胜负
            AddingEventsLanQiu1Adp addingEventsLanQiu1Adp = new AddingEventsLanQiu1Adp(R.layout.item_adding_events_list_adp);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEventsLanQiu1Adp);
            addingEventsLanQiu1Adp.setAddingCount(play_flag);
            addingEventsLanQiu1Adp.setNewData(list);
            addingEventsLanQiu1Adp.setOnItemClickListener(this);
            addingEventsLanQiu1Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("let_victory")) {
            //让分胜负
            AddingEventsLanQiu1Adp addingEventsLanQiu1Adp = new AddingEventsLanQiu1Adp(R.layout.item_adding_events_list_adp);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEventsLanQiu1Adp);
            addingEventsLanQiu1Adp.setAddingCount(play_flag);
            addingEventsLanQiu1Adp.setNewData(list);
            addingEventsLanQiu1Adp.setOnItemClickListener(this);
            addingEventsLanQiu1Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("2")) {
            //分数差
            AddingEventsLanQiu2Adp addingEventsLanQiu2Adp = new AddingEventsLanQiu2Adp(R.layout.item_adding_lanqiu_list_adp_1);
            addingEventsLanQiu2Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEventsLanQiu2Adp);
            addingEventsLanQiu2Adp.setNewData(list);
            addingEventsLanQiu2Adp.setOnItemClickListener(this);
            addingEventsLanQiu2Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("3")) {
            //大小分
            AddingEventsLanQiu1Adp addingEventsLanQiu1Adp = new AddingEventsLanQiu1Adp(R.layout.item_adding_events_list_adp);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            addingEventsLanQiu1Adp.setAddingCount(play_flag);
            recyclerView.setAdapter(addingEventsLanQiu1Adp);
            addingEventsLanQiu1Adp.setNewData(list);
            addingEventsLanQiu1Adp.setOnItemClickListener(this);
            addingEventsLanQiu1Adp.setOnItemChildClickListener(this);

        } else if (play_flag.equals("4")) {
            //混合过关
            AddingEventsLanQiu3Adp addingEventsLanQiu3Adp = new AddingEventsLanQiu3Adp(R.layout.item_adding_lanqiu_list_adp_2);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            addingEventsLanQiu3Adp.setAddingCount(play_flag);
            recyclerView.setAdapter(addingEventsLanQiu3Adp);
            addingEventsLanQiu3Adp.setNewData(list);
            addingEventsLanQiu3Adp.setOnItemClickListener(this);
            addingEventsLanQiu3Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("5")) {
            //竞蓝单关
            AddingEvents4Adp addingEvents4Adp = new AddingEvents4Adp(R.layout.item_adding_events_list_adp_4);
            addingEvents4Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents4Adp);
            addingEvents4Adp.setNewData(list);
            addingEvents4Adp.setOnItemClickListener(this);
            addingEvents4Adp.setOnItemChildClickListener(this);
        }
    }

    private void adpitemView() {
        //  victory——胜平负
        // let_victory——让球胜平负
        // score——比分
        // number——总进球
        // half——半全场
        // mix——混合过关
        // single——单关
        // lottery_c——任九
        // lottery_d——胜负彩

        if (play_flag.equals("victory")) {
            //胜负平
            AddingEventsAdp addingEventsAdp = new AddingEventsAdp(R.layout.item_adding_events_list_adp);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEventsAdp);
            addingEventsAdp.setAddingCount(play_flag);
            addingEventsAdp.setNewData(choose_info);
            addingEventsAdp.setOnItemClickListener(this);
            addingEventsAdp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("let_victory")) {
            //让球胜平负
            AddingEventsAdp addingEventsAdp = new AddingEventsAdp(R.layout.item_adding_events_list_adp);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            addingEventsAdp.setAddingCount(play_flag);
            recyclerView.setAdapter(addingEventsAdp);
            addingEventsAdp.setNewData(choose_info);
            addingEventsAdp.setOnItemClickListener(this);
            addingEventsAdp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("score")) {
            //比分
            AddingEvents2Adp addingEvents2Adp = new AddingEvents2Adp(R.layout.item_adding_events_list_adp_2);
            addingEvents2Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents2Adp);
            addingEvents2Adp.setNewData(choose_info);
            addingEvents2Adp.setOnItemClickListener(this);
            addingEvents2Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("number")) {
            //总进球数
            AddingEvents3Adp addingEvents3Adp = new AddingEvents3Adp(R.layout.item_adding_events_list_adp_3);
            addingEvents3Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents3Adp);
            addingEvents3Adp.setNewData(choose_info);
            addingEvents3Adp.setOnItemClickListener(this);
            addingEvents3Adp.setOnItemChildClickListener(this);

        } else if (play_flag.equals("half")) {
            //半全场
            AddingEvents3Adp addingEvents3Adp = new AddingEvents3Adp(R.layout.item_adding_events_list_adp_3);
            addingEvents3Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents3Adp);
            addingEvents3Adp.setNewData(choose_info);
            addingEvents3Adp.setOnItemClickListener(this);
            addingEvents3Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("mix")) {
            //混合过关
            AddingEvents4Adp addingEvents4Adp = new AddingEvents4Adp(R.layout.item_adding_events_list_adp_4);
            addingEvents4Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents4Adp);
            addingEvents4Adp.setNewData(list);
            addingEvents4Adp.setOnItemClickListener(this);
            addingEvents4Adp.setOnItemChildClickListener(this);
        } else if (play_flag.equals("single")) {
            //竟足单关
            AddingEvents4Adp addingEvents4Adp = new AddingEvents4Adp(R.layout.item_adding_events_list_adp_4);
            addingEvents4Adp.setAddingCount(play_flag);
            recyclerView.setLayoutManager(new LinearLayoutManager(me));
            recyclerView.setAdapter(addingEvents4Adp);
            addingEvents4Adp.setNewData(list);
            addingEvents4Adp.setOnItemClickListener(this);
            addingEvents4Adp.setOnItemChildClickListener(this);
        }
    }


    @OnClick({R.id.image_left_finish, R.id.text_mode, R.id.text_pay, R.id.btnDecrease, R.id.btnIncrease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_mode:
                //过关方式
                start(me, ModeBottomActivity.class);
                break;
            case R.id.btnDecrease:
                //减号
                if (amount == 1) {
                    return;
                }
                amount--;
                editCount.setText(amount + "");
                BigDecimal mul = ArithmeticUtils.mul(pay_money, String.valueOf(amount));   //两块钱*倍数
                shifuMoney.setText("￥" + mul);
                zhuBei.setText(lottery_note+"注"+amount+"倍");
                break;
            case R.id.btnIncrease:
                //加号
                amount++;
                editCount.setText(amount + "");
                BigDecimal mul1 = ArithmeticUtils.mul(pay_money, String.valueOf(amount));
                shifuMoney.setText("￥" + mul1);
                zhuBei.setText(lottery_note+"注"+amount+"倍");
                break;
            case R.id.text_pay:
                //确定下单
                presenter.surecast(play_flag, choose_info, lottery_info, lottery_note, amount, token, shopID );
                break;
            default:
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.text_gallbladder) {
            CustomDialog.build(me, R.layout.layout_custom_frame, new CustomDialog.OnBindView() {
                @Override
                public void onBind(final CustomDialog dialog, View v) {
                    ImageView iamge_finish = v.findViewById(R.id.iamge_finish);
                    iamge_finish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.doDismiss();
                        }
                    });
                }
            }).setCancelable(false).show();

        }
    }

    @Override
    public void getFootballPlayList(List<WFootallBean.DataBeanXXX> data) {

    }

    //下单
    @Override
    public void getAgainOrderSendInfo(Map<String, String> data) {
        textMoney.setText(data.get("recharge_money"));
        lottery_info = data.get("lottery_info");
        textMode.setText(lottery_info);
        lottery_note = data.get("lottery_note");
    }

    //确认下单
    @Override
    public void getSureList(Map<String, String> data) {
        message = data.get("message");
        ToastUtil.show(message);
        bundle.putInt("success", 5);
        bundle.putString("shopDetails", shopDetails + "");
        start(me, bundle, SuccessPayActivity.class);
    }
}