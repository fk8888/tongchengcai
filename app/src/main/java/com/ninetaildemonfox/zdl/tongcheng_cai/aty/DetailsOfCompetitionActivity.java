package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.DetailsOfCompetitionPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DetailsOfCompetitionContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/3 16:53
 * 功能描述： 比赛详情
 * 联系方式：1037438704@qq.com
 */

public class DetailsOfCompetitionActivity extends BaseActivity implements DetailsOfCompetitionContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_zhu)
    ImageView image_zhu;
    @BindView(R.id.image_ke)
    ImageView image_ke;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_analysis)
    TextView textAnalysis;
    @BindView(R.id.text_start_status)
    TextView text_start_status;
    @BindView(R.id.text_guest_team_img_path)
    TextView text_guest_team_img_path;
    @BindView(R.id.text_home_team_img_path)
    TextView text_home_team_img_path;
    @BindView(R.id.text_match_info)
    TextView text_match_info;
    @BindView(R.id.text_shangbanchang)
    TextView text_shangbanchang;
    @BindView(R.id.text_vs)
    TextView text_vs;
    @BindView(R.id.webView)
    WebView webView;
    private String football_match_id;
    private DetailsOfCompetitionPresenter presenter;
    private String type;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_details_of_competition;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        football_match_id = getIntent().getExtras().getString("football_match_id");
        type = getIntent().getExtras().getString("type");
        imageleftFinish.setVisibility(View.VISIBLE);
        textAnalysis.setVisibility(View.GONE);
        textCenter.setText("伊斯坦堡普野社希尔VS奥林...");
        presenter = new DetailsOfCompetitionPresenter(me, this);
        presenter.getFootballMatchRewardsInfo(football_match_id);
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 加快HTML网页加载完成速度
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }

        // 设置支持javascript脚本
        webSettings.setJavaScriptEnabled(true);

        // 设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(true);
        // 设置不出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        // 设置不可以缩放
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);

        //自适应屏幕
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 自适应 屏幕大小界面
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());

    }

    @OnClick({R.id.image_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            default:
        }
    }

    @Override
    public void success(Map<String, String> data) {
        text_guest_team_img_path.setText(data.get("guest_team_name"));
        text_home_team_img_path.setText(data.get("home_team_name"));
        text_match_info.setText(data.get("match_info"));
        GlidUtils.defaultGlid(me, data.get("home_team_img_path"), image_zhu);
        GlidUtils.defaultGlid(me, data.get("guest_team_img_path"), image_ke);

        if (type.equals("1")) {
            text_start_status.setText(data.get("start_status"));
            text_start_status.setVisibility(View.VISIBLE);
            text_shangbanchang.setVisibility(View.GONE);
            text_vs.setText("VS");
            text_vs.setTextColor(ContextCompat.getColor(me, R.color.text_gray_color));
            Map<String, String> instant_info = JSONUtils.parseKeyAndValueToMap(data.get("instant_info"));
            if (instant_info != null) {
                webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl, instant_info.get("message"), "text/html", "utf-8", null);
            }
        } else {
            text_shangbanchang.setVisibility(View.VISIBLE);
            text_start_status.setVisibility(View.GONE);
            Map<String, String> complete_info = JSONUtils.parseKeyAndValueToMap(data.get("complete_info"));
            if (complete_info == null) {
                text_vs.setText("0:0");
                text_vs.setTextColor(ContextCompat.getColor(me, R.color.colorAccent));
                text_shangbanchang.setText("上半场0:0");
            } else {
                text_vs.setText(complete_info.get("score_all_one") + ":" + complete_info.get("score_all_two"));
                text_vs.setTextColor(ContextCompat.getColor(me, R.color.colorAccent));
                text_shangbanchang.setText("上半场" + complete_info.get("score_half_one") + ":" + complete_info.get("score_half_two"));
                webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl, complete_info.get("message"), "text/html", "utf-8", null);
            }
        }

    }
}
