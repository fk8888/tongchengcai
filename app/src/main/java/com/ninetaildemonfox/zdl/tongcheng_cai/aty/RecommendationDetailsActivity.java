package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ColorAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.RecommendationPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.RecommendationContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/4 11:11
 * 功能描述： 推荐详情
 * 联系方式：1037438704@qq.com
 */
public class RecommendationDetailsActivity extends BaseActivity
        implements RecommendationContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_flag)
    TextView text_flag;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_strand)
    TextView text_strand;
    @BindView(R.id.text_comment)
    TextView textComment;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.text_pay)
    TextView text_pay;
    @BindView(R.id.ll_gone_one)
    LinearLayout ll_gone_one;
    @BindView(R.id.ll_gone_two)
    LinearLayout ll_gone_two;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_time)
    TextView text_time;
    @BindView(R.id.text_content)
    TextView text_content;
    @BindView(R.id.text_end_time)
    TextView text_end_time;
    @BindView(R.id.text_week_match_number)
    TextView text_week_match_number;
    @BindView(R.id.text_guest_team_img_path)
    TextView text_guest_team_img_path;
    @BindView(R.id.text_home_team_img_path)
    TextView text_home_team_img_path;
    @BindView(R.id.text_pay_money)
    TextView text_pay_money;
    @BindView(R.id.image_zhu)
    ImageView image_zhu;
    @BindView(R.id.image_ke)
    ImageView image_ke;
    @BindView(R.id.text_push_content)
    TextView text_push_content;

    private RecommendationPresenter presenter;
    private String match_push_id;
    private ColorAdp colorAdp;
    private String pay_money;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_recommendation_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WaitDialog.show(me, "");
        presenter.pushOrdersInfo(token, match_push_id);
    }

    private void initView() {
        match_push_id = getIntent().getExtras().getString("match_push_id");
        presenter = new RecommendationPresenter(me, this);
        imageleftFinish.setVisibility(View.VISIBLE);
        text_strand.setVisibility(View.GONE);
        textCenter.setText("推荐详情");
        recyclerView.setLayoutManager(new GridLayoutManager(me, 3));
        colorAdp = new ColorAdp(R.layout.item_color_list_adp);
        recyclerView.setAdapter(colorAdp);
//        WebSettings webSettings = webView.getSettings();
//        if (Build.VERSION.SDK_INT >= 21) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//        // 加快HTML网页加载完成速度
//        if (Build.VERSION.SDK_INT >= 19) {
//            webSettings.setLoadsImagesAutomatically(true);
//        } else {
//            webSettings.setLoadsImagesAutomatically(false);
//        }
//
//        // 设置支持javascript脚本
//        webSettings.setJavaScriptEnabled(true);
//
//        // 设置此属性，可任意比例缩放
//        webSettings.setUseWideViewPort(true);
//        // 设置不出现缩放工具
//        webSettings.setBuiltInZoomControls(false);
//        // 设置不可以缩放
//        webSettings.setSupportZoom(false);
//        webSettings.setDisplayZoomControls(false);
//
//        //自适应屏幕
////        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        // 自适应 屏幕大小界面
//        webSettings.setLoadWithOverviewMode(true);
//        webView.setWebViewClient(new WebViewClient());

    }

    @OnClick({R.id.image_left_finish, R.id.text_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_pay:
                presenter.payPushOrder(token, match_push_id);
                break;
            default:
        }
    }

    @Override
    public void pushOrdersInfo(Map<String, String> data) {
        //客
        text_guest_team_img_path.setText(data.get("guest_team_name"));
        //主
        text_home_team_img_path.setText(data.get("home_team_name"));
        GlidUtils.defaultGlid(me, data.get("home_team_img_path"), image_zhu);
        GlidUtils.defaultGlid(me, data.get("guest_team_img_path"), image_ke);
        //命中率
        textComment.setText("命中率" + data.get("hit_percent"));
        GlidUtils.circular(me, data.get("head_pic"), imageHead);
        text_name.setText(data.get("nick_name"));
        text_time.setText(data.get("update_time"));
        text_end_time.setText("截止时间：" + data.get("end_time"));
        text_week_match_number.setText(data.get("week") + data.get("match_number") + "/" + data.get("match_type_name"));
        text_content.setText(data.get("content"));
        //支付金额
        pay_money = data.get("pay_money");
        text_pay_money.setText("￥" + data.get("pay_money"));

//        webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl, data.get("push_content"), "text/html", "utf-8", null);
        text_push_content.setText(data.get("push_content"));
        //是否购买  ——当前登录者，是否购买  true——是  false——否
        String is_pay = data.get("is_pay");
        if (is_pay.equals("true")) {
            ll_gone_two.setVisibility(View.VISIBLE);
            ll_gone_one.setVisibility(View.GONE);
        } else {
            ll_gone_two.setVisibility(View.GONE);
            ll_gone_one.setVisibility(View.VISIBLE);
        }

        Map<String, String> play_value = JSONUtils.parseKeyAndValueToMap(data.get("play_value"));
        text_flag.setText("竞猜\n" + play_value.get("flag"));
        ArrayList<Map<String, String>> data2 = JSONUtils.parseKeyAndValueToMapList(play_value.get("data"));
        colorAdp.setNewData(data2);
    }
}