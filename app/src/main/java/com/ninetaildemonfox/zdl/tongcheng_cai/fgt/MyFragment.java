package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ForumMyActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LoginActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LotteryOrderActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.MyIntegralActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.MyPromotionCodeActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.PersonalActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ReceivingAddressActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SystemSettingsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.WalletActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.Preferences;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 10:52
 * 功能描述： 我的界面
 * 联系方式：1037438704@qq.com
 */
public class MyFragment extends BaseFragment implements MyContract {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_my_address)
    TextView textMyAddress;
    @BindView(R.id.image_question)
    ImageView imageQuestion;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_forum)
    TextView text_forum;
    @BindView(R.id.text_lottery_order)
    TextView text_lottery_order;
    @BindView(R.id.text_documentary)
    TextView text_documentary;
    @BindView(R.id.text_shop)
    TextView text_shop;
    @BindView(R.id.my_dimensional)
    TextView my_dimensional;
    @BindView(R.id.ll_my_wallet)
    LinearLayout ll_my_wallet;
    @BindView(R.id.my_code)
    TextView my_code;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_give_count)
    TextView textGiveCount;
    @BindView(R.id.text_forum_count)
    TextView textGorumCount;
    @BindView(R.id.text_recharge_money)
    TextView textRechargeMoney;
    @BindView(R.id.text_withdraw_money)
    TextView textWithdrawMoney;
    private MyPresenter myPresenter;
    private String member_id;


    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        myPresenter = new MyPresenter(fgtContext, this);
        textCenter.setVisibility(View.GONE);
        imageQuestion.setVisibility(View.VISIBLE);
        imageQuestion.setImageResource(R.mipmap.icon_my_install);
    }

    @Override
    public void onResume() {
        super.onResume();
        token = Preferences.getInstance().getString(fgtContext, "user", "token");
        if (TextUtils.isEmpty(token)) {
            textName.setText("点击登录");
        } else {
            myPresenter.memberCenter(token);
        }
    }

    @OnClick({R.id.image_head, R.id.text_my_address, R.id.text_forum,
            R.id.text_lottery_order, R.id.text_documentary, R.id.text_shop
            , R.id.my_dimensional, R.id.image_question, R.id.ll_my_wallet, R.id.my_code, R.id.text_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_head:
                if (textName.getText().toString().trim().equals("点击登录")) {
                    start(fgtContext, LoginActivity.class);
                    return;
                }
                start(fgtContext, PersonalActivity.class);
                break;
            case R.id.text_my_address:
                //我的地址
                start(fgtContext, ReceivingAddressActivity.class);
                break;
            case R.id.text_forum:
                //论坛界面
                if (member_id == null) {
                    return;
                }
                bundle.putString("member_id", member_id);
                start(fgtContext, bundle, ForumMyActivity.class);
                break;
            case R.id.text_lottery_order:
                //彩票订单  1
                bundle.putString("count", "1");
                start(fgtContext, bundle, LotteryOrderActivity.class);
                break;
            case R.id.text_documentary:
                //跟单订单  2
                bundle.putString("count", "2");
                start(fgtContext, bundle, LotteryOrderActivity.class);
                break;
            case R.id.text_shop:
                //商场订单  3
                bundle.putString("count", "3");
                start(fgtContext, bundle, LotteryOrderActivity.class);
                break;
            case R.id.my_dimensional:
                //我的推广码
                start(fgtContext, MyPromotionCodeActivity.class);
                break;
            case R.id.image_question:
                //系统设置
                start(fgtContext, SystemSettingsActivity.class);
                break;
            case R.id.ll_my_wallet:
                //我的钱包
                start(fgtContext, WalletActivity.class);
                break;
            case R.id.my_code:
                //我的积分
                start(fgtContext, MyIntegralActivity.class);
                break;
            case R.id.text_name:
                if (textName.getText().toString().trim().equals("点击登录")) {
                    start(fgtContext, LoginActivity.class);
                    return;
                }
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {
        textName.setText(data.get("nick_name"));
        textGiveCount.setText(data.get("click_total_number"));
        textGorumCount.setText(data.get("post_total_number"));
        textRechargeMoney.setText("￥" + data.get("recharge_money"));
        textWithdrawMoney.setText("￥" + data.get("withdraw_money"));
        GlidUtils.circular(fgtContext, data.get("head_pic"), imageHead);
        member_id = data.get("id");
//                账号—— "account": "18222905308",
//                积分——"integral": "2",
//                "expired_time": "1568622801",
//                邀请码—— "invite_code": "823916",
//                "p_id": "4",
//                真是姓名—— "real_name": "隔壁老樊",
//                昵称——"nick_name": "娃哈哈",
//                "sex": "女",
//                头像——"head_pic": "http://www.tongchengcai.com/Uploads/Member/2019-08-24/5d609631243ad.png",
//                "id_card": "123456789012345678",
//                "id_card_up": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5de8af.png",
//                "ic_card_down": "http://www.tongchengcai.com/Uploads/IdCard/2019-08-24/water_5d610ce5df467.png",
//                账户余额——"recharge_money": "954.00",
//                可提现余额—— "withdraw_money": "50.00",
//                "status": "1",
//                "is_cert": "2",
//                "create_time": "1566436932",
//                "update_time": "1568018001",
//                点赞总数——"click_total_number": 1,
//                论坛发帖总数——"post_total_number": 1,
//                是否这是密码—— "is_password": 2  1——未设置  2——已设置

    }
}
