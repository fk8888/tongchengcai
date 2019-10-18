package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ShoppingMallDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ShoppingMallDetailsPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ShoppingMallDetailsContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 14:21
 * 功能描述： 积分商品详情
 * 联系方式：1037438704@qq.com
 */
public class ShoppingMallDetailsActivity extends BaseActivity implements ShoppingMallDetailsContract {

    @BindView(R.id.text_translate)
    TextView textTranslate;
    @BindView(R.id.imageViewFinish)
    ImageView imageViewFinish;
    @BindView(R.id.imageTitle)
    ImageView imageTitle;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.text_title)
    TextView text_title;
    @BindView(R.id.text_exchange_number)
    TextView text_exchange_number;
    @BindView(R.id.text_integral_number)
    TextView text_integral_number;
    private String integral_goods_id;
    private ShoppingMallDetailsPresenter presenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_shopping_mall_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        whitchBar();
        presenter = new ShoppingMallDetailsPresenter(me, this);
        integral_goods_id = getIntent().getExtras().getString("integral_goods_id");
        presenter.getIntegralGoodsInfo(integral_goods_id);
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
        webView.setWebViewClient(new WebViewClient());
    }


    @OnClick({R.id.text_translate, R.id.imageViewFinish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_translate:
                bundle.putString("integral_goods_id", integral_goods_id);
                bundle.putString("type", "1");
                start(me, bundle, SubmitExchangeActivity.class);
                break;
            case R.id.imageViewFinish:
                finish();
                break;
            default:
        }
    }

    @Override
    public void shopSuccess(Map<String, String> data) {
        GlidUtils.defaultGlid(me, data.get("integral_img_path"), imageTitle);
        text_title.setText(data.get("integral_goods_name"));
        text_exchange_number.setText("兑换量：" + data.get("exchange_number"));
        text_integral_number.setText(data.get("integral_number"));
        webView.loadDataWithBaseURL(BaseOkHttp.serviceUrl, data.get("integral_mark"), "text/html", "utf-8", null);
    }
}