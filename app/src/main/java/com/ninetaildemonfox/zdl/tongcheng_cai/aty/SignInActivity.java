package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.SignInPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.SignInContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.DateUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.datepicker.DPCManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.datepicker.DPDecor;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.datepicker.DPMode;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.datepicker.DatePicker;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/2/27 10:00
 * 功能描述： 签到界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */

public class SignInActivity extends BaseActivity implements SignInContract {
    private int integral_days = 0;//双倍积分日
    Date dateQian = new Date();
    private DPCManager dpcManager;
    private String date;//当前日期
    private ArrayList<String> tmpd;
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.tv_continuous_check_times)
    TextView tv_continuous_check_times;
    @BindView(R.id.tv_integral)
    TextView tv_integral;
    @BindView(R.id.tv_sign2)
    TextView tv_sign2;
    @BindView(R.id.tv_sign1)
    TextView tv_sign1;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.rl_sign)
    RelativeLayout rl_sign;
    @BindView(R.id.my_datepicker)
    DatePicker myDatepicker;
    private SignInPresenter presenter;

    @Override
    protected void onInitialization(Bundle bundle) {
        initViews();
        initData();
        setEvents();
    }

    private void dateType() {
        /**
         * 添加双倍积分
         */
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, integral_days);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数  calendar .add(5,1);则表示对日期进行加一天操作
        date = calendar.getTime();
        dateQian = calendar.getTime();
        SimpleDateFormat formatter2 = new SimpleDateFormat("M");
        tv_month.setText(formatter2.format(date));
        myDatepicker.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance()
                .get(Calendar.MONTH) + 1); //设置日期
        myDatepicker.setMode(DPMode.NONE); //设置选择模式
        myDatepicker.setFestivalDisplay(false); //是否显示节日
        myDatepicker.setTodayDisplay(true); //是否高亮显示今天
        myDatepicker.setHolidayDisplay(false); //是否显示假期
        myDatepicker.setDeferredDisplay(false); //是否显示补休
        myDatepicker.setIsScroll(false); //是否允许滑动 false表示左右上下都不能滑动  单项设置上下or左右 你需要自己扩展
//        myDatepicker.setIsSelChangeColor(true, getResources().getColor(R.color.liji_material_blue_700));
        //设置选择的日期字体颜色,不然有的背景颜色和默认的字体颜色不搭
        myDatepicker.setLeftTitle(Calendar.getInstance().get(Calendar.MONTH) + 1 + "月"); //左上方text
        myDatepicker.setRightTitle(false); //是否签到

        //设置预先选中日期的背景颜色
        myDatepicker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                Bitmap bm_bg = BitmapFactory.decodeResource(getResources(), R.mipmap
                        .icon_sure_duigou);
                // 得到图片的宽、高
                int width_bg = bm_bg.getWidth();
                int height_bg = bm_bg.getHeight();
                canvas.drawBitmap(bm_bg, rect.centerX() - width_bg / 2, rect.centerY() +
                        height_bg, paint);
            }

        });
    }

    @OnClick({R.id.image_left_finish, R.id.text_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_right:
                bundle.putInt("type", 3);
                start(me, bundle, DocumentaryInformationActivity.class);
                break;
            default:
        }
    }

    public void setEvents() {
        //返回上一页
        //签到点击事件
        myDatepicker.setOnClickSignIn(new DatePicker.OnClickSignIn() {
            @Override
            public void signIn() {

            }
        });
        //点击签到
        rl_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addMemberSign(token);
            }
        });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_in;
    }

    private void initData() {
        dpcManager = DPCManager.getInstance();
        dpcManager.clearnDATE_CACHE(); //清除cache
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-M-d");
        sf.format(Calendar.getInstance().getTime());
        /**
         * 添加已签到
         */
        date = sf.format(Calendar.getInstance().getTime());
        dateType();
        //签到请求
        presenter.getMemberSignList(token);
    }

    private void initViews() {
        presenter = new SignInPresenter(me, this);
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("签到领积分");
        textRight.setText("签到规则");
    }

    @Override
    public void success(Map<String, String> data) {
        //判斷是否签到
        String sign = data.get("sign");
//        ——今天是否签到， 1——已签到  0——未签到
        if (sign.equals("1")) {
            rl_sign.setBackgroundResource(R.mipmap.icon_sign_false);
            tv_sign1.setText("今日");
            tv_sign2.setText("已签到");
        } else {
            rl_sign.setBackgroundResource(R.mipmap.icon_sign_true);
            tv_sign1.setText("签到");
            tv_sign2.setText("领积分");
        }
        //积分
        tv_integral.setText(data.get("integral"));
        tv_continuous_check_times.setText(data.get("days"));

        ArrayList<Map<String, String>> list = JSONUtils.parseKeyAndValueToMapList(data.get("list"));
        if (list == null) {
            return;
        }
        tmpd = new ArrayList<>();
        tmpd.clear();
        for (int i = 0; i < list.size(); i++) {
            //这个是返回时间戳
            String sign_date = list.get(i).get("sign_time") + "000";
            String date_timestamp1 = DateUtils.formatDateByTimeZone(Long.valueOf(sign_date), "yyyy-M-d");
            tmpd.add(date_timestamp1);
        }
        dpcManager.clearnDATE_CACHE(); //清除cache
        dpcManager.setDecorBG(tmpd);
        dateType();
    }

}