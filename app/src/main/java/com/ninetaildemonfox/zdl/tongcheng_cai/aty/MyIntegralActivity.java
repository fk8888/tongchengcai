package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.MainActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MessageEvent;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.MyPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.MyContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 11:48
 * 功能描述： 我的积分
 * 联系方式：1037438704@qq.com
 */
public class MyIntegralActivity extends BaseActivity implements MyContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_integral)
    TextView text_integral;
    @BindView(R.id.text_integral_number)
    TextView text_integral_number;
    @BindView(R.id.text_shop)
    TextView text_shop;
    @BindView(R.id.text_sign_in)
    TextView text_sign_in;
    private MyPresenter myPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_integral;
    }

    @Subscribe
    @Override
    public void onInitialization(Bundle bundle) {
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        myPresenter = new MyPresenter(me, this);
        myPresenter.memberCenter(token);
        textCenter.setText("我的积分");
        textRight.setText("积分规则");
        textRight.setVisibility(View.VISIBLE);
        imageleftFinish.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.image_left_finish, R.id.text_integral, R.id.text_shop, R.id.text_sign_in, R.id.text_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_integral:
                //积分明细
                bundle.putString("count", "3");
                start(me, bundle, DetailedActivity.class);
                break;
            case R.id.text_shop:
                //前往商场
                EventBus.getDefault().post(new MessageEvent(2));
                start(me, MainActivity.class);
                break;
            case R.id.text_sign_in:
                start(me, SignInActivity.class);
                break;
            case R.id.text_right:
                //积分规则
                bundle.putInt("type", 2);
                start(me, bundle, DocumentaryPlanningActivity.class);
                break;
            default:
        }
    }


    @Override
    public void success(Map<String, String> data) {
        text_integral_number.setText(data.get("integral"));
    }
}