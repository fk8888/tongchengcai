package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.DocumentaryPlanningPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.DocumentaryPlanningContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MapBean;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/26 23:20
 * 功能描述： 0——跟单规则 1——注册协议 2——积分规则 3——签到规则
 * 联系方式：1037438704@qq.com
 */


public class DocumentaryPlanningActivity extends BaseActivity implements DocumentaryPlanningContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.webView)
    WebView webView;
    private DocumentaryPlanningPresenter planningPresenter;
    private int type;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_documentary_planning;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
//        0——跟单规则 1——注册协议 2——积分规则 3——签到规则
        type = getIntent().getExtras().getInt("type");
        if (type == 0){
            textCenter.setText("跟单规则");
        }else if (type == 1){
            textCenter.setText("注册协议");
        }else if (type == 2){
            textCenter.setText("积分规则");
        }else if (type == 3){
            textCenter.setText("签到规则");
        }else if (type == 4){
            MapBean mapBean = (MapBean) getIntent().getExtras().getSerializable("map");
            Map<String, String> map = mapBean.getMap();
            textCenter.setText(map.get("title"));
            webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl,map.get("content"), "text/html", "utf-8", null);
            return;
        }
        planningPresenter = new DocumentaryPlanningPresenter(me, this);
        planningPresenter.getNormalRuleInfo(type);

    }

    private void initView() {
        imageleftFinish.setVisibility(View.VISIBLE);
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
    public void getNormalRuleInfo(String data) {
        webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl, data, "text/html", "utf-8", null);

    }
}
