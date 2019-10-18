package com.ninetaildemonfox.zdl.tongcheng_cai.fgt;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.dialog.v3.WaitDialog;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.HomeNameListAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.HomeViewBottomAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ShopDetailsAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ForumActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ShopDetailsActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.ShopListActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.SignInActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.WinningListActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseFragment;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.CustomViewsInfo;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.MapBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.ShopDetailsBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.HomePresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.fgt.presenter.contract.HomeContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/2 9:44
 * 功能描述：首页
 * 联系方式：1037438704@qq.com
 */
public class HomeFragment extends BaseFragment
        implements BaseQuickAdapter.OnItemClickListener, HomeContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.image_question)
    ImageView imageQuestion;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.reyclerViewBottom)
    RecyclerView reyclerViewBottom;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.text_winninglist)
    TextView textWinninglist;
    @BindView(R.id.text_forum)
    TextView textForum;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_time)
    TextView text_time;
    @BindView(R.id.text_snap)
    TextView text_snap;
    @BindView(R.id.text_comment)
    TextView text_comment;
    @BindView(R.id.text_post_content)
    TextView text_post_content;
    @BindView(R.id.cZrecyclerView)
    RecyclerView cZrecyclerView;
    private List<ShopDetailsBean> cZList;
    //中奖名单
    @BindView(R.id.recycelrViewNameList)
    RecyclerView recycelrViewNameList;
    private HomeNameListAdp homeNameListAdp;
    private HomeViewBottomAdp homeViewBottomAdp;
    private HomePresenter presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        presenter = new HomePresenter(fgtContext, this);
        WaitDialog.show(fgtContext, "");
        presenter.getIndexData(token);
        imageQuestion.setVisibility(View.VISIBLE);
        imageQuestion.setImageResource(R.mipmap.icon_home_sign);
        textCenter.setText("同城彩");
        recycelrViewNameList.setLayoutManager(new LinearLayoutManager(fgtContext));
        reyclerViewBottom.setLayoutManager(new LinearLayoutManager(fgtContext));
        homeNameListAdp = new HomeNameListAdp(R.layout.item_view_name_list);
        homeViewBottomAdp = new HomeViewBottomAdp(R.layout.item_home_view_bottom);
        recycelrViewNameList.setAdapter(homeNameListAdp);
        reyclerViewBottom.setAdapter(homeViewBottomAdp);
        //店铺点击
        homeViewBottomAdp.setOnItemClickListener(this);

        cZList = new ArrayList<>();
        cZrecyclerView.setLayoutManager(new GridLayoutManager(fgtContext, 4));
        ShopDetailsAdp shopDetailsAdp = new ShopDetailsAdp(R.layout.item_shop_details_adp);
        cZrecyclerView.setAdapter(shopDetailsAdp);
        cZList.add(new ShopDetailsBean("竞彩足球", R.mipmap.icon_football));
        cZList.add(new ShopDetailsBean("竞彩篮球", R.mipmap.icon_basketball));
        cZList.add(new ShopDetailsBean("胜负彩", R.mipmap.icon_shengfucia));
        cZList.add(new ShopDetailsBean("任选九", R.mipmap.icon_renxuanjiu));
        cZList.add(new ShopDetailsBean("排列三", R.mipmap.icon_pailiesna));
        cZList.add(new ShopDetailsBean("排列五", R.mipmap.icon_pailiewu));
        cZList.add(new ShopDetailsBean("七星彩", R.mipmap.icon_qixingcai));
        cZList.add(new ShopDetailsBean("大乐透", R.mipmap.icon_daletou));
        shopDetailsAdp.setNewData(cZList);
        shopDetailsAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(fgtContext, ShopListActivity.class);
            }
        });

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @OnClick({R.id.text_winninglist, R.id.image_question, R.id.text_forum, R.id.text_snap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_winninglist:
                //中奖名单
                start(fgtContext, WinningListActivity.class);
                break;
            case R.id.image_question:
                //签到界面
                start(fgtContext, SignInActivity.class);
                break;
            case R.id.text_forum:
                //论坛中心
                start(fgtContext, ForumActivity.class);
                break;
            case R.id.text_snap:
                ToastUtil.show("点赞了");
                break;
            default:
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Map<String, String> map = homeViewBottomAdp.getData().get(position);
        if (map == null) {
            return;
        }
        MapBean mapBean = new MapBean(map);
        bundle.putSerializable("map", mapBean);
        start(fgtContext, bundle, ShopDetailsActivity.class);
    }

    @Override
    public void getCommentsList(Map<String, String> data) {
        //轮播
        ArrayList<Map<String, String>> banner_list = JSONUtils.parseKeyAndValueToMapList(data.get("banner_list"));
        bean(banner_list);
        //中奖名单
        ArrayList<Map<String, String>> rewards_list = JSONUtils.parseKeyAndValueToMapList(data.get("rewards_list"));
        homeNameListAdp.setNewData(rewards_list);
        //店铺列表
        ArrayList<Map<String, String>> store_list = JSONUtils.parseKeyAndValueToMapList(data.get("store_list"));
        homeViewBottomAdp.setNewData(store_list);

        //论坛
        Map<String, String> post = JSONUtils.parseKeyAndValueToMap(data.get("post"));
        GlidUtils.circular(fgtContext, post.get("head_pic"), imageHead);
        text_name.setText(post.get("nick_name"));
        text_time.setText(post.get("create_time"));
        text_snap.setText(post.get("click_number"));
        text_comment.setText(post.get("comment_number"));
        text_post_content.setText(post.get("post_content"));
        String is_click = post.get("is_click");
        Drawable drawable = null;
        if (is_click.equals("false")) {
            drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_home_snap_false);
        } else {
            drawable = fgtContext.getResources().getDrawable(R.mipmap.icon_home_snap_true);
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            text_snap.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 轮播
     */
    private void bean(ArrayList<Map<String, String>> banner_list) {
        List<CustomViewsInfo> data = new ArrayList<>();
        for (int i = 0; i < banner_list.size(); i++) {
            Map<String, String> map = banner_list.get(i);
            if (map == null) {
                return;
            }
            data.add(new CustomViewsInfo(map.get("id"), map.get("file_id"), map.get("type"), map.get("value"), map.get("file_path")));
        }

        banner.setBannerData(R.layout.layout_custom_view, data);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView image_banner = view.findViewById(R.id.image_banner);
                Glide.with(fgtContext).load(((CustomViewsInfo) model).getXBannerUrl()).into(image_banner);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(fgtContext, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setPageTransformer(Transformer.Cube);
    }
}