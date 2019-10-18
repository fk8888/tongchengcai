package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DetailsOfCompetitionContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
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

public class DetailsOfCompetitionPresenter implements Constants {
    private AppCompatActivity me;
    private DetailsOfCompetitionContract competitionContract;

    public DetailsOfCompetitionPresenter(AppCompatActivity me, DetailsOfCompetitionContract competitionContract) {
        this.me = me;
        this.competitionContract = competitionContract;
    }

    /**
     * 编辑收货地址
     */
    public void getFootballMatchRewardsInfo(String football_match_id) {

        HttpRequest.POST(me, GETFOOTBALLMATCHREWARDSINFO
                , new Parameter()
                        .add("football_match_id", football_match_id)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data == null) {
                            return;
                        }
                        competitionContract.success(data);
                    }
                });
    }
}