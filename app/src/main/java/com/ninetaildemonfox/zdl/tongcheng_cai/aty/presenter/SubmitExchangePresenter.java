package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ShoppingMallDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.SubmitExchangeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class SubmitExchangePresenter implements Constants {
    private AppCompatActivity me;
    private SubmitExchangeContract contract;


    public SubmitExchangePresenter(AppCompatActivity me, SubmitExchangeContract contract) {
        this.me = me;
        this.contract = contract;
    }

    /**
     * 查询用户的默认收货地址
     */
    public void findDefaultAddress(String token) {
        WaitDialog.show(me, "数据请求中");
        HttpRequest.POST(me, FINDDEFAULTADDRESS
                , new Parameter()
                        .add("token", token)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        contract.success(data);
                    }
                });
    }

    /**
     * 提交兑换
     * <p>
     * 参数名称	参数描述	是否为空	参数类型	示例值
     * token	token	否	文本	f31907b4629c5906043b3d548a3f0696
     * integral_goods_id	积分商品ID	否	文本	1
     * integral_number	消耗积分数量	否	文本	10
     * receive_name	收货人姓名	否	文本	张三
     * receive_phone	收货人电话	否	文本	18222905308
     * receive_province	收货人省	否	文本	天津市
     * receive_city	收货人市	否	文本	天津市
     * receive_area	收货人区	否	文本	西青区
     * receive_address	收货人详细地址
     */
    public void exchangeIntegralGoods(String token, String integral_goods_id, String integral_number, String receive_name
            , String receive_phone, String receive_province, String receive_city, String receive_area, String receive_address) {
        WaitDialog.show(me, "数据请求中");
        if (TextUtils.isEmpty(integral_goods_id)) {
            return;
        }
        if (TextUtils.isEmpty(integral_number)) {
            return;
        }
        if (TextUtils.isEmpty(receive_name)) {
            return;
        }
        if (TextUtils.isEmpty(receive_phone)) {
            return;
        }
        if (TextUtils.isEmpty(receive_province)) {
            return;
        }
        if (TextUtils.isEmpty(receive_city)) {
            return;
        }
        if (TextUtils.isEmpty(receive_address)) {
            return;
        }
        HttpRequest.POST(me, EXCHANGEINTEGRALGOODS
                , new Parameter()
                        .add("token", token)
                        .add("integral_goods_id", integral_goods_id)
                        .add("integral_number", integral_number)
                        .add("receive_name", receive_name)
                        .add("receive_phone", receive_phone)
                        .add("receive_province", receive_province)
                        .add("receive_city", receive_city)
                        .add("receive_area", receive_area)
                        .add("receive_address", receive_address)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        ToastUtil.show(map.get("message"));
                        AppManager.getInstance().killActivity(ShoppingMallDetailsActivity.class);
                        me.finish();
                    }
                });
    }

    /**
     * 积分商城订单详情
     */
    public void getExchangeIntegralLogInfo(String token, String integral_exchange_log_id) {
        WaitDialog.show(me, "数据请求中");
        HttpRequest.POST(me, GETEXCHANGEINTEGRALLOGINFO
                , new Parameter()
                        .add("token", token)
                        .add("integral_exchange_log_id", integral_exchange_log_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        contract.getExchangeIntegralLogInfo(data);
                    }
                });
    }


    /**
     * 积分订单确认收货
     */
    public void confirmIntegralOrder(final String token, final String integral_exchange_log_id) {
        if (TextUtils.isEmpty(integral_exchange_log_id)) {
            return;
        }

        HttpRequest.POST(me, CONFIRMINTEGRALORDER
                , new Parameter()
                        .add("token", token)
                        .add("integral_exchange_log_id", integral_exchange_log_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        getExchangeIntegralLogInfo(token,integral_exchange_log_id);
                    }
                });
    }

}